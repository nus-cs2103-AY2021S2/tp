package seedu.address.model.contact;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ContactEmailTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ContactEmail(null));
    }

    @Test
    public void constructor_invalidEmail_throwsIllegalArgumentException() {
        String invalidEmail = "";
        assertThrows(IllegalArgumentException.class, () -> new ContactEmail(invalidEmail));
    }

    @Test
    public void isValidEmail() {
        // null email
        assertThrows(NullPointerException.class, () -> ContactEmail.isValidEmail(null));

        // blank email
        assertFalse(ContactEmail.isValidEmail("")); // empty string
        assertFalse(ContactEmail.isValidEmail(" ")); // spaces only

        // missing parts
        assertFalse(ContactEmail.isValidEmail("@example.com")); // missing local part
        assertFalse(ContactEmail.isValidEmail("peterjackexample.com")); // missing '@' symbol
        assertFalse(ContactEmail.isValidEmail("peterjack@")); // missing domain name

        // invalid parts
        String email;

        email = "peterjack@-";
        assertFalse(ContactEmail.isValidEmail(email)); // invalid domain name
        email = "peter jack@example.com";
        assertFalse(ContactEmail.isValidEmail(email)); // spaces in local part
        email = "peterjack@exam ple.com";
        assertFalse(ContactEmail.isValidEmail(email)); // spaces in domain name
        email = " peterjack@example.com";
        assertFalse(ContactEmail.isValidEmail(email)); // leading space
        email = "peterjack@example.com ";
        assertFalse(ContactEmail.isValidEmail(email)); // trailing space
        email = "peterjack@@example.com";
        assertFalse(ContactEmail.isValidEmail(email)); // double '@' symbol
        email = "peter@jack@example.com";
        assertFalse(ContactEmail.isValidEmail(email)); // '@' symbol in local part
        email = "peterjack@example@com";
        assertFalse(ContactEmail.isValidEmail(email)); // '@' symbol in domain name
        email = "peterjack@.example.com";
        assertFalse(ContactEmail.isValidEmail(email)); // domain name starts with a period
        email = "peterjack@example.com.";
        assertFalse(ContactEmail.isValidEmail(email)); // domain name ends with a period
        email = "peterjack@-example.com";
        assertFalse(ContactEmail.isValidEmail(email)); // domain name starts with a hyphen
        email = "peterjack@example.com-";
        assertFalse(ContactEmail.isValidEmail(email)); // domain name ends with a hyphen
        email = "a!#$%&'*+/=?`{|}~^.-@example.org";
        assertFalse(ContactEmail.isValidEmail(email)); // trailing special characters
        email = "!#$%&'*+/=?`{|}~^.-@example.org";
        assertFalse(ContactEmail.isValidEmail(email)); // leading special characters

        // valid email
        email = "PeterJack_1190@example.com";
        assertTrue(ContactEmail.isValidEmail(email));
        email = "ad@bc";
        assertTrue(ContactEmail.isValidEmail(email)); // minimal
        email = "test@localhost";
        assertTrue(ContactEmail.isValidEmail(email)); // alphabets only
        email = "123@145";
        assertTrue(ContactEmail.isValidEmail(email)); // numeric local part and domain name
        email = "a1+be!g@example1.com";
        assertTrue(ContactEmail.isValidEmail(email)); // mixture of alphanumeric and special characters
        email = "peter_jack@very-very-very-long-example.com";
        assertTrue(ContactEmail.isValidEmail(email)); // long domain name
        email = "peter_jack@very-very-very-long-example.com";
        assertTrue(ContactEmail.isValidEmail(email)); // long local part
    }
}
