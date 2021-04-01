package seedu.address.model.session;

import java.util.List;
import java.util.function.Predicate;

public class SessionStudentPredicate implements Predicate<Session> {
    private final List<SessionId> sessionIdList;

    public SessionStudentPredicate(List<SessionId> sessionIdList) {
        this.sessionIdList = sessionIdList;
    }

    @Override
    public boolean test(Session session) {
        return sessionIdList.contains(session.getClassId());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SessionStudentPredicate // instanceof handles nulls
                && sessionIdList.equals(((SessionStudentPredicate) other).sessionIdList)); // state check
    }
}
