package seedu.address.model.user;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new seedu.address.model.user.Name(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new seedu.address.model.user.Name(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> seedu.address.model.user.Name.isValidName(null));

        // invalid name
        assertFalse(seedu.address.model.user.Name.isValidName("")); // empty string
        assertFalse(seedu.address.model.user.Name.isValidName(" ")); // spaces only
        assertFalse(seedu.address.model.user.Name.isValidName("^")); // only non-alphanumeric characters
        assertFalse(seedu.address.model.user.Name.isValidName("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(seedu.address.model.user.Name.isValidName("peter jack")); // alphabets only
        assertTrue(seedu.address.model.user.Name.isValidName("12345")); // numbers only
        assertTrue(seedu.address.model.user.Name.isValidName("peter the 2nd")); // alphanumeric characters
        assertTrue(seedu.address.model.user.Name.isValidName("Capital Tan")); // with capital letters
        assertTrue(Name.isValidName("David Roger Jackson Ray Jr 2nd")); // long names
    }
}
