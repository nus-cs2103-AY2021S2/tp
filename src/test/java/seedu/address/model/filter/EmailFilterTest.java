package seedu.address.model.filter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.tutor.Email;

public class EmailFilterTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EmailFilter(null));
    }

    @Test
    public void constructor_validEmailFilter_success() {
        // Note that full testing of valid filters is done in isValidEmailFilter test
        // EP 1: Valid Lowercase Filter
        assertEquals("amz", new EmailFilter("amz").emailFilter);

        // EP 2: Valid Uppercase Filter
        assertEquals("amz", new EmailFilter("AMZ").emailFilter);

        // EP 3: Valid Number Filter
        assertEquals("059", new EmailFilter("059").emailFilter);

        // EP 4: Valid Alphanumeric Filter
        assertEquals("amzamz059", new EmailFilter("amzAMZ059").emailFilter);

        // EP 5: Valid Mixed Filter
        assertEquals("amzamz059!@*", new EmailFilter("amzAMZ059!@*").emailFilter);
    }

    @Test
    public void constructor_invalidEmailFilter_throwsIllegalArgumentException() {
        // Note that full testing of invalid filters is done in isValidEmailFilter test
        String invalidEmailFilter = "";
        assertThrows(IllegalArgumentException.class, () -> new EmailFilter(invalidEmailFilter));
    }

    @Test
    public void isValidEmailFilter() {
        // EP 1: Lowercase Alphabet
        assertTrue(EmailFilter.isValidEmailFilter("abcdefghijklmnopqrstuvwxyz"));

        // EP 2: Uppercase Alphabet
        assertTrue(EmailFilter.isValidEmailFilter("ABCDEFGHIJKLMNOPQRSTUVWXYZ"));

        // EP 3: Numbers
        assertTrue(EmailFilter.isValidEmailFilter("0123456789"));

        // EP 4: Mixed Alphanumeric
        assertTrue(EmailFilter.isValidEmailFilter("amzAMZ059"));

        // EP 5: Valid Symbols
        assertTrue(EmailFilter.isValidEmailFilter("!#$%&'*+/=?`{|}~^.-@"));

        // EP 6: Invalid Symbols
        assertFalse(EmailFilter.isValidEmailFilter("("));
        assertFalse(EmailFilter.isValidEmailFilter(","));
        assertFalse(EmailFilter.isValidEmailFilter(";"));
        assertFalse(EmailFilter.isValidEmailFilter(">"));
        assertFalse(EmailFilter.isValidEmailFilter("\""));
        assertFalse(EmailFilter.isValidEmailFilter("["));

        // EP 7: Empty
        assertFalse(EmailFilter.isValidEmailFilter(""));

        // EP 8: Space
        assertFalse(EmailFilter.isValidEmailFilter(" "));
        assertFalse(EmailFilter.isValidEmailFilter("a m z A M Z 0 5 9 ! @ *"));

        // EP 9: null
        assertThrows(NullPointerException.class, () -> EmailFilter.isValidEmailFilter(null));
    }

    @Test
    public void test() {
        EmailFilter emailFilter = new EmailFilter("amz");

        // EP 1: Exact match
        assertTrue(emailFilter.test(new Email("amz@example.com")));

        // EP 2: Different Case
        assertTrue(emailFilter.test(new Email("AMZ@example.com")));

        // EP 3: Partial Match
        assertTrue(emailFilter.test(new Email("amz059@example.com")));
        assertTrue(emailFilter.test(new Email("059amz@example.com")));
        assertTrue(emailFilter.test(new Email("amzAMZ059@example.com")));
        assertTrue(emailFilter.test(new Email("amzAMZ059!%*@example.com")));

        // EP 4: No match
        assertFalse(emailFilter.test(new Email("am@example.com")));
        assertFalse(emailFilter.test(new Email("mz@example.com")));
        assertFalse(emailFilter.test(new Email("bny@example.com")));

        // EP 5: null
        assertFalse(emailFilter.test(null));
    }
}
