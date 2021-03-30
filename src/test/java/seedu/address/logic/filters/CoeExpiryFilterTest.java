package seedu.address.logic.filters;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

public class CoeExpiryFilterTest { //TODO: Complete this test collection
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CoeExpiryFilter(null));
    }

    @Test
    public void constructor_invalidCoeExpiryFilter_throwsIllegalArgumentException() {
        String emptyString = "";
        assertThrows(IllegalArgumentException.class, () -> new CoeExpiryFilter(emptyString));
        String invalidStringArgument = "today";
        assertThrows(IllegalArgumentException.class, () -> new CoeExpiryFilter(invalidStringArgument));
        String negativeNumber = "-1234";
        assertThrows(IllegalArgumentException.class, () -> new CoeExpiryFilter(negativeNumber));
    }

    @Test
    public void isValidFilter() {
        // null filter
        assertThrows(NullPointerException.class, () -> CoeExpiryFilter.isValidFilter(null));

        // invalid filter
        assertFalse(CoeExpiryFilter.isValidFilter("")); // empty string
        assertFalse(CoeExpiryFilter.isValidFilter(" ")); // spaces only
        assertFalse(CoeExpiryFilter.isValidFilter("today")); // wrong string
        assertFalse(CoeExpiryFilter.isValidFilter("-1")); // negative number

        // valid filter
        assertTrue(CoeExpiryFilter.isValidFilter("exp")); // special keyword
        assertTrue(CoeExpiryFilter.isValidFilter("0")); // zero is valid
        assertTrue(CoeExpiryFilter.isValidFilter("3")); // long address
    }
    //TODO: Add appropriate test modified from AddCommandTest.jaa
}
