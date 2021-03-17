package seedu.booking.model.booking;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.booking.testutil.VenueBuilder;

public class VenueNameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        VenueNameContainsKeywordsPredicate firstPredicate =
                new VenueNameContainsKeywordsPredicate(firstPredicateKeywordList);
        VenueNameContainsKeywordsPredicate secondPredicate =
                new VenueNameContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        VenueNameContainsKeywordsPredicate firstPredicateCopy =
                new VenueNameContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different venues -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        VenueNameContainsKeywordsPredicate predicate =
                new VenueNameContainsKeywordsPredicate(Collections.singletonList("Hall"));
        assertTrue(predicate.test(new VenueBuilder().withName("Hall").build()));

        // Multiple keywords
        predicate = new VenueNameContainsKeywordsPredicate(Arrays.asList("Sports", "Hall"));
        assertTrue(predicate.test(new VenueBuilder().withName("Sports Hall").build()));

        // Mixed-case keywords
        predicate = new VenueNameContainsKeywordsPredicate(Arrays.asList("sPorTs", "hAll"));
        assertTrue(predicate.test(new VenueBuilder().withName("Sports Hall").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        VenueNameContainsKeywordsPredicate predicate = new VenueNameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new VenueBuilder().withName("Sports Hall").build()));

        // Non-matching keyword
        predicate = new VenueNameContainsKeywordsPredicate(Arrays.asList("Court"));
        assertFalse(predicate.test(new VenueBuilder().withName("Hall").build()));
    }
}
