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

public class EmailContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordsList = Collections.singletonList("emailOne");
        List<String> secondPredicateKeywordsList = Arrays.asList("emailTwo", "emailThree");

        EmailContainsKeywordsPredicate firstPredicate =
                new EmailContainsKeywordsPredicate(firstPredicateKeywordsList);
        EmailContainsKeywordsPredicate secondPredicate =
                new EmailContainsKeywordsPredicate(secondPredicateKeywordsList);

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);

        // same values -> returns true
        EmailContainsKeywordsPredicate firstPredicateCopy =
                new EmailContainsKeywordsPredicate(firstPredicateKeywordsList);
        assertEquals(firstPredicate, firstPredicateCopy);

        // different types -> returns false
        assertNotEquals(firstPredicate, 1);
        assertNotEquals(firstPredicate, new PersonBlacklistedPredicate(false));

        // null -> returns false
        assertNotEquals(firstPredicate, null);

        // different values -> returns false
        assertNotEquals(firstPredicate, secondPredicate);
    }

    @Test
    public void test_emailContainsKeywords_returnsTrue() {
        // One keyword - full match
        EmailContainsKeywordsPredicate predicate =
                new EmailContainsKeywordsPredicate(Collections.singletonList("abc@def.com"));
        assertTrue(predicate.test(new PersonBuilder().withEmail("abc@def.com").build()));

        // One keyword - partial match
        predicate = new EmailContainsKeywordsPredicate(Collections.singletonList("abc"));
        assertTrue(predicate.test(new PersonBuilder().withEmail("abc@def.com").build()));

        // Multiple keywords
        predicate = new EmailContainsKeywordsPredicate(Arrays.asList("abc", ".com"));
        assertTrue(predicate.test(new PersonBuilder().withEmail("abc@def.com").build()));

        // Only one matching keyword
        assertTrue(predicate.test(new PersonBuilder().withEmail("ghi@def.com").build()));
    }

    @Test
    public void test_emailDoesNotContainKeywords_returnsTrue() {
        // Non-matching keywords
        EmailContainsKeywordsPredicate predicate =
                new EmailContainsKeywordsPredicate(Collections.singletonList("abc@def.com"));
        assertFalse(predicate.test(new PersonBuilder().withEmail("abc@example.com").build()));

        // Keywords matching other fields
        predicate = new EmailContainsKeywordsPredicate(
                Arrays.asList("emailOne", "81234567", "emailThree", "emailFour"));
        assertFalse(predicate.test(new PersonBuilder().withName("emailOne").withPhone("81234567")
                .withEmail("abc@example.com").withAddress("emailThree").withTags("emailFour").build()));
    }
}
