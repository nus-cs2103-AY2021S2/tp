package seedu.address.model.pool;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPools.OFFICEPOOL;
import static seedu.address.testutil.TypicalPools.WORKPOOL;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

public class PooledPassengerContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        PooledPassengerContainsKeywordsPredicate firstPredicate =
                new PooledPassengerContainsKeywordsPredicate(firstPredicateKeywordList);
        PooledPassengerContainsKeywordsPredicate secondPredicate =
                new PooledPassengerContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PooledPassengerContainsKeywordsPredicate firstPredicateCopy =
                new PooledPassengerContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different passenger -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        PooledPassengerContainsKeywordsPredicate predicate =
                new PooledPassengerContainsKeywordsPredicate(Collections.singletonList("Alice"));
        assertTrue(predicate.test(WORKPOOL));

        // Multiple keywords
        predicate = new PooledPassengerContainsKeywordsPredicate(Arrays.asList("Alice", "George"));
        assertTrue(predicate.test(WORKPOOL));

        // Only one matching keyword
        predicate = new PooledPassengerContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(WORKPOOL));

        // Mixed-case keywords
        predicate = new PooledPassengerContainsKeywordsPredicate(Arrays.asList("aLIce", "GeorGe"));
        assertTrue(predicate.test(WORKPOOL));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        PooledPassengerContainsKeywordsPredicate predicate =
                new PooledPassengerContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(OFFICEPOOL));

        // Non-matching keyword
        predicate = new PooledPassengerContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(OFFICEPOOL));

        // Keywords match Driver's name, tag and TripDay, but does not match any passenger's name
        predicate = new PooledPassengerContainsKeywordsPredicate(Arrays.asList("Alice", "friends", "Monday"));
        assertFalse(predicate.test(OFFICEPOOL));
    }
}
