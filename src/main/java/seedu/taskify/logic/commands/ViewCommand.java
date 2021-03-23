package seedu.taskify.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.taskify.commons.core.Messages;
import seedu.taskify.model.Model;
import seedu.taskify.model.task.predicates.TaskHasSameDatePredicate;

/**
 * Finds and lists all tasks in Taskify with the same specified LocalDateTime.
 */
public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all tasks with the same date as the "
            + "specified date and displays them as a list with index numbers.\n"
            + "Parameters: DATE[yyyy-mm-dd][today][tomorrow]\n"
            + "Example: " + COMMAND_WORD + " 2021-05-21" + " | "
            + COMMAND_WORD + " today" + " | " + COMMAND_WORD + " tomorrow";

    private final TaskHasSameDatePredicate predicate;

    public ViewCommand(TaskHasSameDatePredicate predicate) {
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
                || (other instanceof ViewCommand // instanceof handles nulls
                && predicate.equals(((ViewCommand) other).predicate)); // state check
    }
}
