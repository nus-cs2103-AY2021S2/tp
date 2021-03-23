package seedu.student.model;

import javafx.collections.ObservableList;
import seedu.student.model.appointment.Appointment;
import seedu.student.model.student.Student;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyStudentBook {

    /**
     * Returns an unmodifiable view of the students list.
     * This list will not contain any duplicate students.
     */
    ObservableList<Student> getStudentList();

    ObservableList<Appointment> getAppointmentList();
}
