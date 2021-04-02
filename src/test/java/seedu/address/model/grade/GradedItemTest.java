package seedu.address.model.grade;

import org.junit.jupiter.api.Test;
import seedu.address.model.appointment.AppointmentDateTime;
import seedu.address.model.tutor.Name;
import seedu.address.model.tutor.Phone;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

public class GradedItemTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new GradedItem(null));
    }

    @Test
    public void constructor_invalidGradedItem_throwsIllegalArgumentException() {
        String invalidGradedItem = "";
        assertThrows(IllegalArgumentException.class, () -> new Phone(invalidGradedItem));
    }

    @Test
    public void isValidGradedItem() {
        // null phone number
        assertThrows(NullPointerException.class, () -> GradedItem.isValidGradedItem(null));

        // invalid graded item
        assertFalse(GradedItem.isValidGradedItem("")); // empty string
        assertFalse(GradedItem.isValidGradedItem(" ")); // spaces only
        assertFalse(GradedItem.isValidGradedItem("^")); // only non-alphanumeric characters
        assertFalse(GradedItem.isValidGradedItem("final*")); // contains non-alphanumeric characters

        // valid graded item
        assertTrue(GradedItem.isValidGradedItem("final exam")); // alphabets only
        assertTrue(GradedItem.isValidGradedItem("12345")); // numbers only
        assertTrue(GradedItem.isValidGradedItem("2nd lab")); // alphanumeric characters
        assertTrue(GradedItem.isValidGradedItem("Capital Final")); // with capital letters
        assertTrue(GradedItem.isValidGradedItem("GCE O Level Maths Paper 1 and 2")); // long names
    }
}
