package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class BirthdateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Birthdate((String) null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidBirthdate = "";
        assertThrows(IllegalArgumentException.class, () -> new Birthdate(invalidBirthdate));
    }

    @Test
    public void isValidGender() {
        // null address
        assertThrows(NullPointerException.class, () -> Birthdate.isValidBirthdate((String) null));

        // invalid addresses
        assertFalse(Birthdate.isValidBirthdate("")); // empty string
        assertFalse(Birthdate.isValidBirthdate("2020-02-31")); // date doesn't exist

        // valid addresses
        assertTrue(Birthdate.isValidBirthdate("2019-01-05"));
        assertTrue(Birthdate.isValidBirthdate("2000-01-01"));
        assertTrue(Birthdate.isValidBirthdate("1980-12-25"));
    }
}
