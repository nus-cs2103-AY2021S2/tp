package seedu.address.model.contact;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Contains unit tests for {@code ContactPhone}.
 */
public class ContactPhoneTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ContactPhone(null));
    }

    @Test
    public void constructor_invalidPhone_throwsIllegalArgumentException() {
        String invalidPhone = "";
        assertThrows(IllegalArgumentException.class, () -> new ContactPhone(invalidPhone));
    }

    @Test
    public void isValidPhone() {
        // null phone number
        assertThrows(NullPointerException.class, () -> ContactPhone.isValidPhone(null));

        // invalid phone numbers
        assertFalse(ContactPhone.isValidPhone("")); // empty string
        assertFalse(ContactPhone.isValidPhone(" ")); // spaces only
        assertFalse(ContactPhone.isValidPhone("91")); // less than 3 digits
        assertFalse(ContactPhone.isValidPhone("impostor")); // non-numeric
        assertFalse(ContactPhone.isValidPhone("9011p041")); // alphabets within digits
        assertFalse(ContactPhone.isValidPhone("9312 1534")); // spaces within digits
        assertFalse(ContactPhone.isValidPhone("9812748")); // 7 digits
        assertFalse(ContactPhone.isValidPhone("09127382")); // 8 digits but leading zero
        assertFalse(ContactPhone.isValidPhone("981274822")); // 9 digits
        assertFalse(ContactPhone.isValidPhone("124293842033123")); // long phone numbers

        // valid phone numbers
        assertTrue(ContactPhone.isValidPhone("93121534")); // exactly 8 digits
        assertTrue(ContactPhone.isValidPhone("12345678")); // exactly 8 digits

    }
}
