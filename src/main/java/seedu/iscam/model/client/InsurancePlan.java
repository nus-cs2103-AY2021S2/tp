package seedu.iscam.model.client;

import static java.util.Objects.requireNonNull;
import static seedu.iscam.commons.util.AppUtil.checkArgument;

/**
 * Represents a Client's insurance plan in the address book.
*/
public class InsurancePlan {

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";
    public static final String MESSAGE_CONSTRAINTS =
            "Insurance Plan should not start with a space and only contain alphanumeric characters";

    public final String planName;

    public InsurancePlan(String planName) {
        requireNonNull(planName);
        checkArgument(isValidPlan(planName), MESSAGE_CONSTRAINTS);
        this.planName = planName;
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidPlan(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return planName;
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
}
