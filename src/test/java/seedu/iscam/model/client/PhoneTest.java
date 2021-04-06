package seedu.iscam.model.client;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.iscam.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PhoneTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Phone(null));
    }

    @Test
    public void constructor_invalidPhone_throwsIllegalArgumentException() {
        String invalidPhone = "";
        assertThrows(IllegalArgumentException.class, () -> new Phone(invalidPhone));
    }

    @Test
    public void isValidPhone() {
        // null phone number
        assertThrows(NullPointerException.class, () -> Phone.isValidPhoneLength(null));

        // invalid phone numbers
        assertFalse(Phone.isValidPhoneLength("")); // empty string
        assertFalse(Phone.isValidPhoneLength(" ")); // spaces only
        assertFalse(Phone.isValidPhoneLength("91")); // less than 3 numbers
        assertFalse(Phone.isValidPhoneLength("phone")); // non-numeric
        assertFalse(Phone.isValidPhoneLength("9312 1534")); // spaces within digits
        assertFalse(Phone.isValidPhoneLength("124293842033123")); // long phone numbers
        assertFalse(Phone.isValidPhoneLength("911")); // exactly 3 numbers
        assertFalse(Phone.isValidNumbersOnly("9011p041")); // alphabets within digits
        assertFalse(Phone.isValidPhoneNumber("12345678")); // does not start with 6, 8 or 9
        assertFalse(Phone.isValidNumbersOnly("9011p1041")); // alphabets within digits over length 8


        // valid phone numbers (8 digits only)
        assertTrue(Phone.isValidPhoneLength("63121534")); // start with 6
        assertTrue(Phone.isValidPhoneLength("83121534")); // start with 8
        assertTrue(Phone.isValidPhoneLength("93121534")); // start with 9
    }
}
