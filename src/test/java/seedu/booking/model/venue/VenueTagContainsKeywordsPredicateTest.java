package seedu.booking.model.venue;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.booking.model.Tag;
import seedu.booking.testutil.VenueBuilder;

class VenueTagContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        Set<Tag> firstPredicateTagSet = new HashSet<>();
        firstPredicateTagSet.add(new Tag("tag1"));

        Set<Tag> secondPredicateTagSet = new HashSet<>();
        secondPredicateTagSet.add(new Tag("tag2"));

        VenueTagContainsKeywordsPredicate firstPredicate =
                new VenueTagContainsKeywordsPredicate(firstPredicateTagSet);
        VenueTagContainsKeywordsPredicate secondPredicate =
                new VenueTagContainsKeywordsPredicate(secondPredicateTagSet);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        VenueTagContainsKeywordsPredicate firstPredicateCopy =
                new VenueTagContainsKeywordsPredicate(firstPredicateTagSet);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different venue tags -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_venueTagContainsKeyword_returnsTrue() {
        // One keyword
        Set<Tag> firstPredicateTagSet = new HashSet<>();
        firstPredicateTagSet.add(new Tag("student"));

        VenueTagContainsKeywordsPredicate predicate = new VenueTagContainsKeywordsPredicate(firstPredicateTagSet);
        assertTrue(predicate.test(new VenueBuilder().withTags("student").build()));

        // Mixed-case keywords
        Set<Tag> secondPredicateTagSet = new HashSet<>();
        secondPredicateTagSet.add(new Tag("stuDEnt"));

        predicate = new VenueTagContainsKeywordsPredicate(secondPredicateTagSet);
        assertTrue(predicate.test(new VenueBuilder().withTags("student").build()));
    }

    @Test
    public void test_venueTagDoesNotContainKeyword_returnsFalse() {
        // Non-matching keyword
        Set<Tag> firstPredicateTagSet = new HashSet<>();
        firstPredicateTagSet.add(new Tag("Carol"));

        VenueTagContainsKeywordsPredicate predicate = new VenueTagContainsKeywordsPredicate(firstPredicateTagSet);
        assertFalse(predicate.test(new VenueBuilder().withTags("Alice").build()));

    }
}
