package seedu.address.model.date;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DetailsTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Details(null));
    }

    @Test
    public void constructor_invalidDetails_throwsIllegalArgumentException() {
        String invalidDetails = "";
        assertThrows(IllegalArgumentException.class, () -> new Details(invalidDetails));
    }

    @Test
    public void isValidDetails() {

        //null details
        assertThrows(NullPointerException.class, () -> Details.isValidDetails(null));

        // invalid details
        assertFalse(Details.isValidDetails("")); // empty string
        assertFalse(Details.isValidDetails(" ")); // spaces only
        assertFalse(Details.isValidDetails("2021-03-27")); // valid input for date but missing time
        assertFalse(Details.isValidDetails("1300")); // valid input for time but missing date
        assertFalse(Details.isValidDetails("2021-03-27 13:00")); // valid input for date but invalid time format
        assertFalse(Details.isValidDetails("2021-03-27 2500")); // valid input for date but invalid time value
        assertFalse(Details.isValidDetails("27-03-2021 1300")); // valid input for time but invalid date format
        assertFalse(Details.isValidDetails("27-03-2021 13:00")); // invalid date and time format

        // valid details
        assertTrue(Details.isValidDetails("2021-03-27 1300")); // valid date and time format
        assertTrue(Details.isValidDetails("2021-03-27 0000")); // extreme time value
        assertTrue(Details.isValidDetails("2021-03-27 2359")); // extreme time value
    }
}
