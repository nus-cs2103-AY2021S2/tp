package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Set;

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

public class DoneCommand extends Command {
    public static final String COMMAND_WORD = "done";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks a task as done.\n\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 2";

    public static final String MESSAGE_INDEX_OUT_OF_RANGE = "Please check that your index is positive and within the"
            + " range of the list.";

    public static final String MESSAGE_DONE_TASK_SUCCESS = "Task successfully marked as done. \n\n Task: %1$s ";

    public static final String MESSAGE_TASK_ALREADY_DONE = "Task: %1$s is already done. Did you type the wrong index?";

    private final Index index;

    private Task taskStatusSetToDone;

    public DoneCommand(Index index) {
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();

        verifyTask(lastShownList);
        updateModel(model);
        return new CommandResult(String.format(MESSAGE_DONE_TASK_SUCCESS, taskStatusSetToDone));
    }

    private void verifyTask(List<Task> lastShownList) throws CommandException {
        try {
            ConditionLogic.verifyIndex(index, lastShownList);
        } catch (CommandException ce) {
            throw new CommandException(MESSAGE_INDEX_OUT_OF_RANGE);
        }

        verifyTaskStatusNotDone(lastShownList);
    }

    private void verifyTaskStatusNotDone(List<Task> list) throws CommandException {
        Task taskToBeDone = list.get(index.getZeroBased());
        String taskTitle = taskToBeDone.getTitle().fullTitle;
        AttributeManager attributeManager = new AttributeManager(taskToBeDone);

        if (attributeManager.isDone()) {
            throw new CommandException(String.format(MESSAGE_TASK_ALREADY_DONE, taskTitle));
        }
    }

    private Task retrieveSelectedTask(List<Task> list) {
        return list.get(index.getZeroBased());
    }

    private void updateModel(Model model) throws CommandException {
        List<Task> lastShownList = model.getFilteredTaskList();
        Task taskToSetAsDone = retrieveSelectedTask(lastShownList);
        taskStatusSetToDone = setTaskStatusAsDone(taskToSetAsDone);

        model.setTask(taskToSetAsDone, taskStatusSetToDone);
    }

    /**
     * Creates and returns a {@code Task} which retains all the values of the previous attributes
     * from {@code taskToBeDone} but only updating the Status attribute from 'not done' to 'done.
     */
    public static Task setTaskStatusAsDone(Task taskToBeDone) {
        assert taskToBeDone != null;

        Title previousTitle = taskToBeDone.getTitle();
        Date previousDate = taskToBeDone.getDate();
        RecurringSchedule previousRecurringSchedule = taskToBeDone.getRecurringSchedule();
        Description previousDescription = taskToBeDone.getDescription();
        Duration previousDuration = taskToBeDone.getDuration();
        Status doneStatus = new Status(Status.DONE_STATE);
        Set<Tag> previousTags = taskToBeDone.getTags();

        return new Task(previousTitle, previousDate, previousDuration, previousRecurringSchedule,
                previousDescription, doneStatus, previousTags);
    }

    @Override
    public boolean equals(Object otherCommand) {
        if (otherCommand == this) {
            return true;
        }

        return otherCommand instanceof DoneCommand && index.equals(((DoneCommand) otherCommand).index);
    }
}
