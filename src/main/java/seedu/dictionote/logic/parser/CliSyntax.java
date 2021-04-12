package seedu.dictionote.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands.
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_CONTENT = new Prefix("c/");
    public static final Prefix PREFIX_WEEK = new Prefix("dt/");
    public static final Prefix PREFIX_HEADER = new Prefix("h/");
    public static final Prefix PREFIX_MAINCONTENT = new Prefix("mc/");
    public static final Prefix PREFIX_TERM = new Prefix("tm/");
    public static final Prefix PREFIX_DEFINITION = new Prefix("d/");
    public static final Prefix PREFIX_NOTE_INDEX = new Prefix("ni/");


    /* Option Syntax for UI Command*/
    public static final String OPTION_ALL_PANEL = "-a";
    public static final String OPTION_CONTACT_PANEL = "-c";
    public static final String OPTION_DICTIONARY_CONTENT_PANEL = "-dc";
    public static final String OPTION_DICTIONARY_LIST_PANEL = "-dl";
    public static final String OPTION_DICTIONARY_PANEL = "-d";
    public static final String OPTION_LIST_PANEL = "-l";
    public static final String OPTION_NOTE_CONTENT_PANEL = "-nc";
    public static final String OPTION_NOTE_LIST_PANEL = "-nl";
    public static final String OPTION_NOTE_PANEL = "-n";


}
