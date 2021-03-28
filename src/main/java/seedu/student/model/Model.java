package seedu.student.model;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.student.commons.core.GuiSettings;
import seedu.student.model.appointment.Appointment;
import seedu.student.model.appointment.SameDateAppointmentList;
import seedu.student.model.student.MatriculationNumber;
import seedu.student.model.student.Student;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Student> PREDICATE_SHOW_ALL_STUDENTS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<SameDateAppointmentList> PREDICATE_SHOW_ALL_APPOINTMENTS = unused -> true;
    /**
     *
     *
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' student book file path.
     */
    Path getStudentBookFilePath();

    /**
     * Sets the user prefs' student book file path.
     */
    void setStudentBookFilePath(Path studentBookFilePath);

    /**
     * Replaces student book data with the data in {@code studentBook}.
     */
    void setStudentBook(ReadOnlyStudentBook studentBook);

    /** Returns the studentBook */
    ReadOnlyStudentBook getStudentBook();

    /** Returns a list of students in the studentBook */
    ObservableList<Student> getStudentList();

    /** Returns true if matriculation number exists in the records. */
    boolean isExistingMatricNumber(MatriculationNumber matricNum);

    /**
     * Returns true if a student with the same identity as {@code student} exists in the records.
     */
    boolean hasStudent(Student student);

    /**
     * Deletes the given student.
     * The student must exist in the records.
     */
    void deleteStudent(Student target);

    /**
     * Adds the given student.
     * {@code student} must not already exist in the records.
     */
    void addStudent(Student student);

    /**
     * Replaces the given student {@code target} with {@code editedStudent}.
     * {@code target} must exist in the student book.
     * The student identity of {@code editedStudent} must not be the same as another existing student in
     * the records.
     */
    void setStudent(Student target, Student editedStudent);

    /** Returns an unmodifiable view of the filtered student list */
    ObservableList<Student> getFilteredStudentList();

    /**
     * @param matriculationNumber Matriculation number of the student you wish to get.
     * @return The student you want, null if the student does not exist in the system.
     */
    Student getStudent(MatriculationNumber matriculationNumber);

    /**
     * @param matriculationNumber Matriculation number of the student who's appointment you wish to get.
     * @return The appointment you want, null if the appointment does not exist in the system.
     */
    Appointment getAppointment(MatriculationNumber matriculationNumber);

    /**
     * Updates the filter of the filtered student list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredStudentList(Predicate<Student> predicate);

    void updateFilteredAppointmentList(Predicate<SameDateAppointmentList> predicate);

    boolean hasAppointment(Appointment appointment);

    boolean hasOverlappingAppointment(Appointment appointment);

    void addAppointment(Appointment appointment);

    void deleteAppointment(Appointment appointment);

    ObservableList<SameDateAppointmentList> getFilteredAppointmentList();

    List<Appointment> getAppointmentList();
}
