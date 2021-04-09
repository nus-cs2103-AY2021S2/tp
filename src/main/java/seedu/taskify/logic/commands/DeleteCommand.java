package seedu.taskify.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.taskify.commons.core.Messages;
import seedu.taskify.commons.core.index.Index;
import seedu.taskify.logic.commands.exceptions.CommandException;
import seedu.taskify.model.Model;
import seedu.taskify.model.task.Task;

/**
 * Deletes a task identified using it's displayed index from the app.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the task identified by the index number used in the displayed task list.\n\n"
            + "Parameters: INDEX (must be a positive integer)\n\n"
            + "Example: " + COMMAND_WORD + " 1\n\n"
            + "Note: Multiple tasks can also be deleted at once, by giving multiple indices, giving an "
            + "index range or by giving a status to delete all tasks with that status\n\n"
            + "Parameters for deleting multiple tasks:\n"
            + "   1. Listing multiple indices: INDEX [MORE_INDICES]\n"
            + "   2. Stating the range of indices: INDEX-INDEX\n"
            + "   3. Indicating the status of tasks to delete by: STATUS -all\n\n"
            + "View the User Guide for more information, at https://ay2021s2-cs2103t-w14-4.github.io/tp/UserGuide.html";



    public static final String MESSAGE_DELETE_TASK_SUCCESS = "Deleted Task: %1$s";
    public static final String MESSAGE_SWITCH_TO_HOME = "Switch back to home page to delete!";

    private final Index targetIndex;

    public DeleteCommand(Index targetIndex) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();

        if (!CommandResult.isHomeTab()) {
            throw new CommandException(MESSAGE_SWITCH_TO_HOME);
        }

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteTask(taskToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_TASK_SUCCESS, taskToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                       || (other instanceof DeleteCommand // instanceof handles nulls
                                   && targetIndex.equals(((DeleteCommand) other).targetIndex)); // state check
    }
}
