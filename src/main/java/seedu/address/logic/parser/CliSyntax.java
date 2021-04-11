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
    public static final Prefix PREFIX_CARS_OWNED = new Prefix("c/");
    public static final Prefix PREFIX_DOB = new Prefix("b/");
    public static final Prefix PREFIX_CARS_PREFERRED = new Prefix("cp/");
    public static final Prefix PREFIX_AND = new Prefix("/AND");
    public static final Prefix PREFIX_OR = new Prefix("/OR");
    public static final Prefix PREFIX_NOT = new Prefix("/NOT");
    public static final Prefix PREFIX_COE_EXPIRY = new Prefix("coe/");
}
