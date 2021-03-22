package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_GENDER = new Prefix("g/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_COST = new Prefix("c/");

    /*
     * Subject prefix definitions
     * Reference: s/English r/50 l/Sec 3 y/5 q/A-Level s/Mathematics r/60 l/Sec 4 y/6 q/A-Level
     */
    public static final Prefix PREFIX_SUBJECT_NAME = new Prefix("s/");
    public static final Prefix PREFIX_RATE = new Prefix("r/");
    public static final Prefix PREFIX_EDUCATION_LEVEL = new Prefix("l/");
    public static final Prefix PREFIX_YEAR = new Prefix("y/");
    public static final Prefix PREFIX_QUALIFICATION = new Prefix("q/");

    /*
     * Appointment prefix definitions
     * Reference: n/NAME s/SUBJECT d/DATE fr/TIME_FROM to/TIME_TO [l/LOCATION]
     */
    public static final Prefix PREFIX_DATE = new Prefix("d/");
    public static final Prefix PREFIX_TIME_FROM = new Prefix("fr/");
    public static final Prefix PREFIX_TIME_TO = new Prefix("to/");
    public static final Prefix PREFIX_LOCATION = new Prefix("l/");
}
