package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Description;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskStatus;
import seedu.address.model.task.Title;



/**
 * Changes the status of existing task in HEY MATEz to uncompleted
 */
public class UndoTaskCommand extends Command {
    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Changes the status of the task to uncompleted"
            + "by the index number used in the displayed task board. \n"
            + "Parameters: INDEX (must be a positive integer) "
            + "Example: " + COMMAND_WORD + " 1 ";


    public static final String MESSAGE_UNDO_TASK_SUCCESS = "Task: marked as Undo!";
    public static final String MESSAGE_TASK_ALREADY_MARKED_UNCOMPLETED = "Task is already marked as uncompleted!";

    private final Index index;

    /**
     * @param index of the task in the filtered task list to marked as uncompleted
     */
    public UndoTaskCommand(Index index) {
        requireNonNull(index);

        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToMarkUndo = lastShownList.get(index.getZeroBased());
        Task undoTask = createUndoTask(taskToMarkUndo);

        model.setTask(taskToMarkUndo, undoTask);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        return new CommandResult(String.format(MESSAGE_UNDO_TASK_SUCCESS, undoTask));
    }

    /**
     * Creates and returns a {@code Task} with the details of {@code taskToUndo}
     *
     * @params taskToUndo task to be marked as uncompleted
     * @return A Task with the updated uncompleted status
     */
    private static Task createUndoTask(Task taskToUndo) throws CommandException {
        assert taskToUndo != null;

        Title updatedTitle = taskToUndo.getTitle();
        Description updatedDescription = taskToUndo.getDescription();

        TaskStatus current = taskToUndo.getTaskStatus();

        if (current == TaskStatus.UNCOMPLETED) {
            throw new CommandException(MESSAGE_TASK_ALREADY_MARKED_UNCOMPLETED);
        }

        TaskStatus updatedStatus = TaskStatus.UNCOMPLETED;

        return new Task(updatedTitle, updatedDescription, updatedStatus);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UndoTaskCommand)) {
            return false;
        }

        // state check
        UndoTaskCommand e = (UndoTaskCommand) other;
        System.out.println(index);
        System.out.println(e.index);
        return index.equals(e.index);

    }
}
