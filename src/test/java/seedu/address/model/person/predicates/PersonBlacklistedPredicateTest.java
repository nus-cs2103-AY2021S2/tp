package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class PersonBlacklistedPredicateTest {

    @Test
    public void equals() {
        PersonBlacklistedPredicate truePredicate = new PersonBlacklistedPredicate(true);
        PersonBlacklistedPredicate falsePredicate = new PersonBlacklistedPredicate(false);

        // same object -> returns true
        assertEquals(truePredicate, truePredicate);

        PersonBlacklistedPredicate truePredicateCopy = new PersonBlacklistedPredicate(true);
        assertEquals(truePredicate, truePredicateCopy);

        // different types -> returns false
        assertNotEquals(truePredicate, 1);
        assertNotEquals(truePredicate, new NameContainsKeywordsPredicate(Collections.singletonList("alice")));

        // null -> returns false
        assertNotEquals(truePredicate, null);

        // different values -> return false
        assertNotEquals(truePredicate, falsePredicate);
    }

    @Test
    public void test_blacklistStatusMatches_returnsTrue() {
        PersonBlacklistedPredicate predicate = new PersonBlacklistedPredicate(true);
        assertTrue(predicate.test(new PersonBuilder().withBlacklist(true).build()));

        predicate = new PersonBlacklistedPredicate(false);
        assertTrue(predicate.test(new PersonBuilder().withBlacklist(false).build()));
    }

    @Test
    public void test_blacklistStatusDoesNotMatch_returnsFalse() {
        PersonBlacklistedPredicate predicate = new PersonBlacklistedPredicate(true);
        assertFalse(predicate.test(new PersonBuilder().withBlacklist(false).build()));
    }
}
