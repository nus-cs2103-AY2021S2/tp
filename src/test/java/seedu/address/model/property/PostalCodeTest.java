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
        assertFalse(PostalCode.isValidPostal("1234")); // less than 5 numbers
        assertFalse(PostalCode.isValidPostal("12345678901")); // more than 10 numbers
        assertFalse(PostalCode.isValidPostal("postal")); // non-numeric
        assertFalse(PostalCode.isValidPostal("12post34")); // alphabets within digits
        assertFalse(PostalCode.isValidPostal("123 456")); // spaces within digits
        assertFalse(PostalCode.isValidPostal(" 123456")); // leading whitespaces
        assertFalse(PostalCode.isValidPostal("123456 ")); // trailing whitespaces

        // valid postal codes
        assertTrue(PostalCode.isValidPostal("12345")); // exactly 5 numbers
        assertTrue(PostalCode.isValidPostal("123456780")); // exactly 10 numbers
        assertTrue(PostalCode.isValidPostal("123456"));
    }
}
