package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple
 * commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_YEAR = new Prefix("y/");
    public static final Prefix PREFIX_ROOM = new Prefix("r/");

    public static final Prefix PREFIX_ROOM_NUMBER = new Prefix("r/");
    public static final Prefix PREFIX_ROOM_TYPE = new Prefix("t/");
    public static final Prefix PREFIX_ROOM_OCCUPANCY_STATUS = new Prefix("o/");
    public static final Prefix PREFIX_ROOM_TAG = new Prefix("g/");

    public static final Prefix PREFIX_DESCRIPTION = new Prefix("d/");
    public static final Prefix PREFIX_TIMESTAMP = new Prefix("t/");
    public static final Prefix PREFIX_STATUS = new Prefix("s/");
    public static final Prefix PREFIX_CATEGORY = new Prefix("c/");
    public static final Prefix PREFIX_TAG = new Prefix("g/");

    public static final Prefix PREFIX_ALIAS = new Prefix("a/");
    public static final Prefix PREFIX_COMMAND = new Prefix("cmd/");
}
