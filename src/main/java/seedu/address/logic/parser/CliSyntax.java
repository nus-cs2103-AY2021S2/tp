package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions -> To be erased once our classes are finalised */
    //public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");

    /* Prefix definitions for Food */
    public static final Prefix PREFIX_DATE = new Prefix("d/");
    public static final Prefix PREFIX_DATE_FROM = new Prefix("df/");
    public static final Prefix PREFIX_DATE_TO = new Prefix("dt/");
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_CARBOS = new Prefix("c/");
    public static final Prefix PREFIX_FATS = new Prefix("f/");
    public static final Prefix PREFIX_PROTEINS = new Prefix("p/");
}
