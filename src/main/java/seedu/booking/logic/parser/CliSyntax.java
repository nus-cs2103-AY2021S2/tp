package seedu.booking.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ORIGINAL_EMAIL = new Prefix("eo/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_CAPACITY = new Prefix("max/");

    public static final Prefix PREFIX_BOOKER = new Prefix("b/");
    public static final Prefix PREFIX_BOOKING_START = new Prefix("bs/");
    public static final Prefix PREFIX_BOOKING_END = new Prefix("be/");
    public static final Prefix PREFIX_BOOKING_ID = new Prefix("bid/");
    public static final Prefix PREFIX_DESCRIPTION = new Prefix("d/");
    public static final Prefix PREFIX_DATE = new Prefix("date/");
    public static final Prefix PREFIX_BOOKING_ORIGINAL = new Prefix("bido/");

    public static final Prefix PREFIX_VENUE = new Prefix("v/");
    public static final Prefix PREFIX_VENUE_ORIGINAL = new Prefix("vo/");

}
