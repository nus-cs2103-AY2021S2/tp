package seedu.module.logic.commands;

import seedu.module.commons.core.Messages;
import seedu.module.commons.core.index.Index;
import seedu.module.logic.commands.exceptions.CommandException;
import seedu.module.model.Model;
import seedu.module.model.task.Deadline;
import seedu.module.model.task.DoneStatus;
import seedu.module.model.task.Recurrence;
import seedu.module.model.task.Task;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.module.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.module.model.Model.PREDICATE_SHOW_ALL_TASKS;

/**
 * Makes a task repeat at a given interval of
 */
public class RecurCommand extends Command {
    public static final String COMMAND_WORD = "recur";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Make a task recur either daily, weekly or monthly "
            + "to the task identified by the index number used in the last person listing. "
            + "If recurrence specified, it is overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "r/ [RECURRENCE] (must be daily, weekly or monthly)\n"
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

        if (taskToRecur.equals(nextRecurringTask) && model.hasTask(nextRecurringTask)) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_RECURRENCE, taskToRecur.getRecurrence()));
        }

        model.setTask(taskToRecur, nextRecurringTask);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);

        return new CommandResult(String.format(MESSAGE_ADD_RECURRENCE_SUCCESS, nextRecurringTask));
    }

    /**
     * Returns a new valid Deadline for the recurring task if previous recurring deadline has expired.
     *
     * @param currDeadline current deadline of the recurring task
     * @param taskRecurrence recurrence of the task
     * @return new Deadline object for the recurring task.
     */
    public Deadline getRecurringDeadline(Deadline currDeadline, Recurrence taskRecurrence) throws CommandException {
        requireAllNonNull(currDeadline, taskRecurrence);

        String nextRecurringDeadlineStr = currDeadline.value;
        Deadline currTime = Deadline.makeDeadlineWithTime(LocalDateTime.now());

        if (currDeadline.compareTo(currTime) < 0) {
            // deadline is expired
            switch (taskRecurrence.getRecurrenceType()) {
            case daily:
                //change date to day + 1
                nextRecurringDeadlineStr = currDeadline.getTime().plusDays(1)
                        .format(Deadline.DATE_TIME_FORMATTER_WITH_TIME);
                break;
            case weekly:
                //change date to day + 7
                nextRecurringDeadlineStr = currDeadline.getTime().plusDays(7)
                        .format(Deadline.DATE_TIME_FORMATTER_WITH_TIME);
                break;
            case monthly:
                //change date to month + 1
                nextRecurringDeadlineStr = currDeadline.getTime().plusMonths(1)
                        .format(Deadline.DATE_TIME_FORMATTER_WITH_TIME);
                break;
            default:
                throw new CommandException(MESSAGE_INVALID_RECURRENCE);
            }
        }
        return new Deadline(nextRecurringDeadlineStr);
    }

    private Task makeNextRecurringTask(Task previousRecurringTask) throws CommandException {
        assert previousRecurringTask != null;

        DoneStatus newDoneStatus = new DoneStatus(false);
        Recurrence recurrence = this.recurrence;
        Deadline lastDeadline = previousRecurringTask.getDeadline();
        Deadline nextRecurringDeadline = getRecurringDeadline(lastDeadline, recurrence);

        Task nextRecurringTask = new Task(previousRecurringTask.getName(),
                nextRecurringDeadline,
                previousRecurringTask.getModule(),
                previousRecurringTask.getDescription(),
                previousRecurringTask.getWorkload(),
                newDoneStatus,
                recurrence,
                previousRecurringTask.getTags());

        return nextRecurringTask;
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
