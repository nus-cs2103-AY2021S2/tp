package seedu.address.model.name;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

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
        assertFalse(Name.isValidName("_")); // contains only non-alphanumeric characters
        assertFalse(Name.isValidName("mayfair*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(Name.isValidName("mayfair")); // alphabets only
        assertTrue(Name.isValidName("12345")); // numbers only
        assertTrue(Name.isValidName("mayfair 2")); // alphanumeric characters
        assertTrue(Name.isValidName("The Mayfair")); // with capital letters
        assertTrue(Name.isValidName("MAYFAIR")); // all capital letters
        assertTrue(Name.isValidName("The Best But Most Expensive Building In Singapore")); // long names
    }
}
