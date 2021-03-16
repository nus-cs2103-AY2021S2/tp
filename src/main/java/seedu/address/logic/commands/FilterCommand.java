package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.DisplayFilterPredicate;
import seedu.address.model.Model;

/**
 * Sets the person list filter.
 */
public class FilterCommand extends Command {

    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_SUCCESS = "Applied filter";

    private final DisplayFilterPredicate filterPredicate;

    public FilterCommand(DisplayFilterPredicate filterPredicate) {
        this.filterPredicate = filterPredicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        model.updateDisplayFilter(filterPredicate);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterCommand) // instanceof handles nulls
                && filterPredicate.equals(((FilterCommand) other).filterPredicate);
    }
}
