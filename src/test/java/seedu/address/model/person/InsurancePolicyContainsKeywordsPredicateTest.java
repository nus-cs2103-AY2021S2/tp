//@@author wongkokian
package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class InsurancePolicyContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        InsurancePolicyContainsKeywordsPredicate firstPredicate =
                new InsurancePolicyContainsKeywordsPredicate(firstPredicateKeywordList);
        InsurancePolicyContainsKeywordsPredicate secondPredicate =
                new InsurancePolicyContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        InsurancePolicyContainsKeywordsPredicate firstPredicateCopy =
                new InsurancePolicyContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_insurancePolicyContainsKeywords_returnsTrue() {
        // One keyword
        InsurancePolicyContainsKeywordsPredicate predicate =
                new InsurancePolicyContainsKeywordsPredicate(Collections.singletonList("P12345"));
        assertTrue(predicate.test(new PersonBuilder().withPolicies("P12345").build()));

        // Multiple keywords
        predicate = new InsurancePolicyContainsKeywordsPredicate(Arrays.asList("12", "345"));
        assertTrue(predicate.test(new PersonBuilder().withPolicies("P12345").build()));

        // Only one matching keyword
        predicate = new InsurancePolicyContainsKeywordsPredicate(Arrays.asList("987", "P12"));
        assertTrue(predicate.test(new PersonBuilder().withPolicies("P12345").build()));

        // Mixed-case keywords
        predicate = new InsurancePolicyContainsKeywordsPredicate(Arrays.asList("p123", "P1234"));
        assertTrue(predicate.test(new PersonBuilder().withPolicies("P12345").build()));
    }

    @Test
    public void test_insurancePolicyDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        InsurancePolicyContainsKeywordsPredicate predicate =
                new InsurancePolicyContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withPolicies("P12345").build()));

        // Non-matching keyword
        predicate = new InsurancePolicyContainsKeywordsPredicate(Arrays.asList("P98765"));
        assertFalse(predicate.test(new PersonBuilder().withPolicies("P12345").build()));

        // Keywords match phone, email and address, but does not match insurance policy
        predicate = new InsurancePolicyContainsKeywordsPredicate(
                Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").withPolicies("P98765").build()));
    }
}
