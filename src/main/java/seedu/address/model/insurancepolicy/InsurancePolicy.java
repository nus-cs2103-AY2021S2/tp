package seedu.address.model.insurancepolicy;

import static java.util.Objects.requireNonNull;

import java.util.Objects;
import java.util.Optional;

/**
 * Represents an InsurancePolicy in the address book.
 * Guarantees: immutable;
 */
public class InsurancePolicy {

    public static final String MESSAGE_CONSTRAINTS = "PolicyIDs should not contain '>'!. URLs should be "
            + "preceded by '>' after the PolicyID.";
    public static final String MESSAGE_NO_POLICY = "Currently does not have an active policy";
    public final String policyId;
    private String policyUrl;

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
        this.policyId = policyId;
        this.policyUrl = url;
    }

    /**
     * Retrieves the {@code policyUrl} associated with this policy.
     *
     * @return the {@code policyUrl} in {@code Optional<String>}.
     */
    public Optional<String> getOptionalPolicyUrl() {
        return Optional.ofNullable(policyUrl);
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
        if (policyUrl == null) {
            return policyId + ": No URL!";
        }
        return policyId + ": " + policyUrl;
    }

    /**
     * Checks if input contains a valid policy ID without '>' character in the ID.
     *
     * @param input policy input to check.
     * @return true if policy input contains a valid policy ID.
     */
    public static boolean isValidPolicyId(String input) {
        String[] splitByAngularBracket = input.split(">", 2);

        if (splitByAngularBracket.length == 1) {
            // return true if length is 1, since no '>' was used, meaning no URL and valid policy ID.
            return true;
        }

        // If input is of a correct format, then splitting by '>' would give us policy ID in the 0th index,
        // and policy URL in the 1st index.

        // By definition, URLs should not contain angular brackets, as they are usually used as delimiters around
        // URLs in free text. Hence our choice to use the '>' character as delimiter here.

        // Thus, we want to check if the URL contains any '>' which was what the input should have been split by.

        String possibleUrl = splitByAngularBracket[1];

        for (int i = 0; i < possibleUrl.length(); i++) {
            if (possibleUrl.charAt(i) == '>') {
                return false;
            }
        }

        return true;
    }

    /**
     * Checks if a policy input by user contains a URL associated with the policy.
     *
     * @param test policy input split by our delimiter '>'.
     * @return true if input contains URL.
     */
    public static boolean hasPolicyUrl(String[] test) {
        return test.length == 2;
    }
}
