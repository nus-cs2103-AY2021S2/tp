package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_BOOKING = new Prefix("b/");
    public static final Prefix PREFIX_RESIDENCE_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_CLEAN_STATUS_TAG = new Prefix("c/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_BOOKING_START_DATE = new Prefix("s/");
    public static final Prefix PREFIX_BOOKING_END_DATE = new Prefix("e/");
    public static final Prefix PREFIX_RESIDENCE = new Prefix("r/");
}
