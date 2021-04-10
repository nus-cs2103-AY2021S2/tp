package seedu.address.logic.conditions;

import static seedu.address.model.task.attributes.RecurringSchedule.INVALID_END_DATE;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.task.AttributeManager;
import seedu.address.model.task.Task;

public class RecurringScheduleVerifier {
    public static final String MESSAGE_INVALID_DATE_RANGE = "Task has invalid date input.\n\nNote: Months of Apr, Jun, "
            + "Sep, Nov has only 30 days while Feb has only 28 days with leap years (mulitples of 4) having 29 days";

    private static final Logger logger = LogsCenter.getLogger(RecurringScheduleVerifier.class);

    private Task task;
    private AttributeManager attributeManager;

    /**
     * Constructor for RecurringScheduleVerifier
     *
     * @param task Task to be verified.
     */
    public RecurringScheduleVerifier(Task task) {
        this.task = task;
        this.attributeManager = new AttributeManager(task);
    }

    /**
     * Checks if the given date is within a valid range.
     *
     * @throws CommandException
     */
    public void checkInvalidDateRange() throws CommandException {
        if (attributeManager.hasInvalidDateRange()) {
            logger.info("Invalid date detected: " + MESSAGE_INVALID_DATE_RANGE);

            throw new CommandException(MESSAGE_INVALID_DATE_RANGE);
        }
    }

    /**
     * Checks if the task is already over the current date.
     *
     * @throws CommandException
     */
    public void checkForExpiredDate() throws CommandException {
        if (attributeManager.hasExpired()) {
            logger.info("Invalid date detected: " + INVALID_END_DATE);
            throw new CommandException(INVALID_END_DATE);
        }
    }
}
