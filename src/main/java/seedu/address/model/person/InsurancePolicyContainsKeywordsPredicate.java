//@@author wongkokian
package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.model.insurancepolicy.InsurancePolicy;

/**
 * Tests that a {@code Person}'s {@code InsurancePolicy} matches any of the keywords given.
 */
public class InsurancePolicyContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public InsurancePolicyContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    static boolean checkPolicies(List<InsurancePolicy> policies, String keyword) {
        boolean containsKeyword = false;
        for (InsurancePolicy policy : policies) {
            if (policy.policyId.toLowerCase().contains(keyword.toLowerCase())) {
                containsKeyword = true;
                break;
            }
        }
        return containsKeyword;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> checkPolicies(person.getPolicies(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof InsurancePolicyContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((InsurancePolicyContainsKeywordsPredicate) other).keywords)); // state check
    }

}
