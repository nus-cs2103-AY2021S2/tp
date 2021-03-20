package seedu.address.model.tuition;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.session.Session;
import seedu.address.model.student.Student;

/**
 * Represents a {@code Student}-{@code Session} linker as a {@code Tuition} in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Tuition {

    // Data fields
    private Student student;
    private Session session;

    /**
     * Every field must be present and not null.
     */
    public Tuition(Student student, Session session) {
        requireAllNonNull(student, session);
        this.student = student;
        this.session = session;
    }

    public Student getStudent() {
        return this.student;
    }

    public Session getSession() {
        return this.session;
    }

    /**
     * Returns true if both students have the same identity and data fields.
     * This defines a stronger notion of equality between two students.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Tuition)) {
            return false;
        }

        Tuition otherTuition = (Tuition) other;
        return otherTuition.getStudent().equals(getStudent())
                && otherTuition.getSession().equals(getSession());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(student, session);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(student.getName())
                .append("; Session Date: ")
                .append(session.getSessionDate())
                .append("; Duration: ")
                .append(session.getDuration())
                .append("; Subject: ")
                .append(session.getSubject())
                .append("; Fee: ")
                .append(session.getFee());

        return builder.toString();
    }

}
