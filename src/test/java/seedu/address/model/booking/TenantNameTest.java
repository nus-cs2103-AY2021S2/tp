package seedu.address.model.booking;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TenantNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TenantName(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new TenantName(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> TenantName.isValidName(null));

        // invalid name
        assertFalse(TenantName.isValidName("")); // empty string
        assertFalse(TenantName.isValidName(" ")); // spaces only
        assertFalse(TenantName.isValidName("^")); // only non-alphanumeric characters
        assertFalse(TenantName.isValidName("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(TenantName.isValidName("peter jack")); // alphabets only
        assertTrue(TenantName.isValidName("12345")); // numbers only
        assertTrue(TenantName.isValidName("peter the 2nd")); // alphanumeric characters
        assertTrue(TenantName.isValidName("Capital Tan")); // with capital letters
        assertTrue(TenantName.isValidName("David Roger Jackson Ray Jr 2nd")); // long names
    }
}
