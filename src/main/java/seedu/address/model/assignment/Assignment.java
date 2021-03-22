package seedu.address.model.assignment;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.model.person.PersonId;
import seedu.address.model.session.SessionId;


public class Assignment {
    private PersonId studentId;
    private PersonId tutorId;
    private SessionId sessionId;

    /**
     * Public Constructor for An Assignment between student/tutors and sessions
     * @param studentId
     * @param tutorId
     * @param sessionId
     */
    public Assignment(PersonId studentId, PersonId tutorId, SessionId sessionId) {
        requireAllNonNull(sessionId);
        this.sessionId = sessionId;
        this.studentId = studentId;
        this.tutorId = tutorId;
    }

    public PersonId getStudentId() {
        return studentId;
    }

    public void setStudentId(PersonId studentId) {
        this.studentId = studentId;
    }

    public PersonId getTutorId() {
        return tutorId;
    }

    public void setTutorId(PersonId tutorId) {
        this.tutorId = tutorId;
    }

    public SessionId getSessionId() {
        return sessionId;
    }

    public void setSessionId(SessionId sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getStudentId())
                .append("; StudentId: ")
                .append(getTutorId())
                .append("; TutorId: ")
                .append(getSessionId())
                .append("; SessionId: ");
        return builder.toString();
    }

}
