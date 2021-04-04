package seedu.address.model.task.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.task.attributes.Date;
import seedu.address.testutil.TaskBuilder;

class TaskDateOnDatePredicateTest {
    @Test
    public void test_taskDateOnDate_returnsTrue() {
        TaskDateOnDatePredicate predicate = new TaskDateOnDatePredicate(new Date("10/04/2021"));
        assertTrue(predicate.test(new TaskBuilder().withDate("10/04/2021").build()));
    }

    @Test
    public void test_taskDateNotOnDate_returnsFalse() {
        // given date on schedule and no date
        TaskDateOnDatePredicate predicate = new TaskDateOnDatePredicate(new Date("08/05/2021"));
        assertFalse(predicate.test(new TaskBuilder().withRecurringSchedule("[08/05/2021][sat][weekly]").build()));

        // wrong date
        predicate = new TaskDateOnDatePredicate(new Date("08/05/2021"));
        assertFalse(predicate.test(new TaskBuilder().withDate("09/05/2021").build()));

        // no date
        predicate = new TaskDateOnDatePredicate(new Date("08/05/2021"));
        assertFalse(predicate.test(new TaskBuilder().build()));
    }
}
