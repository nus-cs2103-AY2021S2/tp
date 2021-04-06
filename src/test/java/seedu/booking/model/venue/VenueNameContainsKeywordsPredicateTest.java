package seedu.booking.model.venue;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.booking.testutil.VenueBuilder;

class VenueNameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("Victoria");
        List<String> secondPredicateKeywordList = Arrays.asList("Victoria", "Hall");

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
    public void test_venueNameContainsKeywords_returnsTrue() {
        // One keyword
        VenueNameContainsKeywordsPredicate predicate =
                new VenueNameContainsKeywordsPredicate(Collections.singletonList("Victoria"));
        assertTrue(predicate.test(new VenueBuilder().withName("Victoria Hall").build()));

        // Multiple keywords
        predicate = new VenueNameContainsKeywordsPredicate(Arrays.asList("Victoria", "Hall"));
        assertTrue(predicate.test(new VenueBuilder().withName("Victoria Hall").build()));

        // Only one matching keyword
        predicate = new VenueNameContainsKeywordsPredicate(Arrays.asList("Hall", "Field"));
        assertTrue(predicate.test(new VenueBuilder().withName("Victoria Hall").build()));

        // Mixed-case keywords
        predicate = new VenueNameContainsKeywordsPredicate(Arrays.asList("VicTOria", "hALL"));
        assertTrue(predicate.test(new VenueBuilder().withName("Victoria Hall").build()));
    }

    @Test
    public void test_venueNameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        VenueNameContainsKeywordsPredicate predicate = new VenueNameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new VenueBuilder().withName("Victoria").build()));

        // Non-matching keyword
        predicate = new VenueNameContainsKeywordsPredicate(Arrays.asList("Field"));
        assertFalse(predicate.test(new VenueBuilder().withName("Victoria hall").build()));
    }
}
