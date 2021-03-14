package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class SchoolTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new School(null));
    }

    @Test
    public void constructor_invalidSchool_throwsIllegalArgumentException() {
        String invalidSchool = "";
        assertThrows(IllegalArgumentException.class, () -> new School(invalidSchool));
    }

    @Test
    public void isValidSchool() {
        // null school
        assertThrows(NullPointerException.class, () -> School.isValidSchool(null));

        // invalid school
        assertFalse(School.isValidSchool("")); // empty string
        assertFalse(School.isValidSchool(" ")); // spaces only
        assertFalse(School.isValidSchool("^")); // only non-alphanumeric characters
        assertFalse(School.isValidSchool("school*")); // contains non-alphanumeric characters

        // valid school
        assertTrue(School.isValidSchool("the best school")); // alphabets only
        assertTrue(School.isValidSchool("12345")); // numbers only
        assertTrue(School.isValidSchool("123 school")); // alphanumeric characters
        assertTrue(School.isValidSchool("Best School")); // with capital letters
        assertTrue(School.isValidSchool("David Roger Jackson Ray Secondary School")); // long school names
    }
}
