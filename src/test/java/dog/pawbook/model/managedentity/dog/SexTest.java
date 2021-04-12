package dog.pawbook.model.managedentity.dog;

import static dog.pawbook.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class SexTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Sex(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidSex = "";
        assertThrows(IllegalArgumentException.class, () -> new Sex(invalidSex));
    }

    @Test
    public void isValidAddress() {
        // null sex
        assertThrows(NullPointerException.class, () -> Sex.isValidSex(null));

        // invalid sex
        assertFalse(Sex.isValidSex("")); // empty string
        assertFalse(Sex.isValidSex(" ")); // spaces only
        assertFalse(Sex.isValidSex("m"));
        assertFalse(Sex.isValidSex("f"));

        // valid sex
        assertTrue(Sex.isValidSex("Male"));
        assertTrue(Sex.isValidSex("Female"));
        assertTrue(Sex.isValidSex("male"));
        assertTrue(Sex.isValidSex("female"));
        assertTrue(Sex.isValidSex("M"));
        assertTrue(Sex.isValidSex("F"));
    }

    @Test
    public void equals() {
        Sex sex1 = new Sex("Female");
        Sex sex2 = new Sex("Male");

        // same object -> returns true
        assertTrue(sex1.equals(sex1));

        // same values -> returns true
        Sex sex1Copy = new Sex("Female");
        assertTrue(sex1.equals(sex1Copy));

        // different types -> returns false
        assertFalse(sex1.equals(1));

        // null -> returns false
        assertFalse(sex1.equals(null));

        // different Sex -> returns false
        assertFalse(sex1.equals(sex2));
    }
}
