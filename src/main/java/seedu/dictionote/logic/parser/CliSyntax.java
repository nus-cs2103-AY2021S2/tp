package seedu.dictionote.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");


    /* Option definitions for UI Command*/
    public static final Option OPTION_DICTIONARY_PANEL = new Option("d");
    public static final Option OPTION_DICTIONARY_CONTENT_PANEL = new Option("dc");
    public static final Option OPTION_DICTIONARY_LIST_PANEL = new Option("dl");
    public static final Option OPTION_NOTE_PANEL = new Option("-n");
    public static final Option OPTION_NOTE_CONTENT_PANEL = new Option("-nc");
    public static final Option OPTION_NOTE_LIST_PANEL = new Option("-nl");
    public static final Option OPTION_LIST_PANEL = new Option("-l");
    public static final Option OPTION_CONTENT_PANEL = new Option("-c");
    public static final Option OPTION_ALL_PANEL = new Option("-a");


}
