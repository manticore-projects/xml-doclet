package com.manticore.tools.xmldoclet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.manticore.tools.xmldoclet.CustomOption.addHyphenPrefix;

/**
 * Defines the command line supported options for the XMLDoclet and parses the options given during Doclet execution.
 *
 * @author Manoel Campos
 */
final class SupportedOptions {
    /**
     * A Map where the key is an option name and the value is the argument value
     * (considering each argument has at most one value).
     * It stores all successfully parsed options given to the Doclet in the command line.
     */
    private final Map<String, String> givenCliOptionsMap = new HashMap<>();

    /**
     * Set of supported options.
     */
    private final Set<CustomOption> supportedOptionsSet;

    public SupportedOptions() {
        this.supportedOptionsSet = Set.of(
                newArgOption("d", "directory", "Destination directory for output file.\nDefault: ."),
                newArgOption("docencoding", "encoding", "Encoding of the output file.\nDefault: UTF8"),
                newNoArgOption("dryrun", "Parse javadoc, but don't write output file.\nDefault: false"),
                newNoArgOption("rst", "Transform the XML into a Restructured Text file (*.rst).\nDefault: false"),
                newNoArgOption("md", "Transform the XML into a Markdown file (*.md).\nDefault: false"),
                newNoArgOption("docbook", "Transform the XML into a DocBook file (*.db.xml).\nDefault: false"),
                newNoArgOption("adoc", "Transform the XML into an Ascii Doctor file (*.adoc).\nDefault: false"),
                newOneArgOption("filename", "Name of the output file.\nDefault: javadoc.xml"),
                newOneArgOption("basePackage", "Name of the base package.\n"),
                newOneArgOption("doctitle", "Document Title\n"),
                newOneArgOption("windowtitle", "Window Title\n"),
                newNoArgOption("noTimestamp", "No Timestamp.\n"),
                newNoArgOption("withFloatingToc", "Renders a Floating TOC on the right side.\n"),

                // Compatibility to standard JavaDoc options since there is no apparent way to filter/remove
                newNoArgOption("Xdoclint:none", "Compatibility only, will be ignored.\n"),
                newNoArgOption("Xdoclint:all", "Compatibility only, will be ignored.\n"),
                newNoArgOption("Xdoclint:html", "Compatibility only, will be ignored.\n"),
                newNoArgOption("Xdoclint:syntax", "Compatibility only, will be ignored.\n"),
                newNoArgOption("Xdoclint:reference", "Compatibility only, will be ignored.\n"),
                newNoArgOption("Xdoclint:missing", "Compatibility only, will be ignored.\n"),
                newNoArgOption("html5", "Compatibility only, will be ignored.\n"));
    }

    public Set<CustomOption> get() {
        return supportedOptionsSet;
    }

    /**
     * Creates an option with one argument that has the same name of the option itself.
     *
     * @param optionName name of the option and its own single argument
     * @param description option description
     */
    private CustomOption newOneArgOption(final String optionName, final String description) {
        return CustomOption.newOneArg(optionName, description, optionName, this::processFirstArgValue);
    }

    /**
     * Creates an option with one argument.
     *
     * @param optionName name of the option
     * @param argName name of the argument to be passed to the option, used in the help message
     * @param description option description
     */
    private CustomOption newArgOption(final String optionName, final String argName, final String description) {
        return CustomOption.newOneArg(optionName, description, argName, this::processFirstArgValue);
    }

    private CustomOption newNoArgOption(final String optionName, final String description) {
        return CustomOption.newNoArgs(optionName, description, (option, __) -> processNoArgValue(option));
    }

    /**
     * Process and stores name of the option in the {@link #givenCliOptionsMap} to indicate it was sucessfully
     * processed (since it has no arguments and no validation is required).
     *
     * @param optionName name of the option to store the argument value
     * @return true to indicate the option was successfully processed.
     */
    private boolean processNoArgValue(final String optionName) {
        storeParsedOption(optionName, null);
        return true;
    }

    /**
     * Process and stores the first argument value passed in the command line for a given option
     * when that option is being processed by {@link CustomOption#process(String, List)}.
     *
     * @param optionName name of the option to store the argument value
     * @param argValues list of arguments to get the first value to store
     * @return true if there was one argument value in the argument list, false otherwise, indicating no value was stored
     */
    private boolean processFirstArgValue(final String optionName, final List<String> argValues) {
        if (argValues.isEmpty()) {
            return false;
        }

        storeParsedOption(optionName, argValues.get(0));
        return true;
    }

    private void storeParsedOption(final String optionName, final String argValue) {
        givenCliOptionsMap.put(addHyphenPrefix(optionName), argValue);
    }

    public boolean hasOption(final String optionName) {
        return givenCliOptionsMap.containsKey(addHyphenPrefix(optionName));
    }

    public String getOptionValue(final CustomOption option) {
        return getOptionValue(option.getName());
    }

    public String getOptionValue(final String optionName) {
        return getOptionValue(optionName, "");
    }

    public String getOptionValue(final CustomOption option, final String defaultValue) {
        return getOptionValue(option.getName(), defaultValue);
    }

    public String getOptionValue(final String optionName, final String defaultValue) {
        return givenCliOptionsMap.getOrDefault(addHyphenPrefix(optionName), defaultValue);
    }

}
