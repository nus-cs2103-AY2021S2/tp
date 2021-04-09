package seedu.address.testutil;

import seedu.address.model.session.SessionId;

/**
 * A utility class containing a list of {@code SessionId} objects to be used in tests.
 */
public class TypicalSessionIds {
    public static final SessionId SESSION_ID_FIRST_CLASS = SessionId.fromOneBased(1);
    public static final SessionId SESSION_ID_SECOND_CLASS = SessionId.fromOneBased(2);
    public static final SessionId SESSION_ID_INVALID_CLASS = SessionId.fromOneBased(8);
}
