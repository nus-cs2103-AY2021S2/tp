package seedu.address.model.filter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.tutor.Phone;

public class PhoneFilterTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PhoneFilter(null));
    }

    @Test
    public void constructor_validPhoneFilter_success() {
        // Note that full testing of valid filters is done in isValidPhoneFilter test
        // EP 1: One Digit
        assertEquals("1", new PhoneFilter("1").phoneFilter);

        // EP 2: Multiple Digits
        assertEquals("0123456789", new PhoneFilter("0123456789").phoneFilter);
    }

    @Test
    public void constructor_invalidPhoneFilter_throwsIllegalArgumentException() {
        // Note that full testing of invalid filters is done in isValidPhoneFilter test
        String invalidPhoneFilter = "";
        assertThrows(IllegalArgumentException.class, () -> new PhoneFilter(invalidPhoneFilter));
    }

    @Test
    public void isValidPhoneFilter() {
        // EP 1: Numbers
        assertTrue(PhoneFilter.isValidPhoneFilter("0"));
        assertTrue(PhoneFilter.isValidPhoneFilter("0123456789"));

        // EP 2: Lowercase Alphabet
        assertFalse(PhoneFilter.isValidPhoneFilter("a"));
        assertFalse(PhoneFilter.isValidPhoneFilter("m"));
        assertFalse(PhoneFilter.isValidPhoneFilter("z"));
        assertFalse(PhoneFilter.isValidPhoneFilter("0a"));
        assertFalse(PhoneFilter.isValidPhoneFilter("a0"));

        // EP 3: Uppercase Alphabet
        assertFalse(PhoneFilter.isValidPhoneFilter("A"));
        assertFalse(PhoneFilter.isValidPhoneFilter("M"));
        assertFalse(PhoneFilter.isValidPhoneFilter("Z"));
        assertFalse(PhoneFilter.isValidPhoneFilter("0A"));
        assertFalse(PhoneFilter.isValidPhoneFilter("A0"));

        // EP 4: Symbols
        assertFalse(PhoneFilter.isValidPhoneFilter("("));
        assertFalse(PhoneFilter.isValidPhoneFilter(")"));
        assertFalse(PhoneFilter.isValidPhoneFilter("!"));
        assertFalse(PhoneFilter.isValidPhoneFilter("@"));
        assertFalse(PhoneFilter.isValidPhoneFilter("%"));
        assertFalse(PhoneFilter.isValidPhoneFilter("-"));
        assertFalse(PhoneFilter.isValidPhoneFilter("+"));
        assertFalse(PhoneFilter.isValidPhoneFilter("-1"));
        assertFalse(PhoneFilter.isValidPhoneFilter("1-"));

        // EP 5: Empty
        assertFalse(PhoneFilter.isValidPhoneFilter(""));

        // EP 6: Space
        assertFalse(PhoneFilter.isValidPhoneFilter(" "));
        assertFalse(PhoneFilter.isValidPhoneFilter("0 "));
        assertFalse(PhoneFilter.isValidPhoneFilter(" 0"));

        // EP 7: null
        assertThrows(NullPointerException.class, () -> PhoneFilter.isValidPhoneFilter(null));
    }

    @Test
    public void test() {
        PhoneFilter phoneFilter = new PhoneFilter("04569");

        // EP 1: Exact match
        assertTrue(phoneFilter.test(new Phone("04569")));

        // EP 3: Partial Match
        assertTrue(phoneFilter.test(new Phone("04569248")));
        assertTrue(phoneFilter.test(new Phone("24804569")));

        // EP 4: No match
        assertFalse(phoneFilter.test(new Phone("0456")));
        assertFalse(phoneFilter.test(new Phone("4569")));
        assertFalse(phoneFilter.test(new Phone("1234")));

        // EP 5: null
        assertFalse(phoneFilter.test(null));
    }
}
