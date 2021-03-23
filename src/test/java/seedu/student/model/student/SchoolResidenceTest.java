package seedu.student.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.student.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class SchoolResidenceTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SchoolResidence(null));
    }

    @Test
    public void constructor_invalidSchoolResidence_throwsIllegalArgumentException() {
        String invalidAddress = "";
        assertThrows(IllegalArgumentException.class, () -> new SchoolResidence(invalidAddress));
    }

    @Test
    public void isValidResidence() {
        // null school residence
        assertThrows(NullPointerException.class, () -> SchoolResidence.isValidResidence(null));

        // invalid school residences
        assertFalse(SchoolResidence.isValidResidence("")); // empty string
        assertFalse(SchoolResidence.isValidResidence(" ")); // spaces only
        assertFalse(SchoolResidence.isValidResidence("ke7"));
        assertFalse(SchoolResidence.isValidResidence("sheares"));
        assertFalse(SchoolResidence.isValidResidence("capt"));
        assertFalse(SchoolResidence.isValidResidence("sH"));
        assertFalse(SchoolResidence.isValidResidence("PgpH"));

        // valid school residences
        assertTrue(SchoolResidence.isValidResidence("CAPT"));
        assertTrue(SchoolResidence.isValidResidence("SH"));
        assertTrue(SchoolResidence.isValidResidence("PGPH"));
    }
}
