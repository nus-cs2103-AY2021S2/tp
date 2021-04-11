package seedu.address.model.filter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.subject.SubjectLevel;

public class SubjectLevelFilterTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SubjectLevelFilter(null));
    }

    @Test
    public void constructor_validSubjectLevelFilter_success() {
        // Note that full testing of valid filters is done in isValidSubjectLevelFilter test
        // EP 1: Valid Lowercase Filter
        assertEquals("amz", new SubjectLevelFilter("amz").subjectLevelFilter);

        // EP 2: Valid Uppercase Filter
        assertEquals("amz", new SubjectLevelFilter("AMZ").subjectLevelFilter);

        // EP 3: Valid Number Filter
        assertEquals("059", new SubjectLevelFilter("059").subjectLevelFilter);

        // EP 4: Valid Alphanumeric Filter
        assertEquals("a m z a m z 0 5 9", new SubjectLevelFilter("a m z A M Z 0 5 9").subjectLevelFilter);
    }

    @Test
    public void constructor_invalidSubjectLevelFilter_throwsIllegalArgumentException() {
        // Note that full testing of invalid filters is done in isValidSubjectLevelFilter test
        String invalidSubjectLevelFilter = "";
        assertThrows(IllegalArgumentException.class, () -> new SubjectLevelFilter(invalidSubjectLevelFilter));
    }

    @Test
    public void isValidSubjectLevelFilter() {
        // EP 1: Lowercase Alphabet
        assertTrue(SubjectLevelFilter.isValidSubjectLevelFilter("abcdefghijklmnopqrstuvwxyz"));

        // EP 2: Uppercase Alphabet
        assertTrue(SubjectLevelFilter.isValidSubjectLevelFilter("ABCDEFGHIJKLMNOPQRSTUVWXYZ"));

        // EP 3: Numbers
        assertTrue(SubjectLevelFilter.isValidSubjectLevelFilter("0123456789"));

        // EP 4: Mixed Alphanumeric
        assertTrue(SubjectLevelFilter.isValidSubjectLevelFilter("amzAMZ059"));

        // EP 5: Mixed Alphanumeric with Spaces
        assertTrue(SubjectLevelFilter.isValidSubjectLevelFilter("a m z A M Z 0 5 9"));

        // EP 6: Empty
        assertFalse(SubjectLevelFilter.isValidSubjectLevelFilter(""));

        // EP 7: Space
        assertFalse(SubjectLevelFilter.isValidSubjectLevelFilter(" "));

        // EP 8: Symbols
        assertFalse(SubjectLevelFilter.isValidSubjectLevelFilter("("));
        assertFalse(SubjectLevelFilter.isValidSubjectLevelFilter(")"));
        assertFalse(SubjectLevelFilter.isValidSubjectLevelFilter("!"));
        assertFalse(SubjectLevelFilter.isValidSubjectLevelFilter("@"));
        assertFalse(SubjectLevelFilter.isValidSubjectLevelFilter("%"));
        assertFalse(SubjectLevelFilter.isValidSubjectLevelFilter("-"));
        assertFalse(SubjectLevelFilter.isValidSubjectLevelFilter("+"));
        assertFalse(SubjectLevelFilter.isValidSubjectLevelFilter("-1"));
        assertFalse(SubjectLevelFilter.isValidSubjectLevelFilter("a m z A M Z 0 5 9 !"));

        // EP 9: null
        assertThrows(NullPointerException.class, () -> SubjectLevelFilter.isValidSubjectLevelFilter(null));
    }

    @Test
    public void test() {
        SubjectLevelFilter subjectLevelFilter = new SubjectLevelFilter("amz");

        // EP 1: Exact match
        assertTrue(subjectLevelFilter.test(new SubjectLevel("amz")));

        // EP 2: Different Case
        assertTrue(subjectLevelFilter.test(new SubjectLevel("AMZ")));

        // EP 3: Partial Match
        assertTrue(subjectLevelFilter.test(new SubjectLevel("amz059")));
        assertTrue(subjectLevelFilter.test(new SubjectLevel("059amz")));
        assertTrue(subjectLevelFilter.test(new SubjectLevel("amz AMZ 059")));

        // EP 4: No match
        assertFalse(subjectLevelFilter.test(new SubjectLevel("am")));
        assertFalse(subjectLevelFilter.test(new SubjectLevel("mz")));
        assertFalse(subjectLevelFilter.test(new SubjectLevel("bny")));

        // EP 5: null
        assertFalse(subjectLevelFilter.test(null));
    }
}
