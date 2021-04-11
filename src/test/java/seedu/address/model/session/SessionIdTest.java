package seedu.address.model.session;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.session.exceptions.InvalidSessionIdException;

public class SessionIdTest {
    @Test
    public void constructor_invalidSessionId_throwsInvalidSessionIdException() {
        Integer invalidSessionId = 0;
        assertThrows(InvalidSessionIdException.class, () -> new SessionId(invalidSessionId));
    }

    @Test
    public void constructor_invalidSessionId_throwsIllegalArgumentException() {
        String invalidSessionId = "";
        assertThrows(IllegalArgumentException.class, () -> new SessionId(invalidSessionId));
    }

    @Test
    public void isValidSessionId() {
        // null session ID
        assertThrows(NullPointerException.class, () -> SessionId.isValidSessionId(null));

        // invalid session ID
        assertFalse(SessionId.isValidSessionId("")); // empty string
        assertFalse(SessionId.isValidSessionId(" ")); // spaces only
        assertFalse(SessionId.isValidSessionId("[]")); // only non-alphanumeric characters
        assertFalse(SessionId.isValidSessionId("john*")); // contains non-alphanumeric characters

        // valid session ID
        assertTrue(SessionId.isValidSessionId("c/1")); // standard session ID with integer
        assertTrue(SessionId.isValidSessionId("c/1000")); //large session ID
    }

    @Test
    public void equals() {
        final SessionId firstSessionId = new SessionId("c/1");
        final SessionId secondSessionId = new SessionId("c/2");

        // same values -> returns true
        final SessionId firstSessionIdCopy = new SessionId("c/1");
        assertTrue(firstSessionId.equals(firstSessionIdCopy));

        // same object -> returns true
        assertTrue(firstSessionId.equals(firstSessionId));

        // null -> returns false
        assertFalse(firstSessionId.equals(null));

        // different types -> returns false
        assertFalse(firstSessionId.equals(0));

        // different values -> returns false
        assertFalse(firstSessionId.equals(secondSessionId));
    }
}
