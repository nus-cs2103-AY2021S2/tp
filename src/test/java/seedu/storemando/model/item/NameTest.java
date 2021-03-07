package seedu.storemando.model.item;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.storemando.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ItemName(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new ItemName(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> ItemName.isValidName(null));

        // invalid name
        assertFalse(ItemName.isValidName("")); // empty string
        assertFalse(ItemName.isValidName(" ")); // spaces only
        assertFalse(ItemName.isValidName("^")); // only non-alphanumeric characters
        assertFalse(ItemName.isValidName("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(ItemName.isValidName("peter jack")); // alphabets only
        assertTrue(ItemName.isValidName("12345")); // numbers only
        assertTrue(ItemName.isValidName("peter the 2nd")); // alphanumeric characters
        assertTrue(ItemName.isValidName("Capital Tan")); // with capital letters
        assertTrue(ItemName.isValidName("David Roger Jackson Ray Jr 2nd")); // long names
    }
}
