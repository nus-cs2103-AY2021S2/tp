package seedu.taskify.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.taskify.logic.commands.util.DeleteMultipleCommandUtil.MESSAGE_INVALID_TASK_FOR_INDEX_RANGE;
import static seedu.taskify.logic.commands.util.DeleteMultipleCommandUtil.MESSAGE_INVALID_TASK_FOR_INDICES;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import seedu.taskify.commons.core.index.Index;
import seedu.taskify.logic.commands.exceptions.CommandException;
import seedu.taskify.logic.commands.util.DeleteMultipleCommandUtil;
import seedu.taskify.model.Model;
import seedu.taskify.model.task.Status;
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

    public static final String MESSAGE_SWITCH_TO_HOME = "Switch back to home page to delete!";

    private final List<Index> targetIndexes;
    private final Optional<Status> statusOfTasksToDelete;
    private final boolean toDeleteByStatus;
    private final boolean isDeletingByRange;


    /**
     * Creates a DeleteMultipleCommand that will delete multiple tasks at once
     * @param targetIndexes the {@link Index}s of the tasks to delete
     * @param isDeletingByRange true if user's input was an index range, false if it was individual indices
     */
    public DeleteMultipleCommand(List<Index> targetIndexes, boolean isDeletingByRange) {
        requireNonNull(targetIndexes);
        this.targetIndexes = targetIndexes;
        this.statusOfTasksToDelete = Optional.empty();
        this.toDeleteByStatus = false;
        this.isDeletingByRange = isDeletingByRange;
    }



    /**
     * Creates a DeleteMultipleCommand that will delete tasks based on their {@link Status}
     * @param statusOfTasksToDelete the {@code Status} of the tasks to be deleted
     */
    public DeleteMultipleCommand(Status statusOfTasksToDelete) {
        this.targetIndexes = new ArrayList<>();
        this.statusOfTasksToDelete = Optional.of(statusOfTasksToDelete);
        this.toDeleteByStatus = true;
        this.isDeletingByRange = false;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Task> lastShownList = model.getFilteredTaskList();
        List<Task> tasksToDelete;

        if (!CommandResult.isHomeTab()) {
            throw new CommandException(MESSAGE_SWITCH_TO_HOME);
        }

        if (toDeleteByStatus) {
            tasksToDelete = getTasksToDelete(statusOfTasksToDelete.get(), lastShownList);
        } else {
            tasksToDelete = getTasksToDelete(lastShownList);
        }

        if (tasksToDelete.isEmpty()) {
            throw new CommandException(DeleteMultipleCommandUtil.MESSAGE_NO_TASKS_OF_GIVEN_STATUS);
        }

        deleteTasks(model, tasksToDelete);
        return new CommandResult(generateSuccessMessage(tasksToDelete));
    }


    private List<Task> getTasksToDelete(List<Task> tasksSource) throws CommandException {
        List<Task> tasksToDelete = new ArrayList<>();

        // Checks if the index range does not correspond to at least one task in Taskify
        boolean isIndexReferringToInvalidTask =
                targetIndexes.get(targetIndexes.size() - 1).getOneBased() > tasksSource.size();

        if (isDeletingByRange && isIndexReferringToInvalidTask) {
            throw new CommandException(MESSAGE_INVALID_TASK_FOR_INDEX_RANGE);
        }

        for (Index targetIndex : targetIndexes) {
            if (targetIndex.getZeroBased() >= tasksSource.size()) {
                throw new CommandException(MESSAGE_INVALID_TASK_FOR_INDICES);
            }

            Task taskToDelete = tasksSource.get(targetIndex.getZeroBased());
            tasksToDelete.add(taskToDelete);
        }
        return tasksToDelete;
    }

    /**
     * Returns all tasks with the specified {@code Status}, that are to be deleted
     */
    private List<Task> getTasksToDelete(Status status, List<Task> tasksSource) {
        return tasksSource.stream().filter(task -> task.getStatus().equals(status)).collect(Collectors.toList());
    }

    private void deleteTasks(Model model, List<Task> tasksToDelete) {
        for (Task toDelete : tasksToDelete) {
            model.deleteTask(toDelete);
        }
    }

    private String generateSuccessMessage(List<Task> tasksToDelete) {
        StringBuilder sb = new StringBuilder();
        sb.append("Deleted Tasks: \n");
        for (Task toDelete : tasksToDelete) {
            sb.append(toDelete);
            sb.append("\n\n");
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
