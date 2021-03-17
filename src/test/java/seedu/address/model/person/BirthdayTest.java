package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

public class BirthdayTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Birthday(null));
    }

    @Test
    public void constructor_invalidBirthday_throwsIllegalArgumentException() {
        String invalidBirthday = "";
        assertThrows(DateTimeParseException.class, () -> new Birthday(invalidBirthday));
    }

    @Test
    public void isValidBirthday() {
        // null birthday
        assertThrows(NullPointerException.class, () -> Birthday.isValidBirthday(null));

        // blank email
        assertFalse(Birthday.isValidBirthday("")); // empty string
        assertFalse(Birthday.isValidBirthday(" ")); // spaces only

        // missing parts
        assertFalse(Birthday.isValidBirthday("1999-12")); // missing local part
        assertFalse(Birthday.isValidBirthday("1999")); // missing local part
        assertFalse(Birthday.isValidBirthday("-12-15")); // missing local part

        // invalid parts
        assertFalse(Birthday.isValidBirthday("abcde")); // invalid input. Should be all int
        assertFalse(Birthday.isValidBirthday("@@@@")); //invalid input. Should not have special characters
        assertFalse(Birthday.isValidBirthday("12-12-1998")); // invalid ordering. Should be YYYY-MM-DD
        assertFalse(Birthday.isValidBirthday("1999/12/12")); // invalid formatting. use - instead of /

        // valid birthdays
        assertTrue(Birthday.isValidBirthday("1999-12-12"));
        assertTrue(Birthday.isValidBirthday("2000-10-10")); // minimal
        assertTrue(Birthday.isValidBirthday("1985-12-15")); // alphabets only
    }
}

