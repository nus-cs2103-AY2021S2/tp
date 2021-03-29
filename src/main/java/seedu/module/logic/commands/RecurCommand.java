package seedu.module.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.module.model.Model.PREDICATE_SHOW_ALL_TASKS;

import java.util.List;

import seedu.module.commons.core.Messages;
import seedu.module.commons.core.index.Index;
import seedu.module.commons.core.optionalfield.OptionalField;
import seedu.module.logic.commands.exceptions.CommandException;
import seedu.module.model.Model;
import seedu.module.model.task.Recurrence;
import seedu.module.model.task.Task;


/**
 * Makes a task repeat at a given interval of
 */
public class RecurCommand extends Command {
    public static final String COMMAND_WORD = "recur";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Make a task recur either daily, weekly or monthly "
            + "to the task identified by the index number used in the last person listing. "
            + "If recurrence specified, it is overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "r/ RECURRENCE (must be daily, weekly or monthly)\n"
            + "Example: " + COMMAND_WORD + " 1 r/ monthly";

    public static final String MESSAGE_ADD_RECURRENCE_SUCCESS = "New recurrence to task added successfully: %1$s";
    public static final String MESSAGE_INVALID_RECURRENCE = "Recurrence can only be daily, weekly or monthly.";
    public static final String MESSAGE_DUPLICATE_RECURRENCE = "This task is already recurring: %1$s";

    private Index index;
    private Recurrence recurrence;

    /**
     * Constructor for {@code RecurCommand} object.
     *
     * @param index one-based index of the Task for the recurrence to be added to.
     */
    public RecurCommand(Index index) {
        requireNonNull(index);
        this.index = index;
    }

    public void setRecurrence(Recurrence recurrence) {
        this.recurrence = recurrence;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        assert model != null;

        List<Task> lastShownList = model.getFilteredTaskList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToRecur = lastShownList.get(index.getZeroBased());
        Task nextRecurringTask = makeNextRecurringTask(taskToRecur);

        if (taskToRecur.equals(nextRecurringTask) && model.hasRecurringTask(nextRecurringTask)) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_RECURRENCE, taskToRecur.getRecurrence()));
        }

        model.setTask(taskToRecur, nextRecurringTask);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);

        return new CommandResult(String.format(MESSAGE_ADD_RECURRENCE_SUCCESS, nextRecurringTask));
    }

    private Task makeNextRecurringTask(Task previousRecurringTask) throws CommandException {
        assert previousRecurringTask != null;

        OptionalField<Recurrence> recurrenceWrapper = new OptionalField<>(recurrence);

        return Task.setRecurrence(previousRecurringTask, recurrenceWrapper);
    }


    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RecurCommand)) {
            return false;
        }

        // state check
        RecurCommand e = (RecurCommand) other;
        return index.equals(e.index)
                && recurrence.equals(e.recurrence);
    }


}
