package seedu.student.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import seedu.student.logic.commands.exceptions.CommandException;
import seedu.student.model.appointment.Appointment;
import seedu.student.model.appointment.SameDateAppointmentList;
import seedu.student.model.appointment.UniqueAppointmentList;
import seedu.student.model.student.MatriculationNumber;
import seedu.student.model.student.Student;
import seedu.student.model.student.UniqueStudentList;

/**
 * Wraps all data at the student-book level
 * Duplicates are not allowed (by .isSameStudent comparison)
 */
public class StudentBook implements ReadOnlyStudentBook {

    private final UniqueStudentList students;
    private final UniqueAppointmentList appointments;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        students = new UniqueStudentList();
        appointments = new UniqueAppointmentList();
    }

    public StudentBook() {}

    /**
     * Creates an StudentBook using the Students in the {@code toBeCopied}
     */
    public StudentBook(ReadOnlyStudentBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the student list with {@code students}.
     * {@code students} must not contain duplicate students.
     */
    public void setStudents(List<Student> students) {
        this.students.setStudents(students);
    }

    public void setAppointments(List<SameDateAppointmentList> appointments) {
        this.appointments.setAppointments(appointments);
    }

    /**
     * Resets the existing data of this {@code StudentBook} with {@code newData}.
     */
    public void resetData(ReadOnlyStudentBook newData) {
        requireNonNull(newData);

        setStudents(newData.getStudentList());
        setAppointments(newData.getAppointmentList());
    }

    //// student-level operations

    /**
     * Returns true if a student with the same identity as {@code student} exists in the student book.
     */
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return students.contains(student);
    }

    /**
     * Adds a student to the student book.
     * The student must not already exist in the student book.
     */
    public void addStudent(Student p) {
        students.add(p);
    }

    /**
     * Replaces the given student {@code target} in the list with {@code editedStudent}.
     * {@code target} must exist in the student book.
     * The student identity of {@code editedStudent} must not be the same as another existing student in
     * the student book.
     */
    public void setStudent(Student target, Student editedStudent) {
        requireNonNull(editedStudent);

        students.setStudent(target, editedStudent);
    }

    /**
     * Removes {@code key} from this {@code StudentBook}.
     * {@code key} must exist in the student book.
     */
    public void removeStudent(Student key) {
        students.remove(key);
    }


    //// appointment-level operations

    /**
     * Returns true if an appointment with the same identity as {@code appointment} exists in the student book.
     */
    public boolean hasAppointment(Appointment appointment) {
        requireNonNull(appointment);
        return appointments.contains(appointment);
    }

    /**
     * Returns true if an appointment with overlapping time with {@code appointment} exists in the student book.
     */
    public boolean hasOverlappingAppointment(Appointment appointment) {
        requireNonNull(appointment);
        return appointments.hasOverlappingAppointment(appointment);
    }

    /**
     * Adds an appointment to the student book.
     * The appointment must not already exist in the student book.
     */
    public void addAppointment(Appointment a) {
        appointments.add(a);
    }

    public void removeAppointment(Appointment key) {
        appointments.remove(key);
    }

    /**
     * Replaces the given appointment {@code target} in the list with {@code editedAppointment}.
     * {@code target} must exist in the student book.
     * The appointment identity of {@code editedAppointment} must not be the same as another existing
     * appointment in the student book.
     */
    public void setAppointment(Appointment target, Appointment editedAppointment) throws CommandException {
        requireNonNull(editedAppointment);
        appointments.setAppointment(target, editedAppointment);
    }

    //// util methods

    @Override
    public String toString() {
        return students.asUnmodifiableObservableList().size() + " students";
        // TODO: refine later
    }

    @Override
    public ObservableList<Student> getStudentList() {
        return students.asUnmodifiableObservableList();
    }

    @Override
    public boolean isExistingMatricNumber(MatriculationNumber matriculationNumber) {
        return students.asUnmodifiableObservableList().stream()
                .anyMatch(student -> student.getMatriculationNumber().equals(matriculationNumber));
    }

    @Override
    public Student getStudent(MatriculationNumber matricNum) {
        return students.asUnmodifiableObservableList().stream()
                .filter(student -> student.getMatriculationNumber().equals(matricNum))
                .findAny()
                .orElse(null);
    }

    @Override
    public ObservableList<SameDateAppointmentList> getAppointmentList() {
        return appointments.asUnmodifiableObservableList();
    }

    @Override
    public Appointment getAppointment(MatriculationNumber matricNum) {
        return getFlatAppointmentList().stream()
                .filter(appt -> appt.getMatriculationNumber().equals(matricNum))
                .findAny()
                .orElse(null);
    }

    @Override
    public List<Appointment> getFlatAppointmentList() {
        return appointments.asUnmodifiableObservableList().stream()
                .flatMap(list -> list.asUnmodifiableObservableList().stream()).collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StudentBook // instanceof handles nulls
                && students.equals(((StudentBook) other).students))
                && appointments.equals(((StudentBook) other).appointments);
    }

    @Override
    public int hashCode() {
        return students.hashCode();
    }
}
