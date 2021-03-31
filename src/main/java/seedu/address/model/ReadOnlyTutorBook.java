package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.tutor.Tutor;

/**
 * Unmodifiable view of an tutor book
 */
public interface ReadOnlyTutorBook {

    /**
     * Returns an unmodifiable view of the tutor list.
     * This list will not contain any duplicate tutors.
     */
    ObservableList<Tutor> getTutorList();

}
