package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;


/**
 * Marks the status of an uncompleted task as complete.
 */
public class DoneTaskCommand extends Command {
    public static final String COMMAND_WORD = "done_task";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the task(s) identified by the index number(s) as complete.\n"
            + "Parameters: INDEX1 [INDEX2]...\n"
            + "Example: " + COMMAND_WORD + " 1 2\n"
            + "Also, note that: \n"
            + "1. Index number is the index shown in the displayed task list.\n"
            + "2. Index numbers must be valid integers larger than zero.\n"
            + "3. Index numbers must not contain duplicates.\n"
            + "4. Completed tasks cannot be marked as complete again.";

    public static final String MESSAGE_DONE_TASK_SUCCESS = "Completed %1$d Task(s).";
    public static final String MESSAGE_TASKS_ALREADY_COMPLETE =
            "Tasks with the following given indexes have already been marked as complete: ";
    public static final String MESSAGE_TASK_ALREADY_COMPLETE =
            "Task with the following given index has already been marked as complete: ";
    public static final String MESSAGE_INDEXES_OUTOFRANGE =
            "Tasks with the following given index do not exist: ";
    public static final String MESSAGE_INDEX_OUTOFRANGE =
            "Task with the following given index does not exist: ";

    private final List<Index> targetIndexes;
    private List<Index> outOfRangeTargetIndexes = new ArrayList<>();
    private List<Index> invalidTargetTaskIndexes = new ArrayList<>();

    public DoneTaskCommand(List<Index> targetIndexes) {
        this.targetIndexes = targetIndexes;
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

        // verify if all indexes are valid
        if (!areTargetIndexesValid(targetIndexes, lastShownList)) {
            String errorMessage = getInvalidIndexesErrorMessage();
            throw new CommandException(errorMessage);
        }

        // check if any target task is already completed
        if (areTasksAlreadyCompleted(targetIndexes, lastShownList)) {
            String errorMessage = getInvalidTasksErrorMessage();
            throw new CommandException(errorMessage);
        }

        updateTargetTasks(targetIndexes, lastShownList, model);
        updateTaskList(model);
        return new CommandResult(String.format(MESSAGE_DONE_TASK_SUCCESS, targetIndexes.size()));
    }

    private boolean areTargetIndexesValid(List<Index> targetIndexes, List<Task> lastShownList) {
        outOfRangeTargetIndexes = findOutOfRangeTargetIndexes(targetIndexes, lastShownList);
        return outOfRangeTargetIndexes.isEmpty();
    }

    private List<Index> findOutOfRangeTargetIndexes(List<Index> targetIndexes, List<Task> lastShownList) {
        List<Index> outOfRangeTargetIndexes = new ArrayList<>();

        for (Index targetIndex : targetIndexes) {
            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                outOfRangeTargetIndexes.add(targetIndex);
            }
        }
        return outOfRangeTargetIndexes;
    }

    private String getInvalidIndexesErrorMessage() {
        assert !outOfRangeTargetIndexes.isEmpty();

        if (outOfRangeTargetIndexes.size() == 1) {
            return MESSAGE_INDEX_OUTOFRANGE + outOfRangeTargetIndexes.toString() + ".\n";
        } else {
            return MESSAGE_INDEXES_OUTOFRANGE + outOfRangeTargetIndexes.toString() + ".\n";
        }
    }

    private boolean areTasksAlreadyCompleted(List<Index> targetIndexes, List<Task> lastShownList) {
        invalidTargetTaskIndexes = findInvalidTargetTasks(targetIndexes, lastShownList);
        return invalidTargetTaskIndexes.size() > 0;
    }

    private List<Index> findInvalidTargetTasks(List<Index> targetIndexes, List<Task> lastShownList) {
        List<Index> invalidTargetTaskIndexes = new ArrayList<>();
        for (Index targetIndex : targetIndexes) {
            Task taskToComplete = lastShownList.get(targetIndex.getZeroBased());
            if (taskToComplete.isComplete()) {
                invalidTargetTaskIndexes.add(targetIndex);
            }
        }
        return invalidTargetTaskIndexes;
    }

    private String getInvalidTasksErrorMessage() {
        assert !invalidTargetTaskIndexes.isEmpty();

        if (invalidTargetTaskIndexes.size() > 1) {
            return MESSAGE_TASKS_ALREADY_COMPLETE + invalidTargetTaskIndexes.toString() + ".\n";
        } else {
            return MESSAGE_TASK_ALREADY_COMPLETE + invalidTargetTaskIndexes.toString() + ".\n";
        }
    }

    /**
     * Replaces the old tasks with the new and completed tasks.
     */
    private void updateTargetTasks(List<Index> targetIndexes, List<Task> lastShownList, Model model) {
        for (Index targetIndex : targetIndexes) {
            Task taskToComplete = lastShownList.get(targetIndex.getZeroBased());
            Task completedTask = createCompletedTask(taskToComplete);
            model.setTask(taskToComplete, completedTask);
        }
    }

    /**
     * Updates the filter of the filtered task list to show all tasks.
     */
    private void updateTaskList(Model model) {
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
    }

    /**
     * Creates and returns a {@code Task} with the same details of {@code taskToComplete},
     * but completionStatus as complete.
     *
     * @param taskToComplete task to be marked as complete.
     * @return a completed task.
     */
    private Task createCompletedTask(Task taskToComplete) {
        assert taskToComplete != null;
        assert !taskToComplete.isComplete();

        Task completedTask = taskToComplete.getCopy();
        completedTask.markTaskAsDone();
        return completedTask;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DoneTaskCommand // instanceof handles nulls
                && targetIndexes.equals(((DoneTaskCommand) other).targetIndexes)
                && outOfRangeTargetIndexes.equals(((DoneTaskCommand) other).outOfRangeTargetIndexes)
                && invalidTargetTaskIndexes.equals(((DoneTaskCommand) other).invalidTargetTaskIndexes)); // state check
    }

}
