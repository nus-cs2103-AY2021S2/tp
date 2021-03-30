package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

import org.junit.jupiter.api.Test;

class RecurringDatesUtilTest {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Test
    void checkExpiryDate() {
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
    void formatRecurringScheduleInput() {
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

        String outputRecurringScheduleDetail = " every " + dayOfWeek + " " + weekFreq + " until "
                + recurringScheduleData[1];

        // valid output recurring detail
        assertEquals(outputRecurringScheduleDetail, " every mon biweekly until 30/06/2021");
    }

    @Test
    void calculateNumWeeksBetweenDates() {
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
