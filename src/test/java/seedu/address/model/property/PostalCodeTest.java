package seedu.address.model.property;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PostalCodeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PostalCode(null));
    }

    @Test
    public void constructor_invalidPostal_throwsIllegalArgumentException() {
        String invalidPostal = "";
        assertThrows(IllegalArgumentException.class, () -> new PostalCode(invalidPostal));
    }

    @Test
    public void isValidPostal() {
        // null postal code
        assertThrows(NullPointerException.class, () -> PostalCode.isValidPostal(null));

        // invalid postal codes
        assertFalse(PostalCode.isValidPostal("")); // empty string
        assertFalse(PostalCode.isValidPostal(" ")); // spaces only
        assertFalse(PostalCode.isValidPostal("12")); // less than 3 numbers
        assertFalse(PostalCode.isValidPostal("postal")); // non-numeric
        assertFalse(PostalCode.isValidPostal("12post34")); // alphabets within digits
        assertFalse(PostalCode.isValidPostal("123 456")); // spaces within digits

        // valid postal codes
        assertTrue(PostalCode.isValidPostal("123")); // exactly 3 numbers
        assertTrue(PostalCode.isValidPostal("123456"));
        assertTrue(PostalCode.isValidPostal("123456789123456789")); // long postal code
    }
}
