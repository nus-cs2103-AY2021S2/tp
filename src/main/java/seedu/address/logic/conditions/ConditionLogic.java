package seedu.address.logic.conditions;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.task.Task;

/**
 * ConditionLogic acts as the facade class in the facade pattern to provide Command classes with the functionality
 * of condition and constraint checkers.
 */
public class ConditionLogic {
    private final Task task;
    private final RecurringScheduleVerifier recurringScheduleVerifier;
    private final DateVerifier dateVerifier;
    private final ConstraintManager constraintManager;

    /**
     * Constructor for the ConditionLogic class.
     *
     * @param task The task to be checked.
     */
    public ConditionLogic(Task task) {
        this.task = task;
        this.recurringScheduleVerifier = new RecurringScheduleVerifier(task);
        this.dateVerifier = new DateVerifier(task);
        this.constraintManager = new ConstraintManager(task);
    }

    /**
     * Verifies that the given index is not out of bounds.
     *
     * @param index Index given by a user input.
     * @param lastShownList the last shown list on the user's screen.
     * @throws CommandException
     */
    public static void verifyIndex(Index index, List<Task> lastShownList) throws CommandException {
        IndexManager.verifyIndex(index, lastShownList);
    }

    /**
     * Checks if the given end date in recurring schedule has expired.
     *
     * @throws CommandException
     */
    public void checkExpiredEndDate() throws CommandException {
        recurringScheduleVerifier.checkExpiredEndDate();
    }

    /**
     * Checks whether there are any matching recurring dates being generated.
     *
     * @throws CommandException
     */
    public void checkMatchingRecurringDates() throws CommandException {
        recurringScheduleVerifier.checkMatchingRecurringDates();
    }


    /**
     * Checks if the given end date in recurring schedule has been more than 6 months of current system date.
     *
     * @throws CommandException
     */
    public void checkEndDateMoreThan6Months() throws CommandException {
        recurringScheduleVerifier.checkEndDateMoreThan6Months();
    }

    /**
     * Checks if the end date in recurring schedule is valid.
     * End date in recurring schedule is valid if months of Feb does not have more than 28 days except leap years and
     * Days did not exceed for months of 30 days (Apr, Jun, Nov, Sep) and 31 days (Jan, Mar, May, Jul, Aug, Oct, Dec)
     *
     * @throws CommandException
     */
    public void checkInvalidEndDateRecurringSchedule() throws CommandException {
        recurringScheduleVerifier.checkInvalidDate();
    }

    /**
     * Checks if the Date is valid.
     * A Date is considered valid if months of Feb does not have more than 28 days except leap years and
     * Days did not exceed for months of 30 days (Apr, Jun, Nov, Sep) and 31 days (Jan, Mar, May, Jul, Aug, Oct, Dec)
     *
     * @throws CommandException
     */
    public void checkInvalidDate() throws CommandException {
        dateVerifier.checkInvalidDate();
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
        constraintManager.enforceAttributeConstraints();
    }

    /**
     * Checks that the title length does not exceeds the max title length.
     *
     * @throws CommandException If the title length exceeds the max title length.
     */
    public void enforceTitleLength() throws CommandException {
        constraintManager.enforceTitleLength();
    }

    /**
     * Checks that a task has a Date attribute.
     *
     * @throws CommandException If the Date attribute is missing.
     */
    public void enforceNonEmptyDate() throws CommandException {
        dateVerifier.enforceNonEmptyDate();
    }

    /**
     * Checks that the Date of a task is not over the current date according to the system.
     *
     * @throws CommandException
     */
    public void enforceDateNotOver() throws CommandException {
        dateVerifier.enforceDateNotOver();
    }

}
