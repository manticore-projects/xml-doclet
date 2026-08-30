.. meta::
   :description: Java Doclet for writing XML, Restructured Text or Markdown from JavaDoc Source Comments
   :keywords: javadoc, doclet, xml, restructured text, sphinx, markdown

######################################
XMLDoclet
######################################

.. toctree::
    :maxdepth: 1
    :hidden:

    usage
    Java API <javadoc.rst>
    Changelog <changelog.md>

.. sidebar:: JavaDoc Website

	.. image:: _images/sample_rst.png

**XMLDoclet** is a Doclet for writing XML files from Java Source Documentation.
It comes with **XSL style sheets** to transform the XML file into various structured text formats and supports a **Floating Table of Content**.

Latest stable release: |XMLDOCLET_STABLE_VERSION_LINK|

Development version: |XMLDOCLET_SNAPSHOT_VERSION_LINK|

*******************************
Features
*******************************

    * Version 2.+ supports ``Java 17`` and later
    * Version 1.+ supports ``Java 11``
    * Maven/Gradle integration
    * Output Formats
        * XML
        * Restructured Text (.rst) for Sphinx/Docutils
        * Markdown (.md)
        * Docbook (.db.xml)
        * Ascii Doctor (.adoc)

*******************************
Examples
*******************************

**XMLDoclet** has been used to generate the API Documentation of the following sites:

  * `JSQLParser Java API <https://www.manticore-projects.com/JSQLParser/javadoc_snapshot.html>`_
  * `JSQLFormatterJava API <https://www.manticore-projects.com/JSQLFormatter/javadoc_snapshot.html>`_
  * `JDBCParquetWriter Java API <https://www.manticore-projects.com/JDBCParquetWriter/javadoc_snapshot.html>`_

..........................
Command Line Options (CLI)
..........................

--d <directory>                  Destination directory for output file [``.\``]

--docencoding <encoding>         Encoding of the output file [``UTF8``]

--dryrun                         Parse javadoc, but don't write output file [``false``]

--filename <filename>            Name of the output file [``javadoc.xml``]

--rst                            Write Restructured Text (.rst) that can be used with Sphinx [``false``]

--md                             Not implemented yet: Write Markdown (.md) [``false``]

--docbook                        Not implemented yet: Write DocBoook XML (.db.xml) [``false``]

--adoc                           Not implemented yet: Write Ascii Doctor (.adoc) [``false``]

--basePackage <name>             If set, shorten the Qualified Names by the Base Package name

--withFloatingToc                Integrate the Floating TOC (you need to add the CSS and JS to the CMS) [``false``]
