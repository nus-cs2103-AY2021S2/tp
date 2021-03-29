package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.date.ImportantDate;

/**
 * Unmodifiable view of a dates book
 */

public interface ReadOnlyDatesBook {

    /**
     * Returns an unmodifiable view of the important dates list.
     * This list will not contain any duplicate important dates.
     */
    ObservableList<ImportantDate> getImportantDatesList();

}
