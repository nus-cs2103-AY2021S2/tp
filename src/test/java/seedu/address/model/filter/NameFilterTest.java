package seedu.address.model.filter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.tutor.Name;

public class NameFilterTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new NameFilter(null));
    }

    @Test
    public void constructor_validNameFilter_success() {
        // Note that full testing of valid filters is done in isValidNameFilter test
        // EP 1: Valid Lowercase Filter
        assertEquals("amz", new NameFilter("amz").nameFilter);

        // EP 2: Valid Uppercase Filter
        assertEquals("amz", new NameFilter("AMZ").nameFilter);

        // EP 3: Valid Number Filter
        assertEquals("059", new NameFilter("059").nameFilter);

        // EP 4: Valid Alphanumeric Filter
        assertEquals("a m z a m z 0 5 9", new NameFilter("a m z A M Z 0 5 9").nameFilter);
    }

    @Test
    public void constructor_invalidNameFilter_throwsIllegalArgumentException() {
        // Note that full testing of invalid filters is done in isValidNameFilter test
        String invalidNameFilter = "";
        assertThrows(IllegalArgumentException.class, () -> new NameFilter(invalidNameFilter));
    }

    @Test
    public void isValidNameFilter() {
        // EP 1: Lowercase Alphabet
        assertTrue(NameFilter.isValidNameFilter("abcdefghijklmnopqrstuvwxyz"));

        // EP 2: Uppercase Alphabet
        assertTrue(NameFilter.isValidNameFilter("ABCDEFGHIJKLMNOPQRSTUVWXYZ"));

        // EP 3: Numbers
        assertTrue(NameFilter.isValidNameFilter("0123456789"));

        // EP 4: Mixed Alphanumeric
        assertTrue(NameFilter.isValidNameFilter("amzAMZ059"));

        // EP 5: Mixed Alphanumeric with Spaces
        assertTrue(NameFilter.isValidNameFilter("a m z A M Z 0 5 9"));

        // EP 6: Empty
        assertFalse(NameFilter.isValidNameFilter(""));

        // EP 7: Space
        assertFalse(NameFilter.isValidNameFilter(" "));

        // EP 8: Symbols
        assertFalse(NameFilter.isValidNameFilter("("));
        assertFalse(NameFilter.isValidNameFilter(")"));
        assertFalse(NameFilter.isValidNameFilter("!"));
        assertFalse(NameFilter.isValidNameFilter("@"));
        assertFalse(NameFilter.isValidNameFilter("%"));
        assertFalse(NameFilter.isValidNameFilter("-"));
        assertFalse(NameFilter.isValidNameFilter("+"));
        assertFalse(NameFilter.isValidNameFilter("-1"));
        assertFalse(NameFilter.isValidNameFilter("a m z A M Z 0 5 9 !"));

        // EP 9: null
        assertThrows(NullPointerException.class, () -> NameFilter.isValidNameFilter(null));
    }

    @Test
    public void test() {
        NameFilter nameFilter = new NameFilter("amz");

        // EP 1: Exact match
        assertTrue(nameFilter.test(new Name("amz")));

        // EP 2: Different Case
        assertTrue(nameFilter.test(new Name("AMZ")));

        // EP 3: Partial Match
        assertTrue(nameFilter.test(new Name("amz059")));
        assertTrue(nameFilter.test(new Name("059amz")));
        assertTrue(nameFilter.test(new Name("amz AMZ 059")));

        // EP 4: No match
        assertFalse(nameFilter.test(new Name("am")));
        assertFalse(nameFilter.test(new Name("mz")));
        assertFalse(nameFilter.test(new Name("bny")));

        // EP 5: null
        assertFalse(nameFilter.test(null));
    }
}
