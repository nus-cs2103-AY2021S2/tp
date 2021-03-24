package dog.pawbook.logic.parser;

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

    /* For Dog */
    public static final Prefix PREFIX_BREED = new Prefix("b/");
    public static final Prefix PREFIX_OWNERID = new Prefix("o/");
    public static final Prefix PREFIX_DATEOFBIRTH = new Prefix("d/");
    public static final Prefix PREFIX_SEX = new Prefix("s/");

    /* For Program */
    public static final Prefix PREFIX_SESSION = new Prefix("s/");
    public static final Prefix PREFIX_DOGID = new Prefix("d/");
    public static final Prefix PREFIX_PROGRAMID = new Prefix("p/");

}
