package fooddiary.model;

import fooddiary.model.entry.Entry;
import javafx.collections.ObservableList;

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
