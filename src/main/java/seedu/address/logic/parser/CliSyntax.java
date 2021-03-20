package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_TAG = new Prefix("tag/");
    public static final Prefix PREFIX_TRIPDAY = new Prefix("d/");
    public static final Prefix PREFIX_TRIPTIME = new Prefix("t/");
    public static final Prefix PREFIX_COMMUTER = new Prefix("c/");
    public static final Prefix PREFIX_PRICE = new Prefix("pr/");

    /**
     * Prevents CliSyntax from being instantiated.
     */
    private CliSyntax() {}
}
