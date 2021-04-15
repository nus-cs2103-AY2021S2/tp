package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class BirthdayTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Birthday((LocalDate) null));
    }

    @Test
    public void constructor_invalidBirthday_throwsIllegalArgumentException() {
        String invalidAddress = "";
        assertThrows(IllegalArgumentException.class, () -> new Birthday(invalidAddress));
    }

    @Test
    public void isValidBirthdayString() {
        // invalid birthday
        assertFalse(Birthday.isValidBirthday("")); // empty string
        assertFalse(Birthday.isValidBirthday(" ")); // spaces only
        assertFalse(Birthday.isValidBirthday("99-99-9999"));
        assertFalse(Birthday.isValidBirthday("02-02-2099"));

        // valid birthday
        assertTrue(Birthday.isValidBirthday("01-01-1998"));
        assertTrue(Birthday.isValidBirthday("03-02-1998"));
        assertTrue(Birthday.isValidBirthday("02-01-2000"));
    }

    @Test
    public void isValidBirthdayDate() {
        LocalDate now = LocalDate.now();

        assertTrue(Birthday.isValidBirthday(now));

        assertFalse(Birthday.isValidBirthday(now.plusDays(1)));
    }
}
