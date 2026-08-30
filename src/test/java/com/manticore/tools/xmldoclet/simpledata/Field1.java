package com.manticore.tools.xmldoclet.simpledata;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Field1
 */
public class Field1 {
    /** field */
    public String field0;

    /** field */
    public String field1;

    /** field */
    @SuppressWarnings("unused")
    private String field2;

    /** field */
    String field3;

    /** field */
    protected String field4;

    /** field */
    public volatile String field5;

    /** field */
    public static String field6;

    /** field */
    public transient String field7;

    /** field */
    public final String field8 = "mer";

    /** field */
    public final String field9 = "testy";

    /** field */
    public final int field10 = 10;

    /** field */
    @Deprecated
    public String field11;

    /** field */
    @Deprecated
    @Annotation12("mister")
    public String field12;

    /** field */
    public String field13;

    /** field */
    public ArrayList<?> field14;

    /** field */
    public HashMap<String, Integer> field15;

    /** field */
    public String[] field16;

    /**
     * Constant holding U+0001, a character that is legal in Java source but not representable
     * in XML 1.0. Mirrors the HASH_SUBSTITUTION sentinel in JSqlParser's SimpleCharStream.
     * The escape is written literally here so that this file itself stays free of control
     * characters; the compiler translates it into the actual U+0001 before the doclet sees it.
     */
    public final char field17 = '\u0001';

    /** Constant with an embedded U+0001, surrounded by legal characters. */
    public final String field18 = "a\u0001b";

}
