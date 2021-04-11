package seedu.heymatez.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.heymatez.commons.core.Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX;

import java.util.List;
import java.util.Set;

import seedu.heymatez.commons.core.index.Index;
import seedu.heymatez.logic.commands.exceptions.CommandException;
import seedu.heymatez.model.Model;
import seedu.heymatez.model.assignee.Assignee;
import seedu.heymatez.model.task.Deadline;
import seedu.heymatez.model.task.Description;
import seedu.heymatez.model.task.Priority;
import seedu.heymatez.model.task.Task;
import seedu.heymatez.model.task.TaskStatus;
import seedu.heymatez.model.task.Title;

/**
 * Represents an Undo Task Command.
 * Changes the status of existing task in HeyMatez to uncompleted.
 */
public class UndoTaskCommand extends Command {
    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Changes the status of a task with the task index "
            + "you specify from completed to uncompleted. \n"
            + "Parameters: INDEX (must be a positive integer) \n"
            + "Example: " + COMMAND_WORD + " 1 ";

    public static final String MESSAGE_UNDO_TASK_SUCCESS = "Task has been marked as uncompleted.";

    public static final String MESSAGE_TASK_ALREADY_MARKED_UNCOMPLETED = "Task has already been marked as uncompleted!";

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
            throw new CommandException(MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToMarkUndo = lastShownList.get(index.getZeroBased());
        Task undoTask = createUndoTask(taskToMarkUndo);

        assert undoTask != null;

        model.setTask(taskToMarkUndo, undoTask);

        return new CommandResult(MESSAGE_UNDO_TASK_SUCCESS);
    }

    /**
     * Creates and returns a {@code Task} with the details of {@code taskToUndo}.
     *
     * @params taskToUndo task to be marked as uncompleted
     * @return A Task with the updated uncompleted status
     */
    private static Task createUndoTask(Task taskToUndo) throws CommandException {
        assert taskToUndo != null;

        Title updatedTitle = taskToUndo.getTitle();
        Description updatedDescription = taskToUndo.getDescription();
        Deadline updatedDeadline = taskToUndo.getDeadline();

        TaskStatus current = taskToUndo.getTaskStatus();

        if (current == TaskStatus.UNCOMPLETED) {
            throw new CommandException(MESSAGE_TASK_ALREADY_MARKED_UNCOMPLETED);
        }

        TaskStatus updatedStatus = TaskStatus.UNCOMPLETED;

        Priority updatedPriority = taskToUndo.getPriority();
        Set<Assignee> updatedAssignees = taskToUndo.getAssignees();

        return new Task(updatedTitle, updatedDescription, updatedDeadline, updatedStatus, updatedPriority,
                updatedAssignees);
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
