package com.manticore.tools.xmldoclet;

import com.manticore.tools.xmldoclet.xjc.AnnotationArgument;
import com.manticore.tools.xmldoclet.xjc.AnnotationInstance;
import com.manticore.tools.xmldoclet.xjc.ObjectFactory;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Name;
import javax.lang.model.type.ExecutableType;
import javax.lang.model.type.TypeMirror;
import java.util.List;
import java.util.logging.Logger;

import static com.manticore.tools.xmldoclet.TypeUtils.getQualifiedName;
import static com.manticore.tools.xmldoclet.TypeUtils.isArray;

/**
 * @author Manoel Campos
 */
class AnnotationParser {
    // For some odd reason, the spotless plugin is crazily joining multiple lines on this file. Formatting was disabled.
    // @formatter:off
    private final static Logger LOGGER = Logger.getLogger(AnnotationParser.class.getName());

    private final Parser parser;
    private final ObjectFactory objectFactory;

    AnnotationParser(final Parser parser) {
        this.parser = parser;
        this.objectFactory = parser.objectFactory;
    }

    /**
     * Parses annotation instances of an annotable program element
     *
     * @param programElement the name of a program element to parse its annotations
     * @param annotationDesc the annotation to parse
     * @return representation of annotations
     */
    protected AnnotationInstance parse(final Name programElement, final  AnnotationMirror annotationDesc) {
        final var annotationInstance = objectFactory.createAnnotationInstance();

        try {
            final var annotTypeInfo = annotationDesc.getAnnotationType();
            annotationInstance.setName(annotTypeInfo.asElement().getSimpleName().toString());
            annotationInstance.setQualified(getQualifiedName(annotTypeInfo.asElement()));
        } catch (ClassCastException castException) {
            LOGGER.severe(
                    "Unable to obtain type data about an annotation found on: "
                          + programElement
                          + "\n"
                          + "Add to the classpath the class/jar that defines this annotation."
                    );
        }

        for (final var elementValuesPair : annotationDesc.getElementValues().entrySet()) {
            final AnnotationArgument annotationArgumentNode = objectFactory.createAnnotationArgument();
            // The key is an element that represents the method defined in the annotation interface
            // which enables setting a value to the argument
            final ExecutableElement annotationArgumentSetter = elementValuesPair.getKey();
            annotationArgumentNode.setName(annotationArgumentSetter.getSimpleName().toString());

            final TypeMirror annotationArgumentType = getAnnotationArgumentType(annotationArgumentSetter);
            annotationArgumentNode.setType(parser.parseTypeInfo(annotationArgumentType));
            annotationArgumentNode.setPrimitive(annotationArgumentType.getKind().isPrimitive());
            annotationArgumentNode.setArray(isArray(annotationArgumentType));

            final Object objValue = elementValuesPair.getValue();
            parseAnnotationArgValue(programElement, annotationArgumentNode, objValue);

            annotationInstance.getArgument().add(annotationArgumentNode);
        }

        return annotationInstance;
    }

    /**
     * Parses the value of a given annotation argument.
     * @param programElement the name of a program element to parse
     * @param arg  annotation argument to parse its value
     * @param argValue the value for an annotation argument
     */
    private void parseAnnotationArgValue(final Name programElement, final AnnotationArgument arg, final Object argValue) {
        if (argValue instanceof AnnotationValue) {
            AnnotationValue annotationValue = (AnnotationValue) argValue;
            if (annotationValue.getValue() instanceof List<?>) {
                List<?> valueList = (List<?>) annotationValue.getValue();
                parseAnnotationArgListValue(programElement, arg, valueList);
            } else {
                arg.getValue().add(XmlChars.sanitize(annotationValue.getValue().toString()));
            }
        } else if (argValue != null) {
            arg.getValue().add(XmlChars.sanitize(argValue.toString()));
        }

    }

    /**
     * Parses the value of a given annotation argument when such a value is a List,
     * indicating there are multiple values for that argumento (such as {@code @Annotation1({"A", "B"})}).
     * @param programElement the name of a program element to parse
     * @param arg  annotation argument to parse its value list
     */
    private void parseAnnotationArgListValue(final Name programElement, final AnnotationArgument arg, final List<?> valueList) {
        for (final Object value : valueList) {
            if (value instanceof AnnotationMirror) {
                AnnotationMirror annoDesc = (AnnotationMirror) value;
                arg.getAnnotation().add(parse(programElement, annoDesc));
            } else {
                /*
                Consider the annotation @Annotation1("A") or @Annotation1({"A", "B"}).
                The annotation value is an AnnotationValue object with value attribute.
                This attribute is a List (even if there is a single value).
                But each value is not the actual value, but another AnnotationValue object with a value attribute.
                */
                AnnotationValue annotationValue = (AnnotationValue) value;
                arg.getValue().add(XmlChars.sanitize(annotationValue.getValue().toString()));
            }
        }

    }

    /**
     * {@return the data type of an annotation argument value from
     *          the method that gets such a value (the annotation argument definition method)}
     * @param annotationArgumentGetter a type that represents the method that gets the value for the argument,
     *                                 specified in the interface that defines the annotation.
     */
    private static TypeMirror getAnnotationArgumentType(final ExecutableElement annotationArgumentGetter) {
        final TypeMirror annotationArgumentGetterType = annotationArgumentGetter.asType();
        return ((ExecutableType) annotationArgumentGetterType).getReturnType();
    }

    // @formatter:on
}
