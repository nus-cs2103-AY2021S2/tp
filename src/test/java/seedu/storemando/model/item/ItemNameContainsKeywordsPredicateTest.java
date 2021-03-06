package seedu.storemando.model.item;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.storemando.testutil.ItemBuilder;

public class ItemNameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        ItemNameContainsKeywordsPredicate firstPredicate =
            new ItemNameContainsKeywordsPredicate(firstPredicateKeywordList, false);
        ItemNameContainsKeywordsPredicate secondPredicate =
            new ItemNameContainsKeywordsPredicate(secondPredicateKeywordList, false);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ItemNameContainsKeywordsPredicate firstPredicateCopy =
            new ItemNameContainsKeywordsPredicate(firstPredicateKeywordList, false);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different item -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        ItemNameContainsKeywordsPredicate predicate =
            new ItemNameContainsKeywordsPredicate(Collections.singletonList("Alice"), false);
        assertTrue(predicate.test(new ItemBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new ItemNameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"), false);
        assertTrue(predicate.test(new ItemBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        predicate = new ItemNameContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"), false);
        assertTrue(predicate.test(new ItemBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new ItemNameContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"), false);
        assertTrue(predicate.test(new ItemBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        ItemNameContainsKeywordsPredicate predicate = new ItemNameContainsKeywordsPredicate(Collections.emptyList(),
            false);
        assertFalse(predicate.test(new ItemBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new ItemNameContainsKeywordsPredicate(Arrays.asList("Carol"), false);
        assertFalse(predicate.test(new ItemBuilder().withName("Alice Bob").build()));

        // Keywords match quantity, expirydate and location, but does not match name
        predicate = new ItemNameContainsKeywordsPredicate(Arrays.asList("12345", "alice@email.com", "Main", "Street"),
            false);
        assertFalse(predicate.test(new ItemBuilder().withName("Alice").withQuantity("12345")
            .withExpiryDate("alice@email.com").withLocation("Main Street").build()));
    }
}
