package seedu.heymatez.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.heymatez.testutil.TaskBuilder;

/**
 * Contains unit tests for {@code TaskContainsAssigneePredicate}.
 */
public class TaskContainsAssigneePredicateTest {
    @Test
    public void equals() {
        String firstPredicateKeyword = "first";
        String secondPredicateKeyword = "second";

        TaskContainsAssigneePredicate firstPredicate = new TaskContainsAssigneePredicate(firstPredicateKeyword);
        TaskContainsAssigneePredicate secondPredicate = new TaskContainsAssigneePredicate(secondPredicateKeyword);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TaskContainsAssigneePredicate firstPredicateCopy = new TaskContainsAssigneePredicate(firstPredicateKeyword);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_taskContainsAssignee_returnsTrue() {
        // One keyword
        TaskContainsAssigneePredicate predicate = new TaskContainsAssigneePredicate("Alice");
        assertTrue(predicate.test(new TaskBuilder().withAssignees("Alice").build()));
    }


    @Test
    public void test_taskDoesNotContainAssigne_returnsFalse() {
        TaskContainsAssigneePredicate predicate = new TaskContainsAssigneePredicate("Alice");
        Task task = new TaskBuilder().withTitle("Meeting").build();

        assertFalse(predicate.test(task));
    }

    @Test
    public void test_taskContainsMultipleAssignee_returnsFalse() {
        TaskContainsAssigneePredicate predicate = new TaskContainsAssigneePredicate("Alice");
        Task task = new TaskBuilder().withAssignees("Alice", "Benoson").build();

        assertTrue(predicate.test(task));
    }

    @Test
    public void test_taskContainsMultipleAssignees_searchMultipleNamesreturnsFalse() {
        TaskContainsAssigneePredicate predicate = new TaskContainsAssigneePredicate("Alice Benson");
        Task task = new TaskBuilder().withAssignees("Alice", "Benoson").build();

        assertFalse(predicate.test(task));
    }

    @Test
    public void test_taskContainsMultipleAssignees_searchMixedCasingsReturnsTrue() {
        TaskContainsAssigneePredicate predicate = new TaskContainsAssigneePredicate("AliCE");
        Task task = new TaskBuilder().withAssignees("Alice").build();

        assertTrue(predicate.test(task));
    }
}
