package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

class RecurringDatesUtilTest {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Test
    void checkExpiryDate() {
        LocalDate currentDate = LocalDate.now();
        String endDateInput = "05/02/2019";
        LocalDate endDate = LocalDate.parse(endDateInput, FORMATTER);
        assertTrue(endDate.isBefore(currentDate));

        endDateInput = "31/12/2021";
        endDate = LocalDate.parse(endDateInput, FORMATTER);
        assertFalse(endDate.isBefore(currentDate));
    }

    @Test
    void formatRecurringScheduleInput() {
        String recurringSchedule = "[30/06/2021][MON][Biweekly]";
        String[] recurringScheduleData = recurringSchedule.replaceAll("\\]", "").split("\\[");

        LocalDate endDate = LocalDate.parse(recurringScheduleData[1], FORMATTER);
        assertEquals(endDate, LocalDate.parse("30/06/2021", FORMATTER));

        String dayOfWeek = recurringScheduleData[2].toLowerCase();
        assertEquals(dayOfWeek, "mon");

        String weekFreq = recurringScheduleData[3].toLowerCase();
        assertEquals(weekFreq, "biweekly");
    }
}
