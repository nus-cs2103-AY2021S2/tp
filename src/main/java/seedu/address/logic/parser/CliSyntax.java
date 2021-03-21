package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    // Prefix shared by Events and Tasks
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_CATEGORY = new Prefix("c/");

    // Prefix for Tasks
    public static final Prefix PREFIX_DEADLINE = new Prefix("d/");
    public static final Prefix PREFIX_PRIORITY = new Prefix("p/");

    // Prefix for Events
    public static final Prefix PREFIX_STARTDATE = new Prefix("sd/");
    public static final Prefix PREFIX_STARTTIME = new Prefix("st/");
    public static final Prefix PREFIX_ENDDATE = new Prefix("ed/");
    public static final Prefix PREFIX_ENDTIME = new Prefix("et/");

    // Prefix for Task and Event sorting
    public static final Prefix PREFIX_SORT = new Prefix("by/");
}
