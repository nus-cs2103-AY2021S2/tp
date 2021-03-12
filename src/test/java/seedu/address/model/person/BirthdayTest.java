
package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class BirthdayTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Birthday(null));
    }

    @Test
    public void constructor_invalidBirthday_throwsIllegalArgumentException() {
        String invalidBirthday = "";
        assertThrows(IllegalArgumentException.class, () -> new Birthday(invalidBirthday));
    }

    @Test
    public void isValidEmail() {
        // null birthday
        assertThrows(NullPointerException.class, () -> Birthday.isValidBirthday(null));

        // blank email
        assertFalse(Birthday.isValidBirthday("")); // empty string
        assertFalse(Birthday.isValidBirthday(" ")); // spaces only

        // missing parts
        assertFalse(Birthday.isValidBirthday("1999-12")); // missing local part

        // invalid parts
        assertFalse(Birthday.isValidBirthday("abcde")); // invalid input. Should be all int
        assertFalse(Birthday.isValidBirthday("12-12-1998")); // invalid ordering. Should be YYYY-MM-DD
        assertFalse(Birthday.isValidBirthday("1999/12/12")); // invalid formatting. use - instead of /

        // valid email
        assertTrue(Birthday.isValidBirthday("1999-12-12"));
        assertTrue(Birthday.isValidBirthday("2000-10-10")); // minimal
        assertTrue(Birthday.isValidBirthday("1985-15-12")); // alphabets only
    }
}