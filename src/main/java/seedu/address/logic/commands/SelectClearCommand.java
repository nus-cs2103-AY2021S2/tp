package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Clears the selected persons that are selected by a user.
 */
public class SelectClearCommand extends SelectCommand {

    public static final String MESSAGE_SUCCESS = "Cleared selected persons";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.clearSelectedPersonList();
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return (other instanceof SelectClearCommand);
    }
}
