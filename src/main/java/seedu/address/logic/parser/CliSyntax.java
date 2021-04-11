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
    public static final Prefix PREFIX_OPTION = new Prefix("o/");
    public static final Prefix PREFIX_CHILD = new Prefix("tc/");
    public static final Prefix PREFIX_DATE = new Prefix("d/");
    public static final Prefix PREFIX_CONTACT = new Prefix("c/");

    /* Options Definition for PREFIX_OPTION */
    public static final String OPTION_TAG = "tag";
    public static final String OPTION_NAME = "name";
    public static final String OPTION_DATE = "date";
    public static final String OPTION_ADDRESS = "address";
    public static final String OPTION_CONTACT = "contact";
    public static final String OPTION_PHONE = "phone";
    public static final String OPTION_EMAIL = "email";
    public static final String OPTION_FAVOURITE = "fav";
    public static final String OPTION_REMOVE = "remove";
    public static final String OPTION_CHILD = "child";
    public static final String OPTION_DARK = "dark";
    public static final String OPTION_LIGHT = "light";
    public static final String OPTION_REPLACE = "replace";

    /* Other definitions */
    public static final String PLACEHOLDER = "NIL";
}
