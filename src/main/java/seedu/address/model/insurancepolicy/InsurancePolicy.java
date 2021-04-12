package seedu.address.model.insurancepolicy;

import static java.util.Objects.requireNonNull;

import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * Represents an InsurancePolicy in the address book.
 * Guarantees: immutable;
 */
public class InsurancePolicy {

    public static final String MESSAGE_NO_POLICY = "Currently does not have an active policy";
    /*
    Checks if a String is a valid URL.
    Reused from: https://stackoverflow.com/a/42619410
     */
    public static final Pattern URL_REGEX = Pattern.compile("((ftp|http|https):\\/\\/)?(www.)?(?!.*(ftp|http"
            + "|https|www.))[a-zA-Z0-9_-]+(\\.[a-zA-Z]+)+((\\/)[\\w#]+)*(\\/\\w+\\?[a-zA-Z0-9_]+=\\"
            + "w+(&[a-zA-Z0-9_]+=\\w+)*)?", Pattern.CASE_INSENSITIVE);
    /*
    Checks if String contains angular brackets, which we do not want in a URL.
    Reused from: https://stackoverflow.com/a/4105987
     */
    public static final Pattern ANGULAR_BRACKET_REGEX = Pattern.compile("^[^<>]+$");

    public static final String MESSAGE_CONSTRAINTS = "Policy id should not contain the ; character.";

    public final String policyId;
    private final String policyUrl;

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

        if (otherPolicy.policyUrl == null && policyUrl == null) {
            return policyId.equals(otherPolicy.policyId);
        } else if (otherPolicy.policyUrl != null && policyUrl != null) {
            return policyId.equals(otherPolicy.policyId) && policyUrl.equals(otherPolicy.policyUrl);
        }

        return false;
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
     * Checks if input contains a valid policy ID without '>' character in the ID, and if the URL is of a valid
     * format.
     *
     * @param input policy input to check.
     * @return true if policy input contains a valid policy ID.
     */
    public static boolean isValidPolicyInput(String input) {
        String[] splitByAngularBracket = input.split(">", 2);
        if (splitByAngularBracket[0].contains(";")) {
            return false;
        }
        if (splitByAngularBracket.length == 1) {
            // Return true if length is 1, since no '>' was used, meaning no URL and valid policy ID.
            return true;
        }

        // Due to the variable nature of policy ID names, we only need to check if after splitting, the 2nd item
        // in the array is a valid URL for the entire input to be valid.
        return isValidPolicyUrl(splitByAngularBracket[1]);
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

    /**
     * Checks if a policy input by a user contains a valid URL.
     *
     * @param policyUrl policy URL entered by the user.
     * @return true if the URL is valid.
     */
    public static boolean isValidPolicyUrl(String policyUrl) {
        return URL_REGEX.matcher(policyUrl).find() && ANGULAR_BRACKET_REGEX.matcher(policyUrl).find();
    }
}
