package seedu.address.logic.parser;

/**
 * Contains Regex Format for Date recognition in Raw User Inputs
 */
public class TimeslotRegex {

    /* Timeslot Regex Format */
    //=========== Date ================================================================================
    public static final String DATE_SLASH_SHORT = "([0-9]{2})/([0-9]{2})/([0-9]{2})";
    public static final String DATE_SLASH_LONG = "([0-9]{2})/([0-9]{2})/([0-9]{4})";
    public static final String DATE_DASH_SHORT = "([0-9]{2})-([0-9]{2})-([0-9]{2})";
    public static final String DATE_DASH_LONG = "([0-9]{2})-([0-9]{2})-([0-9]{4})";
}
