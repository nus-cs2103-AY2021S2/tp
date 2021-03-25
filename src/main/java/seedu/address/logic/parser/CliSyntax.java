package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_CODE = new Prefix("mc/");
    public static final Prefix PREFIX_WEIGHTAGE = new Prefix("w/");
    public static final Prefix PREFIX_DEADLINE_DATE = new Prefix("d/");
    public static final Prefix PREFIX_DEADLINE_TIME = new Prefix("t/");
    public static final Prefix PREFIX_REMARK = new Prefix("r/");
    public static final Prefix PREFIX_TAG = new Prefix("pt/");
    public static final Prefix PREFIX_PRIORITYTAG = new Prefix("ptag/");

}
