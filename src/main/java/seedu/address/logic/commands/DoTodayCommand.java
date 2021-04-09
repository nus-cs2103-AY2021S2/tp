package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.util.OperationFlag;
import seedu.address.logic.util.OperationType;
import seedu.address.model.Model;
import seedu.address.model.person.Task;

/**
 * Adds or removes a task from the daily to-do list based on the operation type specified.
 */
public class DoTodayCommand extends Command {

    public static final String COMMAND_WORD = "doToday";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + "Adds the task identified by the index number used in the displayed task list into "
            + "the daily to-do list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_ADDED_TASK_SUCCESS = "Added Task: %1$s to daily to-do list.";

    public static final String MESSAGE_REMOVED_TASK_SUCCESS = "Removed Task: %1$s from daily to-do list.";

    public static final String MESSAGE_DUPLICATE_DAILY_TASK = "This task already exists in the daily task list";

    private final Index targetIndex;

    private final OperationFlag operationFlag;

    /**
     * Constructs a {@code DoTodayCommand}.
     */
    public DoTodayCommand(Index targetIndex, OperationFlag operationFlag) {
        this.targetIndex = targetIndex;
        this.operationFlag = operationFlag;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);


        if (operationFlag.getOperationType() == OperationType.ADD) {
            List<Task> lastShownList = model.getFilteredTaskList();

            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, DoTodayCommand.MESSAGE_USAGE));
            }

            Task taskToAdd = lastShownList.get(targetIndex.getZeroBased());

            if (model.hasDailyTask(taskToAdd)) {
                throw new CommandException(MESSAGE_DUPLICATE_DAILY_TASK);
            }
            model.addToDailyToDoList(taskToAdd);
            return new CommandResult(String.format(MESSAGE_ADDED_TASK_SUCCESS, taskToAdd));
        } else {
            List<Task> lastShownDailyTaskList = model.getDailyTaskList();

            if (targetIndex.getZeroBased() >= lastShownDailyTaskList.size()) {
                throw new CommandException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, DoTodayCommand.MESSAGE_USAGE));
            }

            Task taskToRemove = lastShownDailyTaskList.get(targetIndex.getZeroBased());
            model.removeFromDailyToDoList(taskToRemove);
            return new CommandResult(String.format(MESSAGE_REMOVED_TASK_SUCCESS, taskToRemove));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DoTodayCommand // instanceof handles nulls
                && targetIndex.equals(((DoTodayCommand) other).targetIndex))
                && operationFlag.equals(((DoTodayCommand) other).operationFlag); // state check
    }
}
