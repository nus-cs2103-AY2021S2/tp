package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TaskBuilder;

public class PriorityContainsKeywordPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        PriorityContainsKeywordPredicate firstPredicate = new PriorityContainsKeywordPredicate(firstPredicateKeywordList);
        PriorityContainsKeywordPredicate secondPredicate = new PriorityContainsKeywordPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PriorityContainsKeywordPredicate firstPredicateCopy = new PriorityContainsKeywordPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_priorityContainsKeywords_returnsTrue() {
        // One keyword
        PriorityContainsKeywordPredicate highPredicate = new PriorityContainsKeywordPredicate(Collections.singletonList("high"));
        assertTrue(highPredicate.test(new TaskBuilder().withPriority(TaskBuilder.HIGH_PRIORITY).build()));

        PriorityContainsKeywordPredicate medPredicate = new PriorityContainsKeywordPredicate(Collections.singletonList("medium"));
        assertTrue(medPredicate.test(new TaskBuilder().withPriority(TaskBuilder.MEDIUM_PRIORITY).build()));

        PriorityContainsKeywordPredicate lowPredicate = new PriorityContainsKeywordPredicate(Collections.singletonList("low"));
        assertTrue(lowPredicate.test(new TaskBuilder().withPriority(TaskBuilder.LOW_PRIORITY).build()));

        PriorityContainsKeywordPredicate predicate = new PriorityContainsKeywordPredicate(Collections.singletonList("unassigned"));
        assertTrue(predicate.test(new TaskBuilder().withPriority(TaskBuilder.DEFAULT_PRIORITY).build()));

    }

    @Test
    public void test_taskDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        PriorityContainsKeywordPredicate predicate = new PriorityContainsKeywordPredicate(Collections.emptyList());
        assertFalse(predicate.test(new TaskBuilder().withPriority(TaskBuilder.DEFAULT_PRIORITY).build()));

        predicate = new PriorityContainsKeywordPredicate(Arrays.asList("test"));
        assertFalse(predicate.test(new TaskBuilder().withPriority(TaskBuilder.DEFAULT_PRIORITY).build()));
    }

    @Test
    public void test_taskContainKeywords_returnsTrue() {
        PriorityContainsKeywordPredicate predicate = new PriorityContainsKeywordPredicate(Arrays.asList("Unassigned"));
        assertTrue(predicate.test(new TaskBuilder().withPriority(TaskBuilder.DEFAULT_PRIORITY).build()));

        predicate = new PriorityContainsKeywordPredicate(Arrays.asList("UNASSIGNED"));
        assertTrue(predicate.test(new TaskBuilder().withPriority(TaskBuilder.DEFAULT_PRIORITY).build()));
    }
}
