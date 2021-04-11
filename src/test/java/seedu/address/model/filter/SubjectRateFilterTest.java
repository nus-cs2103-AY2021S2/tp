package seedu.address.model.filter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.subject.SubjectRate;

public class SubjectRateFilterTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SubjectRateFilter(null));
    }

    @Test
    public void constructor_validSubjectRateFilter_success() {
        // Note that full testing of valid filters is done in isValidSubjectRateFilter test
        // EP 1: One Digit
        assertEquals("1", new SubjectRateFilter("1").subjectRateFilter);

        // EP 2: Multiple Digits
        assertEquals("0123456789", new SubjectRateFilter("0123456789").subjectRateFilter);

        // EP 3: Inequalities
        assertEquals(">1", new SubjectRateFilter(">1").subjectRateFilter);
        assertEquals("<1", new SubjectRateFilter("<1").subjectRateFilter);
        assertEquals(">=1", new SubjectRateFilter(">=1").subjectRateFilter);
        assertEquals("<=1", new SubjectRateFilter("<=1").subjectRateFilter);
        assertEquals("=1", new SubjectRateFilter("=1").subjectRateFilter);
    }

    @Test
    public void constructor_invalidSubjectRateFilter_throwsIllegalArgumentException() {
        // Note that full testing of invalid filters is done in isValidSubjectRateFilter test
        String invalidSubjectRateFilter = "";
        assertThrows(IllegalArgumentException.class, () -> new SubjectRateFilter(invalidSubjectRateFilter));
    }

    @Test
    public void isValidSubjectRateFilter() {
        // EP 1: Numbers
        assertTrue(SubjectRateFilter.isValidSubjectRateFilter("0"));
        assertTrue(SubjectRateFilter.isValidSubjectRateFilter("0123456789"));

        // EP 2: Inequalities
        assertTrue(SubjectRateFilter.isValidSubjectRateFilter(">0"));
        assertTrue(SubjectRateFilter.isValidSubjectRateFilter("<0"));
        assertTrue(SubjectRateFilter.isValidSubjectRateFilter(">=0"));
        assertTrue(SubjectRateFilter.isValidSubjectRateFilter("<=0"));
        assertTrue(SubjectRateFilter.isValidSubjectRateFilter("=0"));

        // EP 3: Lowercase Alphabet
        assertFalse(SubjectRateFilter.isValidSubjectRateFilter("a"));
        assertFalse(SubjectRateFilter.isValidSubjectRateFilter("m"));
        assertFalse(SubjectRateFilter.isValidSubjectRateFilter("z"));
        assertFalse(SubjectRateFilter.isValidSubjectRateFilter("0a"));
        assertFalse(SubjectRateFilter.isValidSubjectRateFilter("a0"));

        // EP 4: Uppercase Alphabet
        assertFalse(SubjectRateFilter.isValidSubjectRateFilter("A"));
        assertFalse(SubjectRateFilter.isValidSubjectRateFilter("M"));
        assertFalse(SubjectRateFilter.isValidSubjectRateFilter("Z"));
        assertFalse(SubjectRateFilter.isValidSubjectRateFilter("0A"));
        assertFalse(SubjectRateFilter.isValidSubjectRateFilter("A0"));

        // EP 5: Symbols
        assertFalse(SubjectRateFilter.isValidSubjectRateFilter("("));
        assertFalse(SubjectRateFilter.isValidSubjectRateFilter(")"));
        assertFalse(SubjectRateFilter.isValidSubjectRateFilter("!"));
        assertFalse(SubjectRateFilter.isValidSubjectRateFilter("@"));
        assertFalse(SubjectRateFilter.isValidSubjectRateFilter("%"));
        assertFalse(SubjectRateFilter.isValidSubjectRateFilter("-"));
        assertFalse(SubjectRateFilter.isValidSubjectRateFilter("+"));
        assertFalse(SubjectRateFilter.isValidSubjectRateFilter(">"));
        assertFalse(SubjectRateFilter.isValidSubjectRateFilter("<"));
        assertFalse(SubjectRateFilter.isValidSubjectRateFilter(">="));
        assertFalse(SubjectRateFilter.isValidSubjectRateFilter("<="));
        assertFalse(SubjectRateFilter.isValidSubjectRateFilter("="));
        assertFalse(SubjectRateFilter.isValidSubjectRateFilter("1>"));
        assertFalse(SubjectRateFilter.isValidSubjectRateFilter("1="));

        // EP 6: Empty
        assertFalse(SubjectRateFilter.isValidSubjectRateFilter(""));

        // EP 7: Space
        assertFalse(SubjectRateFilter.isValidSubjectRateFilter(" "));
        assertFalse(SubjectRateFilter.isValidSubjectRateFilter("0 "));
        assertFalse(SubjectRateFilter.isValidSubjectRateFilter(" 0"));

        // EP 8: null
        assertThrows(NullPointerException.class, () -> SubjectRateFilter.isValidSubjectRateFilter(null));
    }

    @Test
    public void test() {
        SubjectRateFilter subjectRateFilter;

        // EP 1: Exact match
        subjectRateFilter = new SubjectRateFilter("04569");
        assertTrue(subjectRateFilter.test(new SubjectRate("04569")));

        subjectRateFilter = new SubjectRateFilter("=04569");
        assertTrue(subjectRateFilter.test(new SubjectRate("04569")));

        subjectRateFilter = new SubjectRateFilter(">=04569");
        assertTrue(subjectRateFilter.test(new SubjectRate("04569")));

        subjectRateFilter = new SubjectRateFilter("<=04569");
        assertTrue(subjectRateFilter.test(new SubjectRate("04569")));

        // EP 2: More Than
        subjectRateFilter = new SubjectRateFilter(">04569");
        assertTrue(subjectRateFilter.test(new SubjectRate("04570")));
        assertFalse(subjectRateFilter.test(new SubjectRate("04569")));
        assertFalse(subjectRateFilter.test(new SubjectRate("04568")));

        // EP 3: Less Than
        subjectRateFilter = new SubjectRateFilter("<04569");
        assertTrue(subjectRateFilter.test(new SubjectRate("04568")));
        assertFalse(subjectRateFilter.test(new SubjectRate("04569")));
        assertFalse(subjectRateFilter.test(new SubjectRate("04570")));

        // EP 4: null
        assertFalse(subjectRateFilter.test(null));
    }
}
