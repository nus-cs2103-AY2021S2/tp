package seedu.address.model.garment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class SizeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Size(null));
    }

    @Test
    public void constructor_invalidSize_throwsIllegalArgumentException() {
        String invalidSize = "";
        assertThrows(IllegalArgumentException.class, () -> new Size(invalidSize));
    }

    @Test
    public void isValidSize() {
        // invalid sizes
        assertFalse(Size.isValidSize("")); // empty string
        assertFalse(Size.isValidSize(" ")); // spaces only
        assertFalse(Size.isValidSize("size")); // non-numeric
        assertFalse(Size.isValidSize("9011p041")); // alphabets within digits
        assertFalse(Size.isValidSize("9 1")); // spaces within digits

        // valid sizes
        assertTrue(Size.isValidSize("23")); // exactly 3 numbers
        assertTrue(Size.isValidSize("11"));
    }
}
