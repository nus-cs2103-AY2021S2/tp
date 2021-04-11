package seedu.address.model.subject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class SubjectRateTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SubjectRate(null));
    }

    @Test
    public void constructor_validSubjectRate_success() {
        // Note that full testing of valid values is done in isValidRate test
        // EP 1: One Digit
        assertEquals(1, new SubjectRate("1").rate);

        // EP 2: Multiple Digits
        assertEquals(12345, new SubjectRate("12345").rate);
    }

    @Test
    public void constructor_invalidSubjectRate_throwsIllegalArgumentException() {
        // Note that full testing of invalid values is done in isValidRate test
        String invalidSubjectRate = "";
        assertThrows(IllegalArgumentException.class, () -> new SubjectRate(invalidSubjectRate));
    }

    @Test
    public void isValidRate() {
        // EP 1: Numbers
        assertTrue(SubjectRate.isValidRate("0"));
        assertTrue(SubjectRate.isValidRate("12345"));

        // EP 2: Lowercase Alphabet
        assertFalse(SubjectRate.isValidRate("a"));
        assertFalse(SubjectRate.isValidRate("m"));
        assertFalse(SubjectRate.isValidRate("z"));
        assertFalse(SubjectRate.isValidRate("0a"));
        assertFalse(SubjectRate.isValidRate("a0"));

        // EP 3: Uppercase Alphabet
        assertFalse(SubjectRate.isValidRate("A"));
        assertFalse(SubjectRate.isValidRate("M"));
        assertFalse(SubjectRate.isValidRate("Z"));
        assertFalse(SubjectRate.isValidRate("0A"));
        assertFalse(SubjectRate.isValidRate("A0"));

        // EP 4: Symbols
        assertFalse(SubjectRate.isValidRate("("));
        assertFalse(SubjectRate.isValidRate(")"));
        assertFalse(SubjectRate.isValidRate("!"));
        assertFalse(SubjectRate.isValidRate("@"));
        assertFalse(SubjectRate.isValidRate("%"));
        assertFalse(SubjectRate.isValidRate("-"));
        assertFalse(SubjectRate.isValidRate("+"));
        assertFalse(SubjectRate.isValidRate("-1"));
        assertFalse(SubjectRate.isValidRate("1-"));

        // EP 5: Empty
        assertFalse(SubjectRate.isValidRate(""));

        // EP 6: Space
        assertFalse(SubjectRate.isValidRate(" "));
        assertFalse(SubjectRate.isValidRate("0 "));
        assertFalse(SubjectRate.isValidRate(" 0"));

        // EP 7: null
        assertThrows(NullPointerException.class, () -> SubjectRate.isValidRate(null));

        // EP 8: Too Long
        assertFalse(SubjectRate.isValidRate("123456"));
    }
}
