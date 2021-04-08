package seedu.address.model.schedule;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCHEDULE_DATE_TIME_FROM_ONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCHEDULE_DATE_TIME_FROM_TWO;

import org.junit.jupiter.api.Test;
import seedu.address.model.appointment.AppointmentDateTime;
import seedu.address.model.appointment.DateViewPredicate;
import seedu.address.testutil.AppointmentBuilder;
import seedu.address.testutil.ScheduleBuilder;

public class ScheduleDateViewPredicateTest {

    AppointmentDateTime firstPredicateDateTime = new AppointmentDateTime(VALID_SCHEDULE_DATE_TIME_FROM_ONE);
    AppointmentDateTime secondPredicateDateTime = new AppointmentDateTime(VALID_SCHEDULE_DATE_TIME_FROM_TWO);

    @Test
    public void equals() {
        ScheduleDateViewPredicate firstPredicate = new ScheduleDateViewPredicate(firstPredicateDateTime);
        ScheduleDateViewPredicate secondPredicate = new ScheduleDateViewPredicate(secondPredicateDateTime);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ScheduleDateViewPredicate firstPredicateCopy = new ScheduleDateViewPredicate(firstPredicateDateTime);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different appointment -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_containDate_returnsTrue() {
        ScheduleDateViewPredicate predicate = new ScheduleDateViewPredicate(firstPredicateDateTime);
        assertTrue(predicate.test(new ScheduleBuilder().withTimeFrom(VALID_SCHEDULE_DATE_TIME_FROM_ONE).build()));
    }

    @Test
    public void test_doesNotContainDate_returnsFalse() {
        // Non-matching date
        DateViewPredicate predicate = new DateViewPredicate(firstPredicateDateTime);
        assertFalse(predicate.test(new AppointmentBuilder().withTimeFrom(VALID_SCHEDULE_DATE_TIME_FROM_TWO).build()));

        // Non-matching date in different format
        predicate = new DateViewPredicate(new AppointmentDateTime("2021/03/12 12:00PM"));
        assertFalse(predicate.test(new AppointmentBuilder().withTimeFrom("2021/03/30 12:00PM").build()));
    }
}
