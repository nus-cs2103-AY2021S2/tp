package seedu.booking.model.venue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.booking.testutil.VenueBuilder;

class VenueTagContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        String firstPredicateKeywordList = "tag1";
        String secondPredicateKeywordList = "tag2";

        VenueTagContainsKeywordsPredicate firstPredicate =
                new VenueTagContainsKeywordsPredicate(firstPredicateKeywordList);
        VenueTagContainsKeywordsPredicate secondPredicate =
                new VenueTagContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        VenueTagContainsKeywordsPredicate firstPredicateCopy =
                new VenueTagContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different venue tags -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void getTagName_returnsCorrectTagName_sameTagName() {
        // Same keyword
        VenueTagContainsKeywordsPredicate predicate = new VenueTagContainsKeywordsPredicate("student");
        assertEquals(predicate.getTagName(), "student");
    }

    @Test
    public void test_venueTagContainsKeyword_returnsTrue() {
        // One keyword
        VenueTagContainsKeywordsPredicate predicate = new VenueTagContainsKeywordsPredicate("student");
        assertTrue(predicate.test(new VenueBuilder().withTags("student").build()));

        // Mixed-case keywords
        predicate = new VenueTagContainsKeywordsPredicate("stuDEnt");
        assertTrue(predicate.test(new VenueBuilder().withTags("student").build()));
    }

    @Test
    public void test_venueTagDoesNotContainKeyword_returnsFalse() {
        // Non-matching keyword
        VenueTagContainsKeywordsPredicate predicate = new VenueTagContainsKeywordsPredicate("Carol");
        assertFalse(predicate.test(new VenueBuilder().withTags("Alice").build()));

    }
}
