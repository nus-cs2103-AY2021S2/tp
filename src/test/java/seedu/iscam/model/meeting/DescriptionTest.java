package seedu.iscam.model.meeting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.iscam.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DescriptionTest {
    @Test
    public void constructor_empty() {
        String emptyDesc = "";
        assertThrows(IllegalArgumentException.class, () -> new Description(emptyDesc));
    }

    @Test
    public void constructor_overThousandChars() {
        String thousandCharsDesc = "desc desc desc desc desc desc desc desc desc desc desc desc desc desc desc desc "
                + "desc desc desc desc desc desc desc desc desc desc desc desc desc desc desc desc desc desc desc desc "
                + "desc desc desc desc desc desc desc desc desc desc desc desc desc desc desc desc desc desc desc desc "
                + "desc desc desc desc desc desc desc desc desc desc desc desc desc desc desc desc desc desc desc desc "
                + "desc desc desc desc desc desc desc desc desc desc desc desc desc desc desc desc desc desc desc desc "
                + "desc desc desc desc desc desc desc desc desc desc desc desc desc desc desc desc desc desc desc desc "
                + "desc desc desc desc desc desc desc desc desc desc desc desc desc desc desc desc desc desc desc desc "
                + "desc desc desc desc desc desc desc desc desc desc desc desc desc desc desc desc desc desc desc desc "
                + "desc desc desc desc desc desc desc desc desc desc desc desc desc desc desc desc desc desc desc desc "
                + "desc desc desc desc desc desc desc desc desc desc desc desc desc desc desc desc desc desc desc desc "
                + "desc desc desc desc desc desc desc";
        assertThrows(IllegalArgumentException.class, () -> new Description(thousandCharsDesc));
    }

    @Test
    public void equals() {
        Description desc = new Description("Just a description");

        // Null -> returns false
        assertNotEquals(desc, null);

        // Same description -> returns true
        Description otherDesc = new Description(desc.toString());
        assertEquals(desc, otherDesc);

        // Different description -> returns false
        otherDesc = new Description("Just another description");
        assertNotEquals(desc, otherDesc);
    }
}
