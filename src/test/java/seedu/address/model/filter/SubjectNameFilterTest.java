package seedu.address.model.filter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.subject.SubjectName;

public class SubjectNameFilterTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SubjectNameFilter(null));
    }

    @Test
    public void constructor_validSubjectNameFilter_success() {
        // Note that full testing of valid filters is done in isValidSubjectNameFilter test
        // EP 1: Valid Lowercase Filter
        assertEquals("amz", new SubjectNameFilter("amz").subjectNameFilter);

        // EP 2: Valid Uppercase Filter
        assertEquals("amz", new SubjectNameFilter("AMZ").subjectNameFilter);

        // EP 3: Valid Number Filter
        assertEquals("059", new SubjectNameFilter("059").subjectNameFilter);

        // EP 4: Valid Alphanumeric Filter
        assertEquals("a m z a m z 0 5 9", new SubjectNameFilter("a m z A M Z 0 5 9").subjectNameFilter);
    }

    @Test
    public void constructor_invalidSubjectNameFilter_throwsIllegalArgumentException() {
        // Note that full testing of invalid filters is done in isValidSubjectNameFilter test
        String invalidSubjectNameFilter = "";
        assertThrows(IllegalArgumentException.class, () -> new SubjectNameFilter(invalidSubjectNameFilter));
    }

    @Test
    public void isValidSubjectNameFilter() {
        // EP 1: Lowercase Alphabet
        assertTrue(SubjectNameFilter.isValidSubjectNameFilter("abcdefghijklmnopqrstuvwxyz"));

        // EP 2: Uppercase Alphabet
        assertTrue(SubjectNameFilter.isValidSubjectNameFilter("ABCDEFGHIJKLMNOPQRSTUVWXYZ"));

        // EP 3: Numbers
        assertTrue(SubjectNameFilter.isValidSubjectNameFilter("0123456789"));

        // EP 4: Mixed Alphanumeric
        assertTrue(SubjectNameFilter.isValidSubjectNameFilter("amzAMZ059"));

        // EP 5: Mixed Alphanumeric with Spaces
        assertTrue(SubjectNameFilter.isValidSubjectNameFilter("a m z A M Z 0 5 9"));

        // EP 6: Empty
        assertFalse(SubjectNameFilter.isValidSubjectNameFilter(""));

        // EP 7: Space
        assertFalse(SubjectNameFilter.isValidSubjectNameFilter(" "));

        // EP 8: Symbols
        assertFalse(SubjectNameFilter.isValidSubjectNameFilter("("));
        assertFalse(SubjectNameFilter.isValidSubjectNameFilter(")"));
        assertFalse(SubjectNameFilter.isValidSubjectNameFilter("!"));
        assertFalse(SubjectNameFilter.isValidSubjectNameFilter("@"));
        assertFalse(SubjectNameFilter.isValidSubjectNameFilter("%"));
        assertFalse(SubjectNameFilter.isValidSubjectNameFilter("-"));
        assertFalse(SubjectNameFilter.isValidSubjectNameFilter("+"));
        assertFalse(SubjectNameFilter.isValidSubjectNameFilter("-1"));
        assertFalse(SubjectNameFilter.isValidSubjectNameFilter("a m z A M Z 0 5 9 !"));

        // EP 9: null
        assertThrows(NullPointerException.class, () -> SubjectNameFilter.isValidSubjectNameFilter(null));
    }

    @Test
    public void test() {
        SubjectNameFilter subjectNameFilter = new SubjectNameFilter("amz");

        // EP 1: Exact match
        assertTrue(subjectNameFilter.test(new SubjectName("amz")));

        // EP 2: Different Case
        assertTrue(subjectNameFilter.test(new SubjectName("AMZ")));

        // EP 3: Partial Match
        assertTrue(subjectNameFilter.test(new SubjectName("amz059")));
        assertTrue(subjectNameFilter.test(new SubjectName("059amz")));
        assertTrue(subjectNameFilter.test(new SubjectName("amz AMZ 059")));

        // EP 4: No match
        assertFalse(subjectNameFilter.test(new SubjectName("am")));
        assertFalse(subjectNameFilter.test(new SubjectName("mz")));
        assertFalse(subjectNameFilter.test(new SubjectName("bny")));

        // EP 5: null
        assertFalse(subjectNameFilter.test(null));
    }
}
