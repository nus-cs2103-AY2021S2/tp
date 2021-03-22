package seedu.address.model.session;

import static java.util.Objects.requireNonNull;

public class SessionId {

    public static final String MESSAGE_CONSTRAINTS =
            "Session ID should only be c/[session ID], and it should not be blank";
    public static final String VALIDATION_REGEX = "[c][/]\\d{1,}";

    private final String sessionId;

    /**
     * Constructs a {@code SessionId} from {Integer id}.
     *
     * @param id A valid ID.
     */
    public SessionId(Integer id) {
        requireNonNull(id);
        sessionId = "c/" + id;
    }

    /**
     * Constructs a {@code SessionId} from {String id}.
     *
     * @param id A valid ID.
     */
    public SessionId(String id) {
        requireNonNull(id);
        sessionId = id;
    }

    public String getSessionId() {
        return sessionId;
    }

    /**
     * Returns true if a given string is a valid session ID.
     */
    public static boolean isValidSessionId(String test) {
        if (test.matches(VALIDATION_REGEX)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return sessionId;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SessionId // instanceof handles nulls
                && sessionId.equals(((SessionId) other).getSessionId())); // state check
    }

    @Override
    public int hashCode() {
        return sessionId.hashCode();
    }
}
