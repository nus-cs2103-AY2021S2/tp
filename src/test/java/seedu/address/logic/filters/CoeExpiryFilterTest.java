package seedu.address.logic.filters;

import org.junit.jupiter.api.Test;

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
}
