package seedu.address.logic.parser;

/**
 * Contains Regex Format for Date recognition in Raw User Inputs
 */
public class TimeslotRegex {

    /* Timeslot Regex Format */
    //=========== Date ================================================================================
    public static final String dateSlashShort = "([0-9]{2})/([0-9]{2})/([0-9]{2})";
    public static final String dateSlashLong = "([0-9]{2})/([0-9]{2})/([0-9]{4})";
    public static final String dateDashShort = "([0-9]{2})-([0-9]{2})-([0-9]{2})";
    public static final String dateDashLong = "([0-9]{2})-([0-9]{2})-([0-9]{4})";
}
