package seedu.address.model.task.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TaskBuilder;

class TaskScheduledOnDatePredicateTest {
    @Test
    public void test_scheduleContainsDate_returnsTrue() {
        TaskScheduledOnDatePredicate predicate = new TaskScheduledOnDatePredicate("24/04/2021");
        assertTrue(predicate.test(new TaskBuilder().withRecurringSchedule("[10/06/2021][sat][weekly]").build()));

        // given date on schedule's end date
        predicate = new TaskScheduledOnDatePredicate("08/05/2021");
        assertTrue(predicate.test(new TaskBuilder().withRecurringSchedule("[08/05/2021][sat][weekly]").build()));
    }

    @Test
    public void test_scheduleDoesNotContainDate_returnsFalse() {
        TaskScheduledOnDatePredicate predicate = new TaskScheduledOnDatePredicate("11/04/2021");
        assertFalse(predicate.test(new TaskBuilder().withRecurringSchedule("[10/06/2021][sat][weekly]").build()));

        // given date after schedule's end date
        predicate = new TaskScheduledOnDatePredicate("08/05/2021");
        assertFalse(predicate.test(new TaskBuilder().withRecurringSchedule("[08/04/2021][sat][weekly]").build()));

        // no schedule and on date
        predicate = new TaskScheduledOnDatePredicate("08/05/2021");
        assertFalse(predicate.test(new TaskBuilder().withDate("08/05/2021").build()));

        // no schedule
        predicate = new TaskScheduledOnDatePredicate("08/05/2021");
        assertFalse(predicate.test(new TaskBuilder().build()));
    }
}
