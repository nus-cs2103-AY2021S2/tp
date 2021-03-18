//@@author mesyeux
package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECURRINGSCHEDULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STARTTIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Description;
import seedu.address.model.task.RecurringSchedule;
import seedu.address.model.task.StartTime;
import seedu.address.model.task.Status;
import seedu.address.model.task.Task;
import seedu.address.model.task.Title;

/**
 * Deletes a specific field in a task identified using it's displayed index from the planner.
 */
public class DeleteFieldCommand extends Command {
    public static final String COMMAND_WORD = "delete-field";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the specified field in the task identified by the index number "
            + "used in the displayed task list.\n"
            + "Parameters: INDEX (must be a positive integer) FIELD\n"
            + "Field can be: "
            + PREFIX_DESCRIPTION + " or "
            + PREFIX_RECURRINGSCHEDULE + " or "
            + PREFIX_TAG + " \n"
            + "Exactly one field is to be specified.\n"
            + "Example: " + COMMAND_WORD + " 1" + " tags/";

    public static final String MESSAGE_DELETE_FIELD_SUCCESS = "Deleted Field in Task: %1$s";

    private final Index targetIndex;

    private final Prefix targetField;

    /**
     * @param targetIndex of the task in the filtered task list to delete the field from
     * @param targetField the field user wants to delete
     */
    public DeleteFieldCommand(Index targetIndex, String targetField) {
        requireNonNull(targetIndex);
        requireNonNull(targetField);

        this.targetIndex = targetIndex;
        this.targetField = new Prefix(targetField);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();

        int targetIndexValue = targetIndex.getZeroBased();
        boolean isValidIndex = targetIndexValue >= lastShownList.size();

        if (isValidIndex) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToDeleteFieldFrom = lastShownList.get(targetIndexValue);
        Task taskWithFieldDeleted = deleteFieldFromTask(taskToDeleteFieldFrom, targetField);

        model.setTask(taskToDeleteFieldFrom, taskWithFieldDeleted);

        return new CommandResult(String.format(MESSAGE_DELETE_FIELD_SUCCESS, taskWithFieldDeleted));
    }

    /**
     * Creates and returns a {@code Task} with the {@code field}
     * deleted from {@code taskToDeleteFieldFrom}.
     */
    private static Task deleteFieldFromTask(Task taskToDeleteFieldFrom, Prefix field) throws CommandException {
        assert taskToDeleteFieldFrom != null;

        Title title = taskToDeleteFieldFrom.getTitle();
        Deadline oldDeadline = taskToDeleteFieldFrom.getDeadline();
        StartTime oldStartTime = taskToDeleteFieldFrom.getStartTime();
        RecurringSchedule oldRecurringSchedule = taskToDeleteFieldFrom.getRecurringSchedule();
        Description oldDescription = taskToDeleteFieldFrom.getDescription();
        Status oldStatus = taskToDeleteFieldFrom.getStatus();
        Set<Tag> oldTags = taskToDeleteFieldFrom.getTags();

        boolean isTitleField = field.equals(PREFIX_TITLE);
        boolean isDeadlineField = field.equals(PREFIX_DEADLINE);
        boolean isRecurringScheduleField = field.equals(PREFIX_RECURRINGSCHEDULE);
        boolean isStartTimeField = field.equals(PREFIX_STARTTIME);
        boolean isDescriptionField = field.equals(PREFIX_DESCRIPTION);
        boolean isStatusField = field.equals(PREFIX_STATUS);
        boolean isTagField = field.equals(PREFIX_TAG);

        if (isTitleField) {
            throw new CommandException("Cannot delete title field.");
        } else if (isDeadlineField) {
            throw new CommandException("Cannot delete deadline field.");
        } else if (isStartTimeField) {
            throw new CommandException("Cannot delete start time field.");
        } else if (isRecurringScheduleField) { //not implemented yet
            return new Task(title, oldDeadline, oldStartTime, oldRecurringSchedule, oldDescription, oldStatus, oldTags);
        } else if (isDescriptionField) {
            Description updatedDescription = new Description("");
            return new Task(title, oldDeadline, oldStartTime, oldRecurringSchedule,
                    updatedDescription, oldStatus, oldTags);
        } else if (isStatusField) {
            throw new CommandException("Cannot delete status field.");
        } else if (isTagField) {
            Set<Tag> updatedTags = new HashSet<>();
            return new Task(title, oldDeadline, oldStartTime, oldRecurringSchedule,
                    oldDescription, oldStatus, updatedTags);
        } else {
            throw new CommandException(Messages.MESSAGE_UNKNOWN_COMMAND);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteFieldCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteFieldCommand) other).targetIndex)) // state check
                && targetField.equals(((DeleteFieldCommand) other).targetField);
    }
}
