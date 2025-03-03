package com.manticore.tools.xmldoclet;

import com.manticore.tools.xmldoclet.xjc.Field;
import com.manticore.tools.xmldoclet.xjc.Root;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Test to specifically investigate the comma issue with HTML entities and tags in JavaDoc comments
 */
class CommaIssueTest extends AbstractTest {

    /**
     * Tests that HTML entities and tags in the XML output don't have commas added around them
     */
    @Test
    void testHtmlWithoutCommas() throws JAXBException {
        // Generate XML for the test class
        final var javaDocElements = newJavaDocElements("CommaIssueTest.java");
        final var rootNode = javaDocElements.rootNode();
        final var classNode = javaDocElements.classNode();

        // Get comments from the test class
        String classComment = classNode.getComment();
        List<Field> fields = classNode.getField();
        String entityFieldComment = fields.get(0).getComment();
        String tagFieldComment = fields.get(1).getComment();

        // Print out the comments directly
        System.out.println("DIRECT CLASS COMMENT: " + classComment);
        System.out.println("ENTITY FIELD COMMENT: " + entityFieldComment);
        System.out.println("TAG FIELD COMMENT: " + tagFieldComment);

        // Generate XML and print it
        String xml = generateXml(rootNode);
        System.out.println("\nGENERATED XML:\n" + xml);

        // Check for commas around HTML entities and tags in the raw comments
        assertFalse(classComment.contains(",<"), "Found comma before < in class comment");
        assertFalse(classComment.contains(",&"), "Found comma before & in class comment");

        // Check for commas around HTML entities and tags in the XML
        assertFalse(xml.contains(",<code>"), "Found comma before <code> in XML");
        assertFalse(xml.contains("</code>,"), "Found comma after </code> in XML");
        assertFalse(xml.contains(",&lt;"), "Found comma before &lt; in XML");
        assertFalse(xml.contains(";,"), "Found comma after ; in XML");
    }

    /**
     * Generate XML from a Root node
     */
    private String generateXml(Root root) throws JAXBException {
        // Create a JAXB context and marshaller
        JAXBContext contextObj = JAXBContext.newInstance(Root.class);
        Marshaller marshaller = contextObj.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        // Marshal to a byte array
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        marshaller.marshal(root, baos);

        // Convert to string and return
        return baos.toString(StandardCharsets.UTF_8);
    }
}
