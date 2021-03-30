package seedu.address.logic.conditions;

import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.task.Task;

/**
 * ConditionManager checks that the necessary constraints on the attributes of Tasks are abided by.
 */
public class ConditionManager {

    public static final String MESSAGE_DEADLINE_EVENT_CONFLICT = "Task cannot have (Date) as well as "
            + "(RecurringSchedule and Duration) at the same time!\nPlease choose either when adding a task.";
    public static final String MESSAGE_DEADLINE_DURATION_CONFLICT = "Task cannot have (Date) as well as "
            + "(Duration) at the same time!\nPlease choose either when adding a task.";
    public static final String MESSAGE_DEADLINE_RECURRING_SCHEDULE_CONFLICT = "Task cannot have (Date) as well as "
            + "(RecurringSchedule) at the same time!\nPlease choose either when adding a task.";

    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    /**
     * Check that the given task abides by the necessary constraints on its attributes.
     *
     * @param task The task to be checked.
     * @throws CommandException If a task has both Date as well as Duration or RecurringSchedule.
     */
    public static void enforceAttributeConstraints(Task task) throws CommandException {
        boolean hasDeadlineValue = !task.isDateEmpty();
        boolean hasDurationValue = !task.isDurationEmpty();
        boolean hasRecurringScheduleValue = !task.isRecurringScheduleEmpty();

        if (hasDeadlineValue && hasDurationValue && hasRecurringScheduleValue) {
            logger.log(Level.INFO, MESSAGE_DEADLINE_EVENT_CONFLICT);
            throw new CommandException(MESSAGE_DEADLINE_EVENT_CONFLICT);
        }
        if (hasDeadlineValue && hasDurationValue) {
            logger.log(Level.INFO, MESSAGE_DEADLINE_DURATION_CONFLICT);
            throw new CommandException(MESSAGE_DEADLINE_DURATION_CONFLICT);
        }
        if (hasDeadlineValue & hasRecurringScheduleValue) {
            logger.log(Level.INFO, MESSAGE_DEADLINE_RECURRING_SCHEDULE_CONFLICT);
            throw new CommandException(MESSAGE_DEADLINE_RECURRING_SCHEDULE_CONFLICT);
        }
    }


}
