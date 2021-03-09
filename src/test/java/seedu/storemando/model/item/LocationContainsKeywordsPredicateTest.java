package seedu.storemando.model.item;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.storemando.testutil.ItemBuilder;

public class LocationContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        LocationContainsKeywordsPredicate firstPredicate =
            new LocationContainsKeywordsPredicate(firstPredicateKeywordList);
        LocationContainsKeywordsPredicate secondPredicate =
            new LocationContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        LocationContainsKeywordsPredicate firstPredicateCopy =
            new LocationContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different item -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_locationContainsKeywords() {
        // One keyword
        LocationContainsKeywordsPredicate predicate =
            new LocationContainsKeywordsPredicate(Collections.singletonList("Apple"));
        assertTrue(predicate.test(new ItemBuilder().withLocation("Apple Banana").build()));

        // Multiple keywords
        predicate = new LocationContainsKeywordsPredicate(Arrays.asList("Apple", "Banana"));
        assertTrue(predicate.test(new ItemBuilder().withLocation("Apple Banana").build()));

        // Only one matching keyword
        predicate = new LocationContainsKeywordsPredicate(Arrays.asList("Apple", "Cherry"));
        assertTrue(predicate.test(new ItemBuilder().withLocation("Apple Cherry").build()));

        // Mixed-case keywords
        predicate = new LocationContainsKeywordsPredicate(Arrays.asList("aPPle", "bANaNa"));
        assertTrue(predicate.test(new ItemBuilder().withLocation("Apple Banana").build()));

        // Not all matching keywords
        predicate = new LocationContainsKeywordsPredicate(Arrays.asList("Apple", "Cherry"));
        assertFalse(predicate.test(new ItemBuilder().withLocation("Apple").build()));
    }

    @Test
    public void test_locationDoesNotContainKeywords() {
        // Zero keywords
        LocationContainsKeywordsPredicate predicate = new LocationContainsKeywordsPredicate(Collections.emptyList());
        assertTrue(predicate.test(new ItemBuilder().withLocation("Apple").build()));

        // Non-matching keyword
        predicate = new LocationContainsKeywordsPredicate(Arrays.asList("Cherry"));
        assertFalse(predicate.test(new ItemBuilder().withLocation("Apple Banana").build()));

        // Keywords match quantity, expirydate and name, but does not match location
        predicate = new LocationContainsKeywordsPredicate(Arrays.asList("Alice", "2020-01-11", "Living", "Room"));
        assertFalse(predicate.test(new ItemBuilder().withName("Alice").withQuantity("12345")
            .withExpiryDate("2020-02-11").withLocation("Living room").build()));
    }
}
