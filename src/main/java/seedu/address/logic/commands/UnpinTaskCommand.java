package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;

/**
 * Unpins a task.
 */

public class UnpinTaskCommand extends Command {
    public static final String COMMAND_WORD = "unpin_task";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Unpins the task identified by the index number used in the displayed task list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_UNPIN_TASK_SUCCESS = "Unpinned Task";
    public static final String MESSAGE_TASK_ALREADY_UNPINNED = "This task is not pinned to begin with.";

    private final Index targetIndex;

    public UnpinTaskCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    /**
     * Executes the command and returns the result message.
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display.
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();

        // verify if index is valid
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToUnpin = lastShownList.get(targetIndex.getZeroBased());
        // verify is task is not pinned to begin with
        if (!taskToUnpin.isPinned()) {
            throw new CommandException(MESSAGE_TASK_ALREADY_UNPINNED);
        }

        Task unpinnedTask = taskToUnpin.getCopy();
        unpinnedTask.unpin();

        //replace the old task with the new and pinned task, update
        model.setTask(taskToUnpin, unpinnedTask);
        model.sortTasksDefault();
        model.updateFilteredTaskList(Model.PREDICATE_SHOW_ALL_TASKS);

        return new CommandResult(MESSAGE_UNPIN_TASK_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UnpinTaskCommand // instanceof handles nulls
                && targetIndex.equals(((UnpinTaskCommand) other).targetIndex)); // state check
    }
}
