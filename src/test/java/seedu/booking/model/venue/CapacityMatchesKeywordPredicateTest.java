package seedu.booking.model.venue;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.booking.testutil.VenueBuilder;

class CapacityMatchesKeywordPredicateTest {

    @Test
    public void equals() {
        Integer firstPredicateKeyword = 50;
        Integer secondPredicateKeyword = 60;

        CapacityMatchesKeywordPredicate firstPredicate = new CapacityMatchesKeywordPredicate(firstPredicateKeyword);
        CapacityMatchesKeywordPredicate secondPredicate = new CapacityMatchesKeywordPredicate(secondPredicateKeyword);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        CapacityMatchesKeywordPredicate firstPredicateCopy = new CapacityMatchesKeywordPredicate(firstPredicateKeyword);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different venues -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_capacityMatchesKeyword_returnsTrue() {
        // One keyword
        CapacityMatchesKeywordPredicate predicate = new CapacityMatchesKeywordPredicate(50);
        assertTrue(predicate.test(new VenueBuilder().withCapacity(50).build()));
    }

    @Test
    public void test_capacityDoesNotMatchKeyword_returnsFalse() {
        // Non-matching keyword
        CapacityMatchesKeywordPredicate predicate = new CapacityMatchesKeywordPredicate(50);
        assertFalse(predicate.test(new VenueBuilder().withCapacity(40).build()));
    }
}
