package com.manticore.tools.xmldoclet;

import com.manticore.tools.xmldoclet.xjc.Class;
import com.manticore.tools.xmldoclet.xjc.Enum;
import com.manticore.tools.xmldoclet.xjc.Package;
import com.manticore.tools.xmldoclet.xjc.*;
import com.sun.source.doctree.DocTree;
import com.sun.source.util.DocTrees;
import jdk.javadoc.doclet.DocletEnvironment;

import javax.lang.model.element.*;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.TypeVariable;
import javax.lang.model.type.WildcardType;
import javax.lang.model.util.ElementFilter;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListMap;

import static com.manticore.tools.xmldoclet.TypeUtils.*;
import static java.util.Objects.requireNonNullElse;

/**
 * The main parser class. It scans the given Doclet document root and creates the XML tree.
 *
 * @author Markus Bernhardt
 * @author Andreas Reichel
 * @author Manoel Campos
 */
public class Parser {
    /**
     * A map where each key is a package name and each value is an object containing a package's JavaDoc.
     */
    protected final Map<String, Package> packages = new ConcurrentSkipListMap<>();

    protected final ObjectFactory objectFactory = new ObjectFactory();

    /**
     * The operating environment of a single invocation of the doclet
     */
    private final DocletEnvironment env;
    private final DocTrees docTrees;
    protected final TypeUtils typeUtils;

    /**
     * @param env the operating environment of a single invocation of the doclet
     */
    public Parser(final DocletEnvironment env) {
        this.env = env;
        this.docTrees = env.getDocTrees();
        this.typeUtils = new TypeUtils(env.getTypeUtils(), env.getElementUtils());
    }

    public Set<TypeElement> getClasses(final DocletEnvironment env) {
        final Set<? extends Element> elements = env.getIncludedElements();
        return ElementFilter.typesIn(elements);
    }

    String getJavaDoc(final Element element) {
        final var docCommentTree = docTrees.getDocCommentTree(element);
        return docCommentTree == null ? "" : docCommentTree.getFullBody().toString();
    }

    /**
     * @return the tags inside a JavaDoc comment
     *
     * @param element the Java element to get its JavaDoc tags
     */
    public List<? extends DocTree> getTags(final Element element) {
        final var docCommentTree = docTrees.getDocCommentTree(element);
        return docCommentTree == null ? List.of() : docCommentTree.getBlockTags();
    }

    /**
     * The entry point into parsing the javadoc.
     *
     * @return The root node, containing everything parsed from javadoc doclet
     */
    public Root parseRootDoc() {
        final Root rootNode = objectFactory.createRoot();

        for (final TypeElement classDoc : getClasses(env)) {
            final Package packageNode = getPackage(rootNode, classDoc);

            switch (classDoc.getKind()) {
                case ANNOTATION_TYPE:
                    packageNode.getAnnotation().add(parseAnnotationTypeDoc(classDoc));
                    break;
                case ENUM:
                    packageNode.getEnum().add(parseEnum(classDoc));
                    break;
                case INTERFACE:
                    packageNode.getInterface().add(parseInterface(classDoc));
                    break;
                default:
                    packageNode.getClazz().add(parseClass(classDoc));
                    break;
            }
        }


        return rootNode;
    }

    /**
     * @return the package node for the given class element
     *
     * @param rootNode
     * @param classElement class to get its package
     */
    Package getPackage(final Root rootNode, final TypeElement classElement) {
        try {
            final var packageDoc = (PackageElement) getTopLevelClass(classElement).getEnclosingElement();

            return packages.computeIfAbsent(packageDoc.getQualifiedName().toString(), pkgName -> {
                final var packageNode = parsePackage(packageDoc);
                packages.put(pkgName, packageNode);
                rootNode.getPackage().add(packageNode);
                return packageNode;
            });
        } catch (Exception e) {
            final var msg = "Error getting the package from element %s. kind %s: nesting kind: %s";
            throw new RuntimeException(
                    msg.formatted(classElement.getQualifiedName(), classElement.getKind(), classElement.getNestingKind()), e);
        }
    }

    /**
     * {@return the top-level class of a given inner class, or the class itself if it's not an inner class}
     *
     * @param classElement a class or inner class
     */
    static TypeElement getTopLevelClass(final TypeElement classElement) {
        return isInnerClass(classElement) ? getTopLevelClass((TypeElement) classElement.getEnclosingElement()) : classElement;
    }

    protected Package parsePackage(final PackageElement packageDoc) {
        final Package packageNode = objectFactory.createPackage();
        packageNode.setName(packageDoc.getQualifiedName().toString());
        final String comment = getJavaDoc(packageDoc);
        if (!comment.isEmpty()) {
            packageNode.setComment(comment);
        }

        for (final DocTree tag : getTags(packageDoc)) {
            packageNode.getTag().add(parseTag(tag));
        }

        return packageNode;
    }

    /**
     * Parse the JavaDoc of an annotation type.
     *
     * @param annotationTypeDoc A AnnotationTypeDoc instance
     * @return the annotation node
     */
    protected Annotation parseAnnotationTypeDoc(final TypeElement annotationTypeDoc) {
        final Annotation annotationNode = objectFactory.createAnnotation();
        annotationNode.setName(annotationTypeDoc.getSimpleName().toString());
        annotationNode.setQualified(getQualifiedName(annotationTypeDoc));
        final String comment = getJavaDoc(annotationTypeDoc);
        if (!comment.isEmpty()) {
            annotationNode.setComment(comment);
        }

        // TODO: What does isIncluded() mean?
        // annotationNode.setIncluded(annotationTypeDoc.isIncluded());

        annotationNode.setScope(parseScope(annotationTypeDoc));

        for (final ExecutableElement annotationTypeElementDoc : getMethods(annotationTypeDoc)) {
            final var annotationElement = parseAnnotationTypeElementDoc(annotationTypeElementDoc);
            annotationNode.getElement().add(annotationElement);
        }

        final var annotationParser = new AnnotationParser(this);
        for (final AnnotationMirror annotationDesc : annotationTypeDoc.getAnnotationMirrors()) {
            final var annotationInstance = annotationParser.parse(annotationTypeDoc.getQualifiedName(), annotationDesc);
            annotationNode.getAnnotation().add(annotationInstance);
        }

        for (final DocTree tag : getTags(annotationTypeDoc)) {
            annotationNode.getTag().add(parseTag(tag));
        }

        return annotationNode;
    }

    /**
     * Parse the elements of an annotation
     *
     * @param annotationTypeElementDoc A AnnotationTypeElementDoc instance
     * @return the annotation element node
     */
    protected AnnotationElement parseAnnotationTypeElementDoc(final ExecutableElement annotationTypeElementDoc) {
        final AnnotationElement annotationElementNode = objectFactory.createAnnotationElement();
        annotationElementNode.setName(annotationTypeElementDoc.getSimpleName().toString());
        annotationElementNode.setQualified(getQualifiedName(annotationTypeElementDoc));
        annotationElementNode.setType(parseTypeInfo(annotationTypeElementDoc.getReturnType()));

        final AnnotationValue value = annotationTypeElementDoc.getDefaultValue();
        if (value != null) {
            annotationElementNode.setDefault(value.toString());
        }

        return annotationElementNode;
    }

    private static String getSimpleName(final VariableElement element) {
        return element.getSimpleName().toString();
    }

    protected Enum parseEnum(final TypeElement classDoc) {
        final Enum enumNode = objectFactory.createEnum();
        enumNode.setName(classDoc.getSimpleName().toString());
        enumNode.setQualified(getQualifiedName(classDoc));
        final String comment = getJavaDoc(classDoc);
        if (!comment.isEmpty()) {
            enumNode.setComment(comment);
        }

        // TODO: What does isIncluded() mean?
        // enumNode.setIncluded(classDoc.isIncluded());

        enumNode.setScope(parseScope(classDoc));

        final TypeMirror superClassType = classDoc.getSuperclass();
        if (superClassType != null) {
            enumNode.setClazz(parseTypeInfo(superClassType));
        }

        for (final TypeMirror interfaceType : classDoc.getInterfaces()) {
            enumNode.getInterface().add(parseTypeInfo(interfaceType));
        }

        for (final VariableElement field : getEnumConstants(classDoc)) {
            enumNode.getConstant().add(parseEnumConstant(field));
        }

        final var annotationParser = new AnnotationParser(this);
        for (final AnnotationMirror annotationDesc : classDoc.getAnnotationMirrors()) {
            enumNode.getAnnotation().add(annotationParser.parse(classDoc.getQualifiedName(), annotationDesc));
        }

        for (final DocTree tag : getTags(classDoc)) {
            enumNode.getTag().add(parseTag(tag));
        }

        return enumNode;
    }

    /**
     * Parses an enum type definition
     *
     * @param fieldDoc
     * @return
     */
    protected EnumConstant parseEnumConstant(final VariableElement fieldDoc) {
        final EnumConstant enumConstant = objectFactory.createEnumConstant();
        enumConstant.setName(getSimpleName(fieldDoc));
        final String comment = getJavaDoc(fieldDoc);
        if (!comment.isEmpty()) {
            enumConstant.setComment(comment);
        }

        final var annotationParser = new AnnotationParser(this);
        for (final AnnotationMirror annotationDesc : fieldDoc.getAnnotationMirrors()) {
            enumConstant.getAnnotation().add(annotationParser.parse(fieldDoc.getSimpleName(), annotationDesc));
        }

        for (final DocTree tag : getTags(fieldDoc)) {
            enumConstant.getTag().add(parseTag(tag));
        }

        return enumConstant;
    }

    protected Interface parseInterface(final TypeElement classDoc) {
        final Interface interfaceNode = objectFactory.createInterface();
        interfaceNode.setName(classDoc.getSimpleName().toString());
        interfaceNode.setQualified(getQualifiedName(classDoc));
        final String comment = getJavaDoc(classDoc);
        if (!comment.isEmpty()) {
            interfaceNode.setComment(comment);
        }

        // TODO: What does isIncluded() mean?
        // interfaceNode.setIncluded(classDoc.isIncluded());

        interfaceNode.setScope(parseScope(classDoc));

        for (final TypeParameterElement typeVariable : classDoc.getTypeParameters()) {
            interfaceNode.getGeneric().add(parseTypeParameter(typeVariable));
        }

        for (final TypeMirror interfaceType : classDoc.getInterfaces()) {
            interfaceNode.getInterface().add(parseTypeInfo(interfaceType));
        }

        for (final ExecutableElement method : getMethods(classDoc)) {
            interfaceNode.getMethod().add(parseMethod(method));
        }

        final var annotationParser = new AnnotationParser(this);
        for (final AnnotationMirror annotationDesc : classDoc.getAnnotationMirrors()) {
            interfaceNode.getAnnotation().add(annotationParser.parse(classDoc.getQualifiedName(), annotationDesc));
        }

        for (final DocTree tag : getTags(classDoc)) {
            interfaceNode.getTag().add(parseTag(tag));
        }

        for (final VariableElement field : getFields(classDoc)) {
            interfaceNode.getField().add(parseField(field));
        }

        return interfaceNode;
    }

    protected Class parseClass(final TypeElement classDoc) {
        final Class classNode = objectFactory.createClass();
        classNode.setName(classDoc.getSimpleName().toString());
        classNode.setQualified(getQualifiedName(classDoc));
        final String comment = getJavaDoc(classDoc);
        if (!comment.isEmpty()) {
            classNode.setComment(comment);
        }
        classNode.setAbstract(hasModifier(classDoc, Modifier.ABSTRACT));
        classNode.setError(typeUtils.isError(classDoc));
        classNode.setException(typeUtils.isException(classDoc));
        classNode.setExternalizable(typeUtils.isExternalizable(classDoc));

        // TODO: What does isIncluded() mean?
        // classNode.setIncluded(classDoc.isIncluded());

        classNode.setSerializable(typeUtils.isSerializable(classDoc));
        classNode.setScope(parseScope(classDoc));

        for (final var typeVariable : classDoc.getTypeParameters()) {
            classNode.getGeneric().add(parseTypeParameter(typeVariable));
        }

        final TypeMirror superClassType = classDoc.getSuperclass();
        if (superClassType != null) {
            classNode.setClazz(parseTypeInfo(superClassType));
        }

        for (final TypeMirror interfaceType : classDoc.getInterfaces()) {
            classNode.getInterface().add(parseTypeInfo(interfaceType));
        }

        for (final ExecutableElement method : getMethods(classDoc)) {
            classNode.getMethod().add(parseMethod(method));
        }

        final var annotationParser = new AnnotationParser(this);
        for (final AnnotationMirror annotationDesc : classDoc.getAnnotationMirrors()) {
            final var annotationInstance = annotationParser.parse(classDoc.getQualifiedName(), annotationDesc);
            classNode.getAnnotation().add(annotationInstance);
        }

        for (final ExecutableElement constructor : getConstructors(classDoc)) {
            classNode.getConstructor().add(parseConstructor(constructor));
        }

        for (final VariableElement field : getFields(classDoc)) {
            classNode.getField().add(parseField(field));
        }

        for (final DocTree tag : getTags(classDoc)) {
            classNode.getTag().add(parseTag(tag));
        }

        return classNode;
    }

    protected Constructor parseConstructor(final ExecutableElement constructorDoc) {
        final Constructor constructorNode = objectFactory.createConstructor();

        constructorNode.setName(constructorDoc.getEnclosingElement().getSimpleName().toString());
        constructorNode.setQualified(constructorDoc.getSimpleName().toString());
        final String comment = getJavaDoc(constructorDoc);
        if (!comment.isEmpty()) {
            constructorNode.setComment(comment);
        }
        constructorNode.setScope(parseScope(constructorDoc));

        // TODO: What does isIncluded() mean?
        // constructorNode.setIncluded(constructorDoc.isIncluded());

        constructorNode.setFinal(hasModifier(constructorDoc, Modifier.FINAL));
        constructorNode.setNative(hasModifier(constructorDoc, Modifier.NATIVE));
        constructorNode.setStatic(hasModifier(constructorDoc, Modifier.STATIC));
        constructorNode.setSynchronized(hasModifier(constructorDoc, Modifier.SYNCHRONIZED));
        constructorNode.setVarArgs(constructorDoc.isVarArgs());
        constructorNode.setSignature(getMethodSignature(constructorDoc));

        for (final VariableElement parameter : constructorDoc.getParameters()) {
            constructorNode.getParameter().add(parseMethodParameter(parameter));
        }

        for (final TypeMirror exceptionType : constructorDoc.getThrownTypes()) {
            constructorNode.getException().add(parseTypeInfo(exceptionType));
        }

        final var annotationParser = new AnnotationParser(this);
        for (final AnnotationMirror annotationDesc : constructorDoc.getAnnotationMirrors()) {
            final var annotationInstance = annotationParser.parse(constructorDoc.getSimpleName(), annotationDesc);
            constructorNode.getAnnotation().add(annotationInstance);
        }

        for (final DocTree tag : getTags(constructorDoc)) {
            constructorNode.getTag().add(parseTag(tag));
        }

        return constructorNode;
    }

    protected Method parseMethod(final ExecutableElement methodDoc) {
        final Method methodNode = objectFactory.createMethod();

        methodNode.setName(methodDoc.getSimpleName().toString());
        methodNode.setQualified(methodDoc.getSimpleName().toString());
        final String comment = getJavaDoc(methodDoc);
        if (!comment.isEmpty()) {
            methodNode.setComment(comment);
        }
        methodNode.setScope(parseScope(methodDoc));
        methodNode.setAbstract(hasModifier(methodDoc, Modifier.ABSTRACT));

        // TODO: What does isIncluded() mean?
        // methodNode.setIncluded(methodDoc.isIncluded());

        methodNode.setFinal(hasModifier(methodDoc, Modifier.FINAL));
        methodNode.setNative(hasModifier(methodDoc, Modifier.NATIVE));
        methodNode.setStatic(hasModifier(methodDoc, Modifier.STATIC));
        methodNode.setSynchronized(hasModifier(methodDoc, Modifier.SYNCHRONIZED));
        methodNode.setVarArgs(methodDoc.isVarArgs());
        methodNode.setSignature(getMethodSignature(methodDoc));
        methodNode.setReturn(parseTypeInfo(methodDoc.getReturnType()));

        for (final VariableElement parameter : methodDoc.getParameters()) {
            methodNode.getParameter().add(parseMethodParameter(parameter));
        }

        for (final TypeMirror exceptionType : methodDoc.getThrownTypes()) {
            methodNode.getException().add(parseTypeInfo(exceptionType));
        }

        final var annotationParser = new AnnotationParser(this);
        for (final AnnotationMirror annotationDesc : methodDoc.getAnnotationMirrors()) {
            final var annotationInstance = annotationParser.parse(methodDoc.getSimpleName(), annotationDesc);
            methodNode.getAnnotation().add(annotationInstance);
        }

        for (final DocTree tag : getTags(methodDoc)) {
            methodNode.getTag().add(parseTag(tag));
        }

        return methodNode;
    }

    protected MethodParameter parseMethodParameter(final VariableElement parameter) {
        final MethodParameter parameterMethodNode = objectFactory.createMethodParameter();
        parameterMethodNode.setName(getSimpleName(parameter));
        parameterMethodNode.setType(parseTypeInfo(parameter.asType()));

        final var annotationParser = new AnnotationParser(this);
        for (final AnnotationMirror annotationDesc : parameter.getAnnotationMirrors()) {
            final var annotationInstance = annotationParser.parse(parameter.getSimpleName(), annotationDesc);
            parameterMethodNode.getAnnotation().add(annotationInstance);
        }

        return parameterMethodNode;
    }

    protected Field parseField(final VariableElement fieldDoc) {
        final Field fieldNode = objectFactory.createField();
        fieldNode.setType(parseTypeInfo(fieldDoc.asType()));
        fieldNode.setName(getSimpleName(fieldDoc));
        fieldNode.setQualified(getSimpleName(fieldDoc));
        String comment = getJavaDoc(fieldDoc);
        if (!comment.isEmpty()) {
            fieldNode.setComment(comment);
        }
        fieldNode.setScope(parseScope(fieldDoc));
        fieldNode.setFinal(hasModifier(fieldDoc, Modifier.FINAL));
        fieldNode.setStatic(hasModifier(fieldDoc, Modifier.STATIC));
        fieldNode.setVolatile(hasModifier(fieldDoc, Modifier.VOLATILE));
        fieldNode.setTransient(hasModifier(fieldDoc, Modifier.TRANSIENT));
        fieldNode.setConstant(requireNonNullElse(fieldDoc.getConstantValue(), "").toString());

        final var annotationParser = new AnnotationParser(this);
        for (final AnnotationMirror annotationDesc : fieldDoc.getAnnotationMirrors()) {
            fieldNode.getAnnotation().add(annotationParser.parse(fieldDoc.getSimpleName(), annotationDesc));
        }

        for (final DocTree tag : getTags(fieldDoc)) {
            fieldNode.getTag().add(parseTag(tag));
        }

        return fieldNode;
    }

    protected Wildcard parseWildcard(final WildcardType wildcard) {
        final Wildcard wildcardNode = objectFactory.createWildcard();

        addIfNotNull(wildcardNode.getExtendsBound(), wildcard.getExtendsBound());
        addIfNotNull(wildcardNode.getSuperBound(), wildcard.getSuperBound());

        return wildcardNode;
    }

    private void addIfNotNull(final List<TypeInfo> wildcardNode, final TypeMirror extendType) {
        if (extendType != null)
            wildcardNode.add(parseTypeInfo(extendType));
    }

    protected TypeParameter parseTypeParameter(final TypeParameterElement typeParameter) {
        return parseTypeParameter((TypeVariable) typeParameter.asType());
    }

    /**
     * Parse type variables for generics
     *
     * @param typeVariable
     * @return
     */
    protected TypeParameter parseTypeParameter(final TypeVariable typeVariable) {
        final TypeParameter typeParameter = objectFactory.createTypeParameter();
        typeParameter.setName(typeVariable.toString());

        final List<String> bounds = typeParameter.getBound();
        final var lowerBound = typeVariable.getLowerBound();

        // If the lower bound is equal to the null type, it means that there is no actual lower bound
        if (!"<nulltype>".equals(lowerBound.toString()))
            bounds.addAll(parseTypeParameterBound(lowerBound));

        final var upperBound = typeVariable.getUpperBound();
        // If upper bound is Object, it means that there is no actual upper bound (since Object means "anything")
        if (!"java.lang.Object".equals(upperBound.toString()))
            bounds.addAll(parseTypeParameterBound(upperBound));

        return typeParameter;
    }

    /**
     * Gets a type parameter bound for a generic type (such as <T extends Number> or <T extends Comparable<E> & Serializable>)
     * and splits the name of each type into a list of strings
     *
     * @param bound the type parameter bound
     * @return a list of strings representing each type parameter bound
     */
    private List<String> parseTypeParameterBound(final TypeMirror bound) {
        final String typesSeparator = "&";
        final var boundName = bound.toString();
        return boundName.contains(typesSeparator) ? List.of(boundName.split(typesSeparator)) : List.of(boundName);
    }

    protected TagInfo parseTag(final DocTree tagDoc) {
        final TagInfo tagNode = objectFactory.createTagInfo();
        tagNode.setName(tagDoc.getKind().tagName);
        tagNode.setText(tagDoc.toString());
        return tagNode;
    }

    /**
     * @return string representation of the element scope
     *
     * @param doc the element to get its scope
     */
    protected String parseScope(final Element doc) {
        if (hasModifier(doc, Modifier.PRIVATE)) {
            return "private";
        } else if (hasModifier(doc, Modifier.PROTECTED)) {
            return "protected";
        } else if (hasModifier(doc, Modifier.PUBLIC)) {
            return "public";
        }

        return "";
    }

    /**
     * Parses a {@link TypeMirror} into a {@link TypeInfo} object used by the XmlDoclet.
     *
     * @param type the {@link TypeMirror} to parse.
     * @return the created {@link TypeInfo} object
     */
    protected TypeInfo parseTypeInfo(final TypeMirror type) {
        final TypeInfo typeInfoNode = objectFactory.createTypeInfo();
        typeInfoNode.setQualified(getQualifiedName(type));
        final String dimension = getArrayDimension(type);
        if (!dimension.isEmpty()) {
            typeInfoNode.setDimension(dimension);
        }

        final WildcardType wildcard = getWildcardType(type);
        if (wildcard != null) {
            typeInfoNode.setWildcard(parseWildcard(wildcard));
        }

        final DeclaredType parameterized = getParameterizedType(type);

        if (parameterized != null) {
            for (final TypeMirror typeArgument : parameterized.getTypeArguments()) {
                typeInfoNode.getGeneric().add(parseTypeInfo(typeArgument));
            }
        }

        return typeInfoNode;
    }
}
