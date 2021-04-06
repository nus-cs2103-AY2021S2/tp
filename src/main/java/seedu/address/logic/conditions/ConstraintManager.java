package seedu.address.logic.conditions;

import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.task.Task;
import seedu.address.model.task.attributes.Attribute;

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

    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    /**
     * Check that the given task abides by the necessary constraints on its attributes.
     *
     * @param task The task to be checked.
     * @throws CommandException If a task has both Date as well as Duration or RecurringSchedule.
     */
    public static void enforceAttributeConstraints(Task task) throws CommandException {
        Attribute date = task.getDate();
        Attribute duration = task.getDuration();
        Attribute recurringSchedule = task.getRecurringSchedule();
        Attribute title = task.getTitle();

        boolean hasDateValue = !date.isEmptyValue();
        boolean hasDurationValue = !duration.isEmptyValue();
        boolean hasRecurringScheduleValue = !recurringSchedule.isEmptyValue();
        boolean hasNoTitle = title.isEmptyValue();

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


}
