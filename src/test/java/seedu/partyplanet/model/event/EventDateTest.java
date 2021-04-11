package seedu.partyplanet.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.partyplanet.testutil.Assert.assertThrows;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class EventDateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EventDate(null));
    }

    @Test
    public void constructor_invalidEventDate() {
        assertThrows(DateTimeException.class, () -> new EventDate("")); // wrong format
        assertThrows(DateTimeException.class, () -> new EventDate("2020-5-2")); // incorrect ISO format
        assertThrows(DateTimeException.class, () -> new EventDate("2020-05-32")); // invalid event date
        assertThrows(IllegalArgumentException.class, () -> new EventDate("--05-02")); // not valid year
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
                .map(EventDate::new)
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

}

