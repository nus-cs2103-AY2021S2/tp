package seedu.booking.model.venue;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.booking.testutil.VenueBuilder;

class VenueDescContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        VenueDescContainsKeywordsPredicate firstPredicate =
                new VenueDescContainsKeywordsPredicate(firstPredicateKeywordList);
        VenueDescContainsKeywordsPredicate secondPredicate =
                new VenueDescContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        VenueDescContainsKeywordsPredicate firstPredicateCopy =
                new VenueDescContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different venue descriptions -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_venueDescContainsKeywords_returnsTrue() {
        // One keyword
        VenueDescContainsKeywordsPredicate predicate =
                new VenueDescContainsKeywordsPredicate(Collections.singletonList("Hall"));
        assertTrue(predicate.test(new VenueBuilder().withDescription("Victoria Hall").build()));

        // Multiple keywords
        predicate = new VenueDescContainsKeywordsPredicate(Arrays.asList("Victoria", "Hall"));
        assertTrue(predicate.test(new VenueBuilder().withDescription("Victoria Hall").build()));

        // Only one matching keyword
        predicate = new VenueDescContainsKeywordsPredicate(Arrays.asList("Victoria", "Field"));
        assertTrue(predicate.test(new VenueBuilder().withDescription("Victoria Hall").build()));

        // Mixed-case keywords
        predicate = new VenueDescContainsKeywordsPredicate(Arrays.asList("ViCToria", "FIEld"));
        assertTrue(predicate.test(new VenueBuilder().withDescription("Victoria Hall").build()));
    }

    @Test
    public void test_venueDescDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        VenueDescContainsKeywordsPredicate predicate = new VenueDescContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new VenueBuilder().withDescription("Alice").build()));

        // Non-matching keyword
        predicate = new VenueDescContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new VenueBuilder().withDescription("Alice Bob").build()));
    }
}
