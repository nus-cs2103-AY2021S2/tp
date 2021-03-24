package seedu.taskify.model.task.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.taskify.testutil.TaskBuilder;

class TaskHasSameDatePredicateTest {

    @Test
    public void equals() {
        // Compares between LocalDate and LocalDateTime to see if date is the same.
        LocalDate firstPredicateDate = LocalDate.parse("2021-03-24");

        // same object -> returns true
        assertTrue(firstPredicateDate.equals(firstPredicateDate));

        // different types -> returns false
        assertFalse(firstPredicateDate.equals(1));

        // null -> returns false
        assertFalse(firstPredicateDate.equals(null));
    }

    @Test
    public void test_sameDate_returnsTrue() {
        // Same dates (testing between LocalDate and LocalDateTime)
        LocalDate date = LocalDate.parse("2020-04-13");
        TaskHasSameDatePredicate predicate = new TaskHasSameDatePredicate(date);
        assertTrue(predicate.test(new TaskBuilder().withDate("2020-04-13 18:00").build()));
    }

    @Test
    public void test_differentDate_returnsFalse() {
        // Different dates
        LocalDate date = LocalDate.parse("2021-03-24");
        TaskHasSameDatePredicate predicate = new TaskHasSameDatePredicate(date);
        assertFalse(predicate.test(new TaskBuilder().withDate("2020-04-13 18:00").build()));
    }
}
