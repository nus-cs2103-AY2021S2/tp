package seedu.address.model.session;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.model.person.PersonId;
import seedu.address.model.tag.Tag;

/**
 * Represents a Session in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Session {
    public static final String MESSAGE_CONSTRAINTS =
            "Session ID should only be c/[session ID], and it should not be blank";
    private static int sessionCount = 0;


    private final SessionId classId;
    private final Day day;
    private final Timeslot timeslot;
    private final Subject subject;
    private final Set<Tag> tags = new HashSet<>();
    private PersonId tutor = new PersonId("");
    private final List<PersonId> students = new ArrayList<>();

    /**
     * Every field must be present and not null.
     */
    public Session(Day day, Timeslot timeslot, Subject subject, Set<Tag> tags) {
        sessionCount++;
        requireAllNonNull(day, timeslot, subject);
        this.classId = new SessionId(sessionCount);
        this.day = day;
        this.timeslot = timeslot;
        this.subject = subject;
        this.tags.addAll(tags);
    }

    /**
     * Every field must be present and not null.
     * @param classId
     * @param day
     * @param timeslot
     * @param subject
     * @param tags
     */
    public Session(SessionId classId, Day day, Timeslot timeslot, Subject subject, Set<Tag> tags) {
        this.classId = classId;
        this.day = day;
        this.timeslot = timeslot;
        this.subject = subject;
        this.tags.addAll(tags);
    }

    public static void setSessionCount(String storedSessionCount) {
        sessionCount = Integer.valueOf(storedSessionCount);
    }

    public static String getSessionCount() {
        return String.valueOf(sessionCount);
    }
    public SessionId getClassId() {
        return classId;
    }

    public Day getDay() {
        return day;
    }

    public Timeslot getTimeslot() {
        return timeslot;
    }

    public Subject getSubject() {
        return subject;
    }

    public PersonId getTutor() {
        return tutor;
    }

    public List<PersonId> getStudents() {
        return students;
    }

    public boolean emptyStudentList() {
        return this.students.size() == 0;
    }

    public boolean emptyTutor() {
        return this.tutor.equals(new PersonId(""));
    }

    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Adds a student to the session
     * @param student The student to be added
     */
    public void assignStudent(PersonId student) {
        requireAllNonNull(student);
        this.students.add(student);
    }

    public void assignTutor(PersonId tutor) {
        this.tutor = tutor;
    }

    /**
     * Removes a student from the session
     * @param student The student to be removed
     */
    public void unassignStudent(PersonId student) {
        requireAllNonNull(student);
        this.students.remove(student);
    }

    public void unassignTutor() {
        this.tutor = new PersonId("");
    }

    /**
     * Returns true if both sessions have the same ID.
     * This defines a weaker notion of equality between two sessions.
     */
    public boolean isSameSession(Session otherSession) {
        if (otherSession == this) {
            return true;
        }

        return otherSession != null
                && otherSession.getClassId().equals(getClassId());
    }

    public boolean hasTutor() {
        return !this.tutor.equals(new PersonId(""));
    }

    public void setTutor(PersonId tutor) {
        this.tutor = tutor;
    }

    public boolean hasStudent() {
        return !this.students.isEmpty();
    }

    public void setStudents(List<PersonId> students) {
        for (PersonId student :students) {
            this.students.add(student);
        }
    }

    private boolean isTutor(PersonId person) {
        return this.tutor.equals(person.getPersonId());
    }

    private boolean checkEnrollement(PersonId person) {
        for (PersonId personId : this.students) {
            if (personId.equals(person.getPersonId())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if current session clashes with another given session
     * @param other The session to compare
     * @return true if clashing, false otherwise
     */
    public boolean isClashingWith(Session other) {
        boolean sameDay = this.day.equals(other.day);
        boolean timeslotClashing = this.timeslot.isClashingWith(other.timeslot);
        return sameDay && timeslotClashing;
    }

    /**
     * Returns true if both sessions have the same session ID and data fields.
     * This defines a stronger notion of equality between two sessions.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Session)) {
            return false;
        }

        Session otherSession = (Session) other;
        return otherSession.getClassId().equals(getClassId())
                && otherSession.getDay().equals(getDay())
                && otherSession.getTimeslot().equals(getTimeslot())
                && otherSession.getSubject().equals(getSubject())
                && otherSession.getTags().equals(getTags())
                && otherSession.getTutor().equals(getTutor())
                && otherSession.getStudents().equals(getStudents());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(" Session ID: ")
                .append(this.getClassId())
                .append("; Subject: ")
                .append(this.getSubject())
                .append("; Day: ")
                .append(this.getDay())
                .append("; Time: ")
                .append(this.getTimeslot().toString());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        return builder.toString();
    }
}
