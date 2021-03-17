package seedu.address.model.insurancepolicy;

import static java.util.Objects.requireNonNull;

/**
 * Represents an InsurancePolicy in the address book.
 * Guarantees: immutable;
 */
public class InsurancePolicy {

    public final String policyId;

    /**
     * Constructs an {@code InsurancePolicy}.
     *
     * @param policyId the Id associated with the policy.
     */
    public InsurancePolicy(String policyId) {
        requireNonNull(policyId);
        this.policyId = policyId;
    }

    /**
     * Checks if another object is the equal to this policy by comparing policy Id.
     * @param other the other object.
     * @return true if the object is equal to this policy.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof InsurancePolicy // instanceof handles nulls
                && policyId.equals(((InsurancePolicy) other).policyId)); // state check
    }

    @Override
    public int hashCode() {
        return policyId.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + policyId + ']';
    }

}
