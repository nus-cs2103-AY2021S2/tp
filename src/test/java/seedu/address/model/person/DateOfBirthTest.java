package seedu.address.model.person;

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
    public void isValidDateOfBirth() {
        // null date of birth
        assertThrows(NullPointerException.class, () -> DateOfBirth.isValidDateOfBirth(null));

        // invalid dates of birth
        assertFalse(DateOfBirth.isValidDateOfBirth("")); // empty string
        assertFalse(DateOfBirth.isValidDateOfBirth(" ")); // spaces only
        assertFalse(DateOfBirth.isValidDateOfBirth("9111")); // wrong length
        assertFalse(DateOfBirth.isValidDateOfBirth("20202020")); // invalid date
        assertFalse(DateOfBirth.isValidDateOfBirth("31042000")); // non-existent dates
        assertFalse(DateOfBirth.isValidDateOfBirth("29022001")); // leap year test
        assertFalse(DateOfBirth.isValidDateOfBirth("31102040")); // future date not allowed

        // valid dates of birth
        assertTrue(DateOfBirth.isValidDateOfBirth("13102000")); // valid dates
        assertTrue(DateOfBirth.isValidDateOfBirth("29022000"));
        assertTrue(DateOfBirth.isValidDateOfBirth("13101993"));
    }

}
