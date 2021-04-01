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
    public static final Prefix PREFIX_GENDER = new Prefix("g/");
    public static final Prefix PREFIX_AGE = new Prefix("age/");
    public static final Prefix PREFIX_BIRTHDATE = new Prefix("b/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_MEETING = new Prefix("m/");
    public static final Prefix PREFIX_INSURANCE = new Prefix("i/");
    public static final Prefix PREFIX_NOTE_RECORD = new Prefix("r/");
    public static final Prefix PREFIX_NOTE_VIEW = new Prefix("v/");
    public static final Prefix PREFIX_CLEAR = new Prefix("c/");
}
