package seedu.address.model.assignee;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AssigneeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Assignee(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidTagName = "";
        assertThrows(IllegalArgumentException.class, () -> new Assignee(invalidTagName));
    }

    @Test
    public void isValidAssigneeName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Assignee.isValidAssigneeName(null));
    }

}
