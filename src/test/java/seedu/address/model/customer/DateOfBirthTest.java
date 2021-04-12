package seedu.address.model.customer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class DateOfBirthTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DateOfBirth(null));
    }

    @Test
    public void constructor_invalidDateOfBirth_throwsIllegalArgumentException() {
        String invalidDateOfBirth = "";
        assertThrows(IllegalArgumentException.class, () -> new DateOfBirth(invalidDateOfBirth));
    }

    @Test
    void isValidDateOfBirth() {
        // null DateOfBirth
        assertThrows(NullPointerException.class, () -> DateOfBirth.isValidDateOfBirth(null));

        // blank DateOfBirth
        assertFalse(DateOfBirth.isValidDateOfBirth("")); // empty string
        assertFalse(DateOfBirth.isValidDateOfBirth(" ")); // spaces only

        // missing parts
        assertFalse(DateOfBirth.isValidDateOfBirth("2030 01 ")); // missing date
        assertFalse(DateOfBirth.isValidDateOfBirth("19980505")); // missing spaces in between yyyy mm dd

        // invalid DateOfBirth
        assertFalse(DateOfBirth.isValidDateOfBirth("2050 10 10")); // invalid year, cannot be born in the future
        assertFalse(DateOfBirth.isValidDateOfBirth("2030 13 01")); // invalid month
        assertFalse(DateOfBirth.isValidDateOfBirth("2030 10 32")); // invalid date

        // valid DateOfBirth
        assertTrue(DateOfBirth.isValidDateOfBirth("1965 01 10"));
        assertTrue(DateOfBirth.isValidDateOfBirth("1998 05 05"));
    }
}
