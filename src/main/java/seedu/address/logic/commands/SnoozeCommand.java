package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Date;
import seedu.address.model.task.Description;
import seedu.address.model.task.Duration;
import seedu.address.model.task.RecurringSchedule;
import seedu.address.model.task.Status;
import seedu.address.model.task.Task;
import seedu.address.model.task.Title;

/**
 * Postpones the date of a task by a specified number of days, if the number is
 * not specified, the default will be set to 1.
 */
public class SnoozeCommand extends Command {

    public static final String COMMAND_WORD = "snooze";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Postpones the date of a task by a given number of days. "
            + "If no number of days is given, the default number of days is set to 1.\n"
            + "Parameters: INDEX [NUMBER] (both must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + "3\n"
            + "This postpones the task at index 3 by 1 day"
            + "Example: " + COMMAND_WORD + "4 2\n"
            + "This postpones the task at index  by 2 days";

    public static final String MESSAGE_SNOOZE_TASK_SUCCESS = "Task: %1$s Snoozed by %2$s days.";

    private final Index index;

    private final int snoozeAmount;

    private final Logger logger = LogsCenter.getLogger(getClass());

    /**
     * Constructor for the SnoozeCommand class.
     *
     * @param index of the task in the filtered task list to snooze
     * @param snoozeAmount number of days to snooze the task by
     */
    public SnoozeCommand(Index index, int snoozeAmount) {
        requireNonNull(index);

        this.index = index;
        this.snoozeAmount = snoozeAmount;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();

        checkForValidIndex(lastShownList);
        Task taskToSnooze = retrieveSelectedTask(lastShownList);
        enforceNonEmptyDate(taskToSnooze);

        Task snoozedTask = updateTaskWithNewDate(taskToSnooze);
        String snoozedTaskTitle = retrieveTaskTitle(snoozedTask);

        updateModel(model, taskToSnooze, snoozedTask);
        return new CommandResult(String.format(MESSAGE_SNOOZE_TASK_SUCCESS, snoozedTaskTitle, snoozeAmount));
    }

    private void checkForValidIndex(List<Task> lastShownList) throws CommandException {
        int indexValue = index.getZeroBased();
        boolean isInvalidIndex = indexValue >= lastShownList.size();

        if (isInvalidIndex) {
            logger.info("Invalid Index detected: " + Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }
    }

    private Task retrieveSelectedTask(List<Task> list) {
        return list.get(index.getZeroBased());
    }

    private void enforceNonEmptyDate(Task taskToSnooze) throws CommandException {
        if (taskToSnooze.isDateEmpty()) {
            logger.log(Level.INFO, "The task selected has no date attribute.\n" + MESSAGE_USAGE);

            throw new CommandException("The task selected has no date attribute.\n" + MESSAGE_USAGE);
        }
    }

    private Task updateTaskWithNewDate(Task taskToSnooze) {
        Date date = taskToSnooze.getDate();
        LocalDate localDate = date.getDate();
        LocalDate newLocalDate = localDate.plusDays(snoozeAmount);

        Title previousTitle = taskToSnooze.getTitle();
        Date newDate = new Date(newLocalDate);
        RecurringSchedule previousRecurringSchedule = taskToSnooze.getRecurringSchedule();
        Description previousDescription = taskToSnooze.getDescription();
        Duration previousDuration = taskToSnooze.getDuration();
        Status previousStatus = taskToSnooze.getStatus();
        Set<Tag> previousTags = taskToSnooze.getTags();

        return new Task(previousTitle, newDate, previousDuration, previousRecurringSchedule,
                previousDescription, previousStatus, previousTags);
    }

    private void updateModel(Model model, Task taskToSetAsDone, Task taskStatusSetToDone) throws CommandException {
        model.setTask(taskToSetAsDone, taskStatusSetToDone);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
    }

    private String retrieveTaskTitle(Task task) {
        return task.getTitle().fullTitle;
    }

    @Override
    public boolean equals(Object otherCommand) {
        if (otherCommand == this) {
            return true;
        }
        return otherCommand instanceof SnoozeCommand && index.equals(((SnoozeCommand) otherCommand).index)
                && snoozeAmount == (((SnoozeCommand) otherCommand).snoozeAmount);
    }

}
