package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    public void equals() {
        String firstRecurringScheduleDetails = "[30/05/2021][mon][weekly]";
        String secondRecurringScheduleDetails = "[30/07/2021][tue][biweekly]";

        RecurringSchedule firstRecurringSchedule = new RecurringSchedule(firstRecurringScheduleDetails);
        RecurringSchedule secondRecurringSchedule = new RecurringSchedule(secondRecurringScheduleDetails);

        // different recurring schedule -> returns true
        assertFalse(firstRecurringSchedule.equals(secondRecurringSchedule));

        // same recurring schedule -> returns true
        assertTrue(firstRecurringSchedule.equals(firstRecurringSchedule));

        // same values -> returns true
        RecurringSchedule firstRecurringScheduleCopy = new RecurringSchedule(firstRecurringScheduleDetails);
        assertTrue(firstRecurringSchedule.equals(firstRecurringScheduleCopy));

        // different types -> returns false
        assertFalse(firstRecurringSchedule.equals(1));

        // null -> returns false
        assertFalse(firstRecurringSchedule.equals(null));
    }

    @Test
    public void isEmptyRecurringScheduleInput() {
        // reject blank space argument since there is input required
        assertFalse(RecurringSchedule.isEmptyRecurringScheduleInput(" "));

        // accept no argument since it is optional for a task to be recurring schedule
        assertTrue(RecurringSchedule.isEmptyRecurringScheduleInput(""));
    }

    @Test
    public void isInvalidRecurringSchedule_missingParameters() {

        // missing frequency of week
        assertFalse(RecurringSchedule.isValidRecurringScheduleInput("[10/06/2021][Mon]"));

        // missing days of week
        assertFalse(RecurringSchedule.isValidRecurringScheduleInput("[10/06/2021][biweekly]"));

        // missing starting date
        assertFalse(RecurringSchedule.isValidRecurringScheduleInput("[Mon][biweekly]"));

        // missing '[]' brackets between starting date, days of week and week frequency
        assertFalse(RecurringSchedule.isValidRecurringScheduleInput("10/06/2021Monbiweekly"));

        // missing month from the starting date
        assertFalse(RecurringSchedule.isValidRecurringScheduleInput("[10/2021][Mon][biweekly]"));

        // missing year from the starting date
        assertFalse(RecurringSchedule.isValidRecurringScheduleInput("[10/06/ ][Mon][biweekly]"));

        // missing days of week
        assertFalse(RecurringSchedule.isValidRecurringScheduleInput("[10/06/2021][][biweekly]"));

        // missing frequency of week
        assertFalse(RecurringSchedule.isValidRecurringScheduleInput("[10/06/2021][Mon][]"));

        // missing starting date, days of week, week frequency
        assertFalse(RecurringSchedule.isValidRecurringScheduleInput("[][][]"));
    }

    @Test
    public void isInvalidRecurringSchedule_invalidInput() {
        // invalid special characters between spaces
        assertFalse(RecurringSchedule.isValidRecurringScheduleInput("[10@06r*2021][Mon][biweekly]"));

        // invalid special characters
        assertFalse(RecurringSchedule.isValidRecurringScheduleInput("[10 @*! 2021][M_n][biwe$$ly]"));

        // invalid format for day in starting date, accept up to value 31 only
        assertFalse(RecurringSchedule.isValidRecurringScheduleInput("[40/12/2021][Mon][biweekly]"));

        // invalid format for month in starting date, accept up to value 12 only
        assertFalse(RecurringSchedule.isValidRecurringScheduleInput("[10/13/2021][Mon][biweekly]"));

        // invalid format for days of week, kept to 3 alphabet characters
        assertFalse(RecurringSchedule.isValidRecurringScheduleInput("[10/06/2021][Monday][biweekly]"));

        // invalid input for frequency of week, only accept weekly or biweekly
        assertFalse(RecurringSchedule.isValidRecurringScheduleInput("[10/06/2021][Mon][fortnight]"));

        // invalid formatting, missing '[]' brackets in between
        assertFalse(RecurringSchedule.isValidRecurringScheduleInput("[10/06/2021-Mon-biweekly]"));

        // wrong order => days of week comes before starting date
        assertFalse(RecurringSchedule.isValidRecurringScheduleInput("[Mon][10/06/2021][biWeekly]"));

        // wrong order => week frequency comes before days of week
        assertFalse(RecurringSchedule.isValidRecurringScheduleInput("[10/06/2021][biWeekly][Mon]"));

        // wrong order => week frequency comes before days of week and days of week comes before starting date
        assertFalse(RecurringSchedule.isValidRecurringScheduleInput("[biWeekly][Mon][10/06/2021]"));
    }

    @Test
    public void isValidRecurringSchedule() {
        // valid biweekly recurring schedule, mixture of large and small caps
        assertTrue(RecurringSchedule.isValidRecurringScheduleInput("[10/06/2021][Mon][biWeekly]"));

        // valid biweekly recurring schedule, large caps only
        assertTrue(RecurringSchedule.isValidRecurringScheduleInput("[10/06/2021][MON][BIWEEKLY]"));

        // valid weekly recurring schedule, mixture of large and small caps
        assertTrue(RecurringSchedule.isValidRecurringScheduleInput("[10/06/2021][Wed][weekly]"));

        // valid weekly recurring schedule, small caps only
        assertTrue(RecurringSchedule.isValidRecurringScheduleInput("[10/06/2021][wed][weekly]"));
    }

    @Test
    public void isInRecurringSchedule() {
        String firstDateString = "10/05/2021";
        List<String> weekDates = new ArrayList<>(Arrays.asList("10/05/2021" , "17/05/2021", "24/05/2021"));

        // date string found in recurring schedule
        assertTrue(weekDates.stream().anyMatch(date -> date.equals(firstDateString)));

        // date string not found in recurring schedule
        String secondDateString = "31/05/2021";
        assertFalse(weekDates.stream().anyMatch(date -> date.equals(secondDateString)));

    }
}
