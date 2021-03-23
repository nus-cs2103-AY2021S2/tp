package dog.pawbook.model;

import dog.pawbook.model.managedentity.Entity;
import javafx.collections.ObservableList;
import javafx.util.Pair;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the entity list.
     */
    ObservableList<Pair<Integer, Entity>> getEntityList();

}
