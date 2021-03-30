package seedu.partyplanet.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.partyplanet.testutil.Assert.assertThrows;

import java.time.LocalDate;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class BirthdayTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Birthday(null));
    }

    @Test
    public void constructor_invalidBirthday_throwsDateTimeException() {
        String invalidBirthday = "";
        assertThrows(IllegalArgumentException.class, () -> new Birthday(invalidBirthday));
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
                .map(Birthday::new)
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
                .map(Birthday::new)
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
}
