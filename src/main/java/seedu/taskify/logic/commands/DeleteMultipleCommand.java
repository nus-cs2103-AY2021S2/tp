package seedu.taskify.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import seedu.taskify.commons.core.Messages;
import seedu.taskify.commons.core.index.Index;
import seedu.taskify.logic.commands.exceptions.CommandException;
import seedu.taskify.model.Model;
import seedu.taskify.model.task.Task;

/**
 * Deletes tasks (more than one) identified using it's displayed indexes from the app.
 */
public class DeleteMultipleCommand extends Command {

    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Delete multiple tasks identified by the index number used in the displayed task list.\n"
            + "Parameters: FIRST_INDEX SECOND_INDEX ...(must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1" + " 3" + " 5";
    private final List<Index> targetIndexes;


    public DeleteMultipleCommand(List<Index> targetIndexes) {
        this.targetIndexes = targetIndexes;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Task> lastShownList = model.getFilteredTaskList();
        List<Task> tasksToDelete = getTasksToDelete(lastShownList);
        deleteTasks(model, tasksToDelete);

        return new CommandResult(generateSuccessMessage(tasksToDelete));
    }


    private List<Task> getTasksToDelete(List<Task> tasksSource) throws CommandException {
        List<Task> tasksToDelete = new ArrayList<>();

        for (int i = 0; i < targetIndexes.size(); i++) {
            Index targetIndex = targetIndexes.get(i);
            if (targetIndex.getZeroBased() >= tasksSource.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
            }

            Task taskToDelete = tasksSource.get(targetIndex.getZeroBased());
            tasksToDelete.add(taskToDelete);
        }
        return tasksToDelete;
    }

    private void deleteTasks(Model model, List<Task> tasksToDelete) {
        for (Task toDelete : tasksToDelete) {
            model.deleteTask(toDelete);
        }
    }

    private String generateSuccessMessage(List<Task> tasksToDelete) {
        StringBuilder sb = new StringBuilder();
        sb.append("Deleted Tasks: ");
        for (Task toDelete : tasksToDelete) {
            sb.append(toDelete);
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o == null || getClass() != o.getClass()) {
            return false;
        } else {
            DeleteMultipleCommand cmd = (DeleteMultipleCommand) o;
            return targetIndexes.equals(cmd.targetIndexes);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(targetIndexes);
    }
}
