package seedu.booking.model.booking;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.booking.testutil.BookingBuilder;

public class BookingContainsVenuePredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateVenue = Arrays.asList("venue1");
        List<String> secondPredicateVenue = Arrays.asList("venue2");

        BookingContainsVenuePredicate firstPredicate =
                new BookingContainsVenuePredicate(firstPredicateVenue);
        BookingContainsVenuePredicate secondPredicate =
                new BookingContainsVenuePredicate(secondPredicateVenue);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        BookingContainsVenuePredicate firstPredicateCopy =
                new BookingContainsVenuePredicate(firstPredicateVenue);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different bookings -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_bookingContainsVenue_returnsTrue() {
        //matching booker
        BookingContainsVenuePredicate predicate = new BookingContainsVenuePredicate(Arrays.asList("venue1"));
        assertTrue(predicate.test(new BookingBuilder().withVenue("venue1").build()));
    }

    @Test
    public void test_bookingDoesNotContainVenue_returnsFalse() {
        // Non-matching booker
        BookingContainsVenuePredicate predicate = new BookingContainsVenuePredicate(Arrays.asList("venue1"));
        assertFalse(predicate.test(new BookingBuilder().withVenue("venue2").build()));
    }
}
