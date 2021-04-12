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

public class PersonTagContainsKeywordsPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("tagOne");
        List<String> secondPredicateKeywordList = Arrays.asList("tagOne", "tagTwo");

        PersonTagContainsKeywordsPredicate firstPredicate =
                new PersonTagContainsKeywordsPredicate(firstPredicateKeywordList);
        PersonTagContainsKeywordsPredicate secondPredicate =
                new PersonTagContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);

        // same values -> returns true
        PersonTagContainsKeywordsPredicate firstPredicateCopy =
                new PersonTagContainsKeywordsPredicate(firstPredicateKeywordList);
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
    public void test_tagContainsKeywords_returnsTrue() {
        // One keyword
        PersonTagContainsKeywordsPredicate predicate =
                new PersonTagContainsKeywordsPredicate(Collections.singletonList("tagOne"));
        assertTrue(predicate.test(new PersonBuilder().withTags("tagOne", "tagTwo", "tagThree").build()));

        // Multiple keywords
        predicate = new PersonTagContainsKeywordsPredicate(Arrays.asList("tagOne", "tagTwo"));
        assertTrue(predicate.test(new PersonBuilder().withTags("tagOne", "tagTwo", "tagThree").build()));

        // Only one matching keyword
        assertTrue(predicate.test(new PersonBuilder().withTags("tagOne", "tagThree", "tagFour").build()));

        // Keywords with non-matching cases
        predicate = new PersonTagContainsKeywordsPredicate(Arrays.asList("TAGONE", "TAGTWO"));
        assertTrue(predicate.test(new PersonBuilder().withTags("tagOne", "tagTwo", "tagThree").build()));
    }

    @Test
    public void test_tagDoesNotContainKeywords_returnsFalse() {
        // Non-matching keywords
        PersonTagContainsKeywordsPredicate predicate =
                new PersonTagContainsKeywordsPredicate(Collections.singletonList("tagOne"));
        assertFalse(predicate.test(new PersonBuilder().withTags("Git", "Gud").build()));
        assertFalse(predicate.test(new PersonBuilder().withTags().build()));

        // Keywords matching other fields
        predicate = new PersonTagContainsKeywordsPredicate(Arrays.asList("tagOne", "tagThree",
                "tagFour@example.com", "phone", "81234567"));
        assertFalse(predicate.test(new PersonBuilder().withName("tagOne").withTags("tagTwo")
                .withAddress("tagThree").withEmail("tagFour@example.com").withModeOfContact("phone")
                .withPhone("81234567").build()));
    }
}
