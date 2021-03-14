package seedu.module.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    //need to change order of tags
    /*
    mapping:
    PREFIX_TASK_NAME -> PREFIX_TASK_NAME ("n/");
    PREFIX_EMAIL -> PREFIX_MODULE ("m/");
    PREFIX_ADDRESS -> PREFIX_DESCRIPTION ("d/");
    PREFIX_PHONE -> PREFIX_DEADLINE ("b/");
    PREFIX_TAG -> PREFIX_TAG("t/"); stays the same
     */
    /* Prefix definitions */
    public static final Prefix PREFIX_TASK_NAME = new Prefix("n/");
    public static final Prefix PREFIX_MODULE = new Prefix("m/");
    public static final Prefix PREFIX_DESCRIPTION = new Prefix("d/");
    public static final Prefix PREFIX_DEADLINE = new Prefix("b/");
    public static final Prefix PREFIX_WORKLOAD = new Prefix("w/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");


}
