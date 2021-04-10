package seedu.address.model.property.client;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ContactTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Contact(null));
    }

    @Test
    public void constructor_invalidContact_throwsIllegalArgumentException() {
        String invalidContact = "";
        assertThrows(IllegalArgumentException.class, () -> new Contact(invalidContact));
    }

    @Test
    public void isValidContact() {
        // null contact number
        assertThrows(NullPointerException.class, () -> Contact.isValidContact(null));

        // invalid contact numbers
        assertFalse(Contact.isValidContact("")); // empty string
        assertFalse(Contact.isValidContact(" ")); // spaces only
        assertFalse(Contact.isValidContact("912345")); // less than 7 numbers
        assertFalse(Contact.isValidContact("9123456789012345")); // more than 15 numbers
        assertFalse(Contact.isValidContact("contact")); // non-numeric
        assertFalse(Contact.isValidContact("9123p456")); // alphabets within digits
        assertFalse(Contact.isValidContact("9123 4567")); // spaces within digits
        assertFalse(Contact.isValidContact(" 91234567")); // leading whitespace
        assertFalse(Contact.isValidContact("91234567 ")); // trailing whitespace

        // valid contact numbers
        assertTrue(Contact.isValidContact("9111222")); // exactly 7 numbers
        assertTrue(Contact.isValidContact("912345678901234")); // exactly 15 numbers
        assertTrue(Contact.isValidContact("91234567"));
    }
}
