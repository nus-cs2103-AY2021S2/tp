package seedu.address.model.session;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalSessionIds.SESSION_ID_FIRST_CLASS;
import static seedu.address.testutil.TypicalSessionIds.SESSION_ID_SECOND_CLASS;
import static seedu.address.testutil.TypicalSessions.FIRST_SESSION;

import org.junit.jupiter.api.Test;

public class SessionIdPredicateTest {
    @Test
    public void equals() {
        SessionIdPredicate firstPredicate = new SessionIdPredicate(SESSION_ID_FIRST_CLASS);
        SessionIdPredicate secondPredicate = new SessionIdPredicate(SESSION_ID_SECOND_CLASS);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        SessionIdPredicate firstPredicateCopy = new SessionIdPredicate(SESSION_ID_FIRST_CLASS);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different session ID -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_sessionContainsMatchingSessionId_returnsTrue() {
        SessionIdPredicate predicate = new SessionIdPredicate(SESSION_ID_FIRST_CLASS);
        assertTrue(predicate.test(FIRST_SESSION));
    }

    @Test
    public void test_sessionContainsNonMatchingSessionId_returnsFalse() {
        SessionIdPredicate predicate = new SessionIdPredicate(new SessionId("c/0"));
        assertFalse(predicate.test(FIRST_SESSION));

        predicate = new SessionIdPredicate(SESSION_ID_SECOND_CLASS);
        assertFalse(predicate.test(FIRST_SESSION));
    }
}
