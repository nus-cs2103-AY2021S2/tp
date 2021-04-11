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

    public static final String MESSAGE_NO_UNCOMPLETED_TASKS = "There are no uncompleted tasks found!";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_UNCOMPLETED_TASKS);

        if (model.isTaskListEmpty()) {
            return new CommandResult(MESSAGE_NO_UNCOMPLETED_TASKS);
        }

        return new CommandResult(MESSAGE_SUCCESS);
    }
}
