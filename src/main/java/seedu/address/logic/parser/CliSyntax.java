package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_ROLE = new Prefix("r/");
    public static final Prefix PREFIX_INDEX = new Prefix("i/");
    public static final Prefix PREFIX_DESCRIPTION = new Prefix("d/");
    public static final Prefix PREFIX_DEADLINE_DATE = new Prefix("by/");
    public static final Prefix PREFIX_EVENT_DATE = new Prefix("on/");
    public static final Prefix PREFIX_EVENT_TIME = new Prefix("at/");
    public static final Prefix PREFIX_EVENT_WEEKLY = new Prefix("w/");
    public static final Prefix PREFIX_MARK_TASK_INDEX = new Prefix("i/");
}
