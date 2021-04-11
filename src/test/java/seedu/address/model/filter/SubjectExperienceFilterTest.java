package seedu.address.model.filter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.subject.SubjectExperience;

public class SubjectExperienceFilterTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SubjectExperienceFilter(null));
    }

    @Test
    public void constructor_validSubjectExperienceFilter_success() {
        // Note that full testing of valid filters is done in isValidSubjectExperienceFilter test
        // EP 1: One Digit
        assertEquals("1", new SubjectExperienceFilter("1").subjectExperienceFilter);

        // EP 2: Multiple Digits
        assertEquals("0123456789", new SubjectExperienceFilter("0123456789").subjectExperienceFilter);

        // EP 3: Inequalities
        assertEquals(">1", new SubjectExperienceFilter(">1").subjectExperienceFilter);
        assertEquals("<1", new SubjectExperienceFilter("<1").subjectExperienceFilter);
        assertEquals(">=1", new SubjectExperienceFilter(">=1").subjectExperienceFilter);
        assertEquals("<=1", new SubjectExperienceFilter("<=1").subjectExperienceFilter);
        assertEquals("=1", new SubjectExperienceFilter("=1").subjectExperienceFilter);
    }

    @Test
    public void constructor_invalidSubjectExperienceFilter_throwsIllegalArgumentException() {
        // Note that full testing of invalid filters is done in isValidSubjectExperienceFilter test
        String invalidSubjectExperienceFilter = "";
        assertThrows(IllegalArgumentException.class, () -> new SubjectExperienceFilter(invalidSubjectExperienceFilter));
    }

    @Test
    public void isValidSubjectExperienceFilter() {
        // EP 1: Numbers
        assertTrue(SubjectExperienceFilter.isValidSubjectExperienceFilter("0"));
        assertTrue(SubjectExperienceFilter.isValidSubjectExperienceFilter("0123456789"));

        // EP 2: Inequalities
        assertTrue(SubjectExperienceFilter.isValidSubjectExperienceFilter(">0"));
        assertTrue(SubjectExperienceFilter.isValidSubjectExperienceFilter("<0"));
        assertTrue(SubjectExperienceFilter.isValidSubjectExperienceFilter(">=0"));
        assertTrue(SubjectExperienceFilter.isValidSubjectExperienceFilter("<=0"));
        assertTrue(SubjectExperienceFilter.isValidSubjectExperienceFilter("=0"));

        // EP 3: Lowercase Alphabet
        assertFalse(SubjectExperienceFilter.isValidSubjectExperienceFilter("a"));
        assertFalse(SubjectExperienceFilter.isValidSubjectExperienceFilter("m"));
        assertFalse(SubjectExperienceFilter.isValidSubjectExperienceFilter("z"));
        assertFalse(SubjectExperienceFilter.isValidSubjectExperienceFilter("0a"));
        assertFalse(SubjectExperienceFilter.isValidSubjectExperienceFilter("a0"));

        // EP 4: Uppercase Alphabet
        assertFalse(SubjectExperienceFilter.isValidSubjectExperienceFilter("A"));
        assertFalse(SubjectExperienceFilter.isValidSubjectExperienceFilter("M"));
        assertFalse(SubjectExperienceFilter.isValidSubjectExperienceFilter("Z"));
        assertFalse(SubjectExperienceFilter.isValidSubjectExperienceFilter("0A"));
        assertFalse(SubjectExperienceFilter.isValidSubjectExperienceFilter("A0"));

        // EP 5: Symbols
        assertFalse(SubjectExperienceFilter.isValidSubjectExperienceFilter("("));
        assertFalse(SubjectExperienceFilter.isValidSubjectExperienceFilter(")"));
        assertFalse(SubjectExperienceFilter.isValidSubjectExperienceFilter("!"));
        assertFalse(SubjectExperienceFilter.isValidSubjectExperienceFilter("@"));
        assertFalse(SubjectExperienceFilter.isValidSubjectExperienceFilter("%"));
        assertFalse(SubjectExperienceFilter.isValidSubjectExperienceFilter("-"));
        assertFalse(SubjectExperienceFilter.isValidSubjectExperienceFilter("+"));
        assertFalse(SubjectExperienceFilter.isValidSubjectExperienceFilter(">"));
        assertFalse(SubjectExperienceFilter.isValidSubjectExperienceFilter("<"));
        assertFalse(SubjectExperienceFilter.isValidSubjectExperienceFilter(">="));
        assertFalse(SubjectExperienceFilter.isValidSubjectExperienceFilter("<="));
        assertFalse(SubjectExperienceFilter.isValidSubjectExperienceFilter("="));
        assertFalse(SubjectExperienceFilter.isValidSubjectExperienceFilter("1>"));
        assertFalse(SubjectExperienceFilter.isValidSubjectExperienceFilter("1="));

        // EP 6: Empty
        assertFalse(SubjectExperienceFilter.isValidSubjectExperienceFilter(""));

        // EP 7: Space
        assertFalse(SubjectExperienceFilter.isValidSubjectExperienceFilter(" "));
        assertFalse(SubjectExperienceFilter.isValidSubjectExperienceFilter("0 "));
        assertFalse(SubjectExperienceFilter.isValidSubjectExperienceFilter(" 0"));

        // EP 8: null
        assertThrows(NullPointerException.class, () -> SubjectExperienceFilter.isValidSubjectExperienceFilter(null));
    }

    @Test
    public void test() {
        SubjectExperienceFilter subjectExperienceFilter;

        // EP 1: Exact match
        subjectExperienceFilter = new SubjectExperienceFilter("19");
        assertTrue(subjectExperienceFilter.test(new SubjectExperience("19")));

        subjectExperienceFilter = new SubjectExperienceFilter("=19");
        assertTrue(subjectExperienceFilter.test(new SubjectExperience("19")));

        subjectExperienceFilter = new SubjectExperienceFilter(">=19");
        assertTrue(subjectExperienceFilter.test(new SubjectExperience("19")));

        subjectExperienceFilter = new SubjectExperienceFilter("<=19");
        assertTrue(subjectExperienceFilter.test(new SubjectExperience("19")));

        // EP 2: More Than
        subjectExperienceFilter = new SubjectExperienceFilter(">19");
        assertTrue(subjectExperienceFilter.test(new SubjectExperience("20")));
        assertFalse(subjectExperienceFilter.test(new SubjectExperience("19")));
        assertFalse(subjectExperienceFilter.test(new SubjectExperience("18")));

        // EP 3: Less Than
        subjectExperienceFilter = new SubjectExperienceFilter("<19");
        assertTrue(subjectExperienceFilter.test(new SubjectExperience("18")));
        assertFalse(subjectExperienceFilter.test(new SubjectExperience("19")));
        assertFalse(subjectExperienceFilter.test(new SubjectExperience("20")));

        // EP 4: null
        assertFalse(subjectExperienceFilter.test(null));
    }
}
