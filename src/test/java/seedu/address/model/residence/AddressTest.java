package seedu.address.model.residence;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AddressTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ResidenceAddress(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidAddress = "";
        assertThrows(IllegalArgumentException.class, () -> new ResidenceAddress(invalidAddress));
    }

    @Test
    public void isValidAddress() {
        // null address
        assertThrows(NullPointerException.class, () -> ResidenceAddress.isValidResidenceAddress(null));

        // invalid addresses
        assertFalse(ResidenceAddress.isValidResidenceAddress("")); // empty string
        assertFalse(ResidenceAddress.isValidResidenceAddress(" ")); // spaces only

        // valid addresses
        assertTrue(ResidenceAddress.isValidResidenceAddress("Blk 456, Den Road, #01-355"));
        assertTrue(ResidenceAddress.isValidResidenceAddress("-")); // one character
        assertTrue(ResidenceAddress.isValidResidenceAddress("Leng Inc; 1234 Market St; "
                + "San Francisco CA 2349879; USA")); // long address
    }
}
