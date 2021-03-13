package seedu.address.logic.commands;

/**
 * Represents an alias command with hidden internal logic and the ability to be executed.
 */
public abstract class AliasCommand extends Command {

    public static final String COMMAND_WORD = "alias";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Add or delete a command alias to the address book. "
            + "Parameters: "
            + "SUB_COMMAND [PARAMETERS]\n"
            + "Sub Commands: "
            + AddAliasCommand.SUB_COMMAND_WORD + " ALIAS_NAME COMMAND_WORD, "
            + DeleteAliasCommand.SUB_COMMAND_WORD + " ALIAS_NAME\n"
            + "Example: "
            + COMMAND_WORD + " " + AddAliasCommand.SUB_COMMAND_WORD + " ls " + ListCommand.COMMAND_WORD + ", "
            + COMMAND_WORD + " " + DeleteAliasCommand.SUB_COMMAND_WORD + " l";

}
