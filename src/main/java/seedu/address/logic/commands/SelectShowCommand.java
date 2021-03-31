package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Lists all the selected persons that are selected by a user.
 */
public class SelectShowCommand extends SelectCommand {

    public static final String MESSAGE_SUCCESS = "Showing selected items";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.applySelectedPredicate();
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return (other instanceof SelectShowCommand);
    }
}
