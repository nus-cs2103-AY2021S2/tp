package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class RecurringScheduleTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new RecurringSchedule(null));
    }

    @Test
    public void constructor_invalidRecurringSchedule_throwsIllegalArgumentException() {
        String invalidRecurringSchedule = "recurring schedule";
        assertThrows(IllegalArgumentException.class, () -> new RecurringSchedule(invalidRecurringSchedule));
    }

    @Test
    public void isInvalidRecurringSchedule() {
        // reject blank space argument since there is input required
        assertFalse(RecurringSchedule.isEmptyRecurringScheduleInput(" "));

        // missing frequency of week
        assertFalse(RecurringSchedule.isValidRecurringScheduleInput("[10 Mar 2021][Mon]"));

        // missing days of week
        assertFalse(RecurringSchedule.isValidRecurringScheduleInput("[10 Mar 2021][biweekly]"));

        // missing starting date
        assertFalse(RecurringSchedule.isValidRecurringScheduleInput("[Mon][biweekly]"));

        // missing '[]' brackets between starting date, days of week and week frequency
        assertFalse(RecurringSchedule.isValidRecurringScheduleInput("10 Mar 2021Monbiweekly"));

        // missing year from the starting date
        assertFalse(RecurringSchedule.isValidRecurringScheduleInput("[10 Mar][Mon][biweekly]"));

        // missing month from the starting date
        assertFalse(RecurringSchedule.isValidRecurringScheduleInput("[10 2021][Mon][biweekly]"));

        // missing year from the starting date
        assertFalse(RecurringSchedule.isValidRecurringScheduleInput("[10 Mar][Mon][biweekly]"));

        // missing days of week
        assertFalse(RecurringSchedule.isValidRecurringScheduleInput("[10 Mar][][biweekly]"));

        // missing frequency of week
        assertFalse(RecurringSchedule.isValidRecurringScheduleInput("[10 Mar][Mon][]"));

        // missing starting date, days of week, week frequency
        assertFalse(RecurringSchedule.isValidRecurringScheduleInput("[][][]"));

        // invalid special characters between spaces
        assertFalse(RecurringSchedule.isValidRecurringScheduleInput("[10@Mar*2021][Mon][biweekly]"));

        // invalid special characters
        assertFalse(RecurringSchedule.isValidRecurringScheduleInput("[10 @*! 2021][M_n][biwe$$ly]"));

        // invalid format for month in starting date, kept to 3 alphabet characters
        assertFalse(RecurringSchedule.isValidRecurringScheduleInput("[10 March 2021][Mon][biweekly]"));

        // invalid format for days of week, kept to 3 alphabet characters
        assertFalse(RecurringSchedule.isValidRecurringScheduleInput("[10 Mar 2021][Monday][biweekly]"));

        // invalid input for frequency of week, only accept weekly or biweekly
        assertFalse(RecurringSchedule.isValidRecurringScheduleInput("[10 Mar 2021][Monday][fortnight]"));

        // invalid formatting, missing '[]' brackets in between
        assertFalse(RecurringSchedule.isValidRecurringScheduleInput("[10 Mar 2021-Monday-biweekly]"));

        // wrong order => days of week comes before starting date
        assertFalse(RecurringSchedule.isValidRecurringScheduleInput("[Mon][10 Mar 2021][biWeekly]"));

        // wrong order => week frequency comes before days of week
        assertFalse(RecurringSchedule.isValidRecurringScheduleInput("[10 Mar 2021][biWeekly][Mon]"));

        // wrong order => week frequency comes before days of week and days of week comes before starting date
        assertFalse(RecurringSchedule.isValidRecurringScheduleInput("[biWeekly][Mon][10 Mar 2021]"));
    }

    @Test
    public void isValidRecurringSchedule() {
        // accept no argument since it is optional for a task to be recurring schedule
        assertTrue(RecurringSchedule.isEmptyRecurringScheduleInput(""));

        // valid biweekly recurring schedule, mixture of large and small caps
        assertTrue(RecurringSchedule.isValidRecurringScheduleInput("[10 Mar 2021][Mon][biWeekly]"));

        // valid biweekly recurring schedule, small caps only
        assertTrue(RecurringSchedule.isValidRecurringScheduleInput("[10 mar 2021][mon][biweekly]"));

        // valid biweekly recurring schedule, large caps only
        assertTrue(RecurringSchedule.isValidRecurringScheduleInput("[10 MAR 2021][MON][BIWEEKLY]"));

        // valid weekly recurring schedule, mixture of large and small caps
        assertTrue(RecurringSchedule.isValidRecurringScheduleInput("[10 Feb 2021][Wed][weekly]"));

        // valid weekly recurring schedule, small caps only
        assertTrue(RecurringSchedule.isValidRecurringScheduleInput("[10 feb 2021][wed][weekly]"));

        // valid weekly recurring schedule, large caps only
        assertTrue(RecurringSchedule.isValidRecurringScheduleInput("[10 FEB 2021][WED][WEEKLY]"));
    }
}
