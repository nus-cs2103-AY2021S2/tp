package seedu.address.logic.parser;

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
    public static final Prefix PREFIX_OPTION = new Prefix("o/");
    public static final Prefix PREFIX_CHILD = new Prefix("tc/");
    public static final Prefix PREFIX_DATE = new Prefix("d/");
    public static final Prefix PREFIX_CONTACT = new Prefix("c/");

    /* Options Definition for PREFIX_OPTION */
    public static final Option OPTION_TAG = new Option("tag");
    public static final Option OPTION_NAME = new Option("name");
    public static final Option OPTION_DATE = new Option("date");
    public static final Option OPTION_ADDRESS = new Option("address");

}
