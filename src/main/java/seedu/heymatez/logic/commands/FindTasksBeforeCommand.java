package seedu.heymatez.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.heymatez.commons.core.Messages.MESSAGE_EMPTY_TASK_LIST;
import static seedu.heymatez.commons.core.Messages.MESSAGE_TASKS_LISTED_OVERVIEW;

import seedu.heymatez.model.Model;
import seedu.heymatez.model.task.DeadlineBeforeDatePredicate;

/**
 * Finds and lists all tasks in HeyMatez with deadline before a specified date.
 */
public class FindTasksBeforeCommand extends Command {
    public static final String COMMAND_WORD = "findBefore";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds all tasks with deadline before the specified date "
            + " and displays them as a list with index numbers.\n "
            + "Parameters: DEADLINE \n"
            + "Example: " + COMMAND_WORD + " 2021-03-26";

    private final DeadlineBeforeDatePredicate predicate;

    public FindTasksBeforeCommand(DeadlineBeforeDatePredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (model.isTaskListEmpty()) {
            return new CommandResult(MESSAGE_EMPTY_TASK_LIST);
        }
        model.updateFilteredTaskList(predicate);
        return new CommandResult(
                String.format(MESSAGE_TASKS_LISTED_OVERVIEW, model.getFilteredTaskList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindTasksBeforeCommand // instanceof handles nulls
                && predicate.equals(((FindTasksBeforeCommand) other).predicate)); // state check
    }
}
