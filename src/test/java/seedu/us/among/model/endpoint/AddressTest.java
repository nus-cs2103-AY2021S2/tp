package seedu.us.among.model.endpoint;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.us.among.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AddressTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Address(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidAddress = "";
        assertThrows(IllegalArgumentException.class, () -> new Address(invalidAddress));
    }

    @Test
    public void isValidAddress() {
        // invalid addresses
        assertFalse(Address.isValidAddress("")); // empty string
        assertFalse(Address.isValidAddress(" ")); // spaces only
        assertFalse(Address.isValidAddress("https://google.com\\")); // invalid address

        // valid addresses
        assertTrue(Address.isValidAddress("https://google.com"));
        assertTrue(Address.isValidAddress("127.0.0.1")); // one character
        assertTrue(Address.isValidAddress("https://reqres.in/api/users")); // long address
    }

    @Test
    public void isUrlValid_validUrl_returnsTrue() {
        boolean actualResult = Address.isUrlValid("https://google.com");
        assertTrue(actualResult);
    }

    @Test
    public void isUrlValid_invalidUrl_returnsFalse() {
        boolean actualResult = Address.isUrlValid("google.com");
        boolean actualResult2 = Address.isUrlValid("hts.com.csx");
        boolean actualResult3 = Address.isUrlValid("httppp://g.com");
        assertFalse(actualResult);
        assertFalse(actualResult2);
        assertFalse(actualResult3);
    }
}
