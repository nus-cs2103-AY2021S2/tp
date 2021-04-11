package seedu.address.logic.conditions;

import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.task.AttributeManager;
import seedu.address.model.task.Task;

/**
 * ConstraintManager checks that the necessary constraints on the attributes of Tasks are abided by.
 * Current constraints:
 * - Duration cannot exist on its own without Deadline or RecurringSchedule.
 * - Deadline cannot exist together with RecurringSchedule.
 * - Title is compulsory.
 */
public class ConstraintManager {

    public static final String MESSAGE_DURATION_STANDALONE_ERROR = "Task cannot have Duration on its own.\n"
            + "Duration must have a Date or RecurringSchedule.";
    public static final String MESSAGE_DATE_RECURRING_SCHEDULE_CONFLICT = "Task cannot have (Date) as well as "
            + "(RecurringSchedule) at the same time!\nPlease choose either when adding a task.";
    public static final String MESSAGE_EMPTY_TITLE = "Title is compulsory for all tasks and cannot be empty.";
    public static final String MESSAGE_TITLE_EXCEED_MAX_LENGTH = "Title cannot have more than 40 characters.\n"
            + "Consider using the description attribute to add extra details.";
    public static final int MAX_TITLE_LENGTH = 40;
    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private Task task;
    private AttributeManager attributeManager;

    /**
     * Constructor for the ConstraintManager class.
     *
     * @param task Task to check constraints.
     */
    public ConstraintManager (Task task) {
        this.task = task;
        this.attributeManager = new AttributeManager(task);
    }

    /**
     * Check that the given task abides by the necessary constraints on its attributes.
     *
     * @throws CommandException If a task has:
     * - Duration only
     * - Date and RecurringSchedule
     * - No title
     */
    public void enforceAttributeConstraints() throws CommandException {
        AttributeManager attributeManager = new AttributeManager(task);

        boolean hasDateValue = !attributeManager.isEmptyDate();
        boolean hasDurationValue = !attributeManager.isEmptyDuration();
        boolean hasRecurringScheduleValue = !attributeManager.isEmptyRecurringSchedule();
        boolean hasNoTitle = attributeManager.isEmptyTitle();

        // Duration cannot exist on its own without Deadline or RecurringSchedule.
        if (hasDurationValue && !(hasRecurringScheduleValue || hasDateValue)) {
            logger.log(Level.INFO, MESSAGE_DURATION_STANDALONE_ERROR);
            throw new CommandException(MESSAGE_DURATION_STANDALONE_ERROR);
        }

        if (hasDateValue && hasRecurringScheduleValue) {
            logger.log(Level.INFO, MESSAGE_DATE_RECURRING_SCHEDULE_CONFLICT);
            throw new CommandException(MESSAGE_DATE_RECURRING_SCHEDULE_CONFLICT);
        }

        if (hasNoTitle) {
            logger.log(Level.INFO, MESSAGE_EMPTY_TITLE);
            throw new CommandException(MESSAGE_EMPTY_TITLE);
        }
    }

    /**
     * Checks that the title length does not exceeds the max title length.
     *
     * @throws CommandException If the title length exceeds the max title length.
     */
    public void enforceTitleLength() throws CommandException {
        if (attributeManager.isEmptyTitle()) {
            logger.log(Level.INFO, MESSAGE_EMPTY_TITLE);
            throw new CommandException(MESSAGE_EMPTY_TITLE);
        }

        String titleString = attributeManager.getTitleString();
        if (titleString.length() > MAX_TITLE_LENGTH) {
            logger.log(Level.INFO, MESSAGE_TITLE_EXCEED_MAX_LENGTH);
            throw new CommandException(MESSAGE_TITLE_EXCEED_MAX_LENGTH);
        }
    }
}
