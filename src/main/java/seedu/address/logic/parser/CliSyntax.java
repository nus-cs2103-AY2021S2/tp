package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    // Prefix definitions for sorting keys and sorting order
    public static final Prefix PREFIX_SORTING_KEY = new Prefix("k/");
    public static final Prefix PREFIX_SORTING_ORDER = new Prefix("o/");

    // Prefix definitions for name, remark and tag
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_REMARK = new Prefix("r/");
    public static final Prefix PREFIX_TAGS = new Prefix("tags/");

    // Prefix definitions for property attributes
    public static final Prefix PREFIX_TYPE = new Prefix("t/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_POSTAL = new Prefix("p/");
    public static final Prefix PREFIX_DEADLINE = new Prefix("d/");

    // Prefix definitions for client attributes
    public static final Prefix PREFIX_CLIENT_NAME = new Prefix("cn/");
    public static final Prefix PREFIX_CLIENT_CONTACT = new Prefix("cc/");
    public static final Prefix PREFIX_CLIENT_EMAIL = new Prefix("ce/");
    public static final Prefix PREFIX_CLIENT_ASKING_PRICE = new Prefix("ca/");

    // Prefix definitions for appointment attributes
    public static final Prefix PREFIX_DATE = new Prefix("d/");
    public static final Prefix PREFIX_TIME = new Prefix("t/");

    // Placeholder prefixes for Person object below to handle errors
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
}
