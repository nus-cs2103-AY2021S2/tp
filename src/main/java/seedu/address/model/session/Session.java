package seedu.address.model.session;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Represents a Session in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Session {
    private static int sessionCount = 0;

    private final String classId;
    private final Day day;
    private final Timeslot timeslot;
    private final Subject subject;
    private final Set<Tag> tags = new HashSet<>();
    private final Person tutor = null;
    private final ArrayList<Person> students = new ArrayList<>();

    /**
     * Every field must be present and not null.
     */
    public Session(Day day, Timeslot timeslot, Subject subject, Set<Tag> tags) {
        sessionCount++;
        requireAllNonNull(day, timeslot, subject);
        this.classId = "c_" + sessionCount;
        this.day = day;
        this.timeslot = timeslot;
        this.subject = subject;
        this.tags.addAll(tags);
    }

    public String getClassId() {
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

    public Person getTutor() {
        return tutor;
    }

    public ArrayList<Person> getStudents() {
        return students;
    }

    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Adds a student to the session
     * @param student The student to be added
     */
    public void assignStudent(Person student) {
        requireAllNonNull(student);
        this.students.add(student);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(this.getClassId())
                .append("; Subject: ")
                .append(this.getSubject())
                .append("; Day: ")
                .append(this.getDay())
                .append("; Time: ")
                .append(this.getTimeslot().toString())
                .append("; Students: ")
                .append(this.getStudents().toString());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        return builder.toString();
    }
}
