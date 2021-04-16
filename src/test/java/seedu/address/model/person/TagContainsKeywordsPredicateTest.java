package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class TagContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        TagContainsKeywordsPredicate firstPredicate = new TagContainsKeywordsPredicate(firstPredicateKeywordList);
        TagContainsKeywordsPredicate secondPredicate = new TagContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);

        // same values -> returns true
        TagContainsKeywordsPredicate firstPredicateCopy = new TagContainsKeywordsPredicate(firstPredicateKeywordList);
        assertEquals(firstPredicateCopy, firstPredicate);

        // different types -> returns false
        assertNotEquals(firstPredicate, 1);

        // null -> returns false
        assertNotEquals(firstPredicate, null);

        // different person -> returns false
        assertNotEquals(secondPredicate, firstPredicate);
    }

    @Test
    public void test_tagContainsKeywords_returnsTrue() {
        // One keyword
        TagContainsKeywordsPredicate predicate = new TagContainsKeywordsPredicate(Collections.singletonList("HR"));
        assertTrue(predicate.test(new PersonBuilder().withTags("HR", "Marketing").build()));

        // Multiple keywords
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("HR", "Marketing"));
        assertTrue(predicate.test(new PersonBuilder().withTags("HR", "Marketing").build()));

        // Only one matching keyword
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("Marketing", "Servicing"));
        assertTrue(predicate.test(new PersonBuilder().withTags("HR", "Servicing").build()));

        // Mixed-case keywords
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("Hr", "mArKetinG"));
        assertTrue(predicate.test(new PersonBuilder().withTags("HR", "Marketing").build()));

        // Similar tag keywords
        predicate = new TagContainsKeywordsPredicate(Collections.singletonList("Market"));
        assertTrue(predicate.test(new PersonBuilder().withTags("Market", "Research").build()));
        assertTrue(predicate.test(new PersonBuilder().withTags("Marketing", "Intern").build()));
    }

    @Test
    public void test_tagDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        TagContainsKeywordsPredicate predicate = new TagContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withTags("HR").build()));

        // Non-matching keyword
        predicate = new TagContainsKeywordsPredicate(Collections.singletonList("Servicing"));
        assertFalse(predicate.test(new PersonBuilder().withTags("HR", "Marketing").build()));

        // Keywords match phone, email, name and address, but does not match tags
        predicate = new TagContainsKeywordsPredicate(
                Arrays.asList("12345", "alice@email.com", "Main", "Street", "Alice", "Gordon"));
        assertFalse(predicate.test(new PersonBuilder().withTags("HR", "Researcher").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").withName("Alice Gordon").build()));
    }
}
