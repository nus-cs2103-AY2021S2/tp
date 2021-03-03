package seedu.address.model.session;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import seedu.address.model.person.Person;

public class Session {
    private static int sessionCount = 0;

    private String classId;
    private Subject subject;
    private Person tutor;
    private ArrayList<Person> students;
    private Day day;
    private LocalTime start;
    private LocalTime end;

    public Session(Subject subject, Person tutor, Day day, LocalTime start, LocalTime end) {
        sessionCount++;
        requireAllNonNull(subject, tutor, day, start, end);
        this.classId = "c_" + sessionCount;
        this.subject = subject;
        this.tutor = tutor;
        this.students = new ArrayList<>();
        this.day = day;
        this.start = start;
        this.end = end;
    }

    public String getClassId() {
        return classId;
    }

    public Subject getSubject() {
        return subject;
    }

    public Person tutor() {
        return tutor;
    }

    public ArrayList<Person> getStudents() {
        return students;
    }

    public Day getDay() {
        return day;
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }

    /**
     * Adds a student to the session
     * @param student The student to be added
     */
    public assignStudent(Person student) {
        requireAllNonNull(student);
        this.students.add(student);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(this.getClassId())
                .append("; Subject: ")
                .append(this.getSubject())
                .append("; Tutor: ")
                .append(this.getTutor().getName())
                .append("; Day: ")
                .append(this.getDay)
                .append("; Time: ")
                .append(this.getStart() + "-" + this.getEnd())
                .append("; Students: ")
                .append(this.getStudents().toString());
    }
}