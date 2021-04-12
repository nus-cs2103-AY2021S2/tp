package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.ModeOfContact;
import seedu.address.testutil.PersonBuilder;

public class ModeOfContactPredicateTest {

    @Test
    public void equals() {
        ModeOfContactPredicate phonePredicate = new ModeOfContactPredicate(new ModeOfContact("phone"));
        ModeOfContactPredicate emailPredicate = new ModeOfContactPredicate(new ModeOfContact("email"));

        // same object -> returns true
        assertEquals(phonePredicate, phonePredicate);

        // same values -> returns true
        ModeOfContactPredicate phonePredicateCopy = new ModeOfContactPredicate(new ModeOfContact("phone"));
        assertEquals(phonePredicate, phonePredicateCopy);

        // different types -> returns false
        assertNotEquals(phonePredicate, 1);
        assertNotEquals(phonePredicate, new PersonBlacklistedPredicate(true));

        // null -> returns false
        assertNotEquals(phonePredicate, null);

        // different values -> returns false
        assertNotEquals(phonePredicate, emailPredicate);
    }

    @Test
    public void test_modeOfContactMatches_returnsTrue() {
        ModeOfContactPredicate predicate = new ModeOfContactPredicate(new ModeOfContact("phone"));
        assertTrue(predicate.test(new PersonBuilder().withModeOfContact("phone").build()));

        predicate = new ModeOfContactPredicate(new ModeOfContact("email"));
        assertTrue(predicate.test(new PersonBuilder().withModeOfContact("email").build()));

        predicate = new ModeOfContactPredicate(new ModeOfContact("address"));
        assertTrue(predicate.test(new PersonBuilder().withModeOfContact("address").build()));
    }

    @Test
    public void test_modeOfContactDoesNotMatch_returnsFalse() {
        ModeOfContactPredicate predicate = new ModeOfContactPredicate(new ModeOfContact("phone"));
        assertFalse(predicate.test(new PersonBuilder().withModeOfContact("email").build()));
        assertFalse(predicate.test(new PersonBuilder().withModeOfContact("address").build()));
    }
}
