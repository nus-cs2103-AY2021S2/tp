package seedu.storemando.model.item;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.storemando.testutil.ItemBuilder;

public class ItemNameContainsPartialKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        ItemNameContainsPartialKeywordsPredicate firstPredicate =
            new ItemNameContainsPartialKeywordsPredicate(firstPredicateKeywordList);
        ItemNameContainsPartialKeywordsPredicate secondPredicate =
            new ItemNameContainsPartialKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ItemNameContainsPartialKeywordsPredicate firstPredicateCopy =
            new ItemNameContainsPartialKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different item -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsPartialKeywords_returnsTrue() {
        // One Partial keyword matching
        ItemNameContainsPartialKeywordsPredicate predicate =
            new ItemNameContainsPartialKeywordsPredicate(Collections.singletonList("App"));
        assertTrue(predicate.test(new ItemBuilder().withName("Apple Banana").build()));

        // Multiple Partial keywords matching
        predicate = new ItemNameContainsPartialKeywordsPredicate(Arrays.asList("App", "Bana"));
        assertTrue(predicate.test(new ItemBuilder().withName("Apple Banana").build()));

        // Only one matching partial keyword
        predicate = new ItemNameContainsPartialKeywordsPredicate(Arrays.asList("Bo", "Cher"));
        assertTrue(predicate.test(new ItemBuilder().withName("Apple Cherry").build()));

        // Mixed-case partial keywords matching
        predicate = new ItemNameContainsPartialKeywordsPredicate(Arrays.asList("aPP", "bANA"));
        assertTrue(predicate.test(new ItemBuilder().withName("Apple Banana").build()));
    }

    @Test
    public void test_nameDoesNotContainPartialKeywords_returnsFalse() {
        // Zero keywords
        ItemNameContainsPartialKeywordsPredicate predicate = new ItemNameContainsPartialKeywordsPredicate(Arrays.asList(
            "Ban")
        );
        assertFalse(predicate.test(new ItemBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new ItemNameContainsPartialKeywordsPredicate(Arrays.asList("Car"));
        assertFalse(predicate.test(new ItemBuilder().withName("Alice Bob").build()));

        // Keywords match quantity, expiryDate and location, but does not match name
        predicate = new ItemNameContainsPartialKeywordsPredicate(Arrays.asList("12345", "2020-10-10", "Main", "Street")
        );
        assertFalse(predicate.test(new ItemBuilder().withName("Alice").withQuantity("12345")
            .withExpiryDate("2020-10-10").withLocation("Main Street").build()));
    }
}
