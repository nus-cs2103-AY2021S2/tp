package seedu.address.logic.conditions;

import static seedu.address.model.task.attributes.RecurringSchedule.INVALID_END_DATE;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.task.AttributeManager;
import seedu.address.model.task.Task;

public class DateVerifier {
    public static final String MESSAGE_INVALID_DATE_RANGE = "Task has invalid date input.\n\nNote: Months of Apr, Jun, "
            + "Sep, Nov has only 30 days while Feb has only 28 days with leap years (mulitples of 4) having 29 days";

    private static final Logger logger = LogsCenter.getLogger(DateVerifier.class);

    /**
     * Checks if the given date is within a valid range.
     *
     * @param task The task added or edited by the user.
     * @throws CommandException
     */
    public static void checkInvalidDateRange(Task task) throws CommandException {
        if (new AttributeManager(task).hasInvalidDateRange()) {
            logger.info("Invalid date detected: " + MESSAGE_INVALID_DATE_RANGE);

            throw new CommandException(MESSAGE_INVALID_DATE_RANGE);
        }
    }

    /**
     * Checks if the task is already over the current date.
     *
     * @param task The task added or edited by the user.
     * @throws CommandException
     */
    public static void checkForExpiredDate(Task task) throws CommandException {
        if (new AttributeManager(task).hasExpired()) {
            logger.info("Invalid date detected: " + INVALID_END_DATE);
            throw new CommandException(INVALID_END_DATE);
        }
    }
}
