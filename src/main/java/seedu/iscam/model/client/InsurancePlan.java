package seedu.iscam.model.client;

import static java.util.Objects.requireNonNull;
import static seedu.iscam.commons.util.AppUtil.checkArgument;

/**
 * Represents a Client's insurance plan in the client book.
 */
public class InsurancePlan {

    public static final String MESSAGE_CONSTRAINTS =
            "Insurance Plan should only contain alphanumeric characters";
    public static final String MESSAGE_LENGTH_CONSTRAINTS = "Insurance plans should not be longer than 50 characters.";
    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";
    private static final int MESSAGE_MAX_LENGTH = 50;
    public final String planName;

    /**
     * Constructs an {@code InsurancePlan}.
     *
     * @param planName A valid Insurance Plan name.
     */
    public InsurancePlan(String planName) {
        requireNonNull(planName);
        checkArgument(isValidPlan(planName), MESSAGE_CONSTRAINTS);
        checkArgument(isValidLength(planName), MESSAGE_LENGTH_CONSTRAINTS);
        this.planName = planName;
    }

    /**
     * Returns true if a given string is a valid plan.
     */
    public static boolean isValidPlan(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if a given plan has valid length.
     */
    public static boolean isValidLength(String test) {
        return test.length() <= MESSAGE_MAX_LENGTH;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof InsurancePlan // instanceof handles nulls
                && planName.equals(((InsurancePlan) other).planName)); // state check
    }

    @Override
    public int hashCode() {
        return planName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    @Override
    public String toString() {
        return '[' + planName + ']';
    }
}
