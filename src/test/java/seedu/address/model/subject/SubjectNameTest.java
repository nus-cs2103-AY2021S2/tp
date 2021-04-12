package seedu.address.model.subject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class SubjectNameTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SubjectName(null));
    }

    @Test
    public void constructor_validSubjectName_success() {
        // Note that full testing of valid values is done in isValidName test
        // EP 1: Valid Lowercase
        assertEquals("amz", new SubjectName("amz").name);

        // EP 2: Valid Uppercase
        assertEquals("AMZ", new SubjectName("AMZ").name);

        // EP 3: Valid Number
        assertEquals("059", new SubjectName("059").name);

        // EP 4: Valid Alphanumeric
        assertEquals("a m z A M Z 0 5 9", new SubjectName("a m z A M Z 0 5 9").name);
    }

    @Test
    public void constructor_invalidSubjectName_throwsIllegalArgumentException() {
        // Note that full testing of invalid values is done in isValidName test
        String invalidSubjectName = "";
        assertThrows(IllegalArgumentException.class, () -> new SubjectName(invalidSubjectName));
    }

    @Test
    public void isValidName() {
        // EP 1: Lowercase Alphabet
        assertTrue(SubjectName.isValidName("abcdefghijklmnopqrstuvwxyz"));

        // EP 2: Uppercase Alphabet
        assertTrue(SubjectName.isValidName("ABCDEFGHIJKLMNOPQRSTUVWXYZ"));

        // EP 3: Numbers
        assertTrue(SubjectName.isValidName("0123456789"));

        // EP 4: Mixed Alphanumeric
        assertTrue(SubjectName.isValidName("amzAMZ059"));

        // EP 5: Mixed Alphanumeric with Spaces
        assertTrue(SubjectName.isValidName("a m z A M Z 0 5 9"));

        // EP 6: Empty
        assertFalse(SubjectName.isValidName(""));

        // EP 7: Space
        assertFalse(SubjectName.isValidName(" "));

        // EP 8: Symbols
        assertFalse(SubjectName.isValidName("("));
        assertFalse(SubjectName.isValidName(")"));
        assertFalse(SubjectName.isValidName("!"));
        assertFalse(SubjectName.isValidName("@"));
        assertFalse(SubjectName.isValidName("%"));
        assertFalse(SubjectName.isValidName("-"));
        assertFalse(SubjectName.isValidName("+"));
        assertFalse(SubjectName.isValidName("-1"));
        assertFalse(SubjectName.isValidName("a m z A M Z 0 5 9 !"));

        // EP 9: null
        assertThrows(NullPointerException.class, () -> SubjectName.isValidName(null));
    }
}
