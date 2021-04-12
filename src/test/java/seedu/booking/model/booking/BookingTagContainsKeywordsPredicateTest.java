package seedu.booking.model.booking;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.booking.model.Tag;
import seedu.booking.testutil.BookingBuilder;

public class BookingTagContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        Set<Tag> firstPredicateTagSet = new HashSet<>();
        firstPredicateTagSet.add(new Tag("tag1"));

        Set<Tag> secondPredicateTagSet = new HashSet<>();
        secondPredicateTagSet.add(new Tag("tag2"));

        BookingTagContainsKeywordsPredicate firstPredicate =
                new BookingTagContainsKeywordsPredicate(firstPredicateTagSet);

        BookingTagContainsKeywordsPredicate secondPredicate =
                new BookingTagContainsKeywordsPredicate(secondPredicateTagSet);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        BookingTagContainsKeywordsPredicate firstPredicateCopy =
                new BookingTagContainsKeywordsPredicate(firstPredicateTagSet);
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
        Set<Tag> firstPredicateTagSet = new HashSet<>();
        firstPredicateTagSet.add(new Tag("tag1"));

        BookingTagContainsKeywordsPredicate predicate = new BookingTagContainsKeywordsPredicate(firstPredicateTagSet);
        assertTrue(predicate.test(new BookingBuilder().withTags("tag1").build()));


        // Mixed-case keywords
        Set<Tag> secondPredicateTagSet = new HashSet<>();
        secondPredicateTagSet.add(new Tag("stuDEnt"));

        predicate = new BookingTagContainsKeywordsPredicate(secondPredicateTagSet);
        assertTrue(predicate.test(new BookingBuilder().withTags("student").build()));
    }

    @Test
    public void test_bookingTagDoesNotContainKeyword_returnsFalse() {
        // Non-matching keyword
        Set<Tag> firstPredicateTagSet = new HashSet<>();
        firstPredicateTagSet.add(new Tag("tag1"));

        BookingTagContainsKeywordsPredicate predicate = new BookingTagContainsKeywordsPredicate(firstPredicateTagSet);
        assertFalse(predicate.test(new BookingBuilder().withTags("hall").build()));
    }
}
