package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions for member*/
    public static final Prefix PREFIX_NEW_NAME = new Prefix("-n ");
    public static final Prefix PREFIX_PHONE = new Prefix("-p ");
    public static final Prefix PREFIX_EMAIL = new Prefix("-e ");
    public static final Prefix PREFIX_ROLE = new Prefix("-r ");
    /* Prefix definitions for task */
    public static final Prefix PREFIX_TITLE = new Prefix("-n ");
    public static final Prefix PREFIX_DESCRIPTION = new Prefix("-d ");
    public static final Prefix PREFIX_STATUS = new Prefix("-s ");
    public static final Prefix PREFIX_DEADLINE = new Prefix("-b ");

}
