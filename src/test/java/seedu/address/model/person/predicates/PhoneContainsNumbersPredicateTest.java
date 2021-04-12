package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class PhoneContainsNumbersPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordsList = Collections.singletonList("12345");
        List<String> secondPredicateKeywordsList = Arrays.asList("23456", "34567");

        PhoneContainsNumbersPredicate firstPredicate =
                new PhoneContainsNumbersPredicate(firstPredicateKeywordsList);
        PhoneContainsNumbersPredicate secondPredicate =
                new PhoneContainsNumbersPredicate(secondPredicateKeywordsList);

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);

        // same values -> returns true
        PhoneContainsNumbersPredicate firstPredicateCopy =
                new PhoneContainsNumbersPredicate(firstPredicateKeywordsList);
        assertEquals(firstPredicate, firstPredicateCopy);

        // different types -> returns false
        assertNotEquals(firstPredicate, 1);
        assertNotEquals(firstPredicate, new NameContainsKeywordsPredicate(Collections.singletonList("alice")));

        // null -> returns false
        assertNotEquals(firstPredicate, null);

        // different values -> returns false
        assertNotEquals(firstPredicate, secondPredicate);
    }

    @Test
    public void test_phoneContainsNumbers_returnsTrue() {
        // One number - full match
        PhoneContainsNumbersPredicate predicate =
                new PhoneContainsNumbersPredicate(Collections.singletonList("81234567"));
        assertTrue(predicate.test(new PersonBuilder().withPhone("81234567").build()));

        // One number - partial match
        predicate = new PhoneContainsNumbersPredicate(Collections.singletonList("123"));
        assertTrue(predicate.test(new PersonBuilder().withPhone("81234567").build()));

        // Multiple numbers
        predicate = new PhoneContainsNumbersPredicate(Arrays.asList("69", "420"));
        assertTrue(predicate.test(new PersonBuilder().withPhone("84204569").build()));

        // Only one matching number
        assertTrue(predicate.test(new PersonBuilder().withPhone("81234569").build()));
    }

    @Test
    public void test_phoneDoesNotContainNumbers() {
        // Non-matching numbers
        PhoneContainsNumbersPredicate predicate =
                new PhoneContainsNumbersPredicate(Collections.singletonList("123"));
        assertFalse(predicate.test(new PersonBuilder().withPhone("98765432").build()));
    }
}
