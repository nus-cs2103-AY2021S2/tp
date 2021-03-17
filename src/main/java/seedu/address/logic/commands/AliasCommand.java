package seedu.address.logic.commands;

/**
 * Represents an alias command with hidden internal logic and the ability to be executed.
 */
public abstract class AliasCommand extends Command {

    public static final String COMMAND_WORD = "alias";
    public static final String ADD_SUB_COMMAND_WORD = "add";
    public static final String DELETE_SUB_COMMAND_WORD = "delete";
    public static final String LIST_SUB_COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Add or delete a command alias to the address book. "
            + "Parameters: "
            + "SUB_COMMAND [PARAMETERS]\n"
            + "Sub Commands: "
            + ADD_SUB_COMMAND_WORD + " ALIAS_NAME COMMAND_WORD, "
            + DELETE_SUB_COMMAND_WORD + " ALIAS_NAME, "
            + LIST_SUB_COMMAND_WORD + "\n"
            + "Example: "
            + COMMAND_WORD + " " + ADD_SUB_COMMAND_WORD + " ls " + ListCommand.COMMAND_WORD + ", "
            + COMMAND_WORD + " " + DELETE_SUB_COMMAND_WORD + " l, "
            + COMMAND_WORD + " " + LIST_SUB_COMMAND_WORD;

}
