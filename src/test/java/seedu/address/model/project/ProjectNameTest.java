package seedu.address.model.project;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ProjectNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ProjectName(null));
    }

    @Test
    public void constructor_invalidProjectName_throwsIllegalArgumentException() {
        String invalidProjectName = "";
        assertThrows(IllegalArgumentException.class, () -> new ProjectName(invalidProjectName));
    }

    @Test
    public void isValidProjectName() {
        // null name
        assertThrows(NullPointerException.class, () -> ProjectName.isValidProjectName(null));

        // invalid name
        assertFalse(ProjectName.isValidProjectName("")); // empty string
        assertFalse(ProjectName.isValidProjectName(" ")); // spaces only
        assertFalse(ProjectName.isValidProjectName("^")); // only non-alphanumeric characters
        assertFalse(ProjectName.isValidProjectName("cs1101s*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(ProjectName.isValidProjectName("school project")); // alphabets only
        assertTrue(ProjectName.isValidProjectName("12345")); // numbers only
        assertTrue(ProjectName.isValidProjectName("cs2103t project")); // alphanumeric characters
        assertTrue(ProjectName.isValidProjectName("CS2103T Project")); // with capital letters
        assertTrue(ProjectName.isValidProjectName("School summer orbital project with long name")); // long names
    }
}
