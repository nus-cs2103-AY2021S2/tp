package seedu.heymatez.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.heymatez.commons.core.Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX;
import static seedu.heymatez.model.Model.PREDICATE_SHOW_ALL_TASKS;

import java.util.HashSet;
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
 * Remove all members assigned to a task, identified by the task's index in the task list.
 */
public class ClearAssigneesCommand extends Command {
    public static final String COMMAND_WORD = "clearAssignees";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Clears all members assigned to a task, identified by the task's index number "
            + " used in the displayed task list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_CLEARED_ASSIGNEES_SUCCESS = "Cleared all Members Assigned to Task: %1$s";


    public static final String MESSAGE_ASSIGNEES_SET_IS_EMPTY = "There are no members assigned to this task, "
            + "nothing to clear!";

    private final Index targetIndex;

    public ClearAssigneesCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToClear = lastShownList.get(targetIndex.getZeroBased());
        Task clearedTask = createModifiedTask(taskToClear);

        if (!taskToClear.hasAnyAssignees()) {
            return new CommandResult(MESSAGE_ASSIGNEES_SET_IS_EMPTY);
        }

        model.setTask(taskToClear, clearedTask);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        return new CommandResult(String.format(MESSAGE_CLEARED_ASSIGNEES_SUCCESS, clearedTask));
    }

    /**
     * Creates and returns a {@code Task} with its assignees cleared.
     */
    private static Task createModifiedTask(Task taskToClear) {
        assert taskToClear != null;

        Title updatedTitle = taskToClear.getTitle();
        Description updatedDescription = taskToClear.getDescription();
        Deadline updatedDeadline = taskToClear.getDeadline();
        TaskStatus updatedStatus = taskToClear.getTaskStatus();
        Priority updatedPriority = taskToClear.getPriority();
        Set<Assignee> updatedAssignees = new HashSet<>();

        return new Task(updatedTitle, updatedDescription, updatedDeadline, updatedStatus, updatedPriority,
                updatedAssignees);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ClearAssigneesCommand // instanceof handles nulls
                && targetIndex.equals(((ClearAssigneesCommand) other).targetIndex));
    }
}
