package seedu.booking.model.booking;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.booking.testutil.BookingBuilder;

public class BookingVenueContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateVenue = Arrays.asList("venue1");
        List<String> secondPredicateVenue = Arrays.asList("venue2");

        BookingVenueContainsKeywordsPredicate firstPredicate =
                new BookingVenueContainsKeywordsPredicate(firstPredicateVenue);
        BookingVenueContainsKeywordsPredicate secondPredicate =
                new BookingVenueContainsKeywordsPredicate(secondPredicateVenue);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        BookingVenueContainsKeywordsPredicate firstPredicateCopy =
                new BookingVenueContainsKeywordsPredicate(firstPredicateVenue);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different booking venue -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_bookingVenueContainsKeyword_returnsTrue() {
        //one keyword
        BookingVenueContainsKeywordsPredicate predicate =
                new BookingVenueContainsKeywordsPredicate(Arrays.asList("venue1"));
        assertTrue(predicate.test(new BookingBuilder().withVenue("venue1").build()));

        // Multiple keywords
        predicate = new BookingVenueContainsKeywordsPredicate(Arrays.asList("Victoria", "Hall"));
        assertTrue(predicate.test(new BookingBuilder().withVenue("Victoria Hall").build()));

        // Only one matching keyword
        predicate = new BookingVenueContainsKeywordsPredicate(Arrays.asList("Hall", "Field"));
        assertTrue(predicate.test(new BookingBuilder().withVenue("Victoria Hall").build()));

        // Mixed-case keywords
        predicate = new BookingVenueContainsKeywordsPredicate(Arrays.asList("VicTOria", "hALL"));
        assertTrue(predicate.test(new BookingBuilder().withVenue("Victoria Hall").build()));
    }

    @Test
    public void test_bookingVenueDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        BookingVenueContainsKeywordsPredicate predicate =
                new BookingVenueContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new BookingBuilder().withVenue("Victoria").build()));

        // Non-matching keyword
        predicate = new BookingVenueContainsKeywordsPredicate(Arrays.asList("venue1"));
        assertFalse(predicate.test(new BookingBuilder().withVenue("venue2").build()));
    }
}
