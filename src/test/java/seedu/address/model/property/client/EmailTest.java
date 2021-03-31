package seedu.address.model.property.client;

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
        assertFalse(Email.isValidEmail("")); // empty string
        assertFalse(Email.isValidEmail(" ")); // spaces only

        // missing some parts of the email
        assertFalse(Email.isValidEmail("@example.com")); // missing prefix part
        assertFalse(Email.isValidEmail("peterjackexample.com")); // missing '@' symbol
        assertFalse(Email.isValidEmail("peterjack@")); // missing domain part

        // invalid emails
        assertFalse(Email.isValidEmail("_peterjack@example.com")); // prefix starts with an underscore
        assertFalse(Email.isValidEmail("peterjack_@example.com")); // prefix ends with an underscore
        assertFalse(Email.isValidEmail("peter..jack@example.com")); // prefix has two consecutive periods
        assertFalse(Email.isValidEmail("peterjack@-")); // invalid domain name
        assertFalse(Email.isValidEmail("peterjack@exam_ple.com")); // underscore in domain name
        assertFalse(Email.isValidEmail("peter jack@example.com")); // spaces in prefix
        assertFalse(Email.isValidEmail("peterjack@exam ple.com")); // spaces in domain name
        assertFalse(Email.isValidEmail(" peterjack@example.com")); // leading space
        assertFalse(Email.isValidEmail("peterjack@example.com ")); // trailing space
        assertFalse(Email.isValidEmail("peterjack@@example.com")); // double '@' symbol
        assertFalse(Email.isValidEmail("peter@jack@example.com")); // '@' symbol in prefix
        assertFalse(Email.isValidEmail("peterjack@example@com")); // '@' symbol in domain name
        assertFalse(Email.isValidEmail("peterjack@.example.com")); // domain name starts with a period
        assertFalse(Email.isValidEmail("peterjack@example.com.")); // domain name ends with a period
        assertFalse(Email.isValidEmail("peterjack@-example.com")); // domain name starts with a hyphen
        assertFalse(Email.isValidEmail("peterjack@example.com-")); // domain name ends with a hyphen
        assertFalse(Email.isValidEmail("peterjack@example.c")); // less than two characters in top level domain

        // valid email
        assertTrue(Email.isValidEmail("PeterJack_1190@example.com")); // prefix with underscore
        assertTrue(Email.isValidEmail("PeterJack-1190@example.com")); // prefix with hyphen
        assertTrue(Email.isValidEmail("PeterJack.1190@example.com")); // prefix with period
        assertTrue(Email.isValidEmail("a@bc")); // minimal
        assertTrue(Email.isValidEmail("peterjack@example")); // alphabets only
        assertTrue(Email.isValidEmail("123@145")); // numeric only
        assertTrue(Email.isValidEmail("a1_bc-d2e.f3@examp1e.com")); // mixture of alphanumeric and special characters
        assertTrue(Email.isValidEmail("peterjack@example-com")); // hyphen only in domain name
        assertTrue(Email.isValidEmail("e1234567@u.nus.edu.sg")); // multiple domain parts in domain name
        assertTrue(Email.isValidEmail("if.you.dream.it_you.can.do.it@example.com")); // long prefix
        assertTrue(Email.isValidEmail("peter_jack@very-very-very-long-example.com")); // long domain name
    }
}
