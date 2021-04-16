package seedu.address.testutil;

import seedu.address.logic.commands.AddAliasCommand;
import seedu.address.model.alias.CommandAlias;

/**
 * A utility class for CommandAlias.
 */
public class CommandAliasUtil {

    /**
     * Returns an add alias command string for adding the {@code commandAlias}.
     */
    public static String getAddAliasCommand(CommandAlias commandAlias) {
        return AddAliasCommand.COMMAND_WORD + " " + AddAliasCommand.ADD_SUB_COMMAND_WORD + " "
                + getCommandAliasDetails(commandAlias);
    }

    /**
     * Returns the part of command string for the given {@code commandAlias}'s details.
     */
    public static String getCommandAliasDetails(CommandAlias commandAlias) {
        return commandAlias.getAlias() + " " + commandAlias.getCommand();
    }

}
