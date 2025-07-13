<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:my="http://manticore-projects.com/my" >

    <xsl:output
            method="xml"
            encoding="utf8"
            omit-xml-declaration="no"
            indent="yes"
            doctype-public="-//OASIS//DTD DocBook XML V4.5//EN"
            doctype-system="http://www.oasis-open.org/docbook/xml/4.5/docbookx.dtd" />

    <xsl:param name="basePackage" select="''" />
    <xsl:param name="doctitle" select="'Java API'" />
    <xsl:param name="withFloatingToc" select="'false'" />

    <xsl:function name="my:replacePRE">
        <xsl:param name="input"/>
        <xsl:choose>
            <xsl:when test="$input and string-length($input) > 0">
                <xsl:variable name="content" select='replace($input, "&lt;pre&gt;([^&lt;]*)&lt;/pre&gt;", "$1")'  />
                <programlisting><xsl:value-of select="normalize-space($content)"/></programlisting>
            </xsl:when>
            <xsl:otherwise>
                <xsl:text/>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:function>

    <xsl:function name="my:replaceTags">
        <xsl:param name="input"/>
        <xsl:choose>
            <xsl:when test="$input and string-length($input) > 0">
                <!-- First, handle programlisting blocks to preserve their content -->
                <xsl:variable name="step1" select='replace($input, "&lt;pre&gt;(.*?)&lt;/pre&gt;", "&lt;programlisting&gt;$1&lt;/programlisting&gt;", "s")'  />

                <!-- Handle code spans -->
                <xsl:variable name="step2" select='replace($step1, "&lt;code&gt;(.*?)&lt;/code&gt;", "&lt;code&gt;$1&lt;/code&gt;", "s")' />

                <!-- Handle paragraphs - but be careful not to nest them -->
                <xsl:variable name="step3" select='replace($step2, "&lt;p&gt;(.*?)&lt;/p&gt;", "$1", "s")' />

                <!-- Handle blockquotes -->
                <xsl:variable name="step4" select='replace($step3, "&lt;blockquote&gt;(.*?)&lt;/blockquote&gt;", "&lt;blockquote&gt;&lt;para&gt;$1&lt;/para&gt;&lt;/blockquote&gt;", "s")'  />

                <!-- Handle line breaks -->
                <xsl:variable name="step5" select='replace($step4, "&lt;br/?&gt;", "&lt;sbr/&gt;")' />

                <!-- Clean up any remaining problematic HTML tags -->
                <xsl:variable name="step6" select='replace($step5, "&lt;/?div[^&gt;]*&gt;", "")'  />
                <xsl:variable name="step7" select='replace($step6, "&lt;/?span[^&gt;]*&gt;", "")'  />

                <!-- Handle common HTML entities -->
                <xsl:variable name="step8" select='replace($step7, "&amp;lt;", "&lt;")'  />
                <xsl:variable name="step9" select='replace($step8, "&amp;gt;", "&gt;")'  />
                <xsl:variable name="step10" select='replace($step9, "&amp;amp;", "&amp;")'  />

                <!-- Clean up simple emphasis tags -->
                <xsl:variable name="step11" select='replace($step10, "&lt;(em|strong|b|i)&gt;([^&lt;]*?)&lt;/(em|strong|b|i)&gt;", "&lt;emphasis&gt;$2&lt;/emphasis&gt;")'  />

                <xsl:value-of select="normalize-space($step11)" disable-output-escaping="yes"/>
            </xsl:when>
            <xsl:otherwise>
                <xsl:text/>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:function>

    <!-- Alternative simpler function for safer text processing -->
    <xsl:function name="my:safeText">
        <xsl:param name="input"/>
        <xsl:choose>
            <xsl:when test="$input and string-length($input) > 0">
                <!-- Strip all HTML tags and just keep the text content -->
                <xsl:variable name="step1" select='replace($input, "&lt;[^&gt;]+&gt;", "", "s")'  />
                <!-- Handle common HTML entities -->
                <xsl:variable name="step2" select='replace($step1, "&amp;lt;", "&lt;")'  />
                <xsl:variable name="step3" select='replace($step2, "&amp;gt;", "&gt;")'  />
                <xsl:variable name="step4" select='replace($step3, "&amp;amp;", "&amp;")'  />
                <xsl:variable name="step5" select='replace($step4, "&amp;nbsp;", " ")'  />
                <xsl:value-of select="normalize-space($step5)"/>
            </xsl:when>
            <xsl:otherwise>
                <xsl:text/>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:function>

    <xsl:function name="my:className">
        <xsl:param name="input"/>
        <xsl:choose>
            <xsl:when test="$input and string-length($input) > 0 and contains($input, '.')">
                <xsl:variable name="name" select="tokenize($input,'\.')[last()]" />
                <xsl:variable name="linkend" select="replace($input, '[&lt;&gt;,\s\[\]]', '_')"/>
                <link>
                    <xsl:attribute name="linkend" select="$linkend"/>
                    <code><xsl:value-of select="$name"/></code>
                </link>
            </xsl:when>
            <xsl:when test="$input and string-length($input) > 0">
                <code><xsl:value-of select="$input" /></code>
            </xsl:when>
            <xsl:otherwise>
                <xsl:text/>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:function>

    <xsl:function name="my:anchorId">
        <xsl:param name="input"/>
        <xsl:choose>
            <xsl:when test="$input and string-length($input) > 0">
                <xsl:value-of select="replace($input, '[&lt;&gt;,\s\[\]]', '_')"/>
            </xsl:when>
            <xsl:otherwise>
                <xsl:text/>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:function>

    <!-- Match the root element -->
    <xsl:template match="/root">
        <book>
            <bookinfo>
                <title><xsl:value-of select="$doctitle"/></title>
                <xsl:choose>
                    <xsl:when test="string-length($basePackage)>0">
                        <subtitle>Base Package: <code><xsl:value-of select="$basePackage"/></code></subtitle>
                    </xsl:when>
                </xsl:choose>
                <author>
                    <firstname>Generated</firstname>
                    <surname>Documentation</surname>
                </author>
            </bookinfo>

            <xsl:for-each select="package">
                <xsl:sort select="@name"/>
                <xsl:apply-templates select="."/>
            </xsl:for-each>
        </book>
    </xsl:template>

    <!-- Match package elements -->
    <xsl:template match="package">
        <xsl:variable name="packageName" select="@name"/>
        <xsl:if test="$packageName and string-length($packageName) > 0">
            <chapter>
                <xsl:attribute name="id" select="my:anchorId(@name)"/>
                <title>
                    <xsl:choose>
                        <xsl:when test="$basePackage=''"><xsl:value-of select="@name"/></xsl:when>
                        <xsl:when test="substring(@name, string-length(concat($basePackage, '.')))=''">Base</xsl:when>
                        <xsl:otherwise><xsl:value-of select="substring(@name, string-length($basePackage)+2)"/></xsl:otherwise>
                    </xsl:choose>
                </title>

                <!-- Process enums in the package -->
                <xsl:for-each select="enum">
                    <xsl:sort select="@qualified"/>
                    <xsl:apply-templates select="."/>
                </xsl:for-each>

                <!-- Process classes in the package -->
                <xsl:for-each select="class">
                    <xsl:sort select="@qualified"/>
                    <xsl:apply-templates select="."/>
                </xsl:for-each>

                <!-- Process interfaces in the package -->
                <xsl:for-each select="interface">
                    <xsl:sort select="@qualified"/>
                    <xsl:apply-templates select="."/>
                </xsl:for-each>
            </chapter>
        </xsl:if>
    </xsl:template>

    <!-- Match interface elements -->
    <xsl:template match="interface">
        <xsl:variable name="interfaceName" select="@name"/>
        <xsl:variable name="qualifiedInterfaceName" select="@qualified"/>

        <xsl:if test="$interfaceName and string-length($interfaceName) > 0">
            <section>
                <xsl:attribute name="id" select="my:anchorId(@qualified)"/>
                <title>interface <xsl:value-of select="$interfaceName"/></title>

                <xsl:choose>
                    <xsl:when test="interface">
                        <para>
                            <emphasis role="bold">Implements:</emphasis>
                            <xsl:for-each select="interface">
                                <xsl:sort select="@name"/>
                                <xsl:if test="@qualified and string-length(@qualified) > 0">
                                    <xsl:copy-of select="my:className(@qualified)"/>
                                    <xsl:if test="position() != last()">
                                        <xsl:text>, </xsl:text>
                                    </xsl:if>
                                </xsl:if>
                            </xsl:for-each>
                        </para>
                    </xsl:when>
                </xsl:choose>

                <xsl:choose>
                    <xsl:when test="//*[interface[@qualified=$qualifiedInterfaceName]]">
                        <para>
                            <emphasis role="bold">Provides:</emphasis>
                            <xsl:for-each select="//*[interface[@qualified=$qualifiedInterfaceName]]">
                                <xsl:sort select="@name"/>
                                <xsl:if test="@qualified and string-length(@qualified) > 0">
                                    <xsl:copy-of select="my:className(@qualified)"/>
                                    <xsl:if test="position() != last()">
                                        <xsl:text>, </xsl:text>
                                    </xsl:if>
                                </xsl:if>
                            </xsl:for-each>
                        </para>
                    </xsl:when>
                </xsl:choose>

                <xsl:choose>
                    <xsl:when test="comment and string-length(comment) > 0">
                        <para><xsl:value-of select='my:safeText(comment)'/></para>
                    </xsl:when>
                </xsl:choose>

                <!-- Process methods in the interface -->
                <xsl:apply-templates select="method"/>
            </section>
        </xsl:if>
    </xsl:template>

    <!-- Match class elements -->
    <xsl:template match="class">
        <xsl:variable name="className" select="@name"/>
        <xsl:variable name="qualifiedClassName" select="@qualified"/>

        <xsl:if test="$className and string-length($className) > 0">
            <section>
                <xsl:attribute name="id" select="my:anchorId(@qualified)"/>
                <title>class <xsl:value-of select="$className"/></title>

                <xsl:choose>
                    <xsl:when test="class">
                        <para>
                            <emphasis role="bold">Extends:</emphasis>
                            <xsl:if test="class/@qualified and string-length(class/@qualified) > 0">
                                <xsl:copy-of select="my:className(class/@qualified)"/>
                            </xsl:if>
                        </para>
                    </xsl:when>
                </xsl:choose>

                <xsl:choose>
                    <xsl:when test="interface">
                        <para>
                            <emphasis role="bold">Implements:</emphasis>
                            <xsl:for-each select="interface">
                                <xsl:sort select="@name"/>
                                <xsl:if test="@qualified and string-length(@qualified) > 0">
                                    <xsl:copy-of select="my:className(@qualified)"/>
                                    <xsl:if test="position() != last()">
                                        <xsl:text>, </xsl:text>
                                    </xsl:if>
                                </xsl:if>
                            </xsl:for-each>
                        </para>
                    </xsl:when>
                </xsl:choose>

                <xsl:choose>
                    <xsl:when test="//class[class[@qualified=$qualifiedClassName]]">
                        <para>
                            <emphasis role="bold">Provides:</emphasis>
                            <xsl:for-each select="//class[class[@qualified=$qualifiedClassName]]">
                                <xsl:sort select="@name"/>
                                <xsl:if test="@qualified and string-length(@qualified) > 0">
                                    <xsl:copy-of select="my:className(@qualified)"/>
                                    <xsl:if test="position() != last()">
                                        <xsl:text>, </xsl:text>
                                    </xsl:if>
                                </xsl:if>
                            </xsl:for-each>
                        </para>
                    </xsl:when>
                </xsl:choose>

                <xsl:choose>
                    <xsl:when test="comment and string-length(comment) > 0">
                        <para><xsl:value-of select='my:safeText(comment)'/></para>
                    </xsl:when>
                </xsl:choose>

                <!-- Process constructors -->
                <xsl:apply-templates select="constructor[@scope='public']"/>

                <!-- Process methods -->
                <xsl:apply-templates select="method[@scope='public']"/>
            </section>
        </xsl:if>
    </xsl:template>

    <!-- Match enum elements -->
    <xsl:template match="enum">
        <xsl:variable name="enumName" select="@name"/>
        <xsl:variable name="qualifiedEnumName" select="@qualified"/>

        <xsl:if test="$enumName and string-length($enumName) > 0">
            <section>
                <xsl:attribute name="id" select="my:anchorId(@qualified)"/>
                <title>enum <xsl:value-of select="$enumName"/></title>

                <para><emphasis role="bold">Values:</emphasis></para>

                <itemizedlist>
                    <xsl:for-each select="constant">
                        <xsl:if test="@name and string-length(@name) > 0">
                            <listitem>
                                <para>
                                    <code><xsl:value-of select="@name"/></code>
                                    <xsl:if test="comment and string-length(comment) > 0">
                                        <xsl:text> - </xsl:text>
                                        <xsl:value-of select="my:safeText(comment)"/>
                                    </xsl:if>
                                </para>
                            </listitem>
                        </xsl:if>
                    </xsl:for-each>
                </itemizedlist>

                <xsl:choose>
                    <xsl:when test="comment and string-length(comment) > 0">
                        <para><xsl:value-of select='my:safeText(comment)'/></para>
                    </xsl:when>
                </xsl:choose>
            </section>
        </xsl:if>
    </xsl:template>

    <!-- Match constructor elements -->
    <xsl:template match="constructor[@scope='public']">
        <xsl:variable name="constructorName" select="@name"/>

        <xsl:if test="$constructorName and string-length($constructorName) > 0">
            <section>
                <xsl:attribute name="id" select="my:anchorId(concat(parent::*/@qualified, '.', $constructorName))"/>
                <title>
                    <xsl:value-of select="$constructorName"/>(
                    <xsl:choose>
                        <xsl:when test="parameter">
                            <xsl:for-each select="parameter">
                                <xsl:if test="@name and string-length(@name) > 0">
                                    <xsl:value-of select="@name"/>
                                    <xsl:if test="position() != last()">
                                        <xsl:text>, </xsl:text>
                                    </xsl:if>
                                </xsl:if>
                            </xsl:for-each>
                        </xsl:when>
                    </xsl:choose>
                    )
                </title>

                <xsl:choose>
                    <xsl:when test="comment and string-length(comment) > 0">
                        <para><xsl:value-of select='my:safeText(comment)'/></para>
                    </xsl:when>
                </xsl:choose>

                <!-- Process constructor parameters -->
                <xsl:if test="parameter">
                    <para><emphasis role="bold">Parameters:</emphasis></para>
                    <variablelist>
                        <xsl:apply-templates select="parameter"/>
                    </variablelist>
                </xsl:if>
            </section>
        </xsl:if>
    </xsl:template>

    <xsl:template match="method[@scope='public']">
        <xsl:variable name="methodName" select="@name"/>

        <xsl:if test="$methodName and string-length($methodName) > 0">
            <section>
                <xsl:attribute name="id" select="my:anchorId(concat(parent::*/@qualified, '.', $methodName))"/>
                <title>
                    <xsl:choose>
                        <xsl:when test="annotation">
                            <xsl:for-each select="annotation">
                                <emphasis>@<xsl:if test="@name and string-length(@name) > 0">
                                    <xsl:value-of select="@name"/>
                                </xsl:if></emphasis>
                                <xsl:if test="position() != last()">
                                    <xsl:text>, </xsl:text>
                                </xsl:if>
                            </xsl:for-each>
                            <sbr/>
                        </xsl:when>
                    </xsl:choose>

                    <xsl:value-of select="$methodName"/>(
                    <xsl:choose>
                        <xsl:when test="parameter">
                            <xsl:for-each select="parameter">
                                <xsl:if test="@name and string-length(@name) > 0">
                                    <xsl:value-of select="@name"/>
                                    <xsl:if test="position() != last()">
                                        <xsl:text>, </xsl:text>
                                    </xsl:if>
                                </xsl:if>
                            </xsl:for-each>
                        </xsl:when>
                    </xsl:choose>
                    )
                    <xsl:choose>
                        <xsl:when test="./return[@qualified!='void']">
                            <xsl:text> → </xsl:text>
                            <xsl:if test="./return/@qualified and string-length(./return/@qualified) > 0">
                                <xsl:copy-of select="my:className(./return/@qualified)"/>
                            </xsl:if>
                        </xsl:when>
                    </xsl:choose>
                </title>

                <xsl:choose>
                    <xsl:when test="comment and string-length(comment) > 0">
                        <para><xsl:value-of select='my:safeText(comment)'/></para>
                    </xsl:when>
                </xsl:choose>

                <!-- Process method parameters -->
                <xsl:if test="parameter">
                    <para><emphasis role="bold">Parameters:</emphasis></para>
                    <variablelist>
                        <xsl:apply-templates select="parameter"/>
                    </variablelist>
                </xsl:if>

                <!-- Process method return -->
                <xsl:apply-templates select="./return[@qualified!='void']"/>
            </section>
        </xsl:if>
    </xsl:template>

    <!-- Match param elements -->
    <xsl:template match="parameter">
        <xsl:variable name="paramName" select="@name"/>

        <xsl:if test="$paramName and string-length($paramName) > 0">
            <varlistentry>
                <term>
                    <xsl:if test="type/@qualified and string-length(type/@qualified) > 0">
                        <xsl:copy-of select="my:className(type/@qualified)"/>
                        <xsl:text> </xsl:text>
                    </xsl:if>
                    <emphasis role="bold"><xsl:value-of select="$paramName"/></emphasis>
                    <xsl:choose>
                        <xsl:when test="./return">
                            <xsl:text> → </xsl:text>
                            <xsl:if test="./return/@qualified and string-length(./return/@qualified) > 0">
                                <xsl:copy-of select="my:className(./return/@qualified)"/>
                            </xsl:if>
                        </xsl:when>
                    </xsl:choose>
                </term>
                <listitem>
                    <para>
                        <xsl:choose>
                            <xsl:when test="../tag[@name='@param' and starts-with(@text, concat($paramName, ' '))]">
                                <xsl:value-of select='my:safeText(../tag[@name="@param" and starts-with(@text, $paramName)]/@text)'/>
                            </xsl:when>
                        </xsl:choose>
                    </para>
                </listitem>
            </varlistentry>
        </xsl:if>
    </xsl:template>

    <!-- Match return elements -->
    <xsl:template match="return">
        <xsl:if test="@qualified and string-length(@qualified) > 0">
            <para>
                <emphasis role="bold">Returns:</emphasis>
                <xsl:copy-of select="my:className(@qualified)"/>
                <xsl:choose>
                    <xsl:when test="../tag[@name='@return']">
                        <xsl:text> - </xsl:text>
                        <xsl:value-of select='my:safeText(../tag[@name="@return"]/@text)'/>
                    </xsl:when>
                </xsl:choose>
            </para>
        </xsl:if>
    </xsl:template>

    <!-- Catch-all template for empty text nodes -->
    <xsl:template match="text()[not(normalize-space())]"/>

    <!-- Handle orphaned varlistentry elements by ignoring them -->
    <xsl:template match="varlistentry[not(parent::variablelist)]">
        <!-- Ignore orphaned varlistentry elements that aren't properly wrapped -->
    </xsl:template>

</xsl:stylesheet>