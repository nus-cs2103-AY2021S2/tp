package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    //=========== AddressBook ================================================================================
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");

    //=========== AppointmentSchedule ========================================================================
    public static final Prefix PREFIX_PATIENT = new Prefix("pt/");
    public static final Prefix PREFIX_DOCTOR = new Prefix("dr/");
    public static final Prefix PREFIX_TIMESLOT_START = new Prefix("at/");
    public static final Prefix PREFIX_TIMESLOT_END = new Prefix("to/");
    public static final Prefix PREFIX_TIMESLOT_DURATION = new Prefix("dur/");

}
