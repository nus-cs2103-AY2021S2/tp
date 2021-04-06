package seedu.booking.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.booking.testutil.PersonBuilder;

class PersonTagContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        String firstPredicateKeyword = "student";
        String secondPredicateKeyword = "lecturer";

        PersonTagContainsKeywordsPredicate firstPredicate =
                new PersonTagContainsKeywordsPredicate(firstPredicateKeyword);
        PersonTagContainsKeywordsPredicate secondPredicate =
                new PersonTagContainsKeywordsPredicate(secondPredicateKeyword);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PersonTagContainsKeywordsPredicate firstPredicateCopy =
                new PersonTagContainsKeywordsPredicate(firstPredicateKeyword);
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
        PersonTagContainsKeywordsPredicate predicate = new PersonTagContainsKeywordsPredicate("student");
        assertTrue(predicate.test(new PersonBuilder().withTags("student").build()));

        // Mixed-case keywords
        predicate = new PersonTagContainsKeywordsPredicate("stUDent");
        assertTrue(predicate.test(new PersonBuilder().withTags("student").build()));
    }

    @Test
    public void test_personTagDoesNotContainKeyword_returnsFalse() {
        // Non-matching keyword
        PersonTagContainsKeywordsPredicate predicate = new PersonTagContainsKeywordsPredicate("Amy");
        assertFalse(predicate.test(new PersonBuilder().withTags("student").build()));
    }
}
