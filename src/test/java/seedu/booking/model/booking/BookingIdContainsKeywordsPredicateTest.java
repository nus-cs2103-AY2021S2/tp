package seedu.booking.model.booking;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.booking.testutil.BookingBuilder;

public class BookingIdContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        String firstPredicateKeywordList = "1";
        String secondPredicateKeywordList = "2";

        BookingIdContainsKeywordsPredicate firstPredicate =
                new BookingIdContainsKeywordsPredicate(firstPredicateKeywordList);
        BookingIdContainsKeywordsPredicate secondPredicate =
                new BookingIdContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        BookingIdContainsKeywordsPredicate firstPredicateCopy =
                new BookingIdContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different bookings -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        BookingIdContainsKeywordsPredicate predicate = new BookingIdContainsKeywordsPredicate("1");
        assertTrue(predicate.test(new BookingBuilder().withId(new Id(1)).build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        BookingIdContainsKeywordsPredicate predicate = new BookingIdContainsKeywordsPredicate("");
        assertFalse(predicate.test(new BookingBuilder().withId(new Id(2)).build()));

        // Non-matching keyword
        predicate = new BookingIdContainsKeywordsPredicate("1");
        assertFalse(predicate.test(new BookingBuilder().withId(new Id(2)).build()));
    }
}

