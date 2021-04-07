package seedu.heymatez.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.heymatez.model.Model.PREDICATE_SHOW_ALL_UNCOMPLETED_TASKS;

import seedu.heymatez.logic.commands.exceptions.CommandException;
import seedu.heymatez.model.Model;

/**
 * Lists all uncompleted tasks in HeyMatez to the user.
 */
public class ViewUncompletedTasksCommand extends Command {

    public static final String COMMAND_WORD = "viewUncompletedTasks";

    public static final String MESSAGE_SUCCESS = "Listed all Uncompleted Tasks";

    public static final String MESSAGE_NO_UNCOMPLETED_TASKS = "There are no uncompleted tasks at the moment!";

    public static final String MESSAGE_EMPTY_TASK_LIST = "Tasks' list is empty, there are no tasks to be displayed! \n"
            + "Consider adding tasks to the list before using the 'viewUncompletedTasks' command.";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.getFilteredTaskList().isEmpty()) {
            throw new CommandException(MESSAGE_EMPTY_TASK_LIST);
        }
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_UNCOMPLETED_TASKS);
        if (model.getFilteredTaskList().isEmpty()) {
            throw new CommandException(MESSAGE_NO_UNCOMPLETED_TASKS);
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
