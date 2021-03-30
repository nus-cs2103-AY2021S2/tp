package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;

/**
 * Deletes a task identified using it's displayed index from the planner.
 */
public class DeleteTaskCommand extends Command {

    public static final String COMMAND_WORD = "rmt";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes a task from the planner.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String SHORT_MESSAGE_USAGE = COMMAND_WORD + " "
            + "INDEX (must be a positive integer)\n";

    public static final String MESSAGE_DELETE_TASK_SUCCESS = "Deleted Task: %1$s";

    private final Index targetIndex;

    //@@author mesyeux
    /**
     * @param targetIndex of the task in the filtered task list to delete
     */
    public DeleteTaskCommand(Index targetIndex) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
    }
    //@@author

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();

        int targetIndexValue = targetIndex.getZeroBased();
        boolean isInvalidIndex = targetIndexValue >= lastShownList.size();

        if (isInvalidIndex) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToDelete = lastShownList.get(targetIndexValue);
        taskToDelete.getTags().forEach(model::deleteTag);
        model.deleteTask(taskToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_TASK_SUCCESS, taskToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                //@@author mesyeux
                || (other instanceof DeleteTaskCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteTaskCommand) other).targetIndex)); // state check
                //@@author
    }
}
