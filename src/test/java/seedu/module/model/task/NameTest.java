package seedu.module.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.module.testutil.Assert.assertThrows;

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
        assertFalse(Name.isValidName(" TP")); // space before proper name

        // valid name
        assertTrue(Name.isValidName("TP v1.2")); // complex name
        assertTrue(Name.isValidName("12345")); // numbers only
        assertTrue(Name.isValidName("quiz2")); // alphanumeric characters
        assertTrue(Name.isValidName("QUIZ")); // with capital letters
        assertTrue(Name.isValidName("Project")); // long names
    }

    @Test
    public void equals() {
        Name firstName = new Name("First Name");
        Name firstNameOther = new Name("First Name");
        Name secondName = new Name("Second Name");
        String notName = "Not a name";

        //EP: Same object
        assertTrue(firstName.equals(firstName));

        //EP: Different object, same value
        assertTrue(firstName.equals(firstNameOther));

        //EP: Different object, different value
        assertFalse(firstName.equals(secondName));

        //EP: Different types
        assertFalse(firstName.equals(notName));
    }

    @Test
    public void compareTo() {
        Name firstName = new Name("First Name");
        Name firstNameOther = new Name("First Name");
        Name secondName = new Name("Further");

        //EP: Same object
        assertTrue(firstName.compareTo(firstName) == 0);

        //EP: Different object, same value
        assertTrue(firstName.compareTo(firstNameOther) == 0);

        //EP: Different object, different values
        assertTrue(firstName.compareTo(secondName) < 0);
        assertTrue(secondName.compareTo(firstName) > 0);
    }

    @Test
    public void hashCodeTest() {
        String firstNameString = "First Name";
        Name firstName = new Name(firstNameString);
        assertEquals(firstName.hashCode(), firstNameString.hashCode());
    }
}
