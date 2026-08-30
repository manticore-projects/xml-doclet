package com.manticore.tools.xmldoclet;

import com.manticore.tools.xmldoclet.simpledata.Annotation3;
import com.manticore.tools.xmldoclet.simpledata.AnnotationCascadeChild;
import com.manticore.tools.xmldoclet.simpledata.Class1;
import com.manticore.tools.xmldoclet.simpledata.Class2;
import com.manticore.tools.xmldoclet.simpledata.Class3;
import com.manticore.tools.xmldoclet.simpledata.Class4;
import com.manticore.tools.xmldoclet.simpledata.Class5;
import com.manticore.tools.xmldoclet.simpledata.Class6;
import com.manticore.tools.xmldoclet.simpledata.Class7;
import com.manticore.tools.xmldoclet.simpledata.Class8;
import com.manticore.tools.xmldoclet.simpledata.Class9;
import com.manticore.tools.xmldoclet.simpledata.ClassAnnotationCascade;
import com.manticore.tools.xmldoclet.xjc.AnnotationArgument;
import com.manticore.tools.xmldoclet.xjc.AnnotationInstance;
import com.manticore.tools.xmldoclet.xjc.TypeInfo;
import com.manticore.tools.xmldoclet.xjc.TypeParameter;
import org.junit.jupiter.api.Test;

import java.io.Serializable;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test group for Classes
 */
@SuppressWarnings("deprecation")
class ClassTest extends AbstractTest {

    /**
     * Testing nested Annotations
     *
     * @see com.manticore.tools.xmldoclet.simpledata.ClassAnnotationCascade
     */
    @Test
    void testClassAnnotationCascade() {
        final var javaDocElements = newJavaDocElements("ClassAnnotationCascade.java");
        final var rootNode = javaDocElements.rootNode();
        final var packageNode = javaDocElements.packageNode();
        final var classNode = javaDocElements.classNode();

        assertEquals(1, rootNode.getPackage().size());
        assertNull(packageNode.getComment());
        assertEquals(SIMPLE_DATA_PACKAGE, packageNode.getName());
        assertEquals(1, packageNode.getClazz().size());
        assertTrue(packageNode.getEnum().isEmpty());
        assertTrue(packageNode.getInterface().isEmpty());

        assertEquals("ClassAnnotationCascade", classNode.getComment());
        assertEquals("ClassAnnotationCascade", classNode.getName());

        assertEquals(ClassAnnotationCascade.class.getName(), classNode.getQualified());

        assertEquals(1, classNode.getAnnotation().size());
        final AnnotationInstance annotationNode = classNode.getAnnotation().get(0);

        assertEquals("AnnotationCascade", annotationNode.getName());
        assertEquals(1, annotationNode.getArgument().size());

        final AnnotationArgument annotationArgNode = annotationNode.getArgument().get(0);

        // Two nested annotations in child attribute
        assertEquals("children", annotationArgNode.getName());
        assertTrue(annotationArgNode.getValue().isEmpty());
        assertEquals(2, annotationArgNode.getAnnotation().size());

        final AnnotationInstance annonNodePrimitive = annotationArgNode.getAnnotation().get(0);
        final AnnotationInstance annonNodeNested = annotationArgNode.getAnnotation().get(1);

        // Equal attribs
        assertEquals(AnnotationCascadeChild.class.getSimpleName(), annonNodePrimitive.getName());
        assertEquals(AnnotationCascadeChild.class.getSimpleName(), annonNodeNested.getName());
        assertEquals(AnnotationCascadeChild.class.getName(), annonNodePrimitive.getQualified());
        assertEquals(AnnotationCascadeChild.class.getName(), annonNodeNested.getQualified());
        assertEquals(2, annonNodePrimitive.getArgument().size());
        assertEquals(2, annonNodeNested.getArgument().size());
        assertEquals("name", annonNodePrimitive.getArgument().get(0).getName());
        assertEquals("name", annonNodeNested.getArgument().get(0).getName());

        // Primitive
        final AnnotationArgument annArgNodePrimitive = annonNodePrimitive.getArgument().get(1);
        assertEquals("dummyData", annArgNodePrimitive.getName());
        assertEquals("java.lang.String[]", annArgNodePrimitive.getType().getQualified());
        assertEquals(0, annArgNodePrimitive.getAnnotation().size());
        assertEquals(3, annArgNodePrimitive.getValue().size());
        assertEquals("A", annArgNodePrimitive.getValue().get(0));
        assertEquals("B", annArgNodePrimitive.getValue().get(1));
        assertEquals("C", annArgNodePrimitive.getValue().get(2));

        // Nested
        final AnnotationArgument annArgNodeNested = annonNodeNested.getArgument().get(1);
        assertEquals("subAnnotations", annArgNodeNested.getName());
        assertEquals(Annotation3.class.getName() + "[]", annArgNodeNested.getType().getQualified());
        assertEquals(3, annArgNodeNested.getAnnotation().size());
        assertEquals(0, annArgNodeNested.getValue().size());
        assertEquals(Annotation3.class.getSimpleName(), annArgNodeNested.getAnnotation().get(0).getName());
        assertEquals(Annotation3.class.getName(), annArgNodeNested.getAnnotation().get(1).getQualified());
        assertEquals(1, annArgNodeNested.getAnnotation().get(2).getArgument().size());

        assertEquals("666", annArgNodeNested.getAnnotation().get(2).getArgument().get(0).getValue().get(0));
    }

    /**
     * testing a class with nothing defined EMPIRICAL OBSERVATION: The default constructor created
     * by the java compiler is not marked synthetic. um what?
     */
    @Test
    void testClass1() {
        final var javaDocElements = newJavaDocElements("Class1.java");
        final var rootNode = javaDocElements.rootNode();
        final var packageNode = javaDocElements.packageNode();
        final var classNode = javaDocElements.classNode();

        assertEquals(1, rootNode.getPackage().size());
        assertNull(packageNode.getComment());
        assertEquals(SIMPLE_DATA_PACKAGE, packageNode.getName());
        assertTrue(packageNode.getAnnotation().isEmpty());
        assertTrue(packageNode.getEnum().isEmpty());
        assertTrue(packageNode.getInterface().isEmpty());
        assertEquals(1, packageNode.getClazz().size());

        assertEquals("Class1", classNode.getComment());
        assertEquals(Class1.class.getSimpleName(), classNode.getName());
        assertEquals(Class1.class.getName(), classNode.getQualified());
        assertEquals("public", classNode.getScope());
        assertEquals(1, classNode.getConstructor().size());
        assertTrue(classNode.getField().isEmpty());
        assertTrue(classNode.getMethod().isEmpty());
        assertTrue(classNode.getAnnotation().isEmpty());
        assertTrue(classNode.getInterface().isEmpty());
        assertEquals(Object.class.getName(), classNode.getClazz().getQualified());
        assertFalse(classNode.isAbstract());
        assertFalse(classNode.isExternalizable());
        assertTrue(classNode.isIncluded());
        assertFalse(classNode.isSerializable());
        assertFalse(classNode.isException());
        assertFalse(classNode.isError());
        assertTrue(classNode.getGeneric().isEmpty());
    }

    /**
     * testing a class with 1 constructor
     */
    @Test
    void testClass2() {
        final var javaDocElements = newJavaDocElements("Class2.java");
        final var rootNode = javaDocElements.rootNode();
        final var packageNode = javaDocElements.packageNode();
        final var classNode = javaDocElements.classNode();
        final var constructor = classNode.getConstructor().get(0);

        assertEquals(1, rootNode.getPackage().size());
        assertNull(packageNode.getComment());
        assertEquals(SIMPLE_DATA_PACKAGE, packageNode.getName());
        assertTrue(packageNode.getAnnotation().isEmpty());
        assertTrue(packageNode.getEnum().isEmpty());
        assertTrue(packageNode.getInterface().isEmpty());
        assertEquals(1, packageNode.getClazz().size());

        assertEquals("Class2", classNode.getComment());
        assertEquals(1, classNode.getConstructor().size());
        assertEquals(classNode.getName(), Class2.class.getSimpleName());
        assertEquals(classNode.getQualified(), Class2.class.getName());
        assertEquals("public", classNode.getScope());
        assertTrue(classNode.getField().isEmpty());
        assertTrue(classNode.getMethod().isEmpty());
        assertTrue(classNode.getAnnotation().isEmpty());
        assertTrue(classNode.getInterface().isEmpty());
        assertEquals(Object.class.getName(), classNode.getClazz().getQualified());
        assertFalse(classNode.isAbstract());
        assertFalse(classNode.isExternalizable());
        assertTrue(classNode.isIncluded());
        assertFalse(classNode.isSerializable());
        assertFalse(classNode.isException());
        assertFalse(classNode.isError());
        assertTrue(classNode.getGeneric().isEmpty());

        assertEquals("Constructor1", constructor.getComment());
        assertEquals("Class2", constructor.getName());
        assertTrue(constructor.getParameter().isEmpty());
        assertTrue(constructor.getAnnotation().isEmpty());
    }

    /**
     * testing a class with 1 method
     */
    @Test
    void testClass3() {
        final var javaDocElements = newJavaDocElements("Class3.java");
        final var rootNode = javaDocElements.rootNode();
        final var packageNode = javaDocElements.packageNode();
        final var classNode = javaDocElements.classNode();
        final var method = classNode.getMethod().get(0);

        assertEquals(1, rootNode.getPackage().size());
        assertNull(packageNode.getComment());
        assertEquals(SIMPLE_DATA_PACKAGE, packageNode.getName());
        assertTrue(packageNode.getAnnotation().isEmpty());
        assertTrue(packageNode.getEnum().isEmpty());
        assertTrue(packageNode.getInterface().isEmpty());
        assertEquals(1, packageNode.getClazz().size());

        assertEquals("Class3", classNode.getComment());
        assertEquals(1, classNode.getConstructor().size());
        assertEquals(classNode.getName(), Class3.class.getSimpleName());
        assertEquals(classNode.getQualified(), Class3.class.getName());
        assertEquals("public", classNode.getScope());
        assertEquals(1, classNode.getMethod().size());
        assertTrue(classNode.getField().isEmpty());
        assertTrue(classNode.getAnnotation().isEmpty());
        assertTrue(classNode.getInterface().isEmpty());
        assertEquals(classNode.getClazz().getQualified(), Object.class.getName());
        assertFalse(classNode.isAbstract());
        assertFalse(classNode.isExternalizable());
        assertTrue(classNode.isIncluded());
        assertFalse(classNode.isSerializable());
        assertFalse(classNode.isException());
        assertFalse(classNode.isError());
        assertTrue(classNode.getGeneric().isEmpty());

        assertEquals("method1", method.getComment());
        assertEquals("method1", method.getName());
        assertEquals("int ()", method.getSignature());
        assertFalse(method.isFinal());
        assertFalse(method.isNative());
        assertFalse(method.isStatic());
        assertFalse(method.isSynchronized());
        assertFalse(method.isVarArgs());
        assertEquals("method1", method.getQualified());
        assertEquals("public", method.getScope());
        assertTrue(method.getAnnotation().isEmpty());
        assertTrue(method.getParameter().isEmpty());
        assertTrue(method.getException().isEmpty());

        final TypeInfo returnNode = method.getReturn();
        assertEquals("int", returnNode.getQualified());
        assertNull(returnNode.getDimension());
        assertTrue(returnNode.getGeneric().isEmpty());
        assertNull(returnNode.getWildcard());
    }

    /**
     * testing a class with 1 field
     */
    @Test
    void testClass4() {
        final var javaDocElements = newJavaDocElements("Class4.java");
        final var rootNode = javaDocElements.rootNode();
        final var packageNode = javaDocElements.packageNode();
        final var classNode = javaDocElements.classNode();
        final var field = classNode.getField().get(0);

        assertEquals(1, rootNode.getPackage().size());
        assertNull(packageNode.getComment());
        assertEquals(SIMPLE_DATA_PACKAGE, packageNode.getName());
        assertTrue(packageNode.getAnnotation().isEmpty());
        assertTrue(packageNode.getEnum().isEmpty());
        assertTrue(packageNode.getInterface().isEmpty());
        assertEquals(1, packageNode.getClazz().size());

        assertEquals("Class4", classNode.getComment());
        assertEquals(1, classNode.getConstructor().size());
        assertEquals(classNode.getName(), Class4.class.getSimpleName());
        assertEquals(classNode.getQualified(), Class4.class.getName());
        assertEquals("public", classNode.getScope());
        assertEquals(1, classNode.getField().size());
        assertTrue(classNode.getMethod().isEmpty());
        assertTrue(classNode.getAnnotation().isEmpty());
        assertTrue(classNode.getInterface().isEmpty());
        assertEquals(classNode.getClazz().getQualified(), Object.class.getName());
        assertFalse(classNode.isAbstract());
        assertFalse(classNode.isExternalizable());
        assertTrue(classNode.isIncluded());
        assertFalse(classNode.isSerializable());
        assertFalse(classNode.isException());
        assertFalse(classNode.isError());
        assertTrue(classNode.getGeneric().isEmpty());

        // test field
        assertEquals("field1", field.getComment());
        assertEquals("field1", field.getName());
        assertEquals("public", field.getScope());
        assertEquals("int", field.getType().getQualified());
        assertNull(field.getType().getDimension());
        assertTrue(field.getType().getGeneric().isEmpty());
        assertNull(field.getType().getWildcard());
        assertFalse(field.isStatic());
        assertFalse(field.isTransient());
        assertFalse(field.isVolatile());
        assertFalse(field.isFinal());
        assertEquals("", field.getConstant());
        assertTrue(field.getAnnotation().isEmpty());
    }

    /**
     * testing a class that extends another class with 1 method
     */
    @Test
    void testClass5() {
        final var javaDocElements = newJavaDocElements("Class5.java", "Class3.java");
        final var rootNode = javaDocElements.rootNode();
        final var packageNode = javaDocElements.packageNode();
        final var classNode = javaDocElements.classNode();

        assertEquals(1, rootNode.getPackage().size());
        assertNull(packageNode.getComment());
        assertEquals(SIMPLE_DATA_PACKAGE, packageNode.getName());
        assertTrue(packageNode.getAnnotation().isEmpty());
        assertTrue(packageNode.getEnum().isEmpty());
        assertTrue(packageNode.getInterface().isEmpty());
        assertEquals(2, packageNode.getClazz().size());

        assertEquals("Class5", classNode.getComment());
        assertEquals(1, classNode.getConstructor().size());
        assertEquals(classNode.getName(), Class5.class.getSimpleName());
        assertEquals(classNode.getQualified(), Class5.class.getName());
        assertEquals("public", classNode.getScope());
        assertTrue(classNode.getMethod().isEmpty());
        assertTrue(classNode.getField().isEmpty());
        assertTrue(classNode.getAnnotation().isEmpty());
        assertTrue(classNode.getInterface().isEmpty());
        assertEquals(getElementPathFromSimpleDataPackage("Class3"), classNode.getClazz().getQualified());
        assertFalse(classNode.isAbstract());
        assertFalse(classNode.isExternalizable());
        assertTrue(classNode.isIncluded());
        assertFalse(classNode.isSerializable());
        assertFalse(classNode.isException());
        assertFalse(classNode.isError());
        assertTrue(classNode.getGeneric().isEmpty());
    }

    /**
     * testing a class that implements one interface
     */
    @Test
    void testClass6() {
        final var javaDocElements = newJavaDocElements("Class6.java");
        final var rootNode = javaDocElements.rootNode();
        final var packageNode = javaDocElements.packageNode();
        final var classNode = javaDocElements.classNode();
        final TypeInfo interfaceNode = classNode.getInterface().get(0);

        assertEquals(1, rootNode.getPackage().size());
        assertNull(packageNode.getComment());
        assertEquals(SIMPLE_DATA_PACKAGE, packageNode.getName());
        assertTrue(packageNode.getAnnotation().isEmpty());
        assertTrue(packageNode.getEnum().isEmpty());
        assertTrue(packageNode.getInterface().isEmpty());
        assertEquals(1, packageNode.getClazz().size());

        assertEquals("Class6", classNode.getComment());
        assertEquals(1, classNode.getConstructor().size());
        assertEquals(Class6.class.getSimpleName(), classNode.getName());
        assertEquals(Class6.class.getName(), classNode.getQualified());
        assertEquals("public", classNode.getScope());
        assertTrue(classNode.getMethod().isEmpty());
        assertTrue(classNode.getField().isEmpty());
        assertEquals(1, classNode.getAnnotation().size());
        assertEquals(1, classNode.getInterface().size());
        assertEquals(Object.class.getName(), classNode.getClazz().getQualified());
        assertFalse(classNode.isAbstract());
        assertFalse(classNode.isExternalizable());
        assertTrue(classNode.isIncluded());
        assertFalse(classNode.isException());
        assertFalse(classNode.isError());
        assertTrue(classNode.getGeneric().isEmpty());

        // the particular interface chosen for this test also will change this flag to true!
        assertTrue(classNode.isSerializable());

        // verify interface
        assertEquals(interfaceNode.getQualified(), Serializable.class.getName());
    }

    /**
     * testing one annotation instance on the class
     */
    @Test
    void testClass7() {
        final var javaDocElements = newJavaDocElements("Class7.java");
        final var rootNode = javaDocElements.rootNode();
        final var packageNode = javaDocElements.packageNode();
        final var classNode = javaDocElements.classNode();

        final AnnotationInstance annotationNode = classNode.getAnnotation().get(0);

        assertEquals(1, rootNode.getPackage().size());
        assertNull(packageNode.getComment());
        assertEquals(SIMPLE_DATA_PACKAGE, packageNode.getName());
        assertTrue(packageNode.getAnnotation().isEmpty());
        assertTrue(packageNode.getEnum().isEmpty());
        assertTrue(packageNode.getInterface().isEmpty());
        assertEquals(1, packageNode.getClazz().size());

        assertEquals("Class7", classNode.getComment());
        assertEquals(1, classNode.getConstructor().size());
        assertEquals(Class7.class.getSimpleName(), classNode.getName());
        assertEquals(Class7.class.getName(), classNode.getQualified());
        assertEquals("public", classNode.getScope());
        assertTrue(classNode.getMethod().isEmpty());
        assertTrue(classNode.getField().isEmpty());
        assertEquals(1, classNode.getAnnotation().size());
        assertTrue(classNode.getInterface().isEmpty());
        assertEquals(Object.class.getName(), classNode.getClazz().getQualified());
        assertFalse(classNode.isAbstract());
        assertFalse(classNode.isExternalizable());
        assertTrue(classNode.isIncluded());
        assertFalse(classNode.isSerializable());
        assertFalse(classNode.isException());
        assertFalse(classNode.isError());
        assertTrue(classNode.getGeneric().isEmpty());

        // test annotation 'deprecated' on class
        assertEquals("java.lang.Deprecated", annotationNode.getQualified());
        assertEquals("Deprecated", annotationNode.getName());
        assertTrue(annotationNode.getArgument().isEmpty());
    }

    /**
     * testing abstract keyword on class
     */
    @Test
    void testClass8() {
        final var javaDocElements = newJavaDocElements("Class8.java");
        final var rootNode = javaDocElements.rootNode();
        final var packageNode = javaDocElements.packageNode();
        final var classNode = javaDocElements.classNode();


        assertEquals(1, rootNode.getPackage().size());
        assertNull(packageNode.getComment());
        assertEquals(SIMPLE_DATA_PACKAGE, packageNode.getName());
        assertTrue(packageNode.getAnnotation().isEmpty());
        assertTrue(packageNode.getEnum().isEmpty());
        assertTrue(packageNode.getInterface().isEmpty());
        assertEquals(1, packageNode.getClazz().size());

        assertEquals("Class8", classNode.getComment());
        assertEquals(1, classNode.getConstructor().size());
        assertEquals(Class8.class.getSimpleName(), classNode.getName());
        assertEquals(Class8.class.getName(), classNode.getQualified());
        assertEquals("public", classNode.getScope());
        assertTrue(classNode.getMethod().isEmpty());
        assertTrue(classNode.getField().isEmpty());
        assertTrue(classNode.getAnnotation().isEmpty());
        assertTrue(classNode.getInterface().isEmpty());
        assertEquals(Object.class.getName(), classNode.getClazz().getQualified());
        assertTrue(classNode.isAbstract());
        assertFalse(classNode.isExternalizable());
        assertTrue(classNode.isIncluded());
        assertFalse(classNode.isSerializable());
        assertFalse(classNode.isException());
        assertFalse(classNode.isError());
        assertTrue(classNode.getGeneric().isEmpty());
    }

    /**
     * testing java.io.Externalizable interface on class
     */
    @Test
    void testClass9() {
        final var javaDocElements = newJavaDocElements("Class9.java");
        final var rootNode = javaDocElements.rootNode();
        final var packageNode = javaDocElements.packageNode();
        final var classNode = javaDocElements.classNode();

        assertEquals(1, rootNode.getPackage().size());
        assertNull(packageNode.getComment());
        assertEquals(SIMPLE_DATA_PACKAGE, packageNode.getName());
        assertTrue(packageNode.getAnnotation().isEmpty());
        assertTrue(packageNode.getEnum().isEmpty());
        assertTrue(packageNode.getInterface().isEmpty());
        assertEquals(1, packageNode.getClazz().size());

        assertEquals("Class9", classNode.getComment());
        assertEquals(1, classNode.getConstructor().size());
        assertEquals(Class9.class.getSimpleName(), classNode.getName());
        assertEquals(Class9.class.getName(), classNode.getQualified());
        assertEquals("public", classNode.getScope());
        assertEquals(2, classNode.getMethod().size());
        assertTrue(classNode.getField().isEmpty());
        assertTrue(classNode.getAnnotation().isEmpty());
        assertEquals(1, classNode.getInterface().size());
        assertEquals(Object.class.getName(), classNode.getClazz().getQualified());
        assertFalse(classNode.isAbstract());
        assertTrue(classNode.isExternalizable());
        assertTrue(classNode.isIncluded());
        assertTrue(classNode.isSerializable());
        assertFalse(classNode.isException());
        assertFalse(classNode.isError());
        assertTrue(classNode.getGeneric().isEmpty());
    }

    /**
     * testing difference of scope modifier on class
     */
    @Test
    void testClass10() {
        final var javaDocElements = newJavaDocElements("Class10.java");
        final var rootNode = javaDocElements.rootNode();
        final var packageNode = javaDocElements.packageNode();
        final var classNode = javaDocElements.classNode();

        assertEquals(1, rootNode.getPackage().size());
        assertNull(packageNode.getComment());
        assertEquals(SIMPLE_DATA_PACKAGE, packageNode.getName());
        assertTrue(packageNode.getAnnotation().isEmpty());
        assertTrue(packageNode.getEnum().isEmpty());
        assertTrue(packageNode.getInterface().isEmpty());
        assertEquals(1, packageNode.getClazz().size());

        assertEquals("Class10", classNode.getComment());
        assertEquals(1, classNode.getConstructor().size());
        assertEquals("Class10", classNode.getName());
        assertEquals(getElementPathFromSimpleDataPackage("Class10"), classNode.getQualified());
        assertEquals("", classNode.getScope());
        assertTrue(classNode.getMethod().isEmpty());
        assertTrue(classNode.getField().isEmpty());
        assertTrue(classNode.getAnnotation().isEmpty());
        assertTrue(classNode.getInterface().isEmpty());
        assertEquals(Object.class.getName(), classNode.getClazz().getQualified());
        assertFalse(classNode.isAbstract());
        assertFalse(classNode.isExternalizable());
        assertTrue(classNode.isIncluded());
        assertFalse(classNode.isSerializable());
        assertFalse(classNode.isException());
        assertFalse(classNode.isError());
        assertTrue(classNode.getGeneric().isEmpty());
    }

    /**
     * testing if isException is populated correctly
     */
    @Test
    void testClass11() {
        final var javaDocElements = newJavaDocElements("Class11.java");
        final var rootNode = javaDocElements.rootNode();
        final var packageNode = javaDocElements.packageNode();
        final var classNode = javaDocElements.classNode();

        assertEquals(1, rootNode.getPackage().size());
        assertNull(packageNode.getComment());
        assertEquals(SIMPLE_DATA_PACKAGE, packageNode.getName());
        assertTrue(packageNode.getAnnotation().isEmpty());
        assertTrue(packageNode.getEnum().isEmpty());
        assertTrue(packageNode.getInterface().isEmpty());
        assertEquals(1, packageNode.getClazz().size());

        assertEquals("Class11", classNode.getComment());
        assertEquals(1, classNode.getConstructor().size());
        assertEquals("Class11", classNode.getName());
        assertEquals(getElementPathFromSimpleDataPackage("Class11"), classNode.getQualified());
        assertEquals(classNode.getScope(), "public");
        assertTrue(classNode.getMethod().isEmpty());
        assertTrue(classNode.getField().isEmpty());
        assertEquals(1, classNode.getAnnotation().size());
        assertTrue(classNode.getInterface().isEmpty());
        assertEquals(classNode.getClazz().getQualified(), Exception.class.getName());
        assertFalse(classNode.isAbstract());
        assertFalse(classNode.isExternalizable());
        assertTrue(classNode.isIncluded());
        assertTrue(classNode.isSerializable());
        assertTrue(classNode.isException());
        assertFalse(classNode.isError());
        assertTrue(classNode.getGeneric().isEmpty());
    }

    /**
     * testing if isError is populated correctly
     */
    @Test
    void testClass12() {
        final var javaDocElements = newJavaDocElements("Class12.java");
        final var rootNode = javaDocElements.rootNode();
        final var packageNode = javaDocElements.packageNode();
        final var classNode = javaDocElements.classNode();


        assertEquals(1, rootNode.getPackage().size());
        assertNull(packageNode.getComment());
        assertEquals(SIMPLE_DATA_PACKAGE, packageNode.getName());
        assertTrue(packageNode.getAnnotation().isEmpty());
        assertTrue(packageNode.getEnum().isEmpty());
        assertTrue(packageNode.getInterface().isEmpty());
        assertEquals(1, packageNode.getClazz().size());

        assertEquals("Class12", classNode.getComment());
        assertEquals(1, classNode.getConstructor().size());
        assertEquals("Class12", classNode.getName());
        assertEquals(getElementPathFromSimpleDataPackage("Class12"), classNode.getQualified());
        assertEquals("public", classNode.getScope());
        assertTrue(classNode.getMethod().isEmpty());
        assertTrue(classNode.getField().isEmpty());
        assertEquals(1, classNode.getAnnotation().size());
        assertTrue(classNode.getInterface().isEmpty());
        assertEquals(classNode.getClazz().getQualified(), Error.class.getName());
        assertFalse(classNode.isAbstract());
        assertFalse(classNode.isExternalizable());
        assertTrue(classNode.isIncluded());
        assertTrue(classNode.isSerializable());
        assertFalse(classNode.isException());
        assertTrue(classNode.isError());
        assertTrue(classNode.getGeneric().isEmpty());
    }

    /**
     * testing if type variables can be determined
     */
    @Test
    void testClass13() {
        final var javaDocElements = newJavaDocElements("Class13.java");
        final var rootNode = javaDocElements.rootNode();
        final var packageNode = javaDocElements.packageNode();
        final var classNode = javaDocElements.classNode();

        final TypeParameter typeParameter = classNode.getGeneric().get(0);

        assertEquals(1, rootNode.getPackage().size());
        assertNull(packageNode.getComment());
        assertEquals(SIMPLE_DATA_PACKAGE, packageNode.getName());
        assertTrue(packageNode.getAnnotation().isEmpty());
        assertTrue(packageNode.getEnum().isEmpty());
        assertTrue(packageNode.getInterface().isEmpty());
        assertEquals(1, packageNode.getClazz().size());

        assertEquals("Class13", classNode.getComment());
        assertEquals(1, classNode.getConstructor().size());
        assertEquals("Class13", classNode.getName());
        assertEquals(getElementPathFromSimpleDataPackage("Class13<Fun>"), classNode.getQualified());
        assertEquals("public", classNode.getScope());
        assertEquals(1, classNode.getGeneric().size());
        assertTrue(classNode.getMethod().isEmpty());
        assertTrue(classNode.getField().isEmpty());
        assertTrue(classNode.getAnnotation().isEmpty());
        assertTrue(classNode.getInterface().isEmpty());
        assertEquals(classNode.getClazz().getQualified(), Object.class.getName());
        assertFalse(classNode.isAbstract());
        assertFalse(classNode.isExternalizable());
        assertTrue(classNode.isIncluded());
        assertFalse(classNode.isSerializable());
        assertFalse(classNode.isException());
        assertFalse(classNode.isError());

        // check the 'fun' type var
        assertEquals("Fun", typeParameter.getName());
        assertTrue(typeParameter.getBound().isEmpty());
    }

    /**
     * testing if a single bounds can be determined
     */
    @Test
    void testClass14() {
        final var javaDocElements = newJavaDocElements("Class14.java");
        final var rootNode = javaDocElements.rootNode();
        final var packageNode = javaDocElements.packageNode();
        final var classNode = javaDocElements.classNode();
        final TypeParameter typeParameter = classNode.getGeneric().get(0);

        assertEquals(1, rootNode.getPackage().size());
        assertNull(packageNode.getComment());
        assertEquals(SIMPLE_DATA_PACKAGE, packageNode.getName());
        assertTrue(packageNode.getAnnotation().isEmpty());
        assertTrue(packageNode.getEnum().isEmpty());
        assertTrue(packageNode.getInterface().isEmpty());
        assertEquals(1, packageNode.getClazz().size());

        assertEquals("Class14", classNode.getComment());
        assertEquals(1, classNode.getConstructor().size());
        assertEquals("Class14", classNode.getName());
        assertEquals(getElementPathFromSimpleDataPackage("Class14<Fun>"), classNode.getQualified());
        assertEquals("public", classNode.getScope());
        assertEquals(1, classNode.getGeneric().size());
        assertTrue(classNode.getMethod().isEmpty());
        assertTrue(classNode.getField().isEmpty());
        assertTrue(classNode.getAnnotation().isEmpty());
        assertTrue(classNode.getInterface().isEmpty());
        assertEquals(classNode.getClazz().getQualified(), Object.class.getName());
        assertFalse(classNode.isAbstract());
        assertFalse(classNode.isExternalizable());
        assertTrue(classNode.isIncluded());
        assertFalse(classNode.isSerializable());
        assertFalse(classNode.isException());
        assertFalse(classNode.isError());

        // check the 'fun' type var
        assertEquals("Fun", typeParameter.getName());
        assertEquals(1, typeParameter.getBound().size());
        assertEquals(Number.class.getName(), typeParameter.getBound().get(0));
    }

    /**
     * testing if a multiple bounds can be determined
     */
    @Test
    void testClass15() {
        final var javaDocElements = newJavaDocElements("Class15.java");
        final var rootNode = javaDocElements.rootNode();
        final var packageNode = javaDocElements.packageNode();
        final var classNode = javaDocElements.classNode();
        final TypeParameter typeParameter = classNode.getGeneric().get(0);

        assertEquals(1, rootNode.getPackage().size());
        assertNull(packageNode.getComment());
        assertEquals(SIMPLE_DATA_PACKAGE, packageNode.getName());
        assertTrue(packageNode.getAnnotation().isEmpty());
        assertTrue(packageNode.getEnum().isEmpty());
        assertTrue(packageNode.getInterface().isEmpty());
        assertEquals(1, packageNode.getClazz().size());

        assertEquals("Class15", classNode.getComment());
        assertEquals(1, classNode.getConstructor().size());
        assertEquals("Class15", classNode.getName());
        assertEquals(getElementPathFromSimpleDataPackage("Class15<Fun>"), classNode.getQualified());
        assertEquals("public", classNode.getScope());
        assertEquals(1, classNode.getGeneric().size());
        assertTrue(classNode.getMethod().isEmpty());
        assertTrue(classNode.getField().isEmpty());
        assertTrue(classNode.getAnnotation().isEmpty());
        assertTrue(classNode.getInterface().isEmpty());
        assertEquals(classNode.getClazz().getQualified(), Object.class.getName());
        assertFalse(classNode.isAbstract());
        assertFalse(classNode.isExternalizable());
        assertTrue(classNode.isIncluded());
        assertFalse(classNode.isSerializable());
        assertFalse(classNode.isException());
        assertFalse(classNode.isError());

        // check the 'fun' type var
        assertEquals("Fun", typeParameter.getName());
        assertEquals(2, typeParameter.getBound().size());
        assertEquals(Number.class.getName(), typeParameter.getBound().get(0));
        assertEquals(Runnable.class.getName(), typeParameter.getBound().get(1));
    }

    /**
     * testing integer annotation argument
     */
    @Test
    void testClass16() {
        final var javaDocElements = newJavaDocElements("Class16.java", "Annotation3.java");
        final var classNode = javaDocElements.classNode();

        final AnnotationInstance instance = classNode.getAnnotation().get(0);
        final AnnotationArgument argument = instance.getArgument().get(0);
        assertEquals("id", argument.getName());
        assertEquals("int", argument.getType().getQualified());
        assertEquals(1, argument.getValue().size());
        assertEquals("3", argument.getValue().get(0));
        assertTrue(argument.isPrimitive());
        assertFalse(argument.isArray());
    }

    /**
     * testing integer array annotation argument
     */
    @Test
    void testClass17() {
        final var javaDocElements = newJavaDocElements("Class17.java", "Annotation5.java");
        final var classNode = javaDocElements.classNode();
        final AnnotationInstance instance = classNode.getAnnotation().get(0);
        final AnnotationArgument argument = instance.getArgument().get(0);

        assertEquals("int[]", argument.getType().getQualified());
        assertEquals(2, argument.getValue().size());
        assertEquals("1", argument.getValue().get(0));
        assertEquals("2", argument.getValue().get(1));
        assertFalse(argument.isPrimitive());
        assertTrue(argument.isArray());
    }

    /**
     * testing integer array annotation argument
     */
    @Test
    void testClass18() {
        final var javaDocElements = newJavaDocElements("Class18.java", "Annotation6.java");
        final var classNode = javaDocElements.classNode();
        final AnnotationInstance instance = classNode.getAnnotation().get(0);
        final AnnotationArgument argument = instance.getArgument().get(0);

        assertEquals("java.lang.String", argument.getType().getQualified());
        assertEquals(1, argument.getValue().size());
        assertEquals("hey", argument.getValue().get(0));
        assertFalse(argument.isPrimitive());
        assertFalse(argument.isArray());
    }

    /**
     * testing enum annotation argument
     */
    @Test
    void testClass19() {
        final var javaDocElements = newJavaDocElements("Class19.java", "Annotation7.java", "Enum1.java");
        final var classNode = javaDocElements.classNode();
        final AnnotationInstance instance = classNode.getAnnotation().get(0);
        final AnnotationArgument argument = instance.getArgument().get(0);

        assertEquals(getElementPathFromSimpleDataPackage("Enum1"), argument.getType().getQualified());
        assertEquals(1, argument.getValue().size());
        assertEquals("a", argument.getValue().get(0));
        assertFalse(argument.isPrimitive());
        assertFalse(argument.isArray());
    }

    /**
     * testing class annotation argument
     */
    @Test
    void testClass20() {
        final var javaDocElements = newJavaDocElements("Class20.java", "Annotation8.java");
        final var classNode = javaDocElements.classNode();
        final AnnotationInstance instance = classNode.getAnnotation().get(0);
        final AnnotationArgument argument = instance.getArgument().get(0);
        assertEquals("java.lang.Class", argument.getType().getQualified());
        assertEquals(1, argument.getValue().size());
        assertEquals("java.lang.String", argument.getValue().get(0));
        assertFalse(argument.isPrimitive());
        assertFalse(argument.isArray());
    }

    /**
     * testing character annotation argument
     */
    @Test
    void testClass21() {
        final var javaDocElements = newJavaDocElements("Class21.java", "Annotation10.java");
        final var classNode = javaDocElements.classNode();
        final AnnotationInstance instance = classNode.getAnnotation().get(0);
        final AnnotationArgument argument = instance.getArgument().get(0);

        assertEquals("char", argument.getType().getQualified());
        assertEquals(1, argument.getValue().size());
        assertEquals("a", argument.getValue().get(0));
        assertTrue(argument.isPrimitive());
        assertFalse(argument.isArray());
    }

    /**
     * testing 0 character annotation argument
     */
    @Test
    void testClass22() {
        final var javaDocElements = newJavaDocElements("Class22.java", "Annotation10.java");
        final var classNode = javaDocElements.classNode();
        final AnnotationInstance instance = classNode.getAnnotation().get(0);
        final AnnotationArgument argument = instance.getArgument().get(0);

        assertEquals("char", argument.getType().getQualified());
        assertEquals(1, argument.getValue().size());

        // U+0000 is not representable in XML 1.0, so it is emitted as its Java escape.
        // "\\u0000" below is six characters (backslash, u, 0, 0, 0, 0), not a Unicode escape.
        assertEquals("\\u0000", argument.getValue().get(0));

        assertTrue(argument.isPrimitive());
        assertFalse(argument.isArray());
    }

    /**
     * testing boolean annotation argument
     */
    @Test
    void testClass23() {
        final var javaDocElements = newJavaDocElements("Class23.java", "Annotation11.java");
        final var classNode = javaDocElements.classNode();
        final AnnotationInstance instance = classNode.getAnnotation().get(0);
        final AnnotationArgument argument = instance.getArgument().get(0);

        assertEquals("boolean", argument.getType().getQualified());
        assertEquals(1, argument.getValue().size());
        assertEquals("true", argument.getValue().get(0));
        assertTrue(argument.isPrimitive());
        assertFalse(argument.isArray());
    }

    /**
     * testing empty int array annotation argument
     */
    @Test
    void testClass24() {
        final var javaDocElements = newJavaDocElements("Class24.java", "Annotation5.java");
        final var classNode = javaDocElements.classNode();
        final AnnotationInstance instance = classNode.getAnnotation().get(0);
        final AnnotationArgument argument = instance.getArgument().get(0);

        assertEquals("int[]", argument.getType().getQualified());
        assertTrue(argument.getValue().isEmpty());
        assertFalse(argument.isPrimitive());
        assertTrue(argument.isArray());
    }
}
