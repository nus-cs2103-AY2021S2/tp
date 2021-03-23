package seedu.address.model.person;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

public class RoleTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Role(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidRole = "";
        assertThrows(IllegalArgumentException.class, () -> new Role(invalidRole));
    }

    @Test
    public void isValidRole() {
        // null name
        assertThrows(NullPointerException.class, () -> Role.isValidRole(null));

        // invalid name
        assertFalse(Role.isValidRole("")); // empty string
        assertFalse(Role.isValidRole(" ")); // spaces only
        assertFalse(Role.isValidRole("^")); // only non-alphanumeric characters
        assertFalse(Role.isValidRole("President*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(Role.isValidRole("President")); // alphabets only
        assertTrue(Role.isValidRole("2nd Assistant planner")); // alphanumeric characters
        assertTrue(Role.isValidRole("Vice President")); // with capital letters
        assertTrue(Role.isValidRole("Assistant event planner for House day")); // long roles
    }
}
