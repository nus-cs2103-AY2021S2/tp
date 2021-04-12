package seedu.address.model.subject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class SubjectExperienceTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SubjectExperience(null));
    }

    @Test
    public void constructor_validSubjectExperience_success() {
        // Note that full testing of valid values is done in isValidExperience test
        // EP 1: One Digit
        assertEquals(1, new SubjectExperience("1").experience);

        // EP 2: Multiple Digits
        assertEquals(12, new SubjectExperience("12").experience);
    }

    @Test
    public void constructor_invalidSubjectExperience_throwsIllegalArgumentException() {
        // Note that full testing of invalid values is done in isValidExperience test
        String invalidSubjectExperience = "";
        assertThrows(IllegalArgumentException.class, () -> new SubjectExperience(invalidSubjectExperience));
    }

    @Test
    public void isValidExperience() {
        // EP 1: Numbers
        assertTrue(SubjectExperience.isValidExperience("0"));
        assertTrue(SubjectExperience.isValidExperience("12"));

        // EP 2: Lowercase Alphabet
        assertFalse(SubjectExperience.isValidExperience("a"));
        assertFalse(SubjectExperience.isValidExperience("m"));
        assertFalse(SubjectExperience.isValidExperience("z"));
        assertFalse(SubjectExperience.isValidExperience("0a"));
        assertFalse(SubjectExperience.isValidExperience("a0"));

        // EP 3: Uppercase Alphabet
        assertFalse(SubjectExperience.isValidExperience("A"));
        assertFalse(SubjectExperience.isValidExperience("M"));
        assertFalse(SubjectExperience.isValidExperience("Z"));
        assertFalse(SubjectExperience.isValidExperience("0A"));
        assertFalse(SubjectExperience.isValidExperience("A0"));

        // EP 4: Symbols
        assertFalse(SubjectExperience.isValidExperience("("));
        assertFalse(SubjectExperience.isValidExperience(")"));
        assertFalse(SubjectExperience.isValidExperience("!"));
        assertFalse(SubjectExperience.isValidExperience("@"));
        assertFalse(SubjectExperience.isValidExperience("%"));
        assertFalse(SubjectExperience.isValidExperience("-"));
        assertFalse(SubjectExperience.isValidExperience("+"));
        assertFalse(SubjectExperience.isValidExperience("-1"));
        assertFalse(SubjectExperience.isValidExperience("1-"));

        // EP 5: Empty
        assertFalse(SubjectExperience.isValidExperience(""));

        // EP 6: Space
        assertFalse(SubjectExperience.isValidExperience(" "));
        assertFalse(SubjectExperience.isValidExperience("0 "));
        assertFalse(SubjectExperience.isValidExperience(" 0"));

        // EP 7: null
        assertThrows(NullPointerException.class, () -> SubjectExperience.isValidExperience(null));

        // EP 8: Too Long
        assertFalse(SubjectExperience.isValidExperience("123"));
    }
}
