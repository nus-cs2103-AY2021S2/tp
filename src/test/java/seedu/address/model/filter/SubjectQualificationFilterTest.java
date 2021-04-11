package seedu.address.model.filter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.subject.SubjectQualification;

public class SubjectQualificationFilterTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SubjectQualificationFilter(null));
    }

    @Test
    public void constructor_validSubjectQualificationFilter_success() {
        // Note that full testing of valid filters is done in isValidSubjectQualificationFilter test
        // EP 1: Valid Lowercase Filter
        assertEquals("amz", new SubjectQualificationFilter("amz").subjectQualificationFilter);

        // EP 2: Valid Uppercase Filter
        assertEquals("amz", new SubjectQualificationFilter("AMZ").subjectQualificationFilter);

        // EP 3: Valid Number Filter
        assertEquals("059", new SubjectQualificationFilter("059").subjectQualificationFilter);

        // EP 4: Valid Alphanumeric Filter
        assertEquals("a m z a m z 0 5 9", new SubjectQualificationFilter(
                "a m z A M Z 0 5 9").subjectQualificationFilter);

        // EP 5: Valid Mixed Filter
        assertEquals("a m z a m z 0 5 9 - ( )", new SubjectQualificationFilter(
            "a m z A M Z 0 5 9 - ( )").subjectQualificationFilter);
    }

    @Test
    public void constructor_invalidSubjectQualificationFilter_throwsIllegalArgumentException() {
        // Note that full testing of invalid filters is done in isValidSubjectQualificationFilter test
        String invalidSubjectQualificationFilter = "";
        assertThrows(IllegalArgumentException.class, () ->
                new SubjectQualificationFilter(invalidSubjectQualificationFilter));
    }

    @Test
    public void isValidSubjectQualificationFilter() {
        // EP 1: Lowercase Alphabet
        assertTrue(SubjectQualificationFilter.isValidSubjectQualificationFilter("abcdefghijklmnopqrstuvwxyz"));

        // EP 2: Uppercase Alphabet
        assertTrue(SubjectQualificationFilter.isValidSubjectQualificationFilter("ABCDEFGHIJKLMNOPQRSTUVWXYZ"));

        // EP 3: Numbers
        assertTrue(SubjectQualificationFilter.isValidSubjectQualificationFilter("0123456789"));

        // EP 4: Mixed Alphanumeric
        assertTrue(SubjectQualificationFilter.isValidSubjectQualificationFilter("amzAMZ059"));

        // EP 5: Mixed Alphanumeric with Spaces
        assertTrue(SubjectQualificationFilter.isValidSubjectQualificationFilter("a m z A M Z 0 5 9"));

        // EP 6: Valid Symbols
        assertTrue(SubjectQualificationFilter.isValidSubjectQualificationFilter("- ( )"));
        assertTrue(SubjectQualificationFilter.isValidSubjectQualificationFilter("a m z A M Z 0 5 9 - ( )"));

        // EP 7: Invalid Symbols
        assertFalse(SubjectQualificationFilter.isValidSubjectQualificationFilter("!"));
        assertFalse(SubjectQualificationFilter.isValidSubjectQualificationFilter("@"));
        assertFalse(SubjectQualificationFilter.isValidSubjectQualificationFilter("%"));
        assertFalse(SubjectQualificationFilter.isValidSubjectQualificationFilter("+"));
        assertFalse(SubjectQualificationFilter.isValidSubjectQualificationFilter("+1"));
        assertFalse(SubjectQualificationFilter.isValidSubjectQualificationFilter("a m z A M Z 0 5 9 !"));

        // EP 8: Empty
        assertFalse(SubjectQualificationFilter.isValidSubjectQualificationFilter(""));

        // EP 9: Space
        assertFalse(SubjectQualificationFilter.isValidSubjectQualificationFilter(" "));

        // EP 10: null
        assertThrows(NullPointerException.class, () ->
                SubjectQualificationFilter.isValidSubjectQualificationFilter(null));
    }

    @Test
    public void test() {
        SubjectQualificationFilter subjectQualificationFilter = new SubjectQualificationFilter("amz-");

        // EP 1: Exact match
        assertTrue(subjectQualificationFilter.test(new SubjectQualification("amz-")));

        // EP 2: Different Case
        assertTrue(subjectQualificationFilter.test(new SubjectQualification("AMZ-")));

        // EP 3: Partial Match
        assertTrue(subjectQualificationFilter.test(new SubjectQualification("amz-059")));
        assertTrue(subjectQualificationFilter.test(new SubjectQualification("059amz-")));
        assertTrue(subjectQualificationFilter.test(new SubjectQualification("amz- AMZ 059")));

        // EP 4: No match
        assertFalse(subjectQualificationFilter.test(new SubjectQualification("am")));
        assertFalse(subjectQualificationFilter.test(new SubjectQualification("mz")));
        assertFalse(subjectQualificationFilter.test(new SubjectQualification("bny")));

        // EP 5: null
        assertFalse(subjectQualificationFilter.test(null));
    }
}
