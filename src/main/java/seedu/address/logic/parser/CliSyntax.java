package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions for students */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_STUDY_LEVEL = new Prefix("l/");
    public static final Prefix PREFIX_GUARDIAN_PHONE = new Prefix("g/");
    public static final Prefix PREFIX_RELATIONSHIP = new Prefix("r/");

    /* Prefix definitions for sessions */
    public static final Prefix PREFIX_DATE = new Prefix("d/");
    public static final Prefix PREFIX_TIME = new Prefix("t/");
    public static final Prefix PREFIX_DURATION = new Prefix("k/");
    public static final Prefix PREFIX_SUBJECT = new Prefix("s/");
    public static final Prefix PREFIX_FEE = new Prefix("f/");

    public static final Prefix PREFIX_INDEX = new Prefix("i/");

}
