package seedu.address.model.subject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class SubjectLevelTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SubjectLevel(null));
    }

    @Test
    public void constructor_validSubjectLevel_success() {
        // Note that full testing of valid values is done in isValidLevel test
        // EP 1: Valid Lowercase
        assertEquals("amz", new SubjectLevel("amz").level);

        // EP 2: Valid Uppercase
        assertEquals("AMZ", new SubjectLevel("AMZ").level);

        // EP 3: Valid Number
        assertEquals("059", new SubjectLevel("059").level);

        // EP 4: Valid Alphanumeric
        assertEquals("a m z A M Z 0 5 9", new SubjectLevel("a m z A M Z 0 5 9").level);
    }

    @Test
    public void constructor_invalidSubjectLevel_throwsIllegalArgumentException() {
        // Note that full testing of invalid values is done in isValidLevel test
        String invalidSubjectLevel = "";
        assertThrows(IllegalArgumentException.class, () -> new SubjectLevel(invalidSubjectLevel));
    }

    @Test
    public void isValidLevel() {
        // EP 1: Lowercase Alphabet
        assertTrue(SubjectLevel.isValidLevel("abcdefghijklmnopqrstuvwxyz"));

        // EP 2: Uppercase Alphabet
        assertTrue(SubjectLevel.isValidLevel("ABCDEFGHIJKLMNOPQRSTUVWXYZ"));

        // EP 3: Numbers
        assertTrue(SubjectLevel.isValidLevel("0123456789"));

        // EP 4: Mixed Alphanumeric
        assertTrue(SubjectLevel.isValidLevel("amzAMZ059"));

        // EP 5: Mixed Alphanumeric with Spaces
        assertTrue(SubjectLevel.isValidLevel("a m z A M Z 0 5 9"));

        // EP 6: Empty
        assertFalse(SubjectLevel.isValidLevel(""));

        // EP 7: Space
        assertFalse(SubjectLevel.isValidLevel(" "));

        // EP 8: Symbols
        assertFalse(SubjectLevel.isValidLevel("("));
        assertFalse(SubjectLevel.isValidLevel(")"));
        assertFalse(SubjectLevel.isValidLevel("!"));
        assertFalse(SubjectLevel.isValidLevel("@"));
        assertFalse(SubjectLevel.isValidLevel("%"));
        assertFalse(SubjectLevel.isValidLevel("-"));
        assertFalse(SubjectLevel.isValidLevel("+"));
        assertFalse(SubjectLevel.isValidLevel("-1"));
        assertFalse(SubjectLevel.isValidLevel("a m z A M Z 0 5 9 !"));

        // EP 9: null
        assertThrows(NullPointerException.class, () -> SubjectLevel.isValidLevel(null));
    }
}
