package seedu.address.model.filter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.tutor.Address;

public class AddressFilterTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddressFilter(null));
    }

    @Test
    public void constructor_validAddressFilter_success() {
        // Note that full testing of valid filters is done in isValidAddressFilter test
        // EP 1: Valid Lowercase Filter
        assertEquals("amz", new AddressFilter("amz").addressFilter);

        // EP 2: Valid Uppercase Filter
        assertEquals("amz", new AddressFilter("AMZ").addressFilter);

        // EP 3: Valid Number Filter
        assertEquals("059", new AddressFilter("059").addressFilter);

        // EP 4: Valid Alphanumeric Filter
        assertEquals("a m z a m z 0 5 9", new AddressFilter("a m z A M Z 0 5 9").addressFilter);

        // EP 5: Valid Mixed Filter
        assertEquals("a m z a m z 0 5 9 ! % )", new AddressFilter("a m z A M Z 0 5 9 ! % )").addressFilter);
    }

    @Test
    public void constructor_invalidAddressFilter_throwsIllegalArgumentException() {
        // Note that full testing of invalid filters is done in isValidAddressFilter test
        String invalidAddressFilter = "";
        assertThrows(IllegalArgumentException.class, () -> new AddressFilter(invalidAddressFilter));
    }

    @Test
    public void isValidAddressFilter() {
        // EP 1: Lowercase Alphabet
        assertTrue(AddressFilter.isValidAddressFilter("abcdefghijklmnopqrstuvwxyz"));

        // EP 2: Uppercase Alphabet
        assertTrue(AddressFilter.isValidAddressFilter("ABCDEFGHIJKLMNOPQRSTUVWXYZ"));

        // EP 3: Numbers
        assertTrue(AddressFilter.isValidAddressFilter("0123456789"));

        // EP 4: Mixed Alphanumeric
        assertTrue(AddressFilter.isValidAddressFilter("amzAMZ059"));

        // EP 5: Mixed Alphanumeric with Spaces
        assertTrue(AddressFilter.isValidAddressFilter("a m z A M Z 0 5 9"));

        // EP 6: Symbols
        assertTrue(AddressFilter.isValidAddressFilter("!@#$%^&*()-+,./;'<>?:\"`~[]{}"));

        // EP 7: Mixed
        assertTrue(AddressFilter.isValidAddressFilter("a m z A M Z 0 5 9 ! % )"));

        // EP 8: Empty
        assertFalse(AddressFilter.isValidAddressFilter(""));

        // EP 9: Space
        assertFalse(AddressFilter.isValidAddressFilter(" "));

        // EP 10: null
        assertThrows(NullPointerException.class, () -> AddressFilter.isValidAddressFilter(null));
    }

    @Test
    public void test() {
        AddressFilter addressFilter = new AddressFilter("amz");

        // EP 1: Exact match
        assertTrue(addressFilter.test(new Address("amz")));

        // EP 2: Different Case
        assertTrue(addressFilter.test(new Address("AMZ")));

        // EP 3: Partial Match
        assertTrue(addressFilter.test(new Address("amz059")));
        assertTrue(addressFilter.test(new Address("059amz")));
        assertTrue(addressFilter.test(new Address("amz AMZ 059")));
        assertTrue(addressFilter.test(new Address("amz AMZ 059 !%)")));

        // EP 4: No match
        assertFalse(addressFilter.test(new Address("am")));
        assertFalse(addressFilter.test(new Address("mz")));
        assertFalse(addressFilter.test(new Address("bny")));

        // EP 5: null
        assertFalse(addressFilter.test(null));
    }
}
