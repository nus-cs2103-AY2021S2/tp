package seedu.address.model.groupmate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class RoleTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Role(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidTagName1 = "";
        String invalidTagName2 = "q";
        String invalidTagName3 = "a-";
        String invalidTagName4 = "-b";
        String invalidTagName5 = "c_";
        String invalidTagName6 = "_d";
        assertThrows(IllegalArgumentException.class, () -> new Role(invalidTagName1));
        assertThrows(IllegalArgumentException.class, () -> new Role(invalidTagName2));
        assertThrows(IllegalArgumentException.class, () -> new Role(invalidTagName3));
        assertThrows(IllegalArgumentException.class, () -> new Role(invalidTagName4));
        assertThrows(IllegalArgumentException.class, () -> new Role(invalidTagName5));
        assertThrows(IllegalArgumentException.class, () -> new Role(invalidTagName6));
    }

    @Test
    public void isValidTagName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Role.isValidRoleName(null));

        assertTrue(Role.isValidRoleName("ma"));
        assertTrue(Role.isValidRoleName("Ma"));
        assertTrue(Role.isValidRoleName("MA"));
        assertTrue(Role.isValidRoleName("a-b"));
        assertTrue(Role.isValidRoleName("a_b"));
        assertTrue(Role.isValidRoleName("apple"));
        assertFalse(Role.isValidRoleName("a"));
        assertFalse(Role.isValidRoleName("B"));
        assertFalse(Role.isValidRoleName("abcd_"));
        assertFalse(Role.isValidRoleName("_abcd"));
        assertFalse(Role.isValidRoleName("-abcd"));
        assertFalse(Role.isValidRoleName("abcd-"));
        assertFalse(Role.isValidRoleName("-_-"));
        assertFalse(Role.isValidRoleName("_-_"));
    }

}
