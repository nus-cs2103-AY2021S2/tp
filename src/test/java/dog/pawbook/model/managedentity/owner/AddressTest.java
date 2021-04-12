package dog.pawbook.model.managedentity.owner;

import static dog.pawbook.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class AddressTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Address(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidAddress = "";
        assertThrows(IllegalArgumentException.class, () -> new Address(invalidAddress));
    }

    @Test
    public void isValidAddress() {
        // null address
        assertThrows(NullPointerException.class, () -> Address.isValidAddress(null));

        // invalid addresses
        assertFalse(Address.isValidAddress("")); // empty string
        assertFalse(Address.isValidAddress(" ")); // spaces only

        // valid addresses
        assertTrue(Address.isValidAddress("Blk 456, Den Road, #01-355"));
        assertTrue(Address.isValidAddress("-")); // one character
        assertTrue(Address.isValidAddress("Leng Inc; 1234 Market St; San Francisco CA 2349879; USA")); // long address
    }

    @Test
    public void equals() {
        Address address1 = new Address("123, Clementi Ave 6, #11-02");
        Address address2 = new Address("74 Bukit Timah Road");

        // same object -> returns true
        assertTrue(address1.equals(address1));

        // same values -> returns true
        Address address1Copy = new Address("123, Clementi Ave 6, #11-02");
        assertTrue(address1.equals(address1Copy));

        // different type -> returns false
        assertFalse(address1.equals(1));

        // null -> return false
        assertFalse(address1.equals(null));

        // different Address -> returns false
        assertFalse(address1.equals(address2));
    }
}
