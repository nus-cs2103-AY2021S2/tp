package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.conditions.ConditionLogic;
import seedu.address.model.Model;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.AttributeManager;
import seedu.address.model.task.Task;
import seedu.address.model.task.attributes.Date;
import seedu.address.model.task.attributes.Description;
import seedu.address.model.task.attributes.Duration;
import seedu.address.model.task.attributes.RecurringSchedule;
import seedu.address.model.task.attributes.Status;
import seedu.address.model.task.attributes.Title;

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
            + "Example: " + COMMAND_WORD + " 3\n"
            + "This postpones the task at index 3 by 1 day\n"
            + "Example: " + COMMAND_WORD + " 4 2\n"
            + "This postpones the task at index 4 by 2 days";

    public static final String MESSAGE_SNOOZE_TASK_SUCCESS = "Task: %1$s Snoozed by %2$d days.";

    public static final String MESSAGE_MAX_SNOOZE_AMOUNT_EXCEEDED =
            "The maximum number of days to snooze a task is 365.";

    public static final int MAX_SNOOZE_AMOUNT = 365;

    private final Index index;

    private final int snoozeAmount;

    private final Logger logger = LogsCenter.getLogger(getClass());

    /**
     * Constructor for the SnoozeCommand class.
     *
     * @param index of the task in the filtered task list to snooze.
     * @param snoozeAmount number of days to snooze the task by.
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

        enforceCommandValidity(lastShownList);
        updateModel(model);
        return commandResultGenerator(lastShownList);
    }

    private void enforceCommandValidity(List<Task> lastShownList) throws CommandException {
        ConditionLogic.verifyIndex(index, lastShownList);
        Task taskToSnooze = retrieveSelectedTask(lastShownList);
        ConditionLogic conditionLogic = new ConditionLogic(taskToSnooze);
        conditionLogic.enforceNonEmptyDate();
        enforceMaxSnoozeAmount();
    }

    private Task retrieveSelectedTask(List<Task> list) {
        return list.get(index.getZeroBased());
    }

    private void enforceMaxSnoozeAmount() throws CommandException {
        if (snoozeAmount > MAX_SNOOZE_AMOUNT) {
            throw new CommandException(MESSAGE_MAX_SNOOZE_AMOUNT_EXCEEDED);
        }
    }

    private void updateModel(Model model) throws CommandException {
        List<Task> lastShownList = model.getFilteredTaskList();
        Task taskToSnooze = retrieveSelectedTask(lastShownList);
        Task snoozedTask = updateTaskWithNewDate(taskToSnooze);

        model.setTask(taskToSnooze, snoozedTask);
    }

    private Task updateTaskWithNewDate(Task taskToSnooze) {
        AttributeManager attributeManager = new AttributeManager(taskToSnooze);
        LocalDate localDate = attributeManager.getDate();
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

    private CommandResult commandResultGenerator(List<Task> lastShownList) {
        Task taskToSnooze = retrieveSelectedTask(lastShownList);
        String taskTitle = retrieveTaskTitle(taskToSnooze);
        return new CommandResult(String.format(MESSAGE_SNOOZE_TASK_SUCCESS, taskTitle, snoozeAmount));
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
