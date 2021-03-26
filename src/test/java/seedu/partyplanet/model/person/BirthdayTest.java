package seedu.partyplanet.model.person;

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

public class BirthdayTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Birthday(null));
    }

    @Test
    public void constructor_invalidBirthday_throwsDateTimeException() {
        String invalidBirthday = "";
        assertThrows(DateTimeException.class, () -> new Birthday(invalidBirthday));
    }

    @Test
    public void isValidBirthday_invalidBirthdays() {
        // null birthdays
        assertThrows(NullPointerException.class, () -> Birthday.isValidBirthdayDate(null));

        // invalid birthdays
        assertFalse(Birthday.isValidBirthdayDate("")); // empty string
        assertFalse(Birthday.isValidBirthdayDate(" ")); // spaces only
        assertFalse(Birthday.isValidBirthdayDate("91")); // none match
        assertFalse(Birthday.isValidBirthdayDate("2021 Feb 20")); // unsupported format
        assertFalse(Birthday.isValidBirthdayDate("20Feb2021")); // unsupported format
    }

    @Test
    public void isValidBirthday_validWithYears() {
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
            assertTrue(Birthday.isValidBirthdayDate(validInput));
        }
        assertEquals(1, Arrays.stream(validInputs)
                .map(Date::parseDate)
                .map(x -> (LocalDate) x)
                .distinct()
                .count());
    }

    @Test
    public void isValidBirthday_validWithoutYears() {
        String[] validInputs = new String[]{
            "--02-03",
            "3/2",
            "3 Feb",
            "3 February",
            "Feb 3",
            "February 3",
        };
        for (String validInput: validInputs) {
            assertTrue(Birthday.isValidBirthdayDate(validInput));
        }
        assertEquals(1, Arrays.stream(validInputs)
                .map(Date::parseDate)
                .map(x -> (MonthDay) x)
                .distinct()
                .count());
    }

    @Test
    public void isValidBirthdayDate() {
        LocalDate referenceDate = LocalDate.of(2021, 6, 6);

        // MonthDay is always valid
        assertTrue(Birthday.isValidBirthdayDate("--02-03", referenceDate));
        assertTrue(Birthday.isValidBirthdayDate("--06-06", referenceDate));
        assertTrue(Birthday.isValidBirthdayDate("--12-12", referenceDate));

        // LocalDate must be before reference
        assertTrue(Birthday.isValidBirthdayDate("2021-06-05", referenceDate));
        assertTrue(Birthday.isValidBirthdayDate("2021-06-06", referenceDate));
        assertFalse(Birthday.isValidBirthdayDate("2021-06-07", referenceDate));
    }

    @Test
    public void compareTo() {
        // Same year
        Birthday localDate0606 = new Birthday("2020-06-06");
        Birthday monthDay0606 = new Birthday("--06-06");
        Birthday monthDay0607 = new Birthday("--06-07");
        Birthday localDate0608 = new Birthday("2020-06-08");

        // Different year
        Birthday localDatePrevYear = new Birthday("2019-08-13");

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
