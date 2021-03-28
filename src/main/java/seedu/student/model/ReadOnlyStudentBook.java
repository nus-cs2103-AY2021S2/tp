package seedu.student.model;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.student.model.appointment.Appointment;
import seedu.student.model.appointment.SameDateAppointmentList;
import seedu.student.model.student.MatriculationNumber;
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

    boolean isExistingMatricNumber(MatriculationNumber matricNum);

    Student getStudent(MatriculationNumber matriculationNumber);

    ObservableList<SameDateAppointmentList> getAppointmentList();

    Appointment getAppointment(MatriculationNumber matricNum);

    List<Appointment> getFlatAppointmentList();

}
