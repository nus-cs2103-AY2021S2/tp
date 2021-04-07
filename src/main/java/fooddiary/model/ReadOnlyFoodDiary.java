package fooddiary.model;

import fooddiary.model.entry.Entry;
import javafx.collections.ObservableList;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyFoodDiary {

    /**
     * Returns an unmodifiable view of the food diary list.
     * This list will not contain any duplicate food diary entries.
     */
    ObservableList<Entry> getEntryList();

}
