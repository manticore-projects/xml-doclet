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
                <xsl:variable name="linkTag" select='replace($input, "\{@link [\s|\n]*([^\}]*[^\s])[\s|\n]*\}", "`$1`", "i")' />
                <xsl:variable name="preTag" select='replace($linkTag, "&lt;pre&gt;\s*([\s\S]*?)\s*&lt;/pre&gt;", "&#xa;&#xa;```&#xa;$1&#xa;```&#xa;&#xa;", "i")'  />
                <xsl:variable name="codeTag" select='replace($preTag, "\{@code [\s|\n]*([^\}]*[^\s])[\s|\n]*\}", "`$1`", "i")' />
                <xsl:variable name="codeTag1" select='replace($codeTag, "&lt;code&gt;\s*([\s\S]*?)\s*&lt;/code&gt;", "`$1`", "i")' />

                <xsl:variable name="normalized" select='replace($codeTag1, "\n\s*", " ", "i")' />
                <xsl:variable name="normalized1" select='replace($normalized, "&lt;p&gt;\s*([\s\S]*?)\s*&lt;/p&gt;", "&#xa;&#xa;$1&#xa;&#xa;", "i")' />
                <xsl:variable name="normalized2" select='replace($normalized1, "&lt;blockquote&gt;\s*([\s\S]*?)\s*&lt;/blockquote&gt;", "&#xa;&#xa;&gt; $1&#xa;&#xa;", "i")'  />
                <xsl:variable name="normalized3" select='replace($normalized2, "&lt;p&gt;\s*", "&#xa;&#xa;", "i")' />
                <xsl:variable name="normalized4" select='replace($normalized3, "&lt;br&gt;\s*", "&#xa;", "i")' />

                <xsl:variable name="anyTag" select='replace($normalized4, "&lt;\s*([\s\S]*?)\s*/?&gt;", "", "i")' />

                <xsl:value-of select="normalize-space($anyTag)"/>
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
                <xsl:value-of select="concat('[', $name, '](#', $input, ')')"/>
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
                            <xsl:value-of select="my:className(@qualified)"/>
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
                            <xsl:value-of select="my:className(@qualified)"/>
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
                        <xsl:value-of select="my:className(class/@qualified)"/>
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
                            <xsl:value-of select="my:className(@qualified)"/>
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
                            <xsl:value-of select="my:className(@qualified)"/>
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
                        <xsl:value-of select="my:className(./return/@qualified)"/>
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
                <xsl:value-of select="my:className(type/@qualified)"/>
                <xsl:text> </xsl:text>
            </xsl:if>
            <xsl:text>**</xsl:text><xsl:value-of select="$paramName"/><xsl:text>**</xsl:text>
            <xsl:choose>
                <xsl:when test="./return">
                    <xsl:text> → </xsl:text>
                    <xsl:if test="./return/@qualified and string-length(./return/@qualified) > 0">
                        <xsl:value-of select="my:className(./return/@qualified)"/>
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
            <xsl:value-of select="my:className(@qualified)"/>
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