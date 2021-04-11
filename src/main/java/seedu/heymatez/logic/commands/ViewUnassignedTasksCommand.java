package seedu.heymatez.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.heymatez.model.Model.PREDICATE_SHOW_ALL_UNASSIGNED_TASKS;

import seedu.heymatez.logic.commands.exceptions.CommandException;
import seedu.heymatez.model.Model;

/**
 * Lists all unassigned tasks in HeyMatez to the user.
 */
public class ViewUnassignedTasksCommand extends Command {

    public static final String COMMAND_WORD = "viewUnassignedTasks";

    public static final String MESSAGE_SUCCESS = "Listed all Unassigned Tasks.";

    public static final String MESSAGE_NO_UNASSIGNED_TASKS = "There are no unassigned tasks found!";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_UNASSIGNED_TASKS);

        if (model.isTaskListEmpty()) {
            return new CommandResult(MESSAGE_NO_UNASSIGNED_TASKS);
        }

        return new CommandResult(MESSAGE_SUCCESS);
    }
}
