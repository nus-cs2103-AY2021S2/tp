package seedu.address.model.remark;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class RemarkTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Remark(null));
    }

    @Test
    public void constructor_invalidRemark_throwsIllegalArgumentException() {
        String invalidRemark = "";
        assertThrows(IllegalArgumentException.class, () -> new Remark(invalidRemark));
    }

    @Test
    public void isValidRemark() {
        // null remark
        assertThrows(NullPointerException.class, () -> Remark.isValidRemark(null));

        // invalid remark
        assertFalse(Remark.isValidRemark("")); // empty string
        assertFalse(Remark.isValidRemark(" ")); // spaces only

        // valid remark
        assertTrue(Remark.isValidRemark("Urgent to sell"));
        assertTrue(Remark.isValidRemark("a")); // one character
        assertTrue(Remark.isValidRemark("^")); // non-alphanumeric character
        assertTrue(Remark.isValidRemark("The remark for this property is a very very long string. "
                + "The remark for this property is a very very long string.")); // long remark
    }
}
