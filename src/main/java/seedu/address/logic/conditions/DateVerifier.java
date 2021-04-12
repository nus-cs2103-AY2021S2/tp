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
