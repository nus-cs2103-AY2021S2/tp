package seedu.address.logic.conditions;

import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.task.AttributeManager;
import seedu.address.model.task.Task;

/**
 * Enforces the conditions for a task's Date attribute.
 */
public class DateVerifier {
    public static final String MESSAGE_EMPTY_DATE = "The task selected has no Date attribute.";
    private static final String MESSAGE_INVALID_DATE = "Task has invalid date input."
            + "\n\nNote: Months of Apr, Jun, "
            + "Sep, Nov has only 30 days while Feb has only 28 days with leap years (mulitples of 4) having 29 days";
    private static final String MESSAGE_DATE_OVER = "The task selected is already over.";
    private static final Logger logger = LogsCenter.getLogger(DateVerifier.class);

    private AttributeManager attributeManager;

    /**
     * Constructor for the DateVerifier.
     *
     * @param task The task with Date to be verified.
     */
    public DateVerifier(Task task) {
        this.attributeManager = new AttributeManager(task);
    }

    /**
     * Checks that a task has a Date attribute.
     *
     * @throws CommandException If the Date attribute is missing.
     */
    public void enforceNonEmptyDate() throws CommandException {
        if (attributeManager.isEmptyDate()) {
            logger.info(MESSAGE_EMPTY_DATE);
            throw new CommandException(MESSAGE_EMPTY_DATE);
        }
    }

    /**
     * Checks if the Date is valid.
     * A Date is considered valid if months of Feb does not have more than 28 days except leap years and
     * Days did not exceed for months of 30 days (Apr, Jun, Nov, Sep) and 31 days (Jan, Mar, May, Jul, Aug, Oct, Dec)
     *
     * @throws CommandException
     */
    public void checkInvalidDate() throws CommandException {
        if (attributeManager.hasInvalidDate()) {
            logger.info("Invalid Date detected: " + MESSAGE_INVALID_DATE);
            throw new CommandException(MESSAGE_INVALID_DATE);
        }
    }

    /**
     * Checks that the Date of a task is not over the current date according to the system.
     *
     * @throws CommandException
     */
    public void enforceDateNotOver() throws CommandException {
        assert(!attributeManager.isEmptyDate());

        if (attributeManager.dateOver()) {
            logger.info(MESSAGE_DATE_OVER);
            throw new CommandException(MESSAGE_DATE_OVER);
        }
    }
}
