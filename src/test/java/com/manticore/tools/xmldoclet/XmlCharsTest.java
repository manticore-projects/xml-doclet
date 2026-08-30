package com.manticore.tools.xmldoclet;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test group for {@link XmlChars}.
 *
 * <p>
 * Note on notation: in the sources below, {@code "\\u0001"} is a six character String consisting of
 * a backslash followed by {@code u0001}. It is <em>not</em> a Unicode escape, because the
 * {@code \\u} sequence is preceded by an odd number of backslashes and therefore not eligible for
 * translation by the lexer (JLS 3.3). A bare {@code \\u0001} would be translated even inside a
 * comment, so it is written doubled throughout this file.
 * </p>
 */
class XmlCharsTest {

    @Test
    void nullAndEmptyArePassedThrough() {
        assertNull(XmlChars.sanitize(null));
        assertEquals("", XmlChars.sanitize(""));
    }

    @Test
    void legalTextIsReturnedUnchangedAndNotCopied() {
        final String[] legalTexts = {
                "A",
                "hello world",
                "tab\there",
                "line\nfeed",
                "carriage\r\nreturn",
                "caf\u00e9 \u4e2d\u6587",
                "delete \u007f is legal in XML 1.0",
                "replacement \ufffd is legal",
                "astral \ud83d\ude00 pair"
        };

        for (final String text : legalTexts) {
            // The fast path must not allocate a copy, since it is taken for virtually every String
            assertSame(text, XmlChars.sanitize(text), "Text was needlessly rewritten: " + text);
        }
    }

    @Test
    void controlCharacterIsEscaped() {
        // This is the HASH_SUBSTITUTION case: static final char HASH_SUBSTITUTION = '\\u0001'
        assertEquals("\\u0001", XmlChars.sanitize("\u0001"));
    }

    @Test
    void allIllegalC0ControlsAreEscaped() {
        for (char c = 0; c < 0x20; c++) {
            final String text = String.valueOf(c);
            if (c == '\t' || c == '\n' || c == '\r') {
                assertSame(text, XmlChars.sanitize(text), "U+%04X must be preserved".formatted((int) c));
            } else {
                assertEquals("\\u%04x".formatted((int) c), XmlChars.sanitize(text));
            }
        }
    }

    @Test
    void illegalCharactersAreEscapedInPlaceAndSurroundingsPreserved() {
        assertEquals("a\\u0001b", XmlChars.sanitize("a\u0001b"));
        assertEquals("\\u0000start", XmlChars.sanitize("\u0000start"));
        assertEquals("end\\u001f", XmlChars.sanitize("end\u001f"));
        assertEquals("\\u0001\\u0002\\u0003", XmlChars.sanitize("\u0001\u0002\u0003"));
    }

    @Test
    void nonCharactersAreEscaped() {
        assertEquals("\\ufffe", XmlChars.sanitize("\ufffe"));
        assertEquals("\\uffff", XmlChars.sanitize("\uffff"));
    }

    @Test
    void unpairedSurrogateIsEscapedAndDoesNotCorruptNeighbours() {
        assertEquals("a\\ud800b", XmlChars.sanitize("a\ud800b"));
        assertEquals("a\\udc00b", XmlChars.sanitize("a\udc00b"));
    }

    @Test
    void wellFormedSurrogatePairIsPreserved() {
        final String emoji = "\ud83d\ude00";
        assertSame(emoji, XmlChars.sanitize(emoji));
        // The pair must survive as one code point, not be split into two escaped surrogates
        assertEquals(1, XmlChars.sanitize("x" + emoji + "y").codePointCount(1, 3));
    }

    @Test
    void backslashesAreNotDoubled() {
        // Sanitizing must not touch legal text, even when it already looks like an escape
        final String text = "C:\\temp\\u0001";
        assertSame(text, XmlChars.sanitize(text));
    }

    @Test
    void sanitizedOutputContainsOnlyLegalCharacters() {
        final var builder = new StringBuilder();
        for (int cp = 0; cp <= 0xFFFF; cp++) {
            builder.appendCodePoint(cp);
        }

        XmlChars.sanitize(builder.toString())
                .codePoints()
                .forEach(cp -> assertTrue(XmlChars.isLegal(cp), () -> "U+%04X leaked through".formatted(cp)));
    }

    @Test
    void isLegalMatchesTheXml10CharProduction() {
        assertFalse(XmlChars.isLegal(0x0));
        assertTrue(XmlChars.isLegal(0x9));
        assertTrue(XmlChars.isLegal(0xA));
        assertFalse(XmlChars.isLegal(0xB));
        assertFalse(XmlChars.isLegal(0xC));
        assertTrue(XmlChars.isLegal(0xD));
        assertFalse(XmlChars.isLegal(0xE));
        assertFalse(XmlChars.isLegal(0x1F));
        assertTrue(XmlChars.isLegal(0x20));
        assertTrue(XmlChars.isLegal(0xD7FF));
        assertFalse(XmlChars.isLegal(0xD800));
        assertFalse(XmlChars.isLegal(0xDFFF));
        assertTrue(XmlChars.isLegal(0xE000));
        assertTrue(XmlChars.isLegal(0xFFFD));
        assertFalse(XmlChars.isLegal(0xFFFE));
        assertFalse(XmlChars.isLegal(0xFFFF));
        assertTrue(XmlChars.isLegal(0x10000));
        assertTrue(XmlChars.isLegal(0x10FFFF));
    }
}
