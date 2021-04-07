package seedu.heymatez.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.heymatez.model.Model.PREDICATE_SHOW_ALL_TASKS;

import seedu.heymatez.logic.commands.exceptions.CommandException;
import seedu.heymatez.model.Model;

/**
 * Lists all tasks in HeyMatez to the user.
 */
public class ViewTasksCommand extends Command {

    public static final String COMMAND_WORD = "viewTasks";

    public static final String MESSAGE_SUCCESS = "Listed all Tasks!";

    public static final String MESSAGE_EMPTY_TASK_LIST = "Task list is empty. There are no tasks to be shown!";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        if (model.getFilteredTaskList().isEmpty()) {
            throw new CommandException(MESSAGE_EMPTY_TASK_LIST);
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
