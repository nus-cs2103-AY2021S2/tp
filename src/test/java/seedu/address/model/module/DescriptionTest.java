package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DescriptionTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Description(null));
    }

    @Test
    public void constructor_invalidDescription_throwsIllegalArgumentException() {
        String invalidDescription = "";
        assertThrows(IllegalArgumentException.class, () -> new Description(invalidDescription));
    }

    @Test
    public void isValidDescription() {
        // null description
        assertThrows(NullPointerException.class, () -> Description.isValidDescription(null));

        // invalid description
        assertFalse(Description.isValidDescription("")); // empty string
        assertFalse(Description.isValidDescription(" ")); // spaces only

        // valid description
        assertTrue(Description.isValidDescription("tp submission")); // alphabets only
        assertTrue(Description.isValidDescription("2103")); // numbers only
        assertTrue(Description.isValidDescription("CS2103T")); // alphanumeric characters
        assertTrue(Description.isValidDescription("Software Engineering")); // with capital letters
        assertTrue(Description.isValidDescription("tp v1.2")); // non-alphanumeric characters
        assertTrue(Description.isValidDescription(
                "CS2103T Software Engineering submission tp v1.2!!!")); // long description
    }
}
