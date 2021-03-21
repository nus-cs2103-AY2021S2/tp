package seedu.partyplanet.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.partyplanet.testutil.Assert.assertThrows;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.MonthDay;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.partyplanet.model.date.Date;

public class EventDateTest {


    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EventDate(null));
    }

    @Test
    public void constructor_invalidEventDate_throwsDateTimeException() {
        String invalidEventDate = "";
        assertThrows(DateTimeException.class, () -> new EventDate(invalidEventDate));
    }

    @Test
    public void isValidEventDate_invalidEventDate() {
        // null event dates
        assertThrows(NullPointerException.class, () -> EventDate.isValidEventDate(null));

        // invalid event dates
        assertFalse(EventDate.isValidEventDate("")); // empty string
        assertFalse(EventDate.isValidEventDate(" ")); // spaces only
        assertFalse(EventDate.isValidEventDate("91")); // none match
        assertFalse(EventDate.isValidEventDate("2021 Feb 20")); // unsupported format
        assertFalse(EventDate.isValidEventDate("20Feb2021")); // unsupported format
    }

    @Test
    public void isValidEventDate_validWithYears() {
        String[] validInputs = new String[]{
            "2021-02-03",
            "3.2.2021",
            "3/2/2021",
            "3 Feb 2021",
            "3 February 2021",
            "Feb 3 2021",
            "February 3 2021",
        };
        for (String validInput: validInputs) {
            assertTrue(EventDate.isValidEventDate(validInput));
        }
        assertEquals(1, Arrays.stream(validInputs)
                .map(Date::parseDate)
                .map(x -> (LocalDate) x)
                .distinct()
                .count());
    }

    @Test
    public void isValidEventDate_validWithoutYears() {
        String[] validInputs = new String[]{
            "--02-03",
            "3/2",
            "3 Feb",
            "3 February",
            "Feb 3",
            "February 3",
        };
        for (String validInput: validInputs) {
            assertTrue(EventDate.isValidEventDate(validInput));
        }
        assertEquals(1, Arrays.stream(validInputs)
                .map(Date::parseDate)
                .map(x -> (MonthDay) x)
                .distinct()
                .count());
    }

    @Test
    public void isValidEventDate() {
        LocalDate referenceDate = LocalDate.of(2021, 6, 6);

        // MonthDay is always valid
        assertTrue(EventDate.isValidEventDate("--02-03"));
        assertTrue(EventDate.isValidEventDate("--06-06"));
        assertTrue(EventDate.isValidEventDate("--12-12"));

        // LocalDate need not be before reference
        assertTrue(EventDate.isValidEventDate("2020-06-05"));
        assertTrue(EventDate.isValidEventDate("2025-06-06"));
        assertTrue(EventDate.isValidEventDate("9999-06-07"));
    }

    @Test
    public void compareTo() {
        // Same year
        EventDate localDate0606 = new EventDate("2020-06-06");
        EventDate monthDay0606 = new EventDate("--06-06");
        EventDate monthDay0607 = new EventDate("--06-07");
        EventDate localDate0608 = new EventDate("2020-06-08");

        // Different year
        EventDate localDatePrevYear = new EventDate("2019-08-13");

        // Same date
        assertEquals(0, localDate0606.compareTo(localDate0606));
        assertEquals(0, monthDay0606.compareTo(monthDay0606));
        assertEquals(0, localDate0606.compareTo(monthDay0606));

        // Different date
        assertTrue(localDate0606.compareTo(monthDay0607) < 0);
        assertTrue(monthDay0606.compareTo(monthDay0607) < 0);
        assertTrue(localDate0606.compareTo(localDate0608) < 0);
        assertTrue(monthDay0607.compareTo(localDate0608) < 0);
        assertTrue(localDate0608.compareTo(localDatePrevYear) < 0);
        assertTrue(localDatePrevYear.compareTo(localDate0606) > 0);
    }
}

