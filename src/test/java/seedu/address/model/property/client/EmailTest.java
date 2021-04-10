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
        assertFalse(Email.isValidEmail("george4788example.com")); // missing '@' symbol
        assertFalse(Email.isValidEmail("george4788@")); // missing whole domain part
        assertFalse(Email.isValidEmail("george4788@example")); // missing top level domain

        // invalid emails
        assertFalse(Email.isValidEmail("george*4788@example.com")); // unrecognized characters in prefix
        assertFalse(Email.isValidEmail("george4788@exa*mple.com")); // unrecognized characters in domain
        assertFalse(Email.isValidEmail("george4788@example.co*m")); // unrecognized characters in top level domain
        assertFalse(Email.isValidEmail("_george4788@example.com")); // prefix starts with an underscore
        assertFalse(Email.isValidEmail("george4788_@example.com")); // prefix ends with an underscore
        assertFalse(Email.isValidEmail("geroge..4788@example.com")); // prefix has two consecutive periods
        assertFalse(Email.isValidEmail("george4788@-")); // invalid domain part
        assertFalse(Email.isValidEmail("george4788@exam_ple.com")); // underscore in domain name
        assertFalse(Email.isValidEmail("george4788@example.c_om")); // underscore in top level domain
        assertFalse(Email.isValidEmail("geroge 4788@example.com")); // spaces in prefix
        assertFalse(Email.isValidEmail("george4788@exam ple.com")); // spaces in domain
        assertFalse(Email.isValidEmail("george4788@example.c om")); // spaces in top level domain
        assertFalse(Email.isValidEmail(" george4788@example.com")); // leading space
        assertFalse(Email.isValidEmail("george4788@example.com ")); // trailing space
        assertFalse(Email.isValidEmail("george4788@@example.com")); // double '@' symbol
        assertFalse(Email.isValidEmail("geroge@4788@example.com")); // '@' symbol in prefix
        assertFalse(Email.isValidEmail("george4788@example@com")); // '@' symbol in domain part
        assertFalse(Email.isValidEmail("george4788@.example.com")); // domain name starts with a period
        assertFalse(Email.isValidEmail("george4788@example.com.")); // top level domain ends with a period
        assertFalse(Email.isValidEmail("george4788@-example.com")); // domain name starts with a hyphen
        assertFalse(Email.isValidEmail("george4788@example.com-")); // domain name ends with a hyphen
        assertFalse(Email.isValidEmail("george4788@example.c")); // less than two characters in top level domain

        // valid email
        assertTrue(Email.isValidEmail("george_4788@example.com")); // prefix with underscore
        assertTrue(Email.isValidEmail("george-4788@example.com")); // prefix with hyphen
        assertTrue(Email.isValidEmail("george.4788@example.com")); // prefix with period
        assertTrue(Email.isValidEmail("a@b.cd")); // minimal
        assertTrue(Email.isValidEmail("george@example.com")); // alphabets only
        assertTrue(Email.isValidEmail("123@456.78")); // numeric only
        assertTrue(Email.isValidEmail("a1_bc-d2e.f3@examp1e.com")); // mix of alphanumeric and special characters
        assertTrue(Email.isValidEmail("george4788@example-com.sg")); // hyphen in domain name
        assertTrue(Email.isValidEmail("george4788@a-b-c-d-e.sg")); // multiple hyphens in single domain name
        assertTrue(Email.isValidEmail("e1234567@u.nus.edu.sg")); // multiple domain parts in domain name
        assertTrue(Email.isValidEmail("if.you.dream.it_you.can.do.it@example.com")); // long prefix
        assertTrue(Email.isValidEmail("peter_jack@very-very-very-long-example.com")); // long domain name
        assertTrue(Email.isValidEmail("peter_jack@example.veryveryveryveryveryverylong")); // long top level domain
    }
}
