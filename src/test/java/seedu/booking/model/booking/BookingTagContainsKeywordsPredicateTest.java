package seedu.booking.model.booking;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.booking.testutil.BookingBuilder;

public class BookingContainsTagPredicateTest {

    @Test
    public void equals() {
        String firstPredicateTag = "tag1";
        String secondPredicateTag = "tag2";

        BookingContainsTagPredicate firstPredicate =
                new BookingContainsTagPredicate(firstPredicateTag);
        BookingContainsTagPredicate secondPredicate =
                new BookingContainsTagPredicate(secondPredicateTag);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        BookingContainsTagPredicate firstPredicateCopy =
                new BookingContainsTagPredicate(firstPredicateTag);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different bookings -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_bookingContainsTag_returnsTrue() {
        //matching booker
        BookingContainsTagPredicate predicate = new BookingContainsTagPredicate("tag1");
        assertTrue(predicate.test(new BookingBuilder().withTags("tag1").build()));
    }

    @Test
    public void test_bookingDoesNotContainTag_returnsFalse() {
        // Non-matching booker
        BookingContainsTagPredicate predicate = new BookingContainsTagPredicate("tag1");
        assertFalse(predicate.test(new BookingBuilder().withTags("hall").build()));
    }
}
