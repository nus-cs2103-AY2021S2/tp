package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;

import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Description;
import seedu.address.model.task.RecurringSchedule;
import seedu.address.model.task.StartTime;
import seedu.address.model.task.Status;
import seedu.address.model.task.Task;
import seedu.address.model.task.Title;

public class DoneCommand extends Command {
    public static final String COMMAND_WORD = "done";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": marks the task identified by the index number used in the displayed task list as done.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 2";

    public static final String MESSAGE_DONE_TASK_SUCCESS = "Task: %1$s marked as done.";

    public static final String MESSAGE_TASK_ALREADY_DONE = "Task: %1$s is already done. Did you type the wrong index?";

    private final Index index;

    public DoneCommand(Index index) {
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();

        boolean isInvalidIndex = index.getZeroBased() >= lastShownList.size();
        if (isInvalidIndex) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToSetAsDone = retrieveSelectedTask(lastShownList);
        String taskTitle = retrieveTaskTitle(taskToSetAsDone);

        boolean isTaskStatusDone = checkIsStatusDone(taskToSetAsDone);

        if (isTaskStatusDone) {
            throw new CommandException(String.format(MESSAGE_TASK_ALREADY_DONE, taskTitle));
        }

        Task taskStatusSetToDone = setTaskStatusAsDone(taskToSetAsDone);
        updateModel(model, taskToSetAsDone, taskStatusSetToDone);
        return new CommandResult(String.format(MESSAGE_DONE_TASK_SUCCESS, taskStatusSetToDone));
    }

    private Task retrieveSelectedTask(List<Task> list) {
        return list.get(index.getZeroBased());
    }

    private String retrieveTaskTitle(Task task) {
        return task.getTitle().fullTitle;
    }

    private boolean checkIsStatusDone(Task task) {
        String statusValue = task.getStatus().toString();
        return statusValue.equals("done");
    }

    private void updateModel(Model model, Task taskToSetAsDone, Task taskStatusSetToDone) {
        model.setTask(taskToSetAsDone, taskStatusSetToDone);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
    }

    /**
     * Creates and returns a {@code Task} which retains all the values of the previous attributes
     * from {@code taskToBeDone} but only updating the Status attribute from 'not done' to 'done.
     */
    public static Task setTaskStatusAsDone(Task taskToBeDone) {
        assert taskToBeDone != null;

        Title previousTitle = taskToBeDone.getTitle();
        Deadline previousDeadline = taskToBeDone.getDeadline();
        RecurringSchedule previousRecurringSchedule = taskToBeDone.getRecurringSchedule();
        Description previousDescription = taskToBeDone.getDescription();
        StartTime previousStartTime = taskToBeDone.getStartTime();
        Status doneStatus = new Status("done");
        Set<Tag> previousTags = taskToBeDone.getTags();

        return new Task(previousTitle, previousDeadline, previousStartTime, previousRecurringSchedule,
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
