package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.TaskDeadlineIsTodayPredicate;

public class TodayTaskCommand extends Command {
    public static final String COMMAND_WORD = "today_task";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all tasks whose deadline is today "
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_TODAY_TASK_SUCCESS = "Listed tasks that have deadline on today\n";

    private final TaskDeadlineIsTodayPredicate predicate;

    public TodayTaskCommand() {
        this.predicate = new TaskDeadlineIsTodayPredicate();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredTaskList(predicate);
        return new CommandResult(MESSAGE_TODAY_TASK_SUCCESS
                + String.format(Messages.MESSAGE_TASKS_LISTED_OVERVIEW, model.getFilteredTaskList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TodayTaskCommand // instanceof handles nulls
                && predicate.equals(((TodayTaskCommand) other).predicate)); // state check
    }
}

