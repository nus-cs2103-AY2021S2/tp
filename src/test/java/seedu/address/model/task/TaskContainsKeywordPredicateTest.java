package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TaskBuilder;

public class TaskContainsKeywordPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        TaskContainsKeywordPredicate firstPredicate = new TaskContainsKeywordPredicate(firstPredicateKeywordList);
        TaskContainsKeywordPredicate secondPredicate = new TaskContainsKeywordPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TaskContainsKeywordPredicate firstPredicateCopy = new TaskContainsKeywordPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_taskContainsKeywords_returnsTrue() {
        // One keyword
        TaskContainsKeywordPredicate predicate = new TaskContainsKeywordPredicate(Collections.singletonList("Meeting"));
        assertTrue(predicate.test(new TaskBuilder().withTitle("Meeting").build()));

        // Multiple keywords
        predicate = new TaskContainsKeywordPredicate(Arrays.asList("Meeting", "CS2103T"));
        assertTrue(predicate.test(new TaskBuilder().withTitle("Meeting").withDescription("CS2103T").build()));

        // Only one matching keyword
        predicate = new TaskContainsKeywordPredicate(Arrays.asList("Meeting", "CS3243"));
        assertTrue(predicate.test(new TaskBuilder().withTitle("Meeting").withDescription("CS2103T").build()));

        // Mixed-case keywords
        predicate = new TaskContainsKeywordPredicate(Arrays.asList("mEEtINg", "cs2103"));
        assertTrue(predicate.test(new TaskBuilder().withTitle("Meeting").withDescription("CS2103T").build()));
    }

    @Test
    public void test_taskDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        TaskContainsKeywordPredicate predicate = new TaskContainsKeywordPredicate(Collections.emptyList());
        assertFalse(predicate.test(new TaskBuilder().withTitle("Meeting").build()));

        // Non-matching keyword for both title and description
        predicate = new TaskContainsKeywordPredicate(Arrays.asList("Proposal", "CS3243"));
        assertFalse(predicate.test(new TaskBuilder().withTitle("Meeting").withDescription("CS1101").build()));
    }
}
