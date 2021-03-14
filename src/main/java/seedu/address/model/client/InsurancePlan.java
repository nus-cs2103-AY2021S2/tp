package seedu.address.model.client;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Client's insurance plan in the address book.
*/
public class InsurancePlan {

    public final String planName;

    public InsurancePlan(String planName) {
        requireNonNull(planName);
        this.planName = planName;
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
