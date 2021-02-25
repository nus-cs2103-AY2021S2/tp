package seedu.address.model.policy;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Policy in the address book.
 * Guarantees: immutable;
 */
public class Policy {

    public final String policyId;

    /**
     * Constructs a {@code Policy}.
     *
     * @param policyId the Id associatied with the policy.
     */
    public Policy(String policyId) {
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
                || (other instanceof Policy // instanceof handles nulls
                && policyId.equals(((Policy) other).policyId)); // state check
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