package seedu.booking.model.booking;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.booking.testutil.BookingBuilder;

public class BookingDescContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateList = Arrays.asList("description one");
        List<String> secondPredicateList = Arrays.asList("description two");

        BookingDescContainsKeywordsPredicate firstPredicate =
                new BookingDescContainsKeywordsPredicate(firstPredicateList);
        BookingDescContainsKeywordsPredicate secondPredicate =
                new BookingDescContainsKeywordsPredicate(secondPredicateList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        BookingDescContainsKeywordsPredicate firstPredicateCopy =
                new BookingDescContainsKeywordsPredicate(firstPredicateList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different descriptions -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_bookingDescContainsKeywords_returnsTrue() {
        //matching keywords
        BookingDescContainsKeywordsPredicate predicate =
                new BookingDescContainsKeywordsPredicate(Arrays.asList("field"));
        assertTrue(predicate.test(new BookingBuilder().withDescription("field").build()));
    }

    @Test
    public void test_bookingDescDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        BookingDescContainsKeywordsPredicate predicate =
                new BookingDescContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new BookingBuilder().withDescription("Alice").build()));

        // Non-matching keyword
        predicate = new BookingDescContainsKeywordsPredicate(Arrays.asList("field"));
        assertFalse(predicate.test(new BookingBuilder().withDescription("hall").build()));
    }
}
