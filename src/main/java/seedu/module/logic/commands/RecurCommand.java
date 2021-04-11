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

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Make a task not recur daily, weekly or biweekly "
            + "or remove a tasks recurrence identified by the INDEX.\n"
            + "Parameters: INDEX, RECURRENCE \n"
            + "If RECURRENCE specified, it is overwritten by the RECURRENCE. "
            + "If RECURRENCE is not specified, recurrence of task removed.\n"
            + "r/RECURRENCE ('', 'daily', 'weekly' or 'biweekly')\n"
            + "Example for adding recurrence: " + COMMAND_WORD + " 1 r/biweekly\n"
            + "Example for removal: " + COMMAND_WORD + " 2 r/";

    public static final String MESSAGE_ADD_RECURRENCE_SUCCESS = "New recurrence to task added successfully.\n%1$s";
    public static final String MESSAGE_INVALID_RECURRENCE = "Recurrence can only be daily, weekly or biweekly.";
    public static final String MESSAGE_DUPLICATE_RECURRENCE = "This task is already recurring %1$s";
    public static final String MESSAGE_REMOVE_RECURRENCE_SUCCESS = "Recurrence for this task has been removed.";
    public static final String MESSAGE_REMOVE_RECURRENCE_UNSUCCESSFUL = "Recurrence was not removed because this"
            + " task does not have any existing recurrence.";

    private final Index index;
    private OptionalField<Recurrence> recurrence;

    /**
     * Constructor for {@code RecurCommand} object.
     *
     * @param index one-based index of the Task for the recurrence to be added to.
     */
    public RecurCommand(Index index) {
        requireNonNull(index);
        this.index = index;
    }

    public void setRecurrence(OptionalField<Recurrence> recurrence) throws IllegalArgumentException {
        assert recurrence != null;
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

        Task nextRecurringTask = Task.makeNextRecurringTask(taskToRecur, recurrence);

        if (taskToRecur.equals(nextRecurringTask) && model.hasRecurringTask(nextRecurringTask)) {
            emptyRecurrenceHandler(recurrence, taskToRecur);
        }

        model.setTask(taskToRecur, nextRecurringTask);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);

        String returnMessage;
        if (recurrence.isNull()) {
            returnMessage = String.format(MESSAGE_REMOVE_RECURRENCE_SUCCESS);
        } else {
            returnMessage = String.format(MESSAGE_ADD_RECURRENCE_SUCCESS, nextRecurringTask);
        }

        return new CommandResult(returnMessage);
    }

    private void emptyRecurrenceHandler(OptionalField<Recurrence> recurrenceOptionalField,
                                        Task taskToCheck) throws CommandException {
        assert recurrenceOptionalField != null;
        requireNonNull(taskToCheck);

        if (!taskToCheck.isRecurring() && recurrenceOptionalField.isNull()) {
            throw new CommandException(MESSAGE_REMOVE_RECURRENCE_UNSUCCESSFUL);
        } else if (recurrenceOptionalField.isNull()) {
            throw new CommandException(MESSAGE_INVALID_RECURRENCE);
        } else {
            throw new CommandException(
                    String.format(MESSAGE_DUPLICATE_RECURRENCE, recurrenceOptionalField.getField()));
        }
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
