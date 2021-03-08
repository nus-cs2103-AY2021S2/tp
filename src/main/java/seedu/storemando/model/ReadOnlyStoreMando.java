package seedu.storemando.model;

import javafx.collections.ObservableList;
import seedu.storemando.model.item.Item;

/**
 * Unmodifiable view of an storemando
 */
public interface ReadOnlyStoreMando {

    /**
     * Returns an unmodifiable view of the items list.
     * This list will not contain any duplicate items.
     */
    ObservableList<Item> getItemList();

}
