//@@author mesyeux
package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECURRINGSCHEDULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.conditions.ConditionLogic;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;
import seedu.address.model.task.attributes.Date;
import seedu.address.model.task.attributes.Description;
import seedu.address.model.task.attributes.Duration;
import seedu.address.model.task.attributes.RecurringSchedule;
import seedu.address.model.task.attributes.Status;
import seedu.address.model.task.attributes.Title;

/**
 * Deletes a specific field in a task identified using it's displayed index from the planner.
 */
public class DeleteFieldCommand extends Command {
    public static final String COMMAND_WORD = "rmf";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Removes a field from a task.\n "
            + "Parameters: INDEX (must be a positive integer) FIELD\n"
            + "Field can be: "
            + PREFIX_DATE + " , "
            + PREFIX_DURATION + " , "
            + PREFIX_DESCRIPTION + " , "
            + PREFIX_RECURRINGSCHEDULE + " or "
            + PREFIX_TAG + " \n"
            + "Exactly one field is to be specified.\n"
            + "Example: " + COMMAND_WORD + " 1 " + PREFIX_TAG;

    public static final String SHORT_MESSAGE_USAGE = COMMAND_WORD + " INDEX FIELD\n";

    public static final String MESSAGE_DELETE_FIELD_SUCCESS = "Deleted field in Task: %1$s";

    public static final String MESSAGE_INVALID_FIELD_STATUS = "Cannot delete status field.";
    public static final String MESSAGE_INVALID_FIELD_TITLE = "Cannot delete title field.";

    public static final String MESSAGE_FIELD_ALREADY_EMPTY = "Field is already empty.";

    private final Index targetIndex;

    private final Prefix targetField;

    private final Logger logger = LogsCenter.getLogger(getClass());

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

        ConditionLogic.verifyIndex(targetIndex, lastShownList);

        Task taskToDeleteFieldFrom = lastShownList.get(targetIndexValue);
        Task taskWithFieldDeleted = deleteFieldFromTask(taskToDeleteFieldFrom, targetField);
        ConditionLogic conditionLogic = new ConditionLogic(taskWithFieldDeleted);
        conditionLogic.enforceAttributeConstraints();

        if (targetField.equals(PREFIX_TAG)) {
            taskToDeleteFieldFrom.getTags().forEach(model::deleteTag);
        }
        model.setTask(taskToDeleteFieldFrom, taskWithFieldDeleted);

        return new CommandResult(String.format(MESSAGE_DELETE_FIELD_SUCCESS, taskWithFieldDeleted));
    }

    /**
     * Creates and returns a {@code Task} with the {@code field}
     * deleted from {@code taskToDeleteFieldFrom}.
     */
    private Task deleteFieldFromTask(Task taskToDeleteFieldFrom, Prefix field) throws CommandException {
        assert taskToDeleteFieldFrom != null;

        checkIsValidPrefix(field);

        Title title = taskToDeleteFieldFrom.getTitle();
        Date oldDate = taskToDeleteFieldFrom.getDate();
        Duration oldDuration = taskToDeleteFieldFrom.getDuration();
        RecurringSchedule oldRecurringSchedule = taskToDeleteFieldFrom.getRecurringSchedule();
        Description oldDescription = taskToDeleteFieldFrom.getDescription();
        Status status = taskToDeleteFieldFrom.getStatus();
        Set<Tag> oldTags = taskToDeleteFieldFrom.getTags();

        checkIsTitleStatus(field);
        checkIsFieldDeleted(field, oldDate, oldDuration, oldRecurringSchedule, oldDescription, oldTags);

        if (field.equals(PREFIX_DATE)) {
            return new Task(title, new Date(""), oldDuration, oldRecurringSchedule,
                    oldDescription, status, oldTags);
        } else if (field.equals(PREFIX_DURATION)) {
            return new Task(title, oldDate, new Duration(""), oldRecurringSchedule,
                    oldDescription, status, oldTags);
        } else if (field.equals(PREFIX_RECURRINGSCHEDULE)) {
            return new Task(title, oldDate, oldDuration, new RecurringSchedule(""), oldDescription, status, oldTags);
        } else if (field.equals(PREFIX_DESCRIPTION)) {
            return new Task(title, oldDate, oldDuration, oldRecurringSchedule,
                    new Description(""), status, oldTags);
        } else if (field.equals(PREFIX_TAG)) {
            return new Task(title, oldDate, oldDuration, oldRecurringSchedule,
                    oldDescription, status, new HashSet<>());
        } else {
            throw new CommandException(Messages.MESSAGE_UNKNOWN_COMMAND);
        }
    }

    private void checkIsValidPrefix(Prefix field) throws CommandException {
        if (!field.isValidPrefix()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PREFIX);
        }
    }

    private void checkIsTitleStatus(Prefix field) throws CommandException {
        if (field.equals(PREFIX_TITLE)) {
            logger.info("User tried to delete title");
            throw new CommandException(MESSAGE_INVALID_FIELD_TITLE);
        }

        if (field.equals(PREFIX_STATUS)) {
            logger.info("User tried to delete status");
            throw new CommandException(MESSAGE_INVALID_FIELD_STATUS);
        }
    }

    private void checkIsFieldDeleted(Prefix field, Date oldDate, Duration oldDuration,
                                     RecurringSchedule oldRecurringSchedule, Description oldDescription,
                                     Set<Tag> oldTags) throws CommandException {

        boolean isFieldAlreadyDeleted = (field.equals(PREFIX_DATE) && oldDate.isEmptyValue())
                || (field.equals(PREFIX_RECURRINGSCHEDULE) && oldRecurringSchedule.isEmptyValue())
                || (field.equals(PREFIX_DURATION) && oldDuration.isEmptyValue())
                || (field.equals(PREFIX_DESCRIPTION) && oldDescription.isEmptyValue())
                || (field.equals(PREFIX_TAG) && oldTags.isEmpty());

        if (isFieldAlreadyDeleted) {
            throw new CommandException(MESSAGE_FIELD_ALREADY_EMPTY);
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
