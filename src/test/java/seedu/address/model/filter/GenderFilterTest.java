package seedu.address.model.filter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

// import seedu.address.model.tutor.Gender;

public class GenderFilterTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new GenderFilter(null));
    }

    @Test
    public void constructor_validGenderFilter_success() {
        // Note that full testing of valid filters is done in isValidGenderFilter test
        // EP 1: Valid Lowercase Filter
        assertEquals("amz", new GenderFilter("amz").genderFilter);

        // EP 2: Valid Uppercase Filter
        assertEquals("amz", new GenderFilter("AMZ").genderFilter);

        // EP 3: Valid Number Filter
        assertEquals("059", new GenderFilter("059").genderFilter);

        // EP 4: Valid Alphanumeric Filter
        assertEquals("a m z a m z 0 5 9", new GenderFilter("a m z A M Z 0 5 9").genderFilter);
    }

    @Test
    public void constructor_invalidGenderFilter_throwsIllegalArgumentException() {
        // Note that full testing of invalid filters is done in isValidGenderFilter test
        String invalidGenderFilter = "";
        assertThrows(IllegalArgumentException.class, () -> new GenderFilter(invalidGenderFilter));
    }

    @Test
    public void isValidGenderFilter() {
        // EP 1: Lowercase Alphabet
        assertTrue(GenderFilter.isValidGenderFilter("abcdefghijklmnopqrstuvwxyz"));

        // EP 2: Uppercase Alphabet
        assertTrue(GenderFilter.isValidGenderFilter("ABCDEFGHIJKLMNOPQRSTUVWXYZ"));

        // EP 3: Numbers
        assertTrue(GenderFilter.isValidGenderFilter("0123456789"));

        // EP 4: Mixed Alphanumeric
        assertTrue(GenderFilter.isValidGenderFilter("amzAMZ059"));

        // EP 5: Mixed Alphanumeric with Spaces
        assertTrue(GenderFilter.isValidGenderFilter("a m z A M Z 0 5 9"));

        // EP 6: Empty
        assertFalse(GenderFilter.isValidGenderFilter(""));

        // EP 7: Space
        assertFalse(GenderFilter.isValidGenderFilter(" "));

        // EP 8: Symbols
        assertFalse(GenderFilter.isValidGenderFilter("("));
        assertFalse(GenderFilter.isValidGenderFilter(")"));
        assertFalse(GenderFilter.isValidGenderFilter("!"));
        assertFalse(GenderFilter.isValidGenderFilter("@"));
        assertFalse(GenderFilter.isValidGenderFilter("%"));
        assertFalse(GenderFilter.isValidGenderFilter("-"));
        assertFalse(GenderFilter.isValidGenderFilter("+"));
        assertFalse(GenderFilter.isValidGenderFilter("-1"));
        assertFalse(GenderFilter.isValidGenderFilter("a m z A M Z 0 5 9 !"));

        // EP 9: null
        assertThrows(NullPointerException.class, () -> GenderFilter.isValidGenderFilter(null));
    }

    /*
    Untested due to integration issue

    @Test
    public void test() {
        GenderFilter genderFilter = new GenderFilter("amz");

        // EP 1: Exact match
        assertTrue(genderFilter.test(new Gender("amz")));

        // EP 2: Different Case
        assertTrue(genderFilter.test(new Gender("AMZ")));

        // EP 3: Partial Match
        assertTrue(genderFilter.test(new Gender("amz059")));
        assertTrue(genderFilter.test(new Gender("059amz")));
        assertTrue(genderFilter.test(new Gender("amz AMZ 059")));

        // EP 4: No match
        assertFalse(genderFilter.test(new Gender("am")));
        assertFalse(genderFilter.test(new Gender("mz")));
        assertFalse(genderFilter.test(new Gender("bny")));

        // EP 5: null
        assertFalse(genderFilter.test(null));
    }
    */
}
