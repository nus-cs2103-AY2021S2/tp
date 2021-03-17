package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_TITLE = new Prefix("n/");
    public static final Prefix PREFIX_DEADLINE = new Prefix("set/");
    public static final Prefix PREFIX_RECURRINGSCHEDULE = new Prefix("r/");
    public static final Prefix PREFIX_DESCRIPTION = new Prefix("d/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_STATUS = new Prefix("st/");
    public static final Prefix PREFIX_STARTTIME = new Prefix("s/");

}
