package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TaskBuilder;

public class TagContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        Set<String> firstPredicateKeywordSet = Collections.singleton("first");
        Set<String> secondPredicateKeywordSet = new HashSet<>(Arrays.asList("first", "second"));

        TagContainsKeywordsPredicate firstPredicate = new TagContainsKeywordsPredicate(firstPredicateKeywordSet);
        TagContainsKeywordsPredicate secondPredicate = new TagContainsKeywordsPredicate(secondPredicateKeywordSet);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TagContainsKeywordsPredicate firstPredicateCopy =
                new TagContainsKeywordsPredicate(firstPredicateKeywordSet);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different task -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_tagContainsKeywords_returnsTrue() {
        // One keyword
        TagContainsKeywordsPredicate predicate = new TagContainsKeywordsPredicate(Collections.singleton(
                "Alice"));
        assertTrue(predicate.test(new TaskBuilder().withTags("Alice").build()));

        // Multiple keywords
        predicate = new TagContainsKeywordsPredicate(new HashSet<>(Arrays.asList("Alice", "Bob")));
        assertTrue(predicate.test(new TaskBuilder().withTags("Alice", "Bob").build()));

        // Only one matching keyword
        predicate = new TagContainsKeywordsPredicate(new HashSet<>(Arrays.asList("Bob", "Carol")));
        assertTrue(predicate.test(new TaskBuilder().withTags("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new TagContainsKeywordsPredicate(new HashSet<>(Arrays.asList("aLIce", "bOB")));
        assertTrue(predicate.test(new TaskBuilder().withTags("Alice", "Bob").build()));
    }

    @Test
    public void test_tagDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        TagContainsKeywordsPredicate predicate = new TagContainsKeywordsPredicate(Collections.EMPTY_SET);
        assertFalse(predicate.test(new TaskBuilder().withTags("Alice").build()));

        // Non-matching keyword
        predicate = new TagContainsKeywordsPredicate(new HashSet<>(Arrays.asList("Carol")));
        assertFalse(predicate.test(new TaskBuilder().withTags("Alice Bob").build()));

        // Keywords match phone, email and address, but does not match tag
        predicate = new TagContainsKeywordsPredicate(new HashSet<>(Arrays.asList
                ("Alice", "12345", "alice@email.com", "Main Street")));
        assertFalse(predicate.test(new TaskBuilder().withTitle("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").withTags("CS2103").build()));
    }
}
