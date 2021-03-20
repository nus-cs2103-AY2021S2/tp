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
            new ItemNameContainsKeywordsPredicate(firstPredicateKeywordList);
        ItemNameContainsKeywordsPredicate secondPredicate =
            new ItemNameContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ItemNameContainsKeywordsPredicate firstPredicateCopy =
            new ItemNameContainsKeywordsPredicate(firstPredicateKeywordList);
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
            new ItemNameContainsKeywordsPredicate(Collections.singletonList("Apple"));
        assertTrue(predicate.test(new ItemBuilder().withName("Apple Banana").build()));

        // Multiple keywords
        predicate = new ItemNameContainsKeywordsPredicate(Arrays.asList("Apple", "Banana"));
        assertTrue(predicate.test(new ItemBuilder().withName("Apple Banana").build()));

        // Only one matching keyword
        predicate = new ItemNameContainsKeywordsPredicate(Arrays.asList("Bob", "Cherry"));
        assertTrue(predicate.test(new ItemBuilder().withName("Apple Cherry").build()));

        // Mixed-case keywords
        predicate = new ItemNameContainsKeywordsPredicate(Arrays.asList("aPPle", "bANANA"));
        assertTrue(predicate.test(new ItemBuilder().withName("Apple Banana").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        ItemNameContainsKeywordsPredicate predicate = new ItemNameContainsKeywordsPredicate(Collections.emptyList()
        );
        assertFalse(predicate.test(new ItemBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new ItemNameContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new ItemBuilder().withName("Alice Bob").build()));

        // Keywords match quantity, expiryDate and location, but does not match name
        predicate = new ItemNameContainsKeywordsPredicate(Arrays.asList("12345", "2020-10-10", "Main", "Street")
        );
        assertFalse(predicate.test(new ItemBuilder().withName("Alice").withQuantity("12345")
            .withExpiryDate("2020-10-10").withLocation("Main Street").build()));
    }
}
