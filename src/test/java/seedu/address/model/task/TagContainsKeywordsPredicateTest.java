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
        TagContainsKeywordsPredicate predicate;

        // One keyword
        predicate = new TagContainsKeywordsPredicate(Collections.singleton(
                "Alice"));
        assertTrue(predicate.test(new TaskBuilder().withTags("Alice").build()));

        // All multiple tag keywords match multiple tags
        predicate = new TagContainsKeywordsPredicate(new HashSet<>(Arrays.asList("Alice", "Bob")));
        assertTrue(predicate.test(new TaskBuilder().withTags("Alice", "Bob").build()));

        // All multiple tag keywords match multiple tags (Order does not matter)
        predicate = new TagContainsKeywordsPredicate(new HashSet<>(Arrays.asList("Bob", "Alice")));
        assertTrue(predicate.test(new TaskBuilder().withTags("Alice", "Bob").build()));

        // All multiple tag keywords match some of the tags
        predicate = new TagContainsKeywordsPredicate(new HashSet<>(Arrays.asList("Alice", "Bob")));
        assertTrue(predicate.test(new TaskBuilder().withTags("Alice", "Bob", "Charles").build()));

        // Mixed-case keywords
        predicate = new TagContainsKeywordsPredicate(new HashSet<>(Arrays.asList("aLIce", "bOB")));
        assertTrue(predicate.test(new TaskBuilder().withTags("Alice", "Bob").build()));
    }

    @Test
    public void test_tagDoesNotContainKeywords_returnsFalse() {
        TagContainsKeywordsPredicate predicate;

        // Only match partial keyword
        predicate = new TagContainsKeywordsPredicate(new HashSet<>(Arrays.asList("Car")));
        assertFalse(predicate.test(new TaskBuilder().withTags("Carol").build()));

        // Only match partial keyword, have extra characters
        predicate = new TagContainsKeywordsPredicate(new HashSet<>(Arrays.asList("Carol")));
        assertFalse(predicate.test(new TaskBuilder().withTags("Car").build()));

        // Non-matching keyword
        predicate = new TagContainsKeywordsPredicate(new HashSet<>(Arrays.asList("Carol")));
        assertFalse(predicate.test(new TaskBuilder().withTags("AliceBob").build()));

        // Keywords match title, deadline and description but does not match tag
        predicate = new TagContainsKeywordsPredicate(new HashSet<>(Arrays.asList
                ("Alice", "12345", "Main Street")));
        assertFalse(predicate.test(new TaskBuilder().withTitle("Alice").withDeadline("12345")
                .withDescription("Main Street").withTags("CS2103").build()));

        // Not all keywords match with the tags
        predicate = new TagContainsKeywordsPredicate(new HashSet<>(Arrays.asList("Bob", "Carol")));
        assertFalse(predicate.test(new TaskBuilder().withTags("Alice", "Bob").build()));
    }
}
