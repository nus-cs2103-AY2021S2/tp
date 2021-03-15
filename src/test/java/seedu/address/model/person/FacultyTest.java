package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class FacultyTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Faculty(null));
    }

    @Test
    public void constructor_invalidFaculty_throwsIllegalArgumentException() {
        String invalidFaculty = "";
        assertThrows(IllegalArgumentException.class, () -> new Faculty(invalidFaculty));
    }

    @Test
    public void isValidFaculty() {
        // null faculty
        assertThrows(NullPointerException.class, () -> Faculty.isValidFaculty(null));

        // invalid faculty
        assertFalse(Faculty.isValidFaculty("")); // empty string
        assertFalse(Faculty.isValidFaculty(" ")); // spaces only
        assertFalse(Faculty.isValidFaculty("C0M")); // numbers
        assertFalse(Faculty.isValidFaculty("SOC"));
        assertFalse(Faculty.isValidFaculty("FOS"));
        assertFalse(Faculty.isValidFaculty("YLLSOM"));

        // valid faculty
        assertTrue(Faculty.isValidFaculty("den"));
        assertTrue(Faculty.isValidFaculty("scale"));
        assertTrue(Faculty.isValidFaculty("YNC"));
        assertTrue(Faculty.isValidFaculty("sCaLE"));
        assertTrue(Faculty.isValidFaculty("Ync"));

    }
}
