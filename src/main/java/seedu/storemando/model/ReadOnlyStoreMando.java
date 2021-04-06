package seedu.storemando.model;

import javafx.collections.ObservableList;
import seedu.storemando.model.item.Item;
import seedu.storemando.model.item.Location;

/**
 * Unmodifiable view of an storemando
 */
public interface ReadOnlyStoreMando {

    /**
     * Returns an unmodifiable view of the items list.
     * This list will not contain any duplicate items.
     */
    ObservableList<Item> getItemList();

    /**
     * Returns an unmodifiable view of the locations list.
     * This list will not contain any duplicate locations.
     */
    ObservableList<Location> getLocationList();

}
