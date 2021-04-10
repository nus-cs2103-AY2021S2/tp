package seedu.address.model.task.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.task.attributes.Date;
import seedu.address.testutil.TaskBuilder;

class TaskOnDatePredicateTest {
    @Test
    public void test_taskContainsDate_returnsTrue() {
        TaskOnDatePredicate predicate = new TaskOnDatePredicate(new Date("15/05/2021"));
        assertTrue(predicate.test(new TaskBuilder().withRecurringSchedule("[15/05/2021][sat][weekly]").build()));

        // given date on schedule's end date
        predicate = new TaskOnDatePredicate(new Date("08/05/2021"));
        assertTrue(predicate.test(new TaskBuilder().withRecurringSchedule("[08/05/2021][sat][weekly]").build()));

        // given date on date
        predicate = new TaskOnDatePredicate(new Date("08/05/2021"));
        assertTrue(predicate.test(new TaskBuilder().withDate("08/05/2021").build()));
    }

    @Test
    public void test_taskDoesNotContainDate_returnsFalse() {
        // given date not in schedule and no date
        TaskOnDatePredicate predicate = new TaskOnDatePredicate(new Date("16/05/2021"));
        assertFalse(predicate.test(new TaskBuilder().withRecurringSchedule("[10/06/2021][sat][weekly]").build()));

        // given date after schedule's end date and no date
        predicate = new TaskOnDatePredicate(new Date("08/05/2021"));
        assertFalse(predicate.test(new TaskBuilder().withRecurringSchedule("[08/04/2021][sat][weekly]").build()));

        // no schedule and wrong date
        predicate = new TaskOnDatePredicate(new Date("08/05/2021"));
        assertFalse(predicate.test(new TaskBuilder().withDate("09/05/2021").build()));

        // no schedule and no date
        predicate = new TaskOnDatePredicate(new Date("08/05/2021"));
        assertFalse(predicate.test(new TaskBuilder().build()));
    }
}
