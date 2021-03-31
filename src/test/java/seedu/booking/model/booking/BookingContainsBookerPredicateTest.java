package seedu.booking.model.booking;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.booking.logic.commands.CommandTestUtil.NON_EXISTENT_EMAIL;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;

import org.junit.jupiter.api.Test;

import seedu.booking.model.person.Email;
import seedu.booking.testutil.BookingBuilder;

public class BookingContainsBookerPredicateTest {

    @Test
    public void equals() {
        Email firstPredicateEmail = new Email(VALID_EMAIL_AMY);
        Email secondPredicateEmail = new Email(VALID_EMAIL_BOB);

        BookingContainsBookerPredicate firstPredicate =
                new BookingContainsBookerPredicate(firstPredicateEmail);
        BookingContainsBookerPredicate secondPredicate =
                new BookingContainsBookerPredicate(secondPredicateEmail);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        BookingContainsBookerPredicate firstPredicateCopy =
                new BookingContainsBookerPredicate(firstPredicateEmail);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different bookers -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_bookingContainsBooker_returnsTrue() {
        //matching booker
        BookingContainsBookerPredicate predicate = new BookingContainsBookerPredicate(new Email(VALID_EMAIL_AMY));
        assertTrue(predicate.test(new BookingBuilder().withEmail(VALID_EMAIL_AMY).build()));
    }

    @Test
    public void test_bookingDoesNotContainBooker_returnsFalse() {
        // Non-matching booker
        BookingContainsBookerPredicate predicate = new BookingContainsBookerPredicate(new Email(NON_EXISTENT_EMAIL));
        assertFalse(predicate.test(new BookingBuilder().withEmail(VALID_EMAIL_AMY).build()));
    }
}

