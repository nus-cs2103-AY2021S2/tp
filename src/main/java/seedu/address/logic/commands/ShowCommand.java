package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.DeadlineDateInRangePredicate;

/**
 * Finds and lists all tasks in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class ShowCommand extends Command {

    public static final String COMMAND_WORD = "show";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all tasks whose date is "
            + "in range of the specified dates and displays them as a list with index numbers.\n"
            + "Parameters: start/START_DATE end/END_DATE\n"
            + "START_DATE and END_DATE should: \n 1.DD-MM-YYYY format,\n 2.it should exist on calendar, and\n"
            + " 3.The year is between 2020-2099\n"
            + "Example: " + COMMAND_WORD + " start/10-10-2020";

    private final DeadlineDateInRangePredicate predicate;

    public ShowCommand(DeadlineDateInRangePredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTaskList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_TASKS_LISTED_OVERVIEW, model.getFilteredTaskList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ShowCommand // instanceof handles nulls
                && predicate.equals(((ShowCommand) other).predicate)); // state check
    }
}
