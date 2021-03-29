package seedu.booking.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.booking.testutil.PersonBuilder;

public class EmailContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        String firstPredicateKeyword = "amy@gmail.com";
        String secondPredicateKeyword = "jon@gmail.com";

        EmailContainsKeywordsPredicate firstPredicate = new EmailContainsKeywordsPredicate(firstPredicateKeyword);
        EmailContainsKeywordsPredicate secondPredicate = new EmailContainsKeywordsPredicate(secondPredicateKeyword);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        EmailContainsKeywordsPredicate firstPredicateCopy = new EmailContainsKeywordsPredicate(firstPredicateKeyword);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_emailContainsKeywords_returnsTrue() {
        // One keyword
        EmailContainsKeywordsPredicate predicate = new EmailContainsKeywordsPredicate("amy@gmail.com");
        assertTrue(predicate.test(new PersonBuilder().withEmail("amy@gmail.com").build()));

        // Mixed-case keywords
        predicate = new EmailContainsKeywordsPredicate("AmY@gmAil.cOm");
        assertTrue(predicate.test(new PersonBuilder().withEmail("amy@gmail.com").build()));
    }

    @Test
    public void test_emailDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        EmailContainsKeywordsPredicate predicate = new EmailContainsKeywordsPredicate(" ");
        assertFalse(predicate.test(new PersonBuilder().withEmail("amy@gmail.com").build()));

        // Non-matching keyword
        predicate = new EmailContainsKeywordsPredicate("Amy");
        assertFalse(predicate.test(new PersonBuilder().withEmail("amy@gmail.com").build()));
    }
}
