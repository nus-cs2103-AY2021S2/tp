package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TaskBuilder;

public class DescriptionContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstKeywords = Collections.singletonList("first");
        List<String> secondKeywords = Arrays.asList("first", "second");

        DescriptionContainsKeywordsPredicate firstPredicate = new DescriptionContainsKeywordsPredicate(firstKeywords);
        DescriptionContainsKeywordsPredicate secondPredicate = new DescriptionContainsKeywordsPredicate(secondKeywords);

        // same object -> returns true
        assertTrue(secondPredicate.equals(secondPredicate));

        // same values -> returns true
        DescriptionContainsKeywordsPredicate firstPredicateCopy =
                new DescriptionContainsKeywordsPredicate(firstKeywords);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(secondPredicate.equals(100));

        // null -> returns false
        assertFalse(secondPredicate.equals(null));

        // different task -> returns false
        assertFalse(secondPredicate.equals(firstPredicate));
    }

    @Test
    public void test_descriptionContainsKeywords_returnsTrue() {
        // One keyword
        DescriptionContainsKeywordsPredicate predicate = new DescriptionContainsKeywordsPredicate(
                Collections.singletonList("Alice"));
        assertTrue(predicate.test(new TaskBuilder().withDescription("Alice Bob").build()));

        // Multiple keywords
        predicate = new DescriptionContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new TaskBuilder().withDescription("Alice Bob").build()));

        // Only one matching keyword
        predicate = new DescriptionContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new TaskBuilder().withDescription("Alice Carol").build()));

        // Mixed order for keywords
        predicate = new DescriptionContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new TaskBuilder().withDescription("Carol Bob").build()));

        // Mixed-case keywords
        predicate = new DescriptionContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new TaskBuilder().withDescription("Alice Bob").build()));
    }

    @Test
    public void test_descriptionDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        DescriptionContainsKeywordsPredicate predicate = new DescriptionContainsKeywordsPredicate(
                Collections.emptyList());
        assertFalse(predicate.test(new TaskBuilder().withDescription("Alice").build()));

        // Non-matching keyword
        predicate = new DescriptionContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new TaskBuilder().withDescription("Alice Bob").build()));

        // Keywords match title, deadline but does not match description
        predicate = new DescriptionContainsKeywordsPredicate(
                Arrays.asList("12345", "Alice"));
        assertFalse(predicate.test(new TaskBuilder().withTitle("Alice").withDeadline("12345")
                .withDescription("Main Street").build()));
    }
}
