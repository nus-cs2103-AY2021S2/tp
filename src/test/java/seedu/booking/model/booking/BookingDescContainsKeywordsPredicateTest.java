package seedu.booking.model.booking;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.booking.testutil.BookingBuilder;

public class BookingContainsDescriptionPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateList = Arrays.asList("description one");
        List<String> secondPredicateList = Arrays.asList("description two");

        BookingContainsDescriptionPredicate firstPredicate =
                new BookingContainsDescriptionPredicate(firstPredicateList);
        BookingContainsDescriptionPredicate secondPredicate =
                new BookingContainsDescriptionPredicate(secondPredicateList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        BookingContainsDescriptionPredicate firstPredicateCopy =
                new BookingContainsDescriptionPredicate(firstPredicateList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different bookings -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_bookingContainsDescription_returnsTrue() {
        //matching booker
        BookingContainsDescriptionPredicate predicate = new BookingContainsDescriptionPredicate(Arrays.asList("field"));
        assertTrue(predicate.test(new BookingBuilder().withDescription("field").build()));
    }

    @Test
    public void test_bookingDoesNotContainDescription_returnsFalse() {
        // Non-matching booker
        BookingContainsDescriptionPredicate predicate = new BookingContainsDescriptionPredicate(Arrays.asList("field"));
        assertFalse(predicate.test(new BookingBuilder().withDescription("hall").build()));
    }
}
