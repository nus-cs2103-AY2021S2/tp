package seedu.booking.model.booking;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.booking.testutil.BookingBuilder;

class BookingDateContainsKeywordPredicateTest {

    @Test
    public void equals() {
        LocalDate firstPredicateDate = LocalDate.parse("2020-02-02");
        LocalDate secondPredicateDate = LocalDate.parse("2030-03-03");

        BookingDateContainsKeywordPredicate firstPredicate =
                new BookingDateContainsKeywordPredicate(firstPredicateDate);
        BookingDateContainsKeywordPredicate secondPredicate =
                new BookingDateContainsKeywordPredicate(secondPredicateDate);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        BookingDateContainsKeywordPredicate firstPredicateCopy =
                new BookingDateContainsKeywordPredicate(firstPredicateDate);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different booking dates -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_bookingDateContainsKeyword_returnsTrue() {
        //matching keyword
        BookingDateContainsKeywordPredicate predicate =
                new BookingDateContainsKeywordPredicate(LocalDate.parse("2020-02-02"));
        assertTrue(predicate.test(new BookingBuilder().withBookingStart("2020-02-01 22:22")
                .withBookingEnd("2020-02-03 22:22").build()));
    }

    @Test
    public void test_bookingDateDoesNotContainKeyword_returnsFalse() {
        // Non-matching keyword
        BookingDateContainsKeywordPredicate predicate =
                new BookingDateContainsKeywordPredicate(LocalDate.parse("2020-02-02"));
        assertFalse(predicate.test(new BookingBuilder().withBookingStart("2019-02-01 22:22")
                .withBookingEnd("2019-02-03 22:22").build()));
    }
}
