package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class JobTitleTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new JobTitle(null));
    }

    @Test
    public void constructor_invalidJobTitle_throwsIllegalArgumentException() {
        String invalidJobTitle = "";
        assertThrows(IllegalArgumentException.class, () -> new JobTitle(invalidJobTitle));
    }

    @Test
    public void isValidJobTitle() {
        // null job title
        assertThrows(NullPointerException.class, () -> JobTitle.isValidJobTitle(null));

        // invalid job titles
        assertFalse(JobTitle.isValidJobTitle("")); // empty string
        assertFalse(JobTitle.isValidJobTitle(" ")); // spaces only

        // valid job titles
        assertTrue(JobTitle.isValidJobTitle("Software Engineer"));
        assertTrue(JobTitle.isValidJobTitle("S")); // one character
        assertTrue(JobTitle.isValidJobTitle("Advanced Certification Administrator: ACA")); // long job title
    }
}
