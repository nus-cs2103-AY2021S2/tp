package seedu.address.model.insurancepolicy;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

/**
 * Represents an InsurancePolicy in the address book.
 * Guarantees: immutable;
 */
public class InsurancePolicy {

    public String policyUrl;
    public final String policyId;

    /**
     * Constructs an {@code InsurancePolicy} without URL.
     *
     * @param policyId the Id associated with the policy.
     */
    public InsurancePolicy(String policyId) {
        requireNonNull(policyId);
        this.policyId = policyId;
        this.policyUrl = null;
    }

    /**
     * Constructs an {@code InsurancePolicy} with URL.
     *
     * @param policyId the Id associated with the policy.
     * @param url the URL to the policy document.
     */
    public InsurancePolicy(String policyId, String url) {
        requireNonNull(policyId, url);
        this.policyId = policyId;
        this.policyUrl = url;
    }

    /**
     * Checks if another object is the equal to this policy by comparing policy Id and policy URL.
     *
     * @param other the other object.
     * @return true if the object is equal to this policy.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (!(other instanceof InsurancePolicy)) {
            return false;
        }

        InsurancePolicy otherPolicy = (InsurancePolicy) other;

        try {
            return policyId.equals(otherPolicy.policyId) && policyUrl.equals(otherPolicy.policyUrl);
        } catch (NullPointerException e) {
            return policyId.equals(otherPolicy.policyId);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(policyUrl, policyId);
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + policyId + ']';
    }

    public String getPolicyWithUrl() {
        if (policyUrl == null) {
            return policyId + ": No URL associated with this policy!";
        }

        return policyId + ": " + policyUrl;
    }

    /**
     * Sets policy URL for this insurance policy.
     *
     * @param url to this policy's document.
     */
    public void setPolicyUrl(String url) {
        policyUrl = url;
    }
}
