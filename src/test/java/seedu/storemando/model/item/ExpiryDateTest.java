package seedu.storemando.model.item;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.storemando.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ExpiryDateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ExpiryDate(null));
    }

    @Test
    public void constructor_invalidExpiryDate_throwsIllegalArgumentException() {
        String invalidExpiryDate = "";
        assertThrows(IllegalArgumentException.class, () -> new ExpiryDate(invalidExpiryDate));
    }

    @Test
    public void isValidExpiryDate() {
        // null expirydate
        assertThrows(NullPointerException.class, () -> ExpiryDate.isValidExpiryDate(null));

        // blank expirydate
        assertFalse(ExpiryDate.isValidExpiryDate("")); // empty string
        assertFalse(ExpiryDate.isValidExpiryDate(" ")); // spaces only

        // missing parts
        assertFalse(ExpiryDate.isValidExpiryDate("2020-10")); // missing DD
        assertFalse(ExpiryDate.isValidExpiryDate("20201010")); // missing '-' symbol
        assertFalse(ExpiryDate.isValidExpiryDate("10-10")); // missing YYYY

        // invalid parts
        assertFalse(ExpiryDate.isValidExpiryDate("peterjack@-")); // invalid domain name
        assertFalse(ExpiryDate.isValidExpiryDate("peterjack@exam_ple.com")); // underscore in domain name
        assertFalse(ExpiryDate.isValidExpiryDate("peter jack@example.com")); // spaces in local part
        assertFalse(ExpiryDate.isValidExpiryDate("peterjack@exam ple.com")); // spaces in domain name
        assertFalse(ExpiryDate.isValidExpiryDate(" peterjack@example.com")); // leading space
        assertFalse(ExpiryDate.isValidExpiryDate("peterjack@example.com ")); // trailing space
        assertFalse(ExpiryDate.isValidExpiryDate("peterjack@@example.com")); // double '@' symbol
        assertFalse(ExpiryDate.isValidExpiryDate("peter@jack@example.com")); // '@' symbol in local part
        assertFalse(ExpiryDate.isValidExpiryDate("peterjack@example@com")); // '@' symbol in domain name
        assertFalse(ExpiryDate.isValidExpiryDate("peterjack@.example.com")); // domain name starts with a period
        assertFalse(ExpiryDate.isValidExpiryDate("peterjack@example.com.")); // domain name ends with a period
        assertFalse(ExpiryDate.isValidExpiryDate("peterjack@-example.com")); // domain name starts with a hyphen
        assertFalse(ExpiryDate.isValidExpiryDate("peterjack@example.com-")); // domain name ends with a hyphen

        // valid expirydate
        assertTrue(ExpiryDate.isValidExpiryDate("2020-10-10"));
        assertTrue(ExpiryDate.isValidExpiryDate("2020-01-01"));
        assertTrue(ExpiryDate.isValidExpiryDate("0001-10-10"));

    }
}
