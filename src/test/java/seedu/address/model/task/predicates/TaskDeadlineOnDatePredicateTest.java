package seedu.address.model.task.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.task.Deadline;
import seedu.address.testutil.TaskBuilder;

class TaskDeadlineOnDatePredicateTest {
    @Test
    public void test_deadlineOnDate_returnsTrue() {
        TaskDeadlineOnDatePredicate predicate = new TaskDeadlineOnDatePredicate(new Deadline("10/04/2021"));
        assertTrue(predicate.test(new TaskBuilder().withDeadline("10/04/2021").build()));
    }

    @Test
    public void test_deadlineNotOnDate_returnsFalse() {
        // given date on schedule and no deadline
        TaskDeadlineOnDatePredicate predicate = new TaskDeadlineOnDatePredicate(new Deadline("08/05/2021"));
        assertFalse(predicate.test(new TaskBuilder().withRecurringSchedule("[08/05/2021][sat][weekly]").build()));

        // wrong deadline
        predicate = new TaskDeadlineOnDatePredicate(new Deadline("08/05/2021"));
        assertFalse(predicate.test(new TaskBuilder().withDeadline("09/05/2021").build()));

        // no deadline
        predicate = new TaskDeadlineOnDatePredicate(new Deadline("08/05/2021"));
        assertFalse(predicate.test(new TaskBuilder().build()));
    }
}
