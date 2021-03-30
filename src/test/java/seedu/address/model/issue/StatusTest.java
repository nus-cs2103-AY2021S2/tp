package seedu.address.model.issue;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StatusTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Status((String) null));
        assertThrows(NullPointerException.class, () -> new Status((IssueStatus) null));
    }

    @Test
    public void constructor_invalidStatus_throwsIllegalArgumentException() {
        String invalidStatus = "abc";
        assertThrows(IllegalArgumentException.class, () -> new Status(invalidStatus));
    }

    @Test
    public void isValidName() {
        // null status
        assertThrows(NullPointerException.class, () -> Status.isValidStatus(null));

        // invalid status
        assertFalse(Status.isValidStatus("")); // empty status
        assertFalse(Status.isValidStatus("abc")); // invalid status

        // valid status
        assertTrue(Status.isValidStatus("pending")); // single alphabet
        assertTrue(Status.isValidStatus("PENDING")); // single digit
        assertTrue(Status.isValidStatus("PeNdInG")); // mix case
        assertTrue(Status.isValidStatus("p")); // single lower case
        assertTrue(Status.isValidStatus("P")); // single upper case
        assertTrue(Status.isValidStatus("closed")); // single alphabet
        assertTrue(Status.isValidStatus("CLOSED")); // single digit
        assertTrue(Status.isValidStatus("ClOsEd")); // mix case
        assertTrue(Status.isValidStatus("c")); // single lower case
        assertTrue(Status.isValidStatus("C")); // single upper case
    }
}
