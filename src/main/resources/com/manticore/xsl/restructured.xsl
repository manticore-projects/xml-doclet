<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:my="http://manticore-projects.com/my" >

    <xsl:output
            method="xml"
            encoding="utf8"
            omit-xml-declaration="yes"
            indent="no" />

    <xsl:param name="basePackage" select="''" />
    <xsl:param name="doctitle" select="'Java API'" />
    <xsl:param name="withFloatingToc" select="'false'" />

    <xsl:function name="my:replacePRE">
        <xsl:param name="input"/>
        <xsl:variable name="content" select='replace($input, "&lt;pre&gt;\s*([^&lt;]*)\s*&lt;/pre&gt;", "$1")'  />
        <xsl:text>``</xsl:text><xsl:value-of select="normalize-space($content)" disable-output-escaping="yes"/><xsl:text>``</xsl:text>
    </xsl:function>

    <xsl:function name="my:replaceTags">
        <xsl:param name="input"/>
        <xsl:variable name="linkTag" select='replace($input, "\{@link [\s|\n]*([^\}]*[^\s])[\s|\n]*\}", "`$1`", "i")' />
        <xsl:variable name="preTag" select='replace($linkTag, "&lt;pre&gt;\s*([\s\S]*?)\s*&lt;/pre&gt;", "`$1`", "i")'  />
        <xsl:variable name="codeTag" select='replace($preTag, "\{@code [\s|\n]*([^\}]*[^\s])[\s|\n]*\}", "`$1`", "i")' />
        <xsl:variable name="codeTag1" select='replace($codeTag, "&lt;code&gt;\s*([\s\S]*?)\s*&lt;/code&gt;", "``$1``", "i")' />

        <xsl:variable name="normalized" select='replace($codeTag1, "\n\s*", " ", "i")' />
        <xsl:variable name="normalized1" select='replace($normalized, "&lt;p&gt;\s*([\s\S]*?)\s*&lt;/p&gt;", "&#xa;| $1", "i")' />
        <xsl:variable name="normalized2" select='replace($normalized1, "&lt;blockquote&gt;\s*([\s\S]*?)\s*&lt;/blockquote&gt;", "&#xa;| $1 &#xa;|", "i")'  />
        <xsl:variable name="normalized3" select='replace($normalized2, "&lt;p&gt;\s*", "&#xa;| ", "i")' />
        <xsl:variable name="normalized4" select='replace($normalized3, "&lt;br&gt;\s*", "&#xa;| ", "i")' />

        <xsl:variable name="anyTag" select='replace($normalized4, "&lt;\s*([\s\S]*?)\s*/?&gt;", "``$1``", "i")' />

        <xsl:value-of select="concat('| ', $anyTag)" disable-output-escaping="yes"/>

    </xsl:function>

    <xsl:function name="my:className">
        <xsl:param name="input"/>
        <xsl:choose>
            <xsl:when test="contains($input, '.')">
                <xsl:variable name="name" select="tokenize($input,'\.')[last()]" />
                <xsl:value-of select="concat(':ref:`', $name, '&lt;', $input, '&gt;`')" disable-output-escaping="yes"/>
            </xsl:when>
            <xsl:otherwise>
                <xsl:value-of select="$input" />
            </xsl:otherwise>
        </xsl:choose>
    </xsl:function>


    <!-- Match the root element -->
    <xsl:template match="/root">
        <xsl:if test="$withFloatingToc='true'" >
<xsl:text disable-output-escaping="yes">
.. raw:: html

    &lt;div id="floating-toc"&gt;
        &lt;div class="search-container"&gt;
            &lt;input type="button" id="toc-hide-show-btn"&gt;&lt;/input&gt;
            &lt;input type="text" id="toc-search" placeholder="Search" /&gt;
        &lt;/div&gt;
        &lt;ul id="toc-list"&gt;&lt;/ul&gt;
    &lt;/div&gt;


</xsl:text>
        </xsl:if>
        <xsl:text  disable-output-escaping="yes">
#######################################################################
</xsl:text><xsl:value-of select="$doctitle"/><xsl:text>
#######################################################################

</xsl:text>
        <xsl:choose>
<xsl:when test="string-length($basePackage)>0"><xsl:text>Base Package: </xsl:text><xsl:value-of select="$basePackage"/><xsl:text>

</xsl:text></xsl:when>
        </xsl:choose>

        <xsl:for-each select="package">
            <xsl:sort select="@name"/>
            <xsl:apply-templates select="."/>
        </xsl:for-each>
    </xsl:template>

    <!-- Match package elements -->
    <xsl:template match="package">
        <xsl:variable name="packageName" select="@name"/>
        <xsl:text  disable-output-escaping="yes">
..  _</xsl:text><xsl:value-of select="@name"/><xsl:text  disable-output-escaping="yes">:
***********************************************************************
</xsl:text><xsl:choose>
            <xsl:when test="$basePackage=''"><xsl:value-of select="@name"/></xsl:when>
            <xsl:when test="substring(@name, string-length(concat($basePackage, '.')))=''">Base</xsl:when>
            <xsl:otherwise><xsl:value-of select="substring(@name, string-length($basePackage)+2)"/></xsl:otherwise>
        </xsl:choose><xsl:text  disable-output-escaping="yes">
***********************************************************************
</xsl:text>

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

        <!-- Process classes in the interface -->
        <xsl:for-each select="interface">
            <xsl:sort select="@qualified"/>
            <xsl:apply-templates select="."/>
        </xsl:for-each>

    </xsl:template>

    <!-- Match interface elements -->
    <xsl:template match="interface">
        <xsl:variable name="interfaceName" select="@name"/>
        <xsl:variable name="qualifiedInterfaceName" select="@qualified"/>

        <!-- Generate reStructuredText heading for interface -->
<xsl:text  disable-output-escaping="yes">
..  _</xsl:text><xsl:value-of select="@qualified"/><xsl:text  disable-output-escaping="yes">:
=======================================================================
</xsl:text>
<xsl:value-of select="$interfaceName"/>
        <xsl:text  disable-output-escaping="yes">
=======================================================================

</xsl:text>

        <xsl:choose>
            <xsl:when test="interface">
                <xsl:text>*implements:* </xsl:text>
                <xsl:for-each select="interface">
                    <xsl:sort select="@name"/>
                    <xsl:value-of select="my:className(@qualified)" disable-output-escaping="yes"/>
                    <xsl:if test="position() != last()">
                        <xsl:text>, </xsl:text>
                    </xsl:if>
                </xsl:for-each>
                <xsl:text> </xsl:text>
            </xsl:when>
        </xsl:choose>

        <xsl:choose>
            <xsl:when test="//*[interface[@qualified=$qualifiedInterfaceName]]">
                <xsl:text>*provides:* </xsl:text>
                <xsl:for-each select="//*[interface[@qualified=$qualifiedInterfaceName]]">
                    <xsl:sort select="@name"/>
                    <xsl:value-of select="my:className(@qualified)" disable-output-escaping="yes"/>
                    <xsl:if test="position() != last()">
                        <xsl:text>, </xsl:text>
                    </xsl:if>
                </xsl:for-each>
                <xsl:text> </xsl:text>
            </xsl:when>
        </xsl:choose>
        <xsl:text>

</xsl:text>

        <xsl:choose>
            <xsl:when test="comment">
                <xsl:value-of select='my:replaceTags(comment)' disable-output-escaping="yes"/>
                <xsl:text>

</xsl:text>
            </xsl:when>
        </xsl:choose>

        <!-- Process methods in the interface -->
        <xsl:apply-templates select="method"/>
    </xsl:template>


    <!-- Match class elements -->
    <xsl:template match="class">
        <xsl:variable name="className" select="@name"/>
        <xsl:variable name="qualifiedClassName" select="@qualified"/>

        <!-- Generate reStructuredText heading for class -->
        <xsl:text  disable-output-escaping="yes">
..  _</xsl:text><xsl:value-of select="@qualified"/><xsl:text  disable-output-escaping="yes">:

=======================================================================
</xsl:text>
<xsl:value-of select="$className"/>
        <xsl:text  disable-output-escaping="yes">
=======================================================================

</xsl:text>

        <xsl:choose>
            <xsl:when test="class">
<xsl:text>*extends:* </xsl:text>
                    <xsl:value-of select="my:className(class/@qualified)" disable-output-escaping="yes"/>
                <xsl:text> </xsl:text>
            </xsl:when>
        </xsl:choose>

        <xsl:choose>
            <xsl:when test="interface">
<xsl:text>*implements:* </xsl:text>
                <xsl:for-each select="interface">
                    <xsl:sort select="@name"/>
                    <xsl:value-of select="my:className(@qualified)" disable-output-escaping="yes"/>
                    <xsl:if test="position() != last()">
                        <xsl:text>, </xsl:text>
                    </xsl:if>
                </xsl:for-each>
                <xsl:text> </xsl:text>
            </xsl:when>
        </xsl:choose>

        <xsl:choose>
            <xsl:when test="//class[class[@qualified=$qualifiedClassName]]">
                <xsl:text>*provides:* </xsl:text>
                <xsl:for-each select="//class[class[@qualified=$qualifiedClassName]]">
                    <xsl:sort select="@name"/>
                    <xsl:value-of select="my:className(@qualified)" disable-output-escaping="yes"/>
                    <xsl:if test="position() != last()">
                        <xsl:text>, </xsl:text>
                    </xsl:if>
                </xsl:for-each>
                <xsl:text> </xsl:text>
            </xsl:when>
        </xsl:choose>
    <xsl:text>

</xsl:text>

        <xsl:choose>
            <xsl:when test="comment">
                <xsl:value-of select='my:replaceTags(comment)' disable-output-escaping="yes"/>
                <xsl:text>

</xsl:text>
            </xsl:when>
        </xsl:choose>

        <!-- Process constructors -->
        <xsl:apply-templates select="constructor"/>

        <!-- Process methods -->
        <xsl:apply-templates select="method"/>
    </xsl:template>

    <!-- Match enum elements -->
    <xsl:template match="enum">
        <xsl:variable name="enumName" select="@name"/>
        <xsl:variable name="qualifiedEnumName" select="@qualified"/>

        <!-- Generate reStructuredText heading for class -->
        <xsl:text  disable-output-escaping="yes">
..  _</xsl:text><xsl:value-of select="@qualified"/><xsl:text  disable-output-escaping="yes">

=======================================================================
</xsl:text>
        <xsl:value-of select="$enumName"/><xsl:text disable-output-escaping="yes">
=======================================================================

</xsl:text>
<xsl:text>[</xsl:text>
        <xsl:for-each select="constant">
            <xsl:value-of select="@name"/>
            <xsl:if test="/comment">
                <xsl:text>:'</xsl:text>
                <xsl:value-of select="/comment"/><xsl:text>'</xsl:text>
            </xsl:if>
            <xsl:if test="position() != last()">
                <xsl:text>, </xsl:text>
            </xsl:if>
        </xsl:for-each>
<xsl:text>]

</xsl:text>
        <xsl:choose>
            <xsl:when test="comment">
                <xsl:value-of select='my:replaceTags(comment)' disable-output-escaping="yes"/>
                <xsl:text>

</xsl:text>
            </xsl:when>
        </xsl:choose>

    </xsl:template>

    <!-- Match constructor elements -->
    <xsl:template match="constructor[@scope='public']">
        <xsl:variable name="constructorName" select="@name"/>

        <!-- Generate reStructuredText heading for constructor -->
<xsl:text>| **</xsl:text><xsl:value-of select="$constructorName"/><xsl:text>** (</xsl:text>
        <xsl:choose>
            <xsl:when test="parameter">
                <xsl:for-each select="parameter">
                    <xsl:value-of select="@name"/>
                    <xsl:if test="position() != last()">
                        <xsl:text>, </xsl:text>
                    </xsl:if>
                </xsl:for-each>
            </xsl:when>
        </xsl:choose>
        <xsl:text>)</xsl:text>
        <xsl:text  disable-output-escaping="yes">
</xsl:text>

        <xsl:choose>
            <xsl:when test="comment">
                <xsl:value-of select='my:replaceTags(comment)' disable-output-escaping="yes"/>
                <xsl:text>
</xsl:text>
            </xsl:when>
        </xsl:choose>

        <!-- Process constructor parameters -->
        <xsl:apply-templates select="parameter"/>

<xsl:text>

</xsl:text>
    </xsl:template>

    <xsl:template match="method[@scope='public']">
        <xsl:variable name="methodName" select="@name"/>

        <xsl:choose>
            <xsl:when test="annotation">
                <xsl:for-each select="annotation">
<xsl:text>| *@</xsl:text>
                    <xsl:value-of select="@name"/>
                    <xsl:text>*</xsl:text>
                    <xsl:if test="position() != last()">
                        <xsl:text>,</xsl:text>
                    </xsl:if>
                </xsl:for-each><xsl:text>
</xsl:text>
            </xsl:when>
        </xsl:choose>

<xsl:text>| **</xsl:text><xsl:value-of select="$methodName"/><xsl:text>** (</xsl:text>
        <xsl:choose>
            <xsl:when test="parameter">
                <xsl:for-each select="parameter">
                    <xsl:value-of select="@name"/>
                    <xsl:if test="position() != last()">
                        <xsl:text>, </xsl:text>
                    </xsl:if>
                </xsl:for-each>
            </xsl:when>
        </xsl:choose>
        <xsl:text>)</xsl:text>
        <xsl:choose>
            <xsl:when test="./return[@qualified!='void']">
                <xsl:text> → </xsl:text>
                <xsl:value-of select="my:className(./return/@qualified)" disable-output-escaping="yes"/>
            </xsl:when>
        </xsl:choose>
        <xsl:text disable-output-escaping="yes">
</xsl:text>

        <xsl:choose>
            <xsl:when test="comment">
                <xsl:value-of select='my:replaceTags(comment)' disable-output-escaping="yes"/>
                <xsl:text>
</xsl:text>
            </xsl:when>
        </xsl:choose>

        <!-- Process method parameters -->
        <xsl:apply-templates select="parameter"/>

        <!-- Process method return -->
        <xsl:apply-templates select="./return[@qualified!='void']"/>

<xsl:text>

</xsl:text>
    </xsl:template>

    <!-- Match param elements -->
    <xsl:template match="parameter">
        <xsl:variable name="paramName" select="@name"/>

        <!-- Generate reStructuredText bullet point for parameter -->
        <xsl:text>|          </xsl:text>
        <xsl:value-of select="my:className(type/@qualified)" disable-output-escaping="yes"/>
        <xsl:text> </xsl:text>
        <xsl:value-of select="$paramName"/>
        <xsl:choose>
            <xsl:when test="./return">
                <xsl:text> </xsl:text>
                <xsl:value-of select="my:className(./return/@qualified)" disable-output-escaping="yes"/>
            </xsl:when>
        </xsl:choose>
        <xsl:choose>
            <xsl:when test="../tag[@name]='@param' and starts-with(../tag[@text], $paramName) ">
                <xsl:text> </xsl:text>
                <xsl:value-of select="@text"/>
            </xsl:when>
        </xsl:choose>
        <xsl:choose>
            <xsl:when test="../tag[@name='@param' and starts-with(@text, concat($paramName, ' '))]">
                <xsl:text>  </xsl:text>
                <xsl:value-of select='my:replaceTags(../tag[@name="@param" and starts-with(@text, $paramName)]/@text)'/>
            </xsl:when>
        </xsl:choose>
        <xsl:text>
</xsl:text>
    </xsl:template>

    <!-- Match return elements -->
    <xsl:template match="return">
        <!-- Generate reStructuredText bullet point for return -->
        <xsl:text>|          returns </xsl:text>
        <xsl:value-of select="my:className(@qualified)" disable-output-escaping="yes"/>
        <xsl:choose>
            <xsl:when test="../tag[@name]='@param'">
                <xsl:text> ― </xsl:text>
                <xsl:value-of select="desc"/>
            </xsl:when>
        </xsl:choose>
        <xsl:choose>
            <xsl:when test="../tag[@name='@return']">
                <xsl:text>  </xsl:text>
                <xsl:value-of select='my:replaceTags(../tag[@name="@return"]/@text)' />
            </xsl:when>
        </xsl:choose>
        <xsl:text>

</xsl:text>
    </xsl:template>

</xsl:stylesheet>
