package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.residence.Residence;

/**
 * Unmodifiable view of a residence tracker.
 */
public interface ReadOnlyResidenceTracker {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     * @return
     */
    ObservableList<Residence> getResidenceList();

}
