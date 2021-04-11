package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;

import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.common.Category;
import seedu.address.model.common.Date;
import seedu.address.model.common.Name;
import seedu.address.model.common.Tag;
import seedu.address.model.task.Priority;
import seedu.address.model.task.Task;

/**
 * Marks the status of a completed task as uncompleted.
 */
public class UndoneTaskCommand extends Command {
    public static final String COMMAND_WORD = "undone_task";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the task identified by the index number as uncompleted.\n"
            + "Parameters: INDEX (must be a valid positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1\n"
            + "Also note: \n"
            + "1. Index number is the index shown in the displayed task list.\n"
            + "2. Index must be an integer greater than zero.";

    public static final String MESSAGE_UNDONE_TASK_SUCCESS = "Uncompleted 1 Task.";
    public static final String MESSAGE_TASK_ALREADY_UNCOMPLETED = "This task has already been marked as uncompleted.";

    private final Index targetIndex;

    public UndoneTaskCommand(Index targetIndex) {
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

        Task taskToUndone = lastShownList.get(targetIndex.getZeroBased());
        // verify if task is already uncompleted
        if (!taskToUndone.isComplete()) {
            throw new CommandException(MESSAGE_TASK_ALREADY_UNCOMPLETED);
        }

        Task uncompletedTask = createUncompletedTask(taskToUndone);

        // replace the old task with the new and completed task and update
        model.setTask(taskToUndone, uncompletedTask);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        return new CommandResult(MESSAGE_UNDONE_TASK_SUCCESS);
    }

    /**
     * Creates and returns a {@code Task} with the same details of {@code taskToUndone},
     * but completionStatus as completed.
     *
     * @param taskToUndone task to be marked as uncompleted.
     * @return an uncompleted task.
     */
    private static Task createUncompletedTask(Task taskToUndone) {
        assert taskToUndone != null;
        assert taskToUndone.isComplete();

        Task uncompletedTask = copyTask(taskToUndone);
        uncompletedTask.markTaskAsUndone();
        return uncompletedTask;
    }

    /**
     * Copies the task given and returns a new task with the same details as the given task.
     *
     * @param taskToCopy task to be copied, here is the task to be uncompleted.
     * @return a copied task.
     */
    private static Task copyTask(Task taskToCopy) {
        Name taskName = taskToCopy.getName();
        Date deadline = taskToCopy.getDeadline();
        Priority priority = taskToCopy.getPriority();
        Set<Category> categories = taskToCopy.getCategories();
        Set<Tag> tags = taskToCopy.getTags();

        Task copiedTask = new Task(taskName, deadline, priority, categories, tags);
        if (taskToCopy.isPinned()) {
            copiedTask.pin();
        }
        return copiedTask;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UndoneTaskCommand // instanceof handles nulls
                && targetIndex.equals(((UndoneTaskCommand) other).targetIndex)); // state check
    }
}
