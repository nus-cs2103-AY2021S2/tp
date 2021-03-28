package seedu.address.model.common;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.ASSIGNMENT;
import static seedu.address.testutil.TypicalTasks.EXERCISE;

import org.junit.jupiter.api.Test;

public class NameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Name(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new Name(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> Name.isValidName(null));

        // invalid name
        assertFalse(Name.isValidName("")); // empty string
        assertFalse(Name.isValidName(" ")); // spaces only
        assertFalse(Name.isValidName("^")); // only non-alphanumeric characters
        assertFalse(Name.isValidName("do * at #")); // contains non-alphanumeric characters

        // valid name
        assertTrue(Name.isValidName("lecture")); // alphabets only
        assertTrue(Name.isValidName("1337")); // numbers only
        assertTrue(Name.isValidName("cs2103 lecture")); // alphanumeric characters
        assertTrue(Name.isValidName("Official Meeting")); // with capital letters
        assertTrue(Name.isValidName("CS2103 Software Programming Lecture 2")); // long names
    }

    @Test
    public void compareTest() {
        assertEquals(ASSIGNMENT.getName().compareTo(EXERCISE.getName()),
                ASSIGNMENT.getName().fullName.compareTo(EXERCISE.getName().fullName));
    }
}
