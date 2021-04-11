package seedu.address.logic.filters;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NameFilterTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new NameFilter(null));
    }

    @Test
    public void constructor_invalidCoeExpiryFilter_throwsIllegalArgumentException() {
        String emptyString = "";
        assertThrows(IllegalArgumentException.class, () -> new CoeExpiryFilter(emptyString));
        String withSpecialCharacter = "peter^";
        assertThrows(IllegalArgumentException.class, () -> new CoeExpiryFilter(withSpecialCharacter));
        String spacesOnly = "   ";
        assertThrows(IllegalArgumentException.class, () -> new CoeExpiryFilter(spacesOnly));
    }

    @Test
    public void isValidFilter() {
        // null filter
        assertThrows(NullPointerException.class, () -> NameFilter.isValidFilter(null));

        // invalid filter
        assertFalse(NameFilter.isValidFilter("")); // empty string
        assertFalse(NameFilter.isValidFilter(" ")); // spaces only
        assertFalse(NameFilter.isValidFilter("^")); // only non-alphanumeric characters
        assertFalse(NameFilter.isValidFilter("peter*")); // contains non-alphanumeric characters

        // valid filter
        assertTrue(NameFilter.isValidFilter("peter jack")); // alphabets only
        assertTrue(NameFilter.isValidFilter("12345")); // numbers only
        assertTrue(NameFilter.isValidFilter("peter the 2nd")); // alphanumeric characters
        assertTrue(NameFilter.isValidFilter("Capital Tan")); // with capital letters
        assertTrue(NameFilter.isValidFilter("David Roger Jackson Ray Jr 2nd")); // long searches
    }

    @Test
    public void test_nullCustomer_throwsNullPointerException() {
        NameFilter filter = new NameFilter("peter");
        assertThrows(NullPointerException.class, () -> filter.test(null));
    }
    //TODO: Add additional tests for true/false criteria of filter.test
}
