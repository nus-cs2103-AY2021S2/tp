package seedu.heymatez.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.heymatez.testutil.TaskBuilder;

/**
 * Contains unit tests for {@code TitleContainsKeywordPredicate}.
 */
public class TitleContainsKeywordPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        TitleContainsKeywordPredicate firstPredicate = new TitleContainsKeywordPredicate(firstPredicateKeywordList);
        TitleContainsKeywordPredicate secondPredicate = new TitleContainsKeywordPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TitleContainsKeywordPredicate firstPredicateCopy = new TitleContainsKeywordPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_titleContainsKeywords_returnsTrue() {
        // One keyword
        TitleContainsKeywordPredicate predicate = new TitleContainsKeywordPredicate(
                Collections.singletonList("Meeting"));
        assertTrue(predicate.test(new TaskBuilder().withTitle("Meeting").build()));

        // Multiple keywords
        predicate = new TitleContainsKeywordPredicate(Arrays.asList("Meeting", "CS2103T"));
        assertTrue(predicate.test(new TaskBuilder().withTitle("CS2103T Meeting").build()));

        // Only one matching keyword
        predicate = new TitleContainsKeywordPredicate(Arrays.asList("Meeting", "CS3243"));
        assertTrue(predicate.test(new TaskBuilder().withTitle("CS2103T Meeting").build()));

        // Mixed-case keywords
        predicate = new TitleContainsKeywordPredicate(Arrays.asList("mEEtINg", "cs2103"));
        assertTrue(predicate.test(new TaskBuilder().withTitle("CS2103T Meeting").build()));
    }

    @Test
    public void test_titleDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        TaskContainsKeywordPredicate predicate = new TaskContainsKeywordPredicate(Collections.emptyList());
        assertFalse(predicate.test(new TaskBuilder().withTitle("Meeting").build()));

        // Non-matching keyword for both title and description
        predicate = new TaskContainsKeywordPredicate(Arrays.asList("Proposal", "CS3243"));
        assertFalse(predicate.test(new TaskBuilder().withTitle("CS2103T Meeting").build()));
    }
}
