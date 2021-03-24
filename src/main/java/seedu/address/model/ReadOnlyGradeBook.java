package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.appointment.Appointment;

/**
 * Unmodifiable view of a grade book
 */
public interface ReadOnlyGradeBook {

    /**
     * Returns an unmodifiable view of the grade list.
     * This list will not contain any duplicate grades.
     */
    ObservableList<Appointment> getGradeList();
}
