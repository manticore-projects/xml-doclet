package com.manticore.tools.xmldoclet;

import com.karuslabs.elementary.junit.Tools;
import com.karuslabs.elementary.junit.ToolsExtension;
import com.manticore.tools.xmldoclet.xjc.ObjectFactory;
import com.sun.source.util.DocTrees;
import jdk.javadoc.doclet.DocletEnvironment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

import static com.manticore.tools.xmldoclet.Level1Class.Level2Class;
import static com.manticore.tools.xmldoclet.Level1Class.Level2Class.Level3Class;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

/** A top-level class. */
class Level1Class {
    /** An inner class (2nd level). */
    static class Level2Class {
        /** An inner-inner class (3rd level). */
        static class Level3Class {}
    }
}

/// Tests for the [Parser] class.
/// It uses [Elementary lib](https://github.com/Pante/elementary)
/// that provides utilities for testing Annotation Processors and Doclets.
@ExtendWith(ToolsExtension.class)
class ParserTest {
    private final DocletEnvironment env = Mockito.mock(DocletEnvironment.class);
    private final Elements elements = Tools.elements();
    private final Types types = Tools.types();
    private Parser parser;

    /** Top-level class */
    private final TypeElement classLevel1AsElement = elements.getTypeElement(Level1Class.class.getName());
    /** Inner class */
    private final TypeElement classLevel2AsElement = elements.getTypeElement(Level2Class.class.getCanonicalName());
    /** Inner-inner class */
    private final TypeElement classLevel3AsElement = elements.getTypeElement(Level3Class.class.getCanonicalName());

    @BeforeEach
    void setUp() {
        Mockito.when(env.getTypeUtils()).thenReturn(types);
        Mockito.when(env.getElementUtils()).thenReturn(elements);
        Mockito.when(env.getDocTrees()).thenReturn((DocTrees) Tools.trees());

        parser = new Parser(env);
    }

    /**
     * When getting the top-level class from a given class that is already top-level returns the class itself.
     */
    @Test
    void getTopLevelClass() {
        assertSame(classLevel1AsElement, Parser.getTopLevelClass(classLevel1AsElement));
    }

    @Test
    void getTopLevelClassFromInnerClass() {
        assertSame(classLevel1AsElement, Parser.getTopLevelClass(classLevel2AsElement));
    }

    @Test
    void getTopLevelClassFromInnerInnerClass() {
        assertSame(classLevel1AsElement, Parser.getTopLevelClass(classLevel3AsElement));
    }

    @Test
    void getPackageFromTopLevelClass() {
        final var rootNode = new ObjectFactory().createRoot();
        assertEquals("com.manticore.tools.xmldoclet", parser.getPackage(rootNode, classLevel1AsElement).getName());
    }

    @Test
    void getPackageFromTopInnerClasses() {
        final var rootNode = new ObjectFactory().createRoot();
        assertEquals("com.manticore.tools.xmldoclet", parser.getPackage(rootNode, classLevel1AsElement).getName());
        assertEquals("com.manticore.tools.xmldoclet", parser.getPackage(rootNode, classLevel2AsElement).getName());
    }
}
