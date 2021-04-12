package seedu.address.model.residence;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ResidenceName(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new ResidenceName(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> ResidenceName.isValidResidenceName(null));

        // invalid name
        assertFalse(ResidenceName.isValidResidenceName("")); // empty string
        assertFalse(ResidenceName.isValidResidenceName(" ")); // spaces only
        assertFalse(ResidenceName.isValidResidenceName("^")); // only non-alphanumeric characters
        assertFalse(ResidenceName.isValidResidenceName("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(ResidenceName.isValidResidenceName("Hudson Village")); // alphabets only
        assertTrue(ResidenceName.isValidResidenceName("12345")); // numbers only
        assertTrue(ResidenceName.isValidResidenceName("North Tower 2")); // alphanumeric characters
        assertTrue(ResidenceName.isValidResidenceName("Capital Heights")); // with capital letters
        assertTrue(ResidenceName.isValidResidenceName("Pinnacle Duxton Cantonment Rd HDB")); // long names
    }
}
