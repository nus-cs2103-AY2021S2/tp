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
        assertFalse(ExpiryDate.isValidExpiryDate("@example.com")); // missing local part
        assertFalse(ExpiryDate.isValidExpiryDate("peterjackexample.com")); // missing '@' symbol
        assertFalse(ExpiryDate.isValidExpiryDate("peterjack@")); // missing domain name

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
        assertTrue(ExpiryDate.isValidExpiryDate("PeterJack_1190@example.com"));
        assertTrue(ExpiryDate.isValidExpiryDate("a@bc")); // minimal
        assertTrue(ExpiryDate.isValidExpiryDate("test@localhost")); // alphabets only
        assertTrue(ExpiryDate.isValidExpiryDate("!#$%&'*+/=?`{|}~^.-@example.org")); // special characters local part
        assertTrue(ExpiryDate.isValidExpiryDate("123@145")); // numeric local part and domain name
        assertTrue(ExpiryDate.isValidExpiryDate("a1+b!@e.com")); // mixture of alphanumeric and special characters
        assertTrue(ExpiryDate.isValidExpiryDate("peter_jack@very-very-very-long-example.com")); // long domain name
        assertTrue(ExpiryDate.isValidExpiryDate("if.you.dream.it_you.can.do.it@example.com")); // long local part
    }
}
