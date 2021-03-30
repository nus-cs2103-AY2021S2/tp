package seedu.address.model.contact;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class EmailTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new seedu.address.model.contact.Email(null));
    }

    @Test
    public void constructor_invalidEmail_throwsIllegalArgumentException() {
        String invalidEmail = "";
        assertThrows(IllegalArgumentException.class, () -> new seedu.address.model.contact.Email(invalidEmail));
    }

    @Test
    public void isValidEmail() {
        // null email
        assertThrows(NullPointerException.class, () -> seedu.address.model.contact.Email.isValidEmail(null));

        // blank email
        assertFalse(seedu.address.model.contact.Email.isValidEmail("")); // empty string
        assertFalse(seedu.address.model.contact.Email.isValidEmail(" ")); // spaces only

        // missing parts
        assertFalse(seedu.address.model.contact.Email.isValidEmail("@example.com")); // missing local part
        assertFalse(seedu.address.model.contact.Email.isValidEmail("peterjackexample.com")); // missing '@' symbol
        assertFalse(seedu.address.model.contact.Email.isValidEmail("peterjack@")); // missing domain name

        // invalid parts
        assertFalse(seedu.address.model.contact.Email.isValidEmail("peterjack@-")); // invalid domain name
        String email = "peterjack@exam_ple.com";
        assertFalse(seedu.address.model.contact.Email.isValidEmail(email)); // underscore in domain name
        assertFalse(seedu.address.model.contact.Email.isValidEmail("peter jack@example.com")); // spaces in local part
        assertFalse(seedu.address.model.contact.Email.isValidEmail("peterjack@exam ple.com")); // spaces in domain name
        assertFalse(seedu.address.model.contact.Email.isValidEmail(" peterjack@example.com")); // leading space
        assertFalse(seedu.address.model.contact.Email.isValidEmail("peterjack@example.com ")); // trailing space
        assertFalse(seedu.address.model.contact.Email.isValidEmail("peterjack@@example.com")); // double '@' symbol
        email = "peter@jack@example.com";
        assertFalse(seedu.address.model.contact.Email.isValidEmail(email)); // '@' symbol in local part
        email = "peterjack@example@com";
        assertFalse(seedu.address.model.contact.Email.isValidEmail(email)); // '@' symbol in domain name
        email = "peterjack@.example.com";
        assertFalse(seedu.address.model.contact.Email.isValidEmail(email)); // domain name starts with a period
        email = "peterjack@example.com.";
        assertFalse(seedu.address.model.contact.Email.isValidEmail(email)); // domain name ends with a period
        email = "peterjack@-example.com";
        assertFalse(seedu.address.model.contact.Email.isValidEmail(email)); // domain name starts with a hyphen
        email = "peterjack@example.com-";
        assertFalse(seedu.address.model.contact.Email.isValidEmail(email)); // domain name ends with a hyphen

        // valid email
        email = "PeterJack_1190@example.com";
        assertTrue(seedu.address.model.contact.Email.isValidEmail(email));
        assertTrue(seedu.address.model.contact.Email.isValidEmail("a@bc")); // minimal
        email = "test@localhost";
        assertTrue(seedu.address.model.contact.Email.isValidEmail(email)); // alphabets only
        email = "!#$%&'*+/=?`{|}~^.-@example.org";
        assertTrue(seedu.address.model.contact.Email.isValidEmail(email)); // special characters local part
        assertTrue(seedu.address.model.contact.Email.isValidEmail("123@145")); // numeric local part and domain name
        email = "a1+be!@example1.com";
        boolean text = seedu.address.model.contact.Email.isValidEmail(email);
        assertTrue(text); // mixture of alphanumeric and special characters
        email = "peter_jack@very-very-very-long-example.com";
        assertTrue(seedu.address.model.contact.Email.isValidEmail(email)); // long domain name
        email = "peter_jack@very-very-very-long-example.com";
        assertTrue(Email.isValidEmail(email)); // long local part
    }
}
