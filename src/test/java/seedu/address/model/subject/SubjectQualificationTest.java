package seedu.address.model.subject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class SubjectQualificationTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SubjectQualification(null));
    }

    @Test
    public void constructor_validSubjectQualification_success() {
        // Note that full testing of valid values is done in isValidQualification test
        // EP 1: Valid Lowercase
        assertEquals("amz", new SubjectQualification("amz").qualification);

        // EP 2: Valid Uppercase
        assertEquals("AMZ", new SubjectQualification("AMZ").qualification);

        // EP 3: Valid Number
        assertEquals("059", new SubjectQualification("059").qualification);

        // EP 4: Valid Alphanumeric
        assertEquals("a m z A M Z 0 5 9", new SubjectQualification(
                "a m z A M Z 0 5 9").qualification);

        // EP 5: Valid Mixed
        assertEquals("a m 9 - ( )", new SubjectQualification(
            "a m 9 - ( )").qualification);
    }

    @Test
    public void constructor_invalidSubjectQualification_throwsIllegalArgumentException() {
        // Note that full testing of invalid values is done in isValidQualification test
        String invalidSubjectQualification = "";
        assertThrows(IllegalArgumentException.class, () ->
                new SubjectQualification(invalidSubjectQualification));
    }

    @Test
    public void isValidQualification() {
        // EP 1: Lowercase Alphabet
        assertTrue(SubjectQualification.isValidQualification("abcdefghijk"));

        // EP 2: Uppercase Alphabet
        assertTrue(SubjectQualification.isValidQualification("ABCDEFGHIJ"));

        // EP 3: Numbers
        assertTrue(SubjectQualification.isValidQualification("0123456789"));

        // EP 4: Mixed Alphanumeric
        assertTrue(SubjectQualification.isValidQualification("amzAMZ059"));

        // EP 5: Mixed Alphanumeric with Spaces
        assertTrue(SubjectQualification.isValidQualification("a m z A M Z 0 5 9"));

        // EP 6: Valid Symbols
        assertTrue(SubjectQualification.isValidQualification("- ( )"));
        assertTrue(SubjectQualification.isValidQualification("a 5 9 - ( )"));

        // EP 7: Invalid Symbols
        assertFalse(SubjectQualification.isValidQualification("!"));
        assertFalse(SubjectQualification.isValidQualification("@"));
        assertFalse(SubjectQualification.isValidQualification("%"));
        assertFalse(SubjectQualification.isValidQualification("+"));
        assertFalse(SubjectQualification.isValidQualification("+1"));
        assertFalse(SubjectQualification.isValidQualification("a M Z 0 5 9 !"));

        // EP 8: Empty
        assertFalse(SubjectQualification.isValidQualification(""));

        // EP 9: Space
        assertFalse(SubjectQualification.isValidQualification(" "));

        // EP 10: null
        assertThrows(NullPointerException.class, () ->
                SubjectQualification.isValidQualification(null));
    }
}
