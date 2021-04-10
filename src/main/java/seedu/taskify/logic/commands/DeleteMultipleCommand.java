package seedu.taskify.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.taskify.logic.commands.util.DeleteUtil.deleteTasksFromModel;
import static seedu.taskify.logic.commands.util.DeleteUtil.generateSuccessMessage;
import static seedu.taskify.logic.commands.util.DeleteUtil.getTasksToDelete;

import java.util.List;
import java.util.Objects;

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

    public static final String MESSAGE_SWITCH_TO_HOME = "Switch back to home page to delete!";

    private final List<Index> targetIndexes;
    private final boolean isDeletingByRange;


    /**
     * Creates a DeleteMultipleCommand that will delete multiple tasks at once
     * @param targetIndexes the {@link Index}s of the tasks to delete
     * @param isDeletingByRange true if user's input was an index range, false if it was individual indices
     */
    public DeleteMultipleCommand(List<Index> targetIndexes, boolean isDeletingByRange) {
        requireNonNull(targetIndexes);
        this.targetIndexes = targetIndexes;
        this.isDeletingByRange = isDeletingByRange;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Task> lastShownList = model.getFilteredTaskList();
        List<Task> tasksToDelete;

        if (!CommandResult.isHomeTab()) {
            throw new CommandException(MESSAGE_SWITCH_TO_HOME);
        }

        tasksToDelete = getTasksToDelete(lastShownList, targetIndexes, isDeletingByRange);
        deleteTasksFromModel(model, tasksToDelete);
        return new CommandResult(generateSuccessMessage(tasksToDelete));
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
