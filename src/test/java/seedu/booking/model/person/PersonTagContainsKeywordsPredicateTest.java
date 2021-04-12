package seedu.booking.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.booking.model.Tag;
import seedu.booking.testutil.PersonBuilder;

class PersonTagContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        Set<Tag> firstPredicateTagSet = new HashSet<>();
        firstPredicateTagSet.add(new Tag("student"));

        Set<Tag> secondPredicateTagSet = new HashSet<>();
        secondPredicateTagSet.add(new Tag("lecturer"));

        PersonTagContainsKeywordsPredicate firstPredicate =
                new PersonTagContainsKeywordsPredicate(firstPredicateTagSet);
        PersonTagContainsKeywordsPredicate secondPredicate =
                new PersonTagContainsKeywordsPredicate(secondPredicateTagSet);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PersonTagContainsKeywordsPredicate firstPredicateCopy =
                new PersonTagContainsKeywordsPredicate(firstPredicateTagSet);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_personTagContainsKeyword_returnsTrue() {
        // One keyword
        Set<Tag> firstPredicateTagSet = new HashSet<>();
        firstPredicateTagSet.add(new Tag("student"));

        PersonTagContainsKeywordsPredicate predicate = new PersonTagContainsKeywordsPredicate(firstPredicateTagSet);
        assertTrue(predicate.test(new PersonBuilder().withTags("student").build()));

        // Mixed-case keywords
        Set<Tag> secondPredicateTagSet = new HashSet<>();
        secondPredicateTagSet.add(new Tag("student"));

        predicate = new PersonTagContainsKeywordsPredicate(secondPredicateTagSet);
        assertTrue(predicate.test(new PersonBuilder().withTags("student").build()));
    }

    @Test
    public void test_personTagDoesNotContainKeyword_returnsFalse() {
        // Non-matching keyword
        Set<Tag> firstPredicateTagSet = new HashSet<>();
        firstPredicateTagSet.add(new Tag("amy"));

        PersonTagContainsKeywordsPredicate predicate = new PersonTagContainsKeywordsPredicate(firstPredicateTagSet);
        assertFalse(predicate.test(new PersonBuilder().withTags("student").build()));
    }
}
