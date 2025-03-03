package com.manticore.tools.xmldoclet.simpledata;

/**
 * Class to test HTML tags and entities in JavaDoc comments.
 * 
 * &lt;p&gt;Testing with HTML entities: &amp;lt; &amp;gt; &amp;amp;&lt;/p&gt;
 * 
 * <p>
 * Testing with HTML tags: <code>inline code</code> and <strong>strong text</strong>
 * </p>
 */
public class CommaIssueTest {

    /**
     * Testing field with HTML entities: &lt;entities&gt; and &quot;quotes&quot;
     */
    public static final String ENTITY_TEST = "entity test";

    /**
     * Testing field with HTML tags: <code>code snippet</code> and <em>emphasized</em>
     */
    public static final String TAG_TEST = "tag test";
}
