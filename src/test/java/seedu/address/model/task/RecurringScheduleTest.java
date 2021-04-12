package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.task.attributes.RecurringSchedule;

public class RecurringScheduleTest {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new RecurringSchedule(null));
    }

    @Test
    public void constructor_invalidRecurringSchedule_throwsIllegalArgumentException() {
        String invalidRecurringSchedule = "recurring schedule";
        assertThrows(IllegalArgumentException.class, () -> new RecurringSchedule(invalidRecurringSchedule));


        String blankRecurringSchedule = " ";
        assertThrows(IllegalArgumentException.class, () -> new RecurringSchedule(blankRecurringSchedule));
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
        RecurringSchedule emptyRecurringSchedule = new RecurringSchedule("");
        // accept no argument since it is optional for a task to be recurring schedule
        assertTrue(emptyRecurringSchedule.isEmptyValue());
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

        // whitespaces between end date, day of week, week frequency
        assertFalse(RecurringSchedule.isValidRecurringScheduleInput("[10/06/2021]   [Mon]    [biweekly]"));

        // invalid format for days of week, kept to 3 alphabet characters
        assertFalse(RecurringSchedule.isValidRecurringScheduleInput("[10/06/2021][Monday][biweekly]"));

        // invalid input for frequency of week, only accept weekly or biweekly
        assertFalse(RecurringSchedule.isValidRecurringScheduleInput("[10/06/2021][Mon][fortnight]"));

        // invalid formatting, missing '[]' brackets in between
        assertFalse(RecurringSchedule.isValidRecurringScheduleInput("[10/06/2021-Mon-biweekly]"));
    }


    @Test
    public void isInvalidRecurringSchedule_invalidInputOrder() {
        // wrong order => days of week comes before starting date
        assertFalse(RecurringSchedule.isValidRecurringScheduleInput("[Mon][10/06/2021][biWeekly]"));

        // wrong order => week frequency comes before days of week
        assertFalse(RecurringSchedule.isValidRecurringScheduleInput("[10/06/2021][biWeekly][Mon]"));

        // wrong order => week frequency comes before days of week and days of week comes before starting date
        assertFalse(RecurringSchedule.isValidRecurringScheduleInput("[biWeekly][Mon][10/06/2021]"));

        // spaces exist
        assertFalse(RecurringSchedule.isValidRecurringScheduleInput("[10/06/2021][wed][weekly ]"));

        // spaces exist
        assertFalse(RecurringSchedule.isValidRecurringScheduleInput("[10/06/2021] [wed][weekly]"));

        // spaces exist
        assertFalse(RecurringSchedule.isValidRecurringScheduleInput("[ 10/06/2021][wed][weekly]"));

        // line breaks exist
        assertFalse(RecurringSchedule.isValidRecurringScheduleInput("[10/06/2021\n][wed][weekly]"));

        // line breaks exist
        assertFalse(RecurringSchedule.isValidRecurringScheduleInput("[10/06/2021][wed][weekly]\n"));
    }

    @Test
    public void isInvalidRecurringSchedule_invalidEndDateInput() {
        // invalid format for day in starting date, accept up to value 31 only
        assertFalse(RecurringSchedule.isValidRecurringScheduleInput("[40/12/2021][Mon][biweekly]"));

        // invalid format for month in starting date, accept up to value 12 only
        assertFalse(RecurringSchedule.isValidRecurringScheduleInput("[10/13/2021][Mon][biweekly]"));

        // invalid format for end date since negative value in day of end date
        assertFalse(RecurringSchedule.isValidRecurringScheduleInput("[-5/13/2021][Mon][biweekly]"));
    }

    @Test
    public void isInvalidDateRange() {
        RecurringSchedule recurringSchedule = new RecurringSchedule("[31/06/2021][Mon][biweekly]");

        // month of june only has 30 days
        assertTrue(recurringSchedule.isInvalidDate());

        recurringSchedule = new RecurringSchedule("[29/02/2022][Mon][biweekly]");
        // month of feb in non-leap year only has 28 days
        assertTrue(recurringSchedule.isInvalidDate());
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

    @Test
    public void checkExpiryDate() {
        LocalDate currentDate = LocalDate.now();
        String endDateInput = "05/02/2019";
        LocalDate endDate = LocalDate.parse(endDateInput, FORMATTER);

        // end date is after current date
        assertTrue(endDate.isBefore(currentDate));

        endDateInput = "31/12/2021";
        endDate = LocalDate.parse(endDateInput, FORMATTER);

        // end date is ahead of current date
        assertFalse(endDate.isBefore(currentDate));
    }

    @Test
    public void formatRecurringScheduleInput() {
        String recurringSchedule = "[30/06/2021][MON][Biweekly]";
        String[] recurringScheduleData = recurringSchedule.replaceAll("\\]", "").split("\\[");

        LocalDate endDate = LocalDate.parse(recurringScheduleData[1], FORMATTER);
        String dayOfWeek = recurringScheduleData[2].toLowerCase();
        String weekFreq = recurringScheduleData[3].toLowerCase();

        // end of date is valid input
        assertEquals(endDate, LocalDate.parse("30/06/2021", FORMATTER));

        // day of week is valid input
        assertEquals(dayOfWeek, "mon");

        // week frequency is valid input
        assertEquals(weekFreq, "biweekly");

        String outputRecurringScheduleDetail = " Every " + dayOfWeek + " " + weekFreq + " until "
                + recurringScheduleData[1];

        // valid output recurring detail
        assertEquals(outputRecurringScheduleDetail, " Every mon biweekly until 30/06/2021");
    }

    @Test
    public void calculateNumWeeksBetweenDates() {
        // current date to be 28/03/2021 (Sun)
        LocalDate currentDate = LocalDate.parse("28/03/2021", FORMATTER);
        // end date to be 02/04/2021 here (Fri of same week)
        LocalDate firstEndDate = LocalDate.parse("02/04/2021", FORMATTER);

        LocalDate startingDate = currentDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
        LocalDate endingDate = firstEndDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.SATURDAY));

        long daysBetweenDates = ChronoUnit.DAYS.between(startingDate, endingDate);
        int numWeeks = (int) Math.ceil(daysBetweenDates / 7.0);

        // number of weeks is considered 0 and not 1
        // since it is within a single week range from the previous week Sun till the current week Sat
        assertEquals(numWeeks, 0);

        // end date to be 03/04/2021 here (Sat to be considered the last day of same week)
        LocalDate secondEndDate = LocalDate.parse("03/04/2021", FORMATTER);
        endingDate = secondEndDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.SATURDAY));

        daysBetweenDates = ChronoUnit.DAYS.between(startingDate, endingDate);
        numWeeks = (int) Math.ceil(daysBetweenDates / 7.0);

        // number of weeks is considered 1 since it has just completed a single week range (inclusive of Sat)
        // from the previous week Sun till the current week Sat
        assertEquals(numWeeks, 1);

        LocalDate thirdEndDate = LocalDate.parse("18/04/2021", FORMATTER);
        endingDate = thirdEndDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.SATURDAY));

        daysBetweenDates = ChronoUnit.DAYS.between(startingDate, endingDate);
        numWeeks = (int) Math.ceil(daysBetweenDates / 7.0);

        // number of weeks is considered 3 since it has completed 3 weeks range
        assertEquals(numWeeks, 3);
    }
}
