package seedu.timeforwheels.model.customer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.timeforwheels.testutil.CustomerBuilder;

public class AttributeContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        AttributeContainsKeywordsPredicate firstPredicate =
            new AttributeContainsKeywordsPredicate(firstPredicateKeywordList);
        AttributeContainsKeywordsPredicate secondPredicate =
            new AttributeContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        AttributeContainsKeywordsPredicate firstPredicateCopy =
            new AttributeContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different customer -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        AttributeContainsKeywordsPredicate predicate =
            new AttributeContainsKeywordsPredicate(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new CustomerBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new AttributeContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new CustomerBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        predicate = new AttributeContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new CustomerBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new AttributeContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new CustomerBuilder().withName("Alice Bob").build()));

        //Address with only one keyword
        predicate = new AttributeContainsKeywordsPredicate(Collections.singletonList("Tampines"));
        assertTrue(predicate.test(new CustomerBuilder().withAddress("Tampines Street").build()));

        //Address with only one matching keyword
        predicate = new AttributeContainsKeywordsPredicate(Arrays.asList("Tampines", "Gardens"));
        assertTrue(predicate.test(new CustomerBuilder().withAddress("Tampines Street").build()));

        // Address with multiple keywords
        predicate = new AttributeContainsKeywordsPredicate(Arrays.asList("Tampines", "Street"));
        assertTrue(predicate.test(new CustomerBuilder().withAddress("Tampines Street").build()));

        // Address with mixed-case keywords
        predicate = new AttributeContainsKeywordsPredicate(Arrays.asList("TaMpInEs", "STrEeT"));
        assertTrue(predicate.test(new CustomerBuilder().withAddress("Tampines Street").build()));

        //Address with only one keyword
        predicate = new AttributeContainsKeywordsPredicate(Collections.singletonList("12345"));
        assertTrue(predicate.test(new CustomerBuilder().withPhone("12345").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        AttributeContainsKeywordsPredicate predicate = new AttributeContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new CustomerBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new AttributeContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new CustomerBuilder().withName("Alice Bob").build()));

        // Keywords does not match name
        predicate = new AttributeContainsKeywordsPredicate(Arrays.asList("12345", "alice@email.com", "Main", "Street"));

        assertFalse(predicate.test(new CustomerBuilder().withName("Alice").build()));
    }
}
