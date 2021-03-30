package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_DESCRIPTION = new Prefix("d/");
    public static final Prefix PREFIX_PLAN_NUMBER = new Prefix("p/");
    public static final Prefix PREFIX_SEM_NUMBER = new Prefix("s/");
    public static final Prefix PREFIX_MODULE_CODE = new Prefix("m/");
    public static final Prefix PREFIX_GRADE = new Prefix("g/");
}
