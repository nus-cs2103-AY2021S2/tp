package seedu.booking.model.booking;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.booking.testutil.BookingBuilder;

public class BookingTagContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        String firstPredicateTag = "tag1";
        String secondPredicateTag = "tag2";

        BookingTagContainsKeywordsPredicate firstPredicate =
                new BookingTagContainsKeywordsPredicate(firstPredicateTag);
        BookingTagContainsKeywordsPredicate secondPredicate =
                new BookingTagContainsKeywordsPredicate(secondPredicateTag);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        BookingTagContainsKeywordsPredicate firstPredicateCopy =
                new BookingTagContainsKeywordsPredicate(firstPredicateTag);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different tags -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_bookingTagContainsKeyword_returnsTrue() {
        //matching keyword
        BookingTagContainsKeywordsPredicate predicate = new BookingTagContainsKeywordsPredicate("tag1");
        assertTrue(predicate.test(new BookingBuilder().withTags("tag1").build()));


        // Mixed-case keywords
        predicate = new BookingTagContainsKeywordsPredicate("stuDEnt");
        assertTrue(predicate.test(new BookingBuilder().withTags("student").build()));
    }

    @Test
    public void test_bookingTagDoesNotContainKeyword_returnsFalse() {
        // Non-matching keyword
        BookingTagContainsKeywordsPredicate predicate = new BookingTagContainsKeywordsPredicate("tag1");
        assertFalse(predicate.test(new BookingBuilder().withTags("hall").build()));
    }
}
