package fooddiary.model;

import javafx.collections.ObservableList;
import fooddiary.model.entry.Entry;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyFoodDiary {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Entry> getEntryList();

}
