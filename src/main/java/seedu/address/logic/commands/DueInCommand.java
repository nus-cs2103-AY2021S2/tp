package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.DeadlineDateInRangePredicate;

/**
 * Finds and lists all tasks in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class DueInCommand extends Command {

    public static final String COMMAND_WORD = "dueIn";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all tasks whose date is "
            + "in range of the specified days or weeks.\n"
            + "Parameters: day/NUMBER_OF_DAYS OR week/NUMBER_OF_WEEKS\n"
            + "NUMBER_OF_DAYS and NUMBER_OF_WEEKS must be a positive integer.\n"
            + "Example: " + COMMAND_WORD + " day/10\n"
            + "If no parameters specified, the command will show assignments within the next 7 days.\n"
            + "If multiple parameters specified, an error will be thrown.\n"
            + "The limit for dueIn is until 31-12-2099";

    private final DeadlineDateInRangePredicate predicate;

    public DueInCommand(DeadlineDateInRangePredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTaskList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_TASKS_LISTED_OVERVIEW, model.getFilteredTaskList().size())
                        + predicate.toString());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DueInCommand // instanceof handles nulls
                && predicate.equals(((DueInCommand) other).predicate)); // state check
    }

    @Override
    public String toString() {
        return "DUEIN";
    }
}
