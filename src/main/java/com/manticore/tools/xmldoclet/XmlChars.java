package com.manticore.tools.xmldoclet;

/**
 * Makes arbitrary Java text safe for XML 1.0 serialization.
 *
 * <p>
 * The XML 1.0 {@code Char} production (see
 * <a href="https://www.w3.org/TR/xml/#charsets">XML 1.0, section 2.2</a>) allows only:
 * </p>
 *
 * <pre>
 * #x9 | #xA | #xD | [#x20-#xD7FF] | [#xE000-#xFFFD] | [#x10000-#x10FFFF]
 * </pre>
 *
 * <p>
 * Everything else - notably the C0 control characters, unpaired surrogates and
 * {@code U+FFFE}/{@code U+FFFF} - cannot appear in an XML 1.0 document <em>at all</em>. They cannot
 * be escaped either: numeric character references such as {@code &#x1;} are equally illegal in XML
 * 1.0. The only options are to drop such a character or to replace it by a textual representation,
 * which is what this class does.
 * </p>
 *
 * <p>
 * This matters for real code. A field like
 * </p>
 *
 * <pre>
 * static final char HASH_SUBSTITUTION = '\\u0001';
 * </pre>
 *
 * <p>
 * (a sentinel that cannot occur in the input the parser accepts) would otherwise be written
 * verbatim into {@code <constant/>} and make the JAXB marshaller fail with
 * {@code Invalid XML character (Unicode: 0x1)}.
 * </p>
 *
 * <p>
 * Illegal characters are replaced by their Java source escape ({@code \\uXXXX}, lower case hex),
 * which is unambiguous, round-trips into Java source and renders sensibly in the RST, Markdown,
 * DocBook and AsciiDoc outputs.
 * </p>
 *
 * @author Andreas Reichel
 */
final class XmlChars {

    private XmlChars() {
        throw new AssertionError("Utility class, do not instantiate.");
    }

    /**
     * Replaces every character that is not representable in XML 1.0 by its Java {@code \\uXXXX}
     * escape.
     *
     * @param text the text to sanitize, may be {@code null}
     * @return the sanitized text, or the very same instance when nothing had to be replaced (which
     *         is the overwhelmingly common case), or {@code null} when {@code text} was
     *         {@code null}
     */
    static String sanitize(final String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }

        final int length = text.length();
        StringBuilder sanitized = null;

        for (int i = 0; i < length;) {
            // For a well-formed surrogate pair this returns the supplementary code point.
            // For an unpaired surrogate it returns the surrogate value itself, which isLegal()
            // rejects.
            final int codePoint = text.codePointAt(i);
            final int charCount = codePoint > Character.MAX_VALUE ? 2 : 1;

            if (isLegal(codePoint)) {
                if (sanitized != null) {
                    sanitized.appendCodePoint(codePoint);
                }
            } else {
                if (sanitized == null) {
                    sanitized = new StringBuilder(length + 16).append(text, 0, i);
                }
                appendEscaped(sanitized, codePoint);
            }

            i += charCount;
        }

        return sanitized == null ? text : sanitized.toString();
    }

    /**
     * @param codePoint the Unicode code point to test
     * @return whether the given code point may appear literally in an XML 1.0 document
     */
    static boolean isLegal(final int codePoint) {
        return codePoint == 0x9
                || codePoint == 0xA
                || codePoint == 0xD
                || codePoint >= 0x20 && codePoint <= 0xD7FF
                || codePoint >= 0xE000 && codePoint <= 0xFFFD
                || codePoint >= 0x10000 && codePoint <= 0x10FFFF;
    }

    /**
     * Appends the Java source escape of an XML-illegal code point. All illegal code points are
     * within the Basic Multilingual Plane, so a single {@code \\uXXXX} escape always suffices; a
     * defensive surrogate pair branch is kept for completeness.
     *
     * @param builder the builder to append to
     * @param codePoint the offending code point
     */
    private static void appendEscaped(final StringBuilder builder, final int codePoint) {
        if (codePoint > Character.MAX_VALUE) {
            for (final char c : Character.toChars(codePoint)) {
                builder.append(String.format("\\u%04x", (int) c));
            }
        } else {
            builder.append(String.format("\\u%04x", codePoint));
        }
    }
}
