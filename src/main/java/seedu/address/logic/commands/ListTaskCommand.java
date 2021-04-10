package seedu.address.logic.commands;
import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;

import seedu.address.model.Model;

/**
 * Lists all tasks in Sochedule to the user.
 */
public class ListTaskCommand extends Command {

    public static final String COMMAND_WORD = "list_task";

    public static final String MESSAGE_SUCCESS = "Listed all task(s).";

    public static final String MESSAGE_EMPTY = "There is no task present!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        return model.isTaskListEmpty() ? new CommandResult(MESSAGE_EMPTY) : new CommandResult(MESSAGE_SUCCESS);
    }
}
