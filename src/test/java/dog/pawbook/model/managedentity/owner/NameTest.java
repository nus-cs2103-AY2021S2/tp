package dog.pawbook.model.managedentity.owner;

import static dog.pawbook.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import dog.pawbook.model.managedentity.Name;

public class NameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Name(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new Name(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> Name.isValidName(null));

        // invalid name
        assertFalse(Name.isValidName("")); // empty string
        assertFalse(Name.isValidName(" ")); // spaces only
        assertFalse(Name.isValidName("^")); // only non-alphanumeric characters
        assertFalse(Name.isValidName("peter*")); // contains disallowed non-alphanumeric characters
        assertFalse(Name.isValidName("Anne,Marie")); // with comma but no space after comma
        assertFalse(Name.isValidName("Anne.Marie")); // with period but no space after period
        assertFalse(Name.isValidName("Anne Marie,")); // with comma at the back

        // valid name
        assertTrue(Name.isValidName("peter jack")); // alphabets only
        assertTrue(Name.isValidName("12345")); // numbers only
        assertTrue(Name.isValidName("peter the 2nd")); // alphanumeric characters
        assertTrue(Name.isValidName("Capital Tan")); // with capital letters
        assertTrue(Name.isValidName("David Roger Jackson Ray Jr 2nd")); // long names
        assertTrue(Name.isValidName("D'Amato")); // with apostrophe
        assertTrue(Name.isValidName("D' Amato")); // with apostrophe and space right after
        assertTrue(Name.isValidName("D. Amato")); // with period
        assertTrue(Name.isValidName("Robert Downey Jr.")); // with period at the end
        assertTrue(Name.isValidName("Anne-Marie")); // with hyphen
        assertTrue(Name.isValidName("Anne, Marie")); // with comma
    }
}
