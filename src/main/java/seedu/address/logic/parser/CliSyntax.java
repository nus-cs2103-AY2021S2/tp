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
    public static final Prefix PREFIX_BIRTHDAY = new Prefix("b/");
    public static final Prefix PREFIX_PATTERN = new Prefix("p/");

    // Meetings
    public static final Prefix PREFIX_DATE = new Prefix("d/");
    public static final Prefix PREFIX_TIME = new Prefix("t/");
    public static final Prefix PREFIX_DESCRIPTION = new Prefix("desc/");

    // For commands that need a secondary index
    public static final Prefix PREFIX_INDEX = new Prefix("i/");

    // Groups
    public static final Prefix PREFIX_PERSONS = new Prefix("p/");

    // Goals
    public static final Prefix PREFIX_FREQUENCY = new Prefix("f/");

}
