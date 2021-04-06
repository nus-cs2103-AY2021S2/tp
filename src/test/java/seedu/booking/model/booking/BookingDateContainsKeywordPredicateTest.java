package seedu.booking.model.booking;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import seedu.booking.testutil.BookingBuilder;

class BookingWithinDatePredicateTest {

    @Test
    public void equals() {
        LocalDate firstPredicateDate = LocalDate.parse("2020-02-02");
        LocalDate secondPredicateDate = LocalDate.parse("2030-03-03");

        BookingWithinDatePredicate firstPredicate =
                new BookingWithinDatePredicate(firstPredicateDate);
        BookingWithinDatePredicate secondPredicate =
                new BookingWithinDatePredicate(secondPredicateDate);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        BookingWithinDatePredicate firstPredicateCopy =
                new BookingWithinDatePredicate(firstPredicateDate);
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
        BookingWithinDatePredicate predicate = new BookingWithinDatePredicate(LocalDate.parse("2020-02-02"));
        assertTrue(predicate.test(new BookingBuilder().withBookingStart("2020-02-01 22:22")
                .withBookingEnd("2020-02-03 22:22").build()));
    }

    @Test
    public void test_bookingDoesNotContainVenue_returnsFalse() {
        // Non-matching booker
        BookingWithinDatePredicate predicate = new BookingWithinDatePredicate(LocalDate.parse("2020-02-02"));
        assertFalse(predicate.test(new BookingBuilder().withBookingStart("2019-02-01 22:22")
                .withBookingEnd("2019-02-03 22:22").build()));
    }
}
