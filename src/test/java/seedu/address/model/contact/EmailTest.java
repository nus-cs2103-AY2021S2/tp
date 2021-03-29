package seedu.address.model.contact;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;
import seedu.address.model.contact.Email;

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
        assertFalse(seedu.address.model.contact.Email.isValidEmail("peterjack@exam_ple.com")); // underscore in domain name
        assertFalse(seedu.address.model.contact.Email.isValidEmail("peter jack@example.com")); // spaces in local part
        assertFalse(seedu.address.model.contact.Email.isValidEmail("peterjack@exam ple.com")); // spaces in domain name
        assertFalse(seedu.address.model.contact.Email.isValidEmail(" peterjack@example.com")); // leading space
        assertFalse(seedu.address.model.contact.Email.isValidEmail("peterjack@example.com ")); // trailing space
        assertFalse(seedu.address.model.contact.Email.isValidEmail("peterjack@@example.com")); // double '@' symbol
        assertFalse(seedu.address.model.contact.Email.isValidEmail("peter@jack@example.com")); // '@' symbol in local part
        assertFalse(seedu.address.model.contact.Email.isValidEmail("peterjack@example@com")); // '@' symbol in domain name
        assertFalse(seedu.address.model.contact.Email.isValidEmail("peterjack@.example.com")); // domain name starts with a period
        assertFalse(seedu.address.model.contact.Email.isValidEmail("peterjack@example.com.")); // domain name ends with a period
        assertFalse(seedu.address.model.contact.Email.isValidEmail("peterjack@-example.com")); // domain name starts with a hyphen
        assertFalse(seedu.address.model.contact.Email.isValidEmail("peterjack@example.com-")); // domain name ends with a hyphen

        // valid email
        assertTrue(seedu.address.model.contact.Email.isValidEmail("PeterJack_1190@example.com"));
        assertTrue(seedu.address.model.contact.Email.isValidEmail("a@bc")); // minimal
        assertTrue(seedu.address.model.contact.Email.isValidEmail("test@localhost")); // alphabets only
        assertTrue(seedu.address.model.contact.Email.isValidEmail("!#$%&'*+/=?`{|}~^.-@example.org")); // special characters local part
        assertTrue(seedu.address.model.contact.Email.isValidEmail("123@145")); // numeric local part and domain name
        assertTrue(seedu.address.model.contact.Email.isValidEmail("a1+be!@example1.com")); // mixture of alphanumeric and special characters
        assertTrue(seedu.address.model.contact.Email.isValidEmail("peter_jack@very-very-very-long-example.com")); // long domain name
        assertTrue(Email.isValidEmail("if.you.dream.it_you.can.do.it@example.com")); // long local part
    }
}
