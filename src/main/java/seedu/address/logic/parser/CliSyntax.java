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

    public static Prefix getPrefixName() {
        return PREFIX_NAME;
    }

    public static Prefix getPrefixPhone() {
        return PREFIX_PHONE;
    }

    public static Prefix getPrefixBooking() {
        return PREFIX_BOOKING;
    }

    public static Prefix getPrefixResidenceAddress() {
        return PREFIX_RESIDENCE_ADDRESS;
    }

    public static Prefix getPrefixCleanStatusTag() {
        return PREFIX_CLEAN_STATUS_TAG;
    }

    public static Prefix getPrefixTag() {
        return PREFIX_TAG;
    }

    public static Prefix getPrefixBookingStartDate() {
        return PREFIX_BOOKING_START_DATE;
    }

    public static Prefix getPrefixBookingEndDate() {
        return PREFIX_BOOKING_END_DATE;
    }

    public static Prefix getPrefixResidence() {
        return PREFIX_RESIDENCE;
    }
}
