package seedu.address.model.assignment;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Set;

import seedu.address.model.person.PersonId;
import seedu.address.model.session.SessionId;

/**
 * Represents an Assignment of a person to a class.
 */
public class Assignment {
    private Set<PersonId> studentIds;
    private PersonId tutorId;
    private SessionId sessionId;

    /**
     * Public Constructor for An Assignment between student/tutors and sessions
     * @param studentIds
     * @param tutorId
     * @param sessionId
     */
    public Assignment(Set<PersonId> studentIds, PersonId tutorId, SessionId sessionId) {
        requireAllNonNull(sessionId);
        this.sessionId = sessionId;
        this.studentIds = studentIds;
        this.tutorId = tutorId;
    }

    public Set<PersonId> getStudentIds() {
        return studentIds;
    }

    public PersonId getTutorId() {
        return tutorId;
    }

    public SessionId getSessionId() {
        return sessionId;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();

        if (!getStudentIds().isEmpty()) {
            String students = getStudentIds().toString();
            String formattedStudents = students.substring(1, students.length() - 1);
            builder.append("Students: ")
                    .append(formattedStudents)
                    .append("; ");
        }

        if (getTutorId() != null) {
            builder.append("Tutor: ")
                    .append(getTutorId())
                    .append("; ");
        }

        builder.append("Session: ")
                .append(getSessionId())
                .append(";");
        return builder.toString();
    }
}
