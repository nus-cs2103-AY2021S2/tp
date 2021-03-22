package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    /* Customer */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");

    /* Order */
    public static final Prefix PREFIX_CHEESE_TYPE = new Prefix("t/");
    public static final Prefix PREFIX_QUANTITY = new Prefix("q/");
    public static final Prefix PREFIX_ORDER_DATE = new Prefix("d/");

    /* Cheese */
    public static final Prefix PREFIX_ASSIGNMENT_STATUS = new Prefix("s/");
    public static final Prefix PREFIX_MANUFACTURE_DATE = new Prefix("d/");
    public static final Prefix PREFIX_MATURITY_DATE = new Prefix("m/");
    public static final Prefix PREFIX_EXPIRY_DATE = new Prefix("e/");

}
