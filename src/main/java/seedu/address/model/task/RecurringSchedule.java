package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Task's email in the planner.
 * Guarantees: immutable; is valid as declared in {@link #isValidRecurringSchedule(String)}
 */
public class RecurringSchedule {

    // alphanumeric and special characters
    public static final String VALIDATION_REGEX = ".*"; // accepts any character except line breaks
    public static final String MESSAGE_CONSTRAINTS = "Recurring Schedule should consists of end date,"
            + " days of week and frequency of week ";
    //private static final String SPECIAL_CHARACTERS = "!#$%&'*+/=?`{|}~^.-";
    public final String value;

    /**
     * Constructs an {@code RecurringSchedule}.
     *
     * @param recurringSchedule A valid recurringSchedule text.
     */
    public RecurringSchedule(String recurringSchedule) {
        requireNonNull(recurringSchedule);
        checkArgument(isValidRecurringSchedule(recurringSchedule), MESSAGE_CONSTRAINTS);
        value = recurringSchedule;
    }

    /**
     * Returns if a given string is a valid email.
     */
    public static boolean isValidRecurringSchedule(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RecurringSchedule // instanceof handles nulls
                && value.equals(((RecurringSchedule) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
