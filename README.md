# A doclet to output javadoc as XML [![Maven Central Version](https://img.shields.io/maven-central/v/com.manticore-projects.tools/xml-doclet)](https://central.sonatype.com/search?q=xml-doclet&namespace=com.manticore-projects.tools)

[Website](https://www.manticore-projects.com/XMLDoclet) | [Documentation](https://www.manticore-projects.com/XMLDoclet/usage.html) | [Example](https://www.manticore-projects.com/JSQLParser/javadoc_snapshot.html#analyticexpression)

This library provides a doclet to output the javadoc comments from Java source code to a XML or a Restructured Text (*.rst) document.

All modern JDKs 17, 21 and 23 are supported by XmlDoclet-2.+ and JDK 11 is supported by XMLDoc-1.4.+ only.

Planned support for Markdown (*.md), Docbook XML and ASCII Doctor (*.adoc). Sponsors or Contributors are most welcome.

Example
-------

See the [JSQLParser Java API](https://www.manticore-projects.com/JSQLParser/javadoc_snapshot.html#arrayexpression)

Gradle
------

```gradle
repositories {
    // Sonatype OSSRH
    maven {
        url = uri('https://s01.oss.sonatype.org/content/repositories/snapshots/')
    }
}
configurations {
    xmlDoclet
}
dependencies {
    xmlDoclet 'com.manticore-projects.tools:xml-doclet:+'
}
tasks.register('xmldoc', Javadoc) {
    // optionally include some extra sources, e.g. generated source files
    // include = '..'
    
    source = sourceSets.main.allJava
    
    // beware, that this folder will be overwritten hard by Gradle
    destinationDir = reporting.file("xmlDoclet")
    options.docletpath = configurations.xmlDoclet.files.asType(List)
    
    // for XmlDoclet-2.+ and JDK17+
    options.doclet = "com.manticore.tools.xmldoclet.XmlDoclet"
    
    // for XmlDoclet-1.+ and JDK11+
    // options.doclet = "com.github.markusbernhardt.xmldoclet.XmlDoclet"

    // transform to Restructured Text and copy to Sphinx Source folder
    options.addBooleanOption("rst", true)
    options.addStringOption("basePackage", "net.sf.jsqlparser")

    doLast {
        copy {
            from reporting.file("xmlDoclet/javadoc.rst")
            into "${projectDir}/src/site/sphinx/"
        }
    }
}
```

Maven
-----

If you are using maven you can use this library by adding the following report to your pom.xml:

```xml
<repositories>
    <repository>
        <id>jsqlparser-snapshots</id>
        <snapshots>
            <enabled>true</enabled>
        </snapshots>
        <url>https://oss.sonatype.org/content/groups/public/</url>
    </repository>
</repositories>
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-javadoc-plugin</artifactId>
    <executions>
        <execution>
            <id>xml-doclet</id>
        <phase>prepare-package</phase>
            <goals>
                <goal>javadoc</goal>
            </goals>
            <configuration>
                <doclet>com.manticore.tools.xmldoclet.XmlDoclet</doclet>
                <additionalparam>-d "${project.build.directory}" -filename ${project.artifactId}-${project.version}-javadoc.xml</additionalparam>
                <useStandardDocletOptions>false</useStandardDocletOptions>
                <docletArtifact>
                    <groupId>com.manticore-projects.tools</groupId>
                    <artifactId>xml-doclet</artifactId>
                    <version>2.0.0</version>
                </docletArtifact>
            </configuration>
        </execution>
    </executions>
</plugin>
```

Options
-------

    -d <directory>            Destination directory for output file.
                              Default: .

    -docencoding <encoding>   Encoding of the output file.
                              Default: UTF8

    -dryrun                   Parse javadoc, but don't write output file.
                              Default: false

    -filename <filename>      Name of the output file.
                              Default: javadoc.xml

    -rst                      Write Restructured Text (*.rst) that can be used with Sphinx
                              Default: false

    -md                       Not implemented yet: Write Markdown (*.md)
                              Default: false

    -docbook                  Not implemented yet: Write DocBoook XML (*.db.xml)
                              Default: false

    -adoc                     Not implemented yet: Write Ascii Doctor (*.adoc)
                              Default: false

    -basePackage <name>       Shortens the Qualified Names by the Base Package name
