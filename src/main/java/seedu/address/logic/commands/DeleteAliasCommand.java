package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.alias.Alias;
import seedu.address.model.alias.CommandAlias;

/**
 * Deletes a command alias in the address book.
 */
public class DeleteAliasCommand extends AliasCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes a command alias in the address book. "
            + "Parameters: " + DELETE_SUB_COMMAND_WORD + " ALIAS\n"
            + "Example: " + COMMAND_WORD + " " + DELETE_SUB_COMMAND_WORD + " ls " + ListCommand.COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Deleted command alias added: %1$s";
    public static final String MESSAGE_ALIAS_NOT_FOUND = "This alias does not exist in the address book";

    private final Alias targetAlias;

    public DeleteAliasCommand(Alias targetAlias) {
        this.targetAlias = targetAlias;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasAlias(targetAlias)) {
            throw new CommandException(MESSAGE_ALIAS_NOT_FOUND);
        }

        CommandAlias deletedCommandAlias = model.getCommandAlias(targetAlias);
        model.deleteAlias(targetAlias);
        return new CommandResult(String.format(MESSAGE_SUCCESS, deletedCommandAlias));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteAliasCommand // instanceof handles nulls
                && targetAlias.equals(((DeleteAliasCommand) other).targetAlias)); // state check
    }

}
