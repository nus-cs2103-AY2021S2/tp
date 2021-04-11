package seedu.address.model.session;

import java.util.function.Predicate;

public class SessionIdPredicate implements Predicate<Session> {
    private final SessionId sessionId;

    public SessionIdPredicate(SessionId sessionId) {
        this.sessionId = sessionId;
    }

    public SessionId getSessionId() {
        return sessionId;
    }

    @Override
    public boolean test(Session other) {
        return other.getClassId().equals(sessionId);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SessionIdPredicate // instanceof handles nulls
                && sessionId.equals(((SessionIdPredicate) other).getSessionId())); // state check
    }
}
