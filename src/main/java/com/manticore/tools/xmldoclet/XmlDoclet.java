package com.manticore.tools.xmldoclet;

import com.manticore.tools.xmldoclet.xjc.Root;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jdk.javadoc.doclet.Doclet;
import jdk.javadoc.doclet.DocletEnvironment;
import jdk.javadoc.doclet.Reporter;
import net.sf.saxon.s9api.*;

import javax.lang.model.SourceVersion;
import javax.tools.Diagnostic;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/// Doclet class.
///
/// - [Doclet](https://docs.oracle.com/en/java/javase/21/docs/api/jdk.javadoc/jdk/javadoc/doclet/package-summary.html)
/// - [Using the new JDK 9 Doclet API (refined in JDK 13)](https://openjdk.org/groups/compiler/using-new-doclet.html)
///
/// @author Markus Bernhardt
/// @author Andreas Reichel
/// @author Manoel Campos
public final class XmlDoclet implements Doclet {
    private static final Logger LOGGER = Logger.getLogger(XmlDoclet.class.getName());

    public static final String RESTRUCTURED_XSL = "/com/manticore/xsl/restructured.xsl";
    public static final String MARKDOWN_XSL = "/com/manticore/xsl/markdown.xsl";
    public static final String ASCII_DOC_XSL = "/com/manticore/xsl/adoc.xsl";
    public static final String DOCBOOK_XSL = "/com/manticore/xsl/docbook.xsl";

    /**
     * The parsed object model. Used in unit tests.
     */
    private static Root root;

    /**
     * Supported Doclet options.
     */
    private final SupportedOptions options;

    private Reporter reporter;

    public XmlDoclet() {
        try {
            this.options = new SupportedOptions();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to initialize XmlDoclet", e);

            // Classes that throw exceptions in their constructors are vulnerable to Finalizer attacks
            // A finalizer attack can be prevented, by declaring the class final, using an empty finalizer declared as final, or by a clever
            // use of a private constructor.
            throw new RuntimeException("Failed to initialize XmlDoclet", e);
        }
    }

    @Override
    public void init(final Locale locale, final Reporter reporter) {
        this.reporter = reporter;
    }

    @Override
    public String getName() {
        return getClass().getSimpleName();
    }

    @Override
    public Set<? extends CustomOption> getSupportedOptions() {
        return options.get();
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    /**
     * Processes the JavaDoc documentation. This method is required for all doclets.
     *
     * @see Doclet#run(DocletEnvironment)
     *
     * @param env the operating environment of a single invocation of the doclet
     *
     * @return <code>true</code> if processing was successful.
     */
    @Override
    public boolean run(final DocletEnvironment env) {
        // find org.glassfish.jaxb.runtime.v2.ContextFactory in the FAT Jar
        Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());

        root = new Parser(env).parseRootDoc();
        save(root);
        return true;
    }

    public static void transform(
            final InputStream xsltInputStream,
            final File xmlFile, final File outFile,
            final Map<String, String> parameters)
            throws IOException, SaxonApiException {
        try (var xmlInputStream = new FileInputStream(xmlFile); var output = new FileOutputStream(outFile)) {
            // Create a Saxon Processor
            final var processor = new Processor(false);

            // Create a DocumentBuilder
            final DocumentBuilder docBuilder = processor.newDocumentBuilder();

            // Parse the XML input
            final XdmNode xmlDoc = docBuilder.build(new StreamSource(xmlInputStream));

            // Create a XsltCompiler
            final XsltCompiler compiler = processor.newXsltCompiler();

            // Set the ClassLoader for the compiler to load resources from the classpath
            compiler.setResourceResolver(new ClasspathResourceURIResolver());

            // Create a XsltExecutable from the XSLT input stream
            final XsltExecutable xsltExecutable = compiler.compile(new StreamSource(xsltInputStream));
            final XsltTransformer transformer = xsltExecutable.load();

            // Set the source document
            transformer.setInitialContextNode(xmlDoc);

            // Set the result destination
            final var serializer = processor.newSerializer(output);
            transformer.setDestination(serializer);

            for (final Map.Entry<String, String> parameter : parameters.entrySet()) {
                if (parameter.getKey()!=null && !parameter.getKey().isEmpty()) {
                    final var name = new QName(parameter.getKey());
                    if (parameter.getValue() != null) {
                        final var values = new XdmAtomicValue(parameter.getValue());
                        transformer.setParameter(name, values);
                    } else {
                        LOGGER.log(Level.WARNING, "Parameter is null: " + name);
                    }
                }
            }

            // Transform the XML
            transformer.transform();
        }
    }

    /**
     * Save XML object model to a file via JAXB.
     *
     * @param root the document root
     */
    public void save(final Root root) {
        if (options.hasOption("dryrun")) {
            return;
        }

        final String filename = options.getOptionValue("filename", "javadoc.xml");

        final String basename = filename.toLowerCase().endsWith(".xml")
                ? filename.substring(0, filename.length() - ".xml".length())
                : filename;

        final File xmlFile = options.hasOption("d")
                ? new File(options.getOptionValue("d"), filename)
                : new File(filename);

        try (
                var fileOutputStream = new FileOutputStream(xmlFile);
                var bufferedOutputStream = new BufferedOutputStream(fileOutputStream)) {
            final var contextObj = JAXBContext.newInstance(Root.class);

            final var marshaller = contextObj.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            if (options.hasOption("docencoding")) {
                marshaller.setProperty(Marshaller.JAXB_ENCODING, options.getOptionValue("docencoding"));
            }

            marshaller.marshal(root, bufferedOutputStream);
            bufferedOutputStream.flush();
            fileOutputStream.flush();

            reporter.print(Diagnostic.Kind.NOTE, "Wrote XML to: " + xmlFile.getAbsolutePath());

            final var parameters = new HashMap<String, String>();
            for (final var option : options.get()) {
                final String optionValue = options.getOptionValue(option, "true");
                parameters.put(option.getParameters(), optionValue);
            }

            if (options.hasOption("rst")) {
                final var outFile = new File(xmlFile.getParent(), basename + ".rst");
                try (var inputStream = XmlDoclet.class.getResourceAsStream(RESTRUCTURED_XSL)) {
                    transform(inputStream, xmlFile, outFile, parameters);
                } catch (Exception ex) {
                    LOGGER.log(Level.SEVERE, "Failed to write Restructured Text", ex);
                }
                reporter.print(Diagnostic.Kind.NOTE, "Wrote Restructured Text to: " + outFile.getAbsolutePath());
            }

            if (options.hasOption("md")) {
                final var outFile = new File(xmlFile.getParent(), basename + ".md");
                try (var inputStream = XmlDoclet.class.getResourceAsStream(MARKDOWN_XSL);) {
                    transform(inputStream, xmlFile, outFile, parameters);
                } catch (Exception ex) {
                    LOGGER.log(Level.SEVERE, "Failed to write Markdown", ex);
                }
                reporter.print(Diagnostic.Kind.NOTE, "Wrote Markdown to: " + outFile.getAbsolutePath());
            }

            if (options.hasOption("docbook")) {
                final var outFile = new File(xmlFile.getParent(), basename + ".db.xml");
                try (var inputStream = XmlDoclet.class.getResourceAsStream(DOCBOOK_XSL);) {
                    transform(inputStream, xmlFile, outFile, parameters);
                } catch (Exception ex) {
                    LOGGER.log(Level.SEVERE, "Failed to write Docbook", ex);
                }
                reporter.print(Diagnostic.Kind.NOTE, "Wrote Docbook to: " + outFile.getAbsolutePath());
            }

            if (options.hasOption("adoc")) {
                final var outFile = new File(xmlFile.getParent(), basename + ".adoc");
                try (var inputStream = XmlDoclet.class.getResourceAsStream(ASCII_DOC_XSL);) {
                    transform(inputStream, xmlFile, outFile, parameters);
                } catch (Exception ex) {
                    LOGGER.log(Level.SEVERE, "Failed to write ASCII Doc", ex);
                }
                reporter.print(Diagnostic.Kind.NOTE, "Wrote ASCII Doc to: " + outFile.getAbsolutePath());
            }
        } catch (RuntimeException | IOException | JAXBException e) {
            LOGGER.log(Level.SEVERE, "Failed to write the XML File", e);
        }
    }

    public static Root getRoot() {
        return root;
    }
}
