<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:my="http://manticore-projects.com/my" >

    <xsl:output
            method="text"
            encoding="utf8"
            omit-xml-declaration="yes"
            indent="no" />

    <xsl:param name="basePackage" select="''" />
    <xsl:param name="doctitle" select="'Java API'" />
    <xsl:param name="withFloatingToc" select="'false'" />

    <xsl:function name="my:replacePRE">
        <xsl:param name="input"/>
        <xsl:choose>
            <xsl:when test="$input and string-length($input) > 0">
                <xsl:variable name="content" select='replace($input, "&lt;pre&gt;\s*([^&lt;]*)\s*&lt;/pre&gt;", "$1")'  />
                <xsl:text>```</xsl:text><xsl:text>&#xa;</xsl:text><xsl:value-of select="normalize-space($content)"/><xsl:text>&#xa;</xsl:text><xsl:text>```</xsl:text>
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
                <!-- Handle {@code} blocks - detect if content should be inline or block -->
                <xsl:variable name="step1">
                    <xsl:analyze-string select="$input" regex="\{{@code\s*(.*?)\s*\}}" flags="s">
                        <xsl:matching-substring>
                            <xsl:variable name="codeContent" select="normalize-space(regex-group(1))"/>
                            <xsl:choose>
                                <xsl:when test="contains($codeContent, '&#xa;') or string-length($codeContent) > 60">
                                    <!-- Multi-line or very long code - use code block -->
                                    <xsl:text>&#xa;&#xa;```&#xa;</xsl:text>
                                    <xsl:value-of select="$codeContent"/>
                                    <xsl:text>&#xa;```&#xa;&#xa;</xsl:text>
                                </xsl:when>
                                <xsl:when test="string-length($codeContent) > 0">
                                    <!-- Short single-line code - use inline code -->
                                    <xsl:text>`</xsl:text>
                                    <xsl:value-of select="$codeContent"/>
                                    <xsl:text>`</xsl:text>
                                </xsl:when>
                                <xsl:otherwise>
                                    <!-- Empty code block - remove it -->
                                    <xsl:text></xsl:text>
                                </xsl:otherwise>
                            </xsl:choose>
                        </xsl:matching-substring>
                        <xsl:non-matching-substring>
                            <xsl:value-of select="."/>
                        </xsl:non-matching-substring>
                    </xsl:analyze-string>
                </xsl:variable>

                <!-- Handle {@link} tags -->
                <xsl:variable name="step2" select='replace($step1, "\{@link\s+([^}]+)\}", "`$1`")' />

                <!-- Handle HTML <pre> tags -->
                <xsl:variable name="preTag" select='replace($step2, "&lt;pre&gt;(.*?)&lt;/pre&gt;", "&#xa;&#xa;```&#xa;$1&#xa;```&#xa;&#xa;", "s")'  />

                <!-- Handle HTML <code> tags -->
                <xsl:variable name="htmlCodeTag" select='replace($preTag, "&lt;code&gt;(.*?)&lt;/code&gt;", "`$1`", "s")' />

                <!-- Handle paragraph tags -->
                <xsl:variable name="paragraphTag" select='replace($htmlCodeTag, "&lt;p&gt;(.*?)&lt;/p&gt;", "&#xa;&#xa;$1&#xa;&#xa;", "s")' />

                <!-- Handle blockquote tags -->
                <xsl:variable name="blockquoteTag" select='replace($paragraphTag, "&lt;blockquote&gt;(.*?)&lt;/blockquote&gt;", "&#xa;&#xa;&gt; $1&#xa;&#xa;", "s")'  />

                <!-- Handle standalone <p> and <br> tags -->
                <xsl:variable name="standaloneP" select='replace($blockquoteTag, "&lt;p&gt;", "&#xa;&#xa;")' />
                <xsl:variable name="lineBreaks" select='replace($standaloneP, "&lt;br\s*/?&gt;", "&#xa;")' />

                <!-- Remove any remaining HTML tags -->
                <xsl:variable name="cleanHtml" select='replace($lineBreaks, "&lt;[^&gt;]*&gt;", "")' />

                <!-- Clean up remaining JavaDoc artifacts -->
                <xsl:variable name="cleanJavaDoc" select='replace($cleanHtml, "\{@[^}]*\}", "")' />

                <!-- Fix formatting issues with commas and punctuation -->
                <xsl:variable name="fixCommas" select='replace($cleanJavaDoc, "\s*,\s*```[^`]*```\s*,\s*", " ")' />
                <xsl:variable name="fixExtraCommas" select='replace($fixCommas, "\s*,\s*`[^`]*`\s*,\s*", " `$1` ")' />

                <!-- Fix empty or malformed code blocks -->
                <xsl:variable name="fixEmptyCode" select='replace($fixExtraCommas, "```\s*```", "")' />
                <xsl:variable name="fixDanglingBraces" select='replace($fixEmptyCode, "^\s*\}\s*```\s*$", "", "m")' />
                <xsl:variable name="fixOrphanBraces" select='replace($fixDanglingBraces, "^\s*\}\s*$", "", "m")' />

                <!-- Normalize whitespace but preserve intended structure -->
                <xsl:variable name="normalizedSpaces" select='replace($fixOrphanBraces, "[ \t]+", " ")' />
                <xsl:variable name="cleanLines" select='replace($normalizedSpaces, "\n\s*\n\s*\n+", "&#xa;&#xa;")' />
                <xsl:variable name="trimmed" select='replace($cleanLines, "^\s+|\s+$", "")' />

                <xsl:value-of select="$trimmed"/>
            </xsl:when>
            <xsl:otherwise>
                <xsl:text/>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:function>

    <xsl:function name="my:className">
        <xsl:param name="input"/>
        <xsl:param name="context"/>
        <xsl:choose>
            <xsl:when test="$input and string-length($input) > 0 and contains($input, '.')">
                <!-- Extract the base class name before any generic type parameters -->
                <xsl:variable name="baseType" select="if (contains($input, '&lt;')) then substring-before($input, '&lt;') else $input" />
                <xsl:variable name="name" select="tokenize($baseType,'\.')[last()]" />
                <!-- Extract generic part if it exists -->
                <xsl:variable name="genericPart" select="if (contains($input, '&lt;')) then substring-after($input, '&lt;') else ''" />

                <!-- Check if this class exists in the current document -->
                <xsl:variable name="localClass" select="$context//class[@qualified=$baseType] | $context//interface[@qualified=$baseType] | $context//enum[@qualified=$baseType]" />

                <xsl:choose>
                    <xsl:when test="$genericPart != ''">
                        <!-- Handle generic types: show display name with generics -->
                        <xsl:variable name="displayName" select="concat($name, '&lt;', substring-before($genericPart, '&gt;'), '&gt;')" />
                        <xsl:choose>
                            <xsl:when test="$localClass">
                                <!-- Link to local class using qualified name as anchor -->
                                <xsl:value-of select="concat('[', $displayName, '](#', $baseType, ')')"/>
                            </xsl:when>
                            <xsl:otherwise>
                                <!-- External class - just show as text -->
                                <xsl:value-of select="concat('`', $displayName, '`')"/>
                            </xsl:otherwise>
                        </xsl:choose>
                    </xsl:when>
                    <xsl:otherwise>
                        <xsl:choose>
                            <xsl:when test="$localClass">
                                <!-- Link to local class using qualified name as anchor -->
                                <xsl:value-of select="concat('[', $name, '](#', $baseType, ')')"/>
                            </xsl:when>
                            <xsl:otherwise>
                                <!-- External class - just show as text -->
                                <xsl:value-of select="concat('`', $name, '`')"/>
                            </xsl:otherwise>
                        </xsl:choose>
                    </xsl:otherwise>
                </xsl:choose>
            </xsl:when>
            <xsl:when test="$input and string-length($input) > 0">
                <xsl:value-of select="concat('`', $input, '`')" />
            </xsl:when>
            <xsl:otherwise>
                <xsl:text/>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:function>

    <!-- Match the root element -->
    <xsl:template match="/root">
        <xsl:if test="$withFloatingToc='true'" >
            <xsl:text>&lt;!-- Table of Contents --&gt;&#xa;</xsl:text>
            <xsl:text>&lt;div id="floating-toc"&gt;&#xa;</xsl:text>
            <xsl:text>&lt;div class="search-container"&gt;&#xa;</xsl:text>
            <xsl:text>&lt;input type="button" id="toc-hide-show-btn"&gt;&lt;/input&gt;&#xa;</xsl:text>
            <xsl:text>&lt;input type="text" id="toc-search" placeholder="Search" /&gt;&#xa;</xsl:text>
            <xsl:text>&lt;/div&gt;&#xa;</xsl:text>
            <xsl:text>&lt;ul id="toc-list"&gt;&lt;/ul&gt;&#xa;</xsl:text>
            <xsl:text>&lt;/div&gt;&#xa;&#xa;</xsl:text>
        </xsl:if>

        <xsl:text># </xsl:text><xsl:value-of select="$doctitle"/><xsl:text>&#xa;&#xa;</xsl:text>

        <xsl:choose>
            <xsl:when test="string-length($basePackage)>0">
                <xsl:text>**Base Package:** </xsl:text><xsl:value-of select="$basePackage"/><xsl:text>&#xa;&#xa;</xsl:text>
            </xsl:when>
        </xsl:choose>

        <xsl:for-each select="package">
            <xsl:sort select="@name"/>
            <xsl:apply-templates select="."/>
        </xsl:for-each>
    </xsl:template>

    <!-- Match package elements -->
    <xsl:template match="package">
        <xsl:variable name="packageName" select="@name"/>
        <xsl:if test="$packageName and string-length($packageName) > 0">
            <xsl:text>&lt;a id="</xsl:text><xsl:value-of select="@name"/><xsl:text>"&gt;&lt;/a&gt;&#xa;</xsl:text>
            <xsl:text>## </xsl:text>
            <xsl:choose>
                <xsl:when test="$basePackage=''"><xsl:value-of select="@name"/></xsl:when>
                <xsl:when test="substring(@name, string-length(concat($basePackage, '.')))=''">Base</xsl:when>
                <xsl:otherwise><xsl:value-of select="substring(@name, string-length($basePackage)+2)"/></xsl:otherwise>
            </xsl:choose>
            <xsl:text>&#xa;&#xa;</xsl:text>

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
        </xsl:if>
    </xsl:template>

    <!-- Match interface elements -->
    <xsl:template match="interface">
        <xsl:variable name="interfaceName" select="@name"/>
        <xsl:variable name="qualifiedInterfaceName" select="@qualified"/>

        <xsl:if test="$interfaceName and string-length($interfaceName) > 0">
            <xsl:text>&lt;a id="</xsl:text><xsl:value-of select="@qualified"/><xsl:text>"&gt;&lt;/a&gt;&#xa;</xsl:text>
            <xsl:text>### </xsl:text><xsl:value-of select="$interfaceName"/><xsl:text>&#xa;&#xa;</xsl:text>

            <xsl:choose>
                <xsl:when test="interface">
                    <xsl:text>**Implements:** </xsl:text>
                    <xsl:for-each select="interface">
                        <xsl:sort select="@name"/>
                        <xsl:if test="@qualified and string-length(@qualified) > 0">
                            <xsl:value-of select="my:className(@qualified, root())"/>
                            <xsl:if test="position() != last()">
                                <xsl:text>, </xsl:text>
                            </xsl:if>
                        </xsl:if>
                    </xsl:for-each>
                    <xsl:text>&#xa;&#xa;</xsl:text>
                </xsl:when>
            </xsl:choose>

            <xsl:choose>
                <xsl:when test="//*[interface[@qualified=$qualifiedInterfaceName]]">
                    <xsl:text>**Provides:** </xsl:text>
                    <xsl:for-each select="//*[interface[@qualified=$qualifiedInterfaceName]]">
                        <xsl:sort select="@name"/>
                        <xsl:if test="@qualified and string-length(@qualified) > 0">
                            <xsl:value-of select="my:className(@qualified, root())"/>
                            <xsl:if test="position() != last()">
                                <xsl:text>, </xsl:text>
                            </xsl:if>
                        </xsl:if>
                    </xsl:for-each>
                    <xsl:text>&#xa;&#xa;</xsl:text>
                </xsl:when>
            </xsl:choose>

            <xsl:choose>
                <xsl:when test="comment and string-length(comment) > 0">
                    <xsl:value-of select='my:replaceTags(comment)'/>
                    <xsl:text>&#xa;&#xa;</xsl:text>
                </xsl:when>
            </xsl:choose>

            <!-- Process methods in the interface -->
            <xsl:apply-templates select="method"/>
        </xsl:if>
    </xsl:template>

    <!-- Match class elements -->
    <xsl:template match="class">
        <xsl:variable name="className" select="@name"/>
        <xsl:variable name="qualifiedClassName" select="@qualified"/>

        <xsl:if test="$className and string-length($className) > 0">
            <xsl:text>&lt;a id="</xsl:text><xsl:value-of select="@qualified"/><xsl:text>"&gt;&lt;/a&gt;&#xa;</xsl:text>
            <xsl:text>### </xsl:text><xsl:value-of select="$className"/><xsl:text>&#xa;&#xa;</xsl:text>

            <xsl:choose>
                <xsl:when test="class">
                    <xsl:text>**Extends:** </xsl:text>
                    <xsl:if test="class/@qualified and string-length(class/@qualified) > 0">
                        <xsl:value-of select="my:className(class/@qualified, root())"/>
                    </xsl:if>
                    <xsl:text>&#xa;&#xa;</xsl:text>
                </xsl:when>
            </xsl:choose>

            <xsl:choose>
                <xsl:when test="interface">
                    <xsl:text>**Implements:** </xsl:text>
                    <xsl:for-each select="interface">
                        <xsl:sort select="@name"/>
                        <xsl:if test="@qualified and string-length(@qualified) > 0">
                            <xsl:value-of select="my:className(@qualified, root())"/>
                            <xsl:if test="position() != last()">
                                <xsl:text>, </xsl:text>
                            </xsl:if>
                        </xsl:if>
                    </xsl:for-each>
                    <xsl:text>&#xa;&#xa;</xsl:text>
                </xsl:when>
            </xsl:choose>

            <xsl:choose>
                <xsl:when test="//class[class[@qualified=$qualifiedClassName]]">
                    <xsl:text>**Provides:** </xsl:text>
                    <xsl:for-each select="//class[class[@qualified=$qualifiedClassName]]">
                        <xsl:sort select="@name"/>
                        <xsl:if test="@qualified and string-length(@qualified) > 0">
                            <xsl:value-of select="my:className(@qualified, root())"/>
                            <xsl:if test="position() != last()">
                                <xsl:text>, </xsl:text>
                            </xsl:if>
                        </xsl:if>
                    </xsl:for-each>
                    <xsl:text>&#xa;&#xa;</xsl:text>
                </xsl:when>
            </xsl:choose>

            <xsl:choose>
                <xsl:when test="comment and string-length(comment) > 0">
                    <xsl:value-of select='my:replaceTags(comment)'/>
                    <xsl:text>&#xa;&#xa;</xsl:text>
                </xsl:when>
            </xsl:choose>

            <!-- Process constructors -->
            <xsl:apply-templates select="constructor"/>

            <!-- Process methods -->
            <xsl:apply-templates select="method"/>
        </xsl:if>
    </xsl:template>

    <!-- Match enum elements -->
    <xsl:template match="enum">
        <xsl:variable name="enumName" select="@name"/>
        <xsl:variable name="qualifiedEnumName" select="@qualified"/>

        <xsl:if test="$enumName and string-length($enumName) > 0">
            <xsl:text>&lt;a id="</xsl:text><xsl:value-of select="@qualified"/><xsl:text>"&gt;&lt;/a&gt;&#xa;</xsl:text>
            <xsl:text>### </xsl:text><xsl:value-of select="$enumName"/><xsl:text>&#xa;&#xa;</xsl:text>
            <xsl:text>**Values:** </xsl:text>
            <xsl:for-each select="constant">
                <xsl:if test="@name and string-length(@name) > 0">
                    <xsl:text>`</xsl:text><xsl:value-of select="@name"/><xsl:text>`</xsl:text>
                    <xsl:if test="comment and string-length(comment) > 0">
                        <xsl:text> (</xsl:text>
                        <xsl:value-of select="comment"/><xsl:text>)</xsl:text>
                    </xsl:if>
                    <xsl:if test="position() != last()">
                        <xsl:text>, </xsl:text>
                    </xsl:if>
                </xsl:if>
            </xsl:for-each>
            <xsl:text>&#xa;&#xa;</xsl:text>

            <xsl:choose>
                <xsl:when test="comment and string-length(comment) > 0">
                    <xsl:value-of select='my:replaceTags(comment)'/>
                    <xsl:text>&#xa;&#xa;</xsl:text>
                </xsl:when>
            </xsl:choose>
        </xsl:if>
    </xsl:template>

    <!-- Match constructor elements -->
    <xsl:template match="constructor[@scope='public']">
        <xsl:variable name="constructorName" select="@name"/>

        <xsl:if test="$constructorName and string-length($constructorName) > 0">
            <xsl:text>#### </xsl:text><xsl:value-of select="$constructorName"/><xsl:text>(</xsl:text>
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
            <xsl:text>)&#xa;&#xa;</xsl:text>

            <xsl:choose>
                <xsl:when test="comment and string-length(comment) > 0">
                    <xsl:value-of select='my:replaceTags(comment)'/>
                    <xsl:text>&#xa;&#xa;</xsl:text>
                </xsl:when>
            </xsl:choose>

            <!-- Process constructor parameters -->
            <xsl:if test="parameter">
                <xsl:text>**Parameters:**&#xa;&#xa;</xsl:text>
                <xsl:apply-templates select="parameter"/>
            </xsl:if>

            <xsl:text>&#xa;</xsl:text>
        </xsl:if>
    </xsl:template>

    <xsl:template match="method[@scope='public']">
        <xsl:variable name="methodName" select="@name"/>

        <xsl:if test="$methodName and string-length($methodName) > 0">
            <xsl:choose>
                <xsl:when test="annotation">
                    <xsl:for-each select="annotation">
                        <xsl:text>*@</xsl:text>
                        <xsl:if test="@name and string-length(@name) > 0">
                            <xsl:value-of select="@name"/>
                        </xsl:if>
                        <xsl:text>*</xsl:text>
                        <xsl:if test="position() != last()">
                            <xsl:text>, </xsl:text>
                        </xsl:if>
                    </xsl:for-each>
                    <xsl:text>&#xa;</xsl:text>
                </xsl:when>
            </xsl:choose>

            <xsl:text>#### </xsl:text><xsl:value-of select="$methodName"/><xsl:text>(</xsl:text>
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
            <xsl:text>)</xsl:text>
            <xsl:choose>
                <xsl:when test="./return[@qualified!='void']">
                    <xsl:text> → </xsl:text>
                    <xsl:if test="./return/@qualified and string-length(./return/@qualified) > 0">
                        <xsl:value-of select="my:className(./return/@qualified, root())"/>
                    </xsl:if>
                </xsl:when>
            </xsl:choose>
            <xsl:text>&#xa;&#xa;</xsl:text>

            <xsl:choose>
                <xsl:when test="comment and string-length(comment) > 0">
                    <xsl:value-of select='my:replaceTags(comment)'/>
                    <xsl:text>&#xa;&#xa;</xsl:text>
                </xsl:when>
            </xsl:choose>

            <!-- Process method parameters -->
            <xsl:if test="parameter">
                <xsl:text>**Parameters:**&#xa;&#xa;</xsl:text>
                <xsl:apply-templates select="parameter"/>
            </xsl:if>

            <!-- Process method return -->
            <xsl:apply-templates select="./return[@qualified!='void']"/>

            <xsl:text>&#xa;</xsl:text>
        </xsl:if>
    </xsl:template>

    <!-- Match param elements -->
    <xsl:template match="parameter">
        <xsl:variable name="paramName" select="@name"/>

        <xsl:if test="$paramName and string-length($paramName) > 0">
            <xsl:text>- </xsl:text>
            <xsl:if test="type/@qualified and string-length(type/@qualified) > 0">
                <xsl:value-of select="my:className(type/@qualified, root())"/>
                <xsl:text> </xsl:text>
            </xsl:if>
            <xsl:text>**</xsl:text><xsl:value-of select="$paramName"/><xsl:text>**</xsl:text>
            <xsl:choose>
                <xsl:when test="./return">
                    <xsl:text> → </xsl:text>
                    <xsl:if test="./return/@qualified and string-length(./return/@qualified) > 0">
                        <xsl:value-of select="my:className(./return/@qualified, root())"/>
                    </xsl:if>
                </xsl:when>
            </xsl:choose>
            <xsl:choose>
                <xsl:when test="../tag[@name='@param' and starts-with(@text, concat($paramName, ' '))]">
                    <xsl:text> - </xsl:text>
                    <xsl:value-of select='my:replaceTags(../tag[@name="@param" and starts-with(@text, concat($paramName, " "))]/@text)'/>
                </xsl:when>
            </xsl:choose>
            <xsl:text>&#xa;</xsl:text>
        </xsl:if>
    </xsl:template>

    <!-- Match return elements -->
    <xsl:template match="return">
        <xsl:if test="@qualified and string-length(@qualified) > 0">
            <xsl:text>**Returns:** </xsl:text>
            <xsl:value-of select="my:className(@qualified, root())"/>
            <xsl:choose>
                <xsl:when test="../tag[@name='@return']">
                    <xsl:text> - </xsl:text>
                    <xsl:value-of select='my:replaceTags(../tag[@name="@return"]/@text)' />
                </xsl:when>
            </xsl:choose>
            <xsl:text>&#xa;&#xa;</xsl:text>
        </xsl:if>
    </xsl:template>

    <!-- Catch-all template for empty text nodes -->
    <xsl:template match="text()[not(normalize-space())]"/>

</xsl:stylesheet>