package seedu.module.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.module.testutil.Assert.assertThrows;

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

        // invalid descriptions
        assertFalse(Description.isValidDescription("")); // empty string
        assertFalse(Description.isValidDescription(" ")); // spaces only

        // valid descriptions
        assertTrue(Description.isValidDescription("Finish the assignment"));
        assertTrue(Description.isValidDescription("-")); // one character
        // long description
        assertTrue(Description.isValidDescription(
                "Create the forbidden colour combination that does not mix well according to a tutor."));
    }

    @Test
    public void equals() {
        Description firstDescription = new Description("First Description");
        Description firstDescriptionOther = new Description("First Description");
        Description secondDescription = new Description("Second Description");
        String notDescription = "Not a Description";

        //EP: Same object
        assertTrue(firstDescription.equals(firstDescription));

        //EP: Different object, same value
        assertTrue(firstDescription.equals(firstDescriptionOther));

        //EP: Different object, different values
        assertFalse(firstDescription.equals(secondDescription));

        //EP: Different types
        assertFalse(firstDescription.equals(notDescription));
    }

    @Test
    public void compareTo() {
        Description firstDescription = new Description("First Description");
        Description firstDescriptionOther = new Description("First Description");
        Description secondDescription = new Description("Further");

        //EP: Same object
        assertTrue(firstDescription.compareTo(firstDescription) == 0);

        //EP: Different object, same value
        assertTrue(firstDescription.compareTo(firstDescriptionOther) == 0);

        //EP: Different object, different values
        assertTrue(firstDescription.compareTo(secondDescription) < 0);
        assertTrue(secondDescription.compareTo(firstDescription) > 0);
    }

    @Test
    public void hashCodeTest() {
        String firstDescriptionString = "First Description";
        Description firstDescription = new Description(firstDescriptionString);
        assertEquals(firstDescription.hashCode(), firstDescriptionString.hashCode());
    }
}
