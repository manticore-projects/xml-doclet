package com.manticore.tools.xmldoclet;

import com.manticore.tools.xmldoclet.simpledata.Annotation12;
import com.manticore.tools.xmldoclet.xjc.AnnotationArgument;
import com.manticore.tools.xmldoclet.xjc.AnnotationInstance;
import com.manticore.tools.xmldoclet.xjc.Class;
import com.manticore.tools.xmldoclet.xjc.Field;
import com.manticore.tools.xmldoclet.xjc.Package;
import com.manticore.tools.xmldoclet.xjc.Root;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import org.junit.jupiter.api.Test;

import java.io.StringWriter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test group for Fields
 */
class FieldTest extends AbstractTest {
    private final Root rootNode;
    private final Package packageNode;
    private final List<Field> fields;

    public FieldTest() {
        final var javaDocElements = newJavaDocElements("Field1.java");
        this.rootNode = javaDocElements.rootNode();
        this.packageNode = javaDocElements.packageNode();
        final Class classNode = packageNode.getClazz().get(0);
        this.fields = classNode.getField();
    }

    @Test
    void mainTest() {
        assertEquals(1, rootNode.getPackage().size());
        assertNull(packageNode.getComment());
        assertEquals(SIMPLE_DATA_PACKAGE, packageNode.getName());
        assertTrue(packageNode.getAnnotation().isEmpty());
        assertTrue(packageNode.getEnum().isEmpty());
        assertTrue(packageNode.getInterface().isEmpty());
        assertEquals(1, packageNode.getClazz().size());
    }

    /**
     * testing a returns of fields
     */
    @Test
    void testField0() {
        // field0 -- test name
        final var field = findByFieldName("field0", fields);
        assertEquals("field0", field.getName());
    }

    @Test
    void testField1() {
        // field1 -- test public field
        final var field = findByFieldName("field1", fields);
        assertEquals("public", field.getScope());
    }

    @Test
    void testField2() {
        // field2 -- test private field
        final var field = findByFieldName("field2", fields);
        assertEquals("private", field.getScope());
    }

    @Test
    void testField3() {
        // field3 -- default scope field (non defined)
        final var field = findByFieldName("field3", fields);
        assertEquals("", field.getScope());
    }

    @Test
    void testField4() {
        // field4 -- protected scope field
        final var field = findByFieldName("field4", fields);
        assertEquals("protected", field.getScope());

        // negative test of final
        assertFalse(field.isFinal());

        // negative test of static
        assertFalse(field.isStatic());

        // negative test of volatile
        assertFalse(field.isVolatile());

        // negative test of transient
        assertFalse(field.isTransient());
    }

    @Test
    void testField5() {
        // field5 -- volatile field
        final var field = findByFieldName("field5", fields);
        assertTrue(field.isVolatile());
    }

    @Test
    void testField6() {
        // field6 -- static field
        final var field = findByFieldName("field6", fields);
        assertTrue(field.isStatic());
    }

    @Test
    void testField7() {
        // field7 -- transient field
        final var field = findByFieldName("field7", fields);
        assertTrue(field.isTransient());
    }

    @Test
    void testField8() {
        // field8 -- final field
        final var field = findByFieldName("field8", fields);
        assertTrue(field.isFinal());
    }

    @Test
    void testField9() {
        // field9 -- string final expression
        final var field = findByFieldName("field9", fields);
        assertEquals("testy", field.getConstant());
    }

    @Test
    void testField10() {
        // field10 -- int final expression
        final var field = findByFieldName("field10", fields);
        assertEquals("10", field.getConstant());
    }

    @Test
    void testField11() {
        // field11 -- annotation
        final var field = findByFieldName("field11", fields);
        assertEquals(1, field.getAnnotation().size());

        AnnotationInstance annotation = field.getAnnotation().get(0);
        assertEquals("java.lang.Deprecated", annotation.getQualified());
        assertEquals("Deprecated", annotation.getName());
        assertTrue(annotation.getArgument().isEmpty());
    }

    @Test
    void testField12() {
        // field12 -- two annotations
        final var field = findByFieldName("field12", fields);
        assertEquals(2, field.getAnnotation().size());

        final var annotation1 = field.getAnnotation().get(0);
        assertEquals("java.lang.Deprecated", annotation1.getQualified());
        assertEquals("Deprecated", annotation1.getName());
        assertTrue(annotation1.getArgument().isEmpty());

        final var annotation2 = field.getAnnotation().get(1);
        assertEquals(annotation2.getQualified(), Annotation12.class.getName());
        assertEquals(annotation2.getName(), Annotation12.class.getSimpleName());
        assertEquals(1, annotation2.getArgument().size());

        final AnnotationArgument argument = annotation2.getArgument().get(0);
        assertEquals("value", argument.getName());
        assertEquals("mister", argument.getValue().get(0));
    }

    @Test
    void testField13() {
        // field13 - type testing
        final var field = findByFieldName("field13", fields);
        assertNotNull(field.getType());
        assertEquals("java.lang.String", field.getType().getQualified());
        assertNull(field.getType().getDimension());
        assertNull(field.getType().getWildcard());
        assertTrue(field.getType().getGeneric().isEmpty());
    }

    @Test
    void testField14() {
        // field14 - wild card
        final var field = findByFieldName("field14", fields);
        assertNotNull(field.getType());
        assertEquals("java.util.ArrayList<?>", field.getType().getQualified());
        assertNotNull(field.getType().getGeneric());
        assertEquals(1, field.getType().getGeneric().size());
        assertEquals("?", field.getType().getGeneric().get(0).getQualified());
        assertNotNull(field.getType().getGeneric().get(0).getWildcard());
    }

    @Test
    void testField15() {
        // field15 - typed generic
        final var field = findByFieldName("field15", fields);
        assertNotNull(field.getType());
        assertEquals("java.util.HashMap<java.lang.String,java.lang.Integer>", field.getType().getQualified());
        assertEquals(2, field.getType().getGeneric().size());
        assertEquals("java.lang.String", field.getType().getGeneric().get(0).getQualified());
        assertNull(field.getType().getGeneric().get(0).getWildcard());
        assertEquals("java.lang.Integer", field.getType().getGeneric().get(1).getQualified());
        assertNull(field.getType().getGeneric().get(1).getWildcard());
    }

    @Test
    void testField16() {
        // field16 - array
        final var field = findByFieldName("field16", fields);
        assertNotNull(field.getType());
        assertEquals("java.lang.String[]", field.getType().getQualified());

        // The dimension is not longer the brackets, but the actual dimension size.
        // For an array, the dimension is 1. For a 2D matrix, the dimension is 2, and so on.
        // TODO: The dimension attribute type should be changed to int, but that will require changing the javadoc.xsd
        assertEquals("1", field.getType().getDimension());
    }

    @Test
    void testField17() {
        // field17 -- char constant holding U+0001, which is legal Java but illegal in XML 1.0.
        // It must be written as its Java escape rather than verbatim.
        // Note: "\\u0001" below is a six character String (backslash, u, 0, 0, 0, 1), not a
        // Unicode escape, because the \\u is preceded by an odd number of backslashes (JLS 3.3).
        final var field = findByFieldName("field17", fields);
        assertEquals("\\u0001", field.getConstant());
    }

    @Test
    void testField18() {
        // field18 -- String constant with an embedded control character: the legal characters
        // around it must survive untouched
        final var field = findByFieldName("field18", fields);
        assertEquals("a\\u0001b", field.getConstant());
    }

    /**
     * Regression test for the JAXB marshaller failing with
     * {@code Invalid XML character (Unicode: 0x1)} on constants such as
     * {@code static final char HASH_SUBSTITUTION = '\\u0001'}.
     *
     * <p>
     * The parser tests alone would not catch this, since they never serialize the object model.
     * </p>
     */
    @Test
    void testMarshallingProducesWellFormedXml() throws Exception {
        final var marshaller = JAXBContext.newInstance(Root.class).createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        final var writer = new StringWriter();
        marshaller.marshal(rootNode, writer);
        final String xml = writer.toString();

        assertTrue(xml.contains("\\u0001"), "The escaped constant is missing from the marshalled XML");

        xml.codePoints().forEach(
                codePoint -> assertTrue(
                        XmlChars.isLegal(codePoint),
                        () -> "Illegal XML 1.0 character U+%04X in the marshalled output".formatted(codePoint)));
    }

    /**
     * Short way of finding fields.
     *
     * @param fieldName the shortname of the method
     * @param fields the list of methods to look through.
     * @return The matching field
     */
    private Field findByFieldName(final String fieldName, final List<Field> fields) {
        for (final var field : fields) {
            if (fieldName.equals(field.getName())) {
                return field;
            }
        }

        throw new AssertionError("Field not found: " + fieldName);
    }
}
