package seedu.address.model.garment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DressCodeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DressCode(null));
    }

    @Test
    public void constructor_invalidDressCode_throwsIllegalArgumentException() {
        String invalidDressCode = "";
        assertThrows(IllegalArgumentException.class, () -> new DressCode(invalidDressCode));
    }

    @Test
    public void isValidDressCode() {
        // null address
        assertThrows(NullPointerException.class, () -> DressCode.isValidDressCode(null));

        // invalid addresses
        assertFalse(DressCode.isValidDressCode("")); // empty string
        assertFalse(DressCode.isValidDressCode(" ")); // spaces only

        // valid addresses
        assertTrue(DressCode.isValidDressCode("FORMAL"));
        assertTrue(DressCode.isValidDressCode("CASUAL"));
        assertTrue(DressCode.isValidDressCode("ACTIVE")); // long address
    }
}
