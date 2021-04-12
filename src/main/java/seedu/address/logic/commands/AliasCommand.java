package seedu.address.logic.commands;

/**
 * Represents an alias command with hidden internal logic and the ability to be executed.
 */
public abstract class AliasCommand extends Command {

    public static final String COMMAND_WORD = "alias";
    public static final String ADD_SUB_COMMAND_WORD = "add";
    public static final String DELETE_SUB_COMMAND_WORD = "delete";
    public static final String LIST_SUB_COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Add, delete or list command alias(es) in the address book.\n"
            + "Parameters: "
            + "{ add | delete | list } [ALIAS] [COMMAND]\n"
            + "Sub Commands: "
            + ADD_SUB_COMMAND_WORD + " ALIAS COMMAND, "
            + DELETE_SUB_COMMAND_WORD + " ALIAS, "
            + LIST_SUB_COMMAND_WORD + "\n"
            + "Examples:\n"
            + COMMAND_WORD + " " + ADD_SUB_COMMAND_WORD + " ls " + ListCommand.COMMAND_WORD + "\n"
            + COMMAND_WORD + " " + DELETE_SUB_COMMAND_WORD + " ls\n"
            + COMMAND_WORD + " " + LIST_SUB_COMMAND_WORD;

}
