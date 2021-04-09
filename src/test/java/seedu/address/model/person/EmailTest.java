package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class EmailTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Email(null));
    }

    @Test
    public void constructor_invalidEmail_throwsIllegalArgumentException() {
        String invalidEmail = "";
        assertThrows(IllegalArgumentException.class, () -> new Email(invalidEmail));
    }

    @Test
    public void isValidEmail() {
        // null email
        assertThrows(NullPointerException.class, () -> Email.isValidEmail(null));

        // blank email
        // empty string
        assertFalse(Email.isValidEmail(""));
        // spaces only
        assertFalse(Email.isValidEmail(" "));

        // missing parts
        // missing local part
        assertFalse(Email.isValidEmail("@example.com"));
        // missing '@' symbol
        assertFalse(Email.isValidEmail("peterjackexample.com"));
        // missing domain name
        assertFalse(Email.isValidEmail("peterjack@"));

        // invalid parts
        // invalid domain name
        assertFalse(Email.isValidEmail("peterjack@-"));
        // underscore in domain name
        assertFalse(Email.isValidEmail("peterjack@exam_ple.com"));
        // spaces in local part
        assertFalse(Email.isValidEmail("peter jack@example.com"));
        // spaces in domain name
        assertFalse(Email.isValidEmail("peterjack@exam ple.com"));
        // leading space
        assertFalse(Email.isValidEmail(" peterjack@example.com"));
        // trailing space
        assertFalse(Email.isValidEmail("peterjack@example.com "));
        // double '@' symbol
        assertFalse(Email.isValidEmail("peterjack@@example.com"));
        // '@' symbol in local part
        assertFalse(Email.isValidEmail("peter@jack@example.com"));
        // '@' symbol in domain name
        assertFalse(Email.isValidEmail("peterjack@example@com"));
        // domain name starts with a period
        assertFalse(Email.isValidEmail("peterjack@.example.com"));
        // domain name ends with a period
        assertFalse(Email.isValidEmail("peterjack@example.com."));
        // domain name starts with a hyphen
        assertFalse(Email.isValidEmail("peterjack@-example.com"));
        // domain name ends with a hyphen
        assertFalse(Email.isValidEmail("peterjack@example.com-"));
        // space in label
        assertFalse(Email.isValidEmail("example@abc def ghi"));
        // trailing period
        assertFalse(Email.isValidEmail("hello@example."));
        // invalid label
        assertFalse(Email.isValidEmail("hello@.example"));
        // no @
        assertFalse(Email.isValidEmail("hello."));
        // 64 character label
        assertFalse(Email.isValidEmail("hello@" + "a".repeat(64)));

        // valid email
        // normal email
        assertTrue(Email.isValidEmail("PeterJack_1190@example.com"));
        // minimal
        assertTrue(Email.isValidEmail("a@bc"));
        // alphabets only
        assertTrue(Email.isValidEmail("test@localhost"));
        // special characters local part
        assertTrue(Email.isValidEmail("!#$%&'*+/=?`{|}~^.-@example.org"));
        // numeric local part and domain name
        assertTrue(Email.isValidEmail("123@145"));
        // mixture of alphanumeric and special characters
        assertTrue(Email.isValidEmail("a1+be!@example1.com"));
        // long domain name
        assertTrue(Email.isValidEmail("peter_jack@very-very-very-long-example.com"));
        // long local part
        assertTrue(Email.isValidEmail("if.you.dream.it_you.can.do.it@example.com"));
        // 63 character label
        assertTrue(Email.isValidEmail("hello@" + "a".repeat(63)));
        assertTrue(Email.isValidEmail("hello@example.a//d"));
    }
}
