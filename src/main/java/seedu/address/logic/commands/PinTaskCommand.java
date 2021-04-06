package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;

/**
 * Pins a task.
 */

public class PinTaskCommand extends Command {
    public static final String COMMAND_WORD = "pin_task";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Pins the task identified by the index number used in the displayed task list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_PIN_TASK_SUCCESS = "Pinned Task";
    public static final String MESSAGE_TASK_ALREADY_PINNED = "This task is already pinned.";

    private final Index targetIndex;

    public PinTaskCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    /**
     * Creates and returns a {@code Task} with the same details of {@code taskToPin},
     * but pinnedStatus as pinned.
     *
     * @param taskToPin task to pin.
     * @return a pinned task.
     */
    private static Task createPinnedTask(Task taskToPin) {
        assert taskToPin != null;

        Task pinnedTask = taskToPin.getCopy();
        pinnedTask.pin();
        return pinnedTask;
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

        Task taskToPin = lastShownList.get(targetIndex.getZeroBased());
        // verify is task is already pinned
        if (taskToPin.isPinned()) {
            throw new CommandException(MESSAGE_TASK_ALREADY_PINNED);
        }

        Task pinnedTask = createPinnedTask(taskToPin);

        //replace the old task with the new and pinned task, update
        model.setTask(taskToPin, pinnedTask);
        model.sortTasksDefault();
        model.updateFilteredTaskList(Model.PREDICATE_SHOW_ALL_TASKS);

        return new CommandResult(MESSAGE_PIN_TASK_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PinTaskCommand // instanceof handles nulls
                && targetIndex.equals(((PinTaskCommand) other).targetIndex)); // state check
    }
}
