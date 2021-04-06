package seedu.booking.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.booking.testutil.PersonBuilder;

class PhoneMatchesKeywordPredicateTest {

    @Test
    public void equals() {
        String firstPredicateKeyword = "99999999";
        String secondPredicateKeyword = "88888888";

        PhoneMatchesKeywordPredicate firstPredicate = new PhoneMatchesKeywordPredicate(firstPredicateKeyword);
        PhoneMatchesKeywordPredicate secondPredicate = new PhoneMatchesKeywordPredicate(secondPredicateKeyword);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PhoneMatchesKeywordPredicate firstPredicateCopy = new PhoneMatchesKeywordPredicate(firstPredicateKeyword);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_phoneMatchesKeyword_returnsTrue() {
        // One keyword
        PhoneMatchesKeywordPredicate predicate = new PhoneMatchesKeywordPredicate("99999999");
        assertTrue(predicate.test(new PersonBuilder().withPhone("99999999").build()));
    }

    @Test
    public void test_phoneDoesNotMatchKeyword_returnsFalse() {
        // Non-matching keyword
        PhoneMatchesKeywordPredicate predicate = new PhoneMatchesKeywordPredicate("99999999");
        assertFalse(predicate.test(new PersonBuilder().withPhone("88888888").build()));
    }
}
