package seedu.address.logic.conditions;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.task.AttributeManager;
import seedu.address.model.task.Task;

public class RecurringScheduleVerifier {
    private static final String MESSAGE_INVALID_END_DATE = "Task has invalid date input in recurring schedule."
            + "\n\nNote: Months of Apr, Jun, "
            + "Sep, Nov has only 30 days while Feb has only 28 days with leap years (mulitples of 4) having 29 days";

    private static final String MESSAGE_EXPIRED_END_DATE = "End date in recurring schedule should be ahead"
            + "of current date !!!";

    private static final String MESSAGE_NO_MATCHING_RECURRING_DATES = "End date in recurring schedule is less than "
            + "a week without matching days found !!!";

    private static final String MESSAGE_MAX_END_DATE_ALLOWED = "End date in recurring schedule is more than 6 months"
            + "ahead, only allowed recurring schedule forecast up to 6 months in advance !!!";

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
     * Checks if the given end date in recurring schedule has expired.
     *
     * @throws CommandException
     */
    public void checkExpiredEndDate() throws CommandException {
        if (attributeManager.hasExpiredEndDate()) {
            logger.info("Expired end date in recurring schedule detected: " + MESSAGE_EXPIRED_END_DATE);

            throw new CommandException(MESSAGE_EXPIRED_END_DATE);
        }
    }

    /**
     * Checks whether there are any matching recurring dates being generated.
     *
     * @throws CommandException
     */
    public void checkMatchingRecurringDates() throws CommandException {
        if (attributeManager.hasNoMatchingRecurringDates()) {
            logger.info("No matching recurring dates in recurring schedule detected: "
                    + MESSAGE_NO_MATCHING_RECURRING_DATES);

            throw new CommandException(MESSAGE_NO_MATCHING_RECURRING_DATES);
        }
    }

    /**
     * Checks if the given end date in recurring schedule has been more than 6 months of current system date.
     *
     * @throws CommandException
     */
    public void checkEndDateMoreThan6Months() throws CommandException {
        if (attributeManager.hasEndDateMoreThan6Months()) {
            logger.info("End date in recurring schedule more than 6 months detected: "
                    + MESSAGE_MAX_END_DATE_ALLOWED);

            throw new CommandException(MESSAGE_MAX_END_DATE_ALLOWED);
        }
    }

    /**
     * Checks if the end date in recurring schedule is valid.
     * End date in recurring schedule is valid if months of Feb does not have more than 28 days except leap years and
     * Days did not exceed for months of 30 days (Apr, Jun, Nov, Sep) and 31 days (Jan, Mar, May, Jul, Aug, Oct, Dec)
     *
     * @throws CommandException
     */
    public void checkInvalidDate() throws CommandException {
        if (attributeManager.hasInvalidDate()) {
            logger.info("Invalid end date in recurring schedule detected: " + MESSAGE_INVALID_END_DATE);
            throw new CommandException(MESSAGE_INVALID_END_DATE);
        }
    }
}
