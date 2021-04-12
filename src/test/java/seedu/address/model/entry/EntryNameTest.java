package seedu.address.model.entry;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Contains unit tests for {@code EntryName}.
 */
public class EntryNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EntryName(null));
    }

    @Test
    public void constructor_invalidEntryName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new EntryName(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> EntryName.isValidName(null));

        // invalid name
        assertFalse(EntryName.isValidName(""));
        assertFalse(EntryName.isValidName(" "));
        assertFalse(EntryName.isValidName("@"));
        assertFalse(EntryName.isValidName("cl@ass"));
        assertFalse(EntryName.isValidName("class        *"));

        // valid name
        assertTrue(EntryName.isValidName("consultation")); // alphabets only
        assertTrue(EntryName.isValidName("12345")); // numbers only
        assertTrue(EntryName.isValidName("consultation the 2nd")); // alphanumeric characters
        assertTrue(EntryName.isValidName("Consultation")); // with capital letters
        assertTrue(EntryName.isValidName("Important class consultation today lol")); // long names
    }
}
