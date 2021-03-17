package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.alias.CommandAlias;

/**
 * Adds a command alias to the address book.
 */
public class AddAliasCommand extends AliasCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a command alias to the address book. "
            + "Parameters: " + ADD_SUB_COMMAND_WORD + " ALIAS COMMAND\n"
            + "Example: " + COMMAND_WORD + " " + ADD_SUB_COMMAND_WORD + " ls " + ListCommand.COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "New command alias added: %1$s";
    public static final String MESSAGE_DUPLICATE_ALIAS = "This alias already exists in the address book";

    private final CommandAlias commandAlias;

    /**
     * Creates an AddAliasCommand to add the specified {@code commandAlias}
     */
    public AddAliasCommand(CommandAlias commandAlias) {
        requireNonNull(commandAlias);
        this.commandAlias = commandAlias;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasCommandAlias(commandAlias)) {
            throw new CommandException(MESSAGE_DUPLICATE_ALIAS);
        }

        model.addAlias(commandAlias);
        return new CommandResult(String.format(MESSAGE_SUCCESS, commandAlias));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddAliasCommand // instanceof handles nulls
                && commandAlias.equals(((AddAliasCommand) other).commandAlias));
    }

}
