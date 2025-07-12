package com.manticore.tools.xmldoclet;

import com.manticore.tools.xmldoclet.xjc.Class;
import com.manticore.tools.xmldoclet.xjc.Package;
import com.manticore.tools.xmldoclet.xjc.Root;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import static java.util.Arrays.stream;

/**
 * Base class for all tests.
 *
 * @author markus
 */
public abstract class AbstractTest {
    private final static Logger LOGGER = Logger.getLogger(AbstractTest.class.getName());

    protected static final String[] TEST_DIR = {"./src/test/java/"};
    protected static final String SIMPLE_DATA_PACKAGE = "com.manticore.tools.xmldoclet.simpledata";
    protected static final String SIMPLE_DATA_DIR = TEST_DIR[0] + SIMPLE_DATA_PACKAGE.replaceAll("\\.", "/");

    protected static final String[] ARGS = {"-dryrun"};
    protected static final String[] SUB_PACKAGES = {"com"};

    /**
     * Gets the first {@link Package} and {@link Class} from a JavaDoc {@link Root} element.
     *
     * @param sourceFileName Name of a source files inside the {@link #SIMPLE_DATA_DIR} to test.
     */
    public JavaDocElements newJavaDocElements(final String... sourceFileName) {
        final var sourceFiles = stream(sourceFileName).map(this::getFilePathFromSimpleDataDir).toArray(String[]::new);
        final var rootNode = new JavaDocCLI(sourceFiles, ARGS).execute();

        final var packageNode = rootNode.getPackage().get(0);
        final var classNode = packageNode.getClazz().isEmpty() ? null : packageNode.getClazz().get(0);
        return new JavaDocElements(rootNode, packageNode, classNode);
    }

    /**
     * Rigorous Parser :-)
     */
    @Test
    public void testSampledoc() {
        new JavaDocCLI(".", TEST_DIR, SUB_PACKAGES, ARGS).execute();
    }

    /**
     * Helper method to concat strings.
     *
     * @param glue the separator.
     * @param strings the strings to concat.
     * @return concatenated string
     */
    public static String join(final String glue, final String[] strings) {
        return join(glue, Arrays.asList(strings));
    }

    /**
     * Helper method to concat strings.
     *
     * @param glue the separator.
     * @param strings the strings to concat.
     * @return concatenated string
     */
    public static String join(final String glue, final List<String> strings) {
        if (strings == null) {
            return null;
        }

        return String.join(glue, strings);
    }


    /**
     * {@return the path (separated by /) to a source file in the {@link #SIMPLE_DATA_DIR}}
     *
     * @param sourceFileName the name of the file to get its full path from the {@link #SIMPLE_DATA_DIR}
     */
    protected String getFilePathFromSimpleDataDir(final String sourceFileName) {
        return join("/", List.of(SIMPLE_DATA_DIR, sourceFileName));
    }

    /**
     * {@return the full qualified name of a class, interface, record, enum or even some other element inside
     * those ones (separated by .) in the {@link #SIMPLE_DATA_PACKAGE}}
     * Those internal elements can be something such as fields, methods, constructors, etc.
     *
     * @param elementName the name of the element (class, interface, record, method, etc.) to get its
     *        full qualified name from the {@link #SIMPLE_DATA_PACKAGE}
     */
    protected static String getElementPathFromSimpleDataPackage(final String elementName) {
        return join(".", List.of(SIMPLE_DATA_PACKAGE, elementName));
    }

}
