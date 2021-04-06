package seedu.booking.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.booking.testutil.PersonBuilder;

public class EmailMatchesKeywordPredicateTest {

    @Test
    public void equals() {
        String firstPredicateKeyword = "amy@gmail.com";
        String secondPredicateKeyword = "jon@gmail.com";

        EmailMatchesKeywordPredicate firstPredicate = new EmailMatchesKeywordPredicate(firstPredicateKeyword);
        EmailMatchesKeywordPredicate secondPredicate = new EmailMatchesKeywordPredicate(secondPredicateKeyword);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        EmailMatchesKeywordPredicate firstPredicateCopy = new EmailMatchesKeywordPredicate(firstPredicateKeyword);
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
        EmailMatchesKeywordPredicate predicate = new EmailMatchesKeywordPredicate("amy@gmail.com");
        assertTrue(predicate.test(new PersonBuilder().withEmail("amy@gmail.com").build()));

        // Mixed-case keywords
        predicate = new EmailMatchesKeywordPredicate("AmY@gmAil.cOm");
        assertTrue(predicate.test(new PersonBuilder().withEmail("amy@gmail.com").build()));
    }

    @Test
    public void test_emailDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        EmailMatchesKeywordPredicate predicate = new EmailMatchesKeywordPredicate(" ");
        assertFalse(predicate.test(new PersonBuilder().withEmail("amy@gmail.com").build()));

        // Non-matching keyword
        predicate = new EmailMatchesKeywordPredicate("Amy");
        assertFalse(predicate.test(new PersonBuilder().withEmail("amy@gmail.com").build()));
    }
}
