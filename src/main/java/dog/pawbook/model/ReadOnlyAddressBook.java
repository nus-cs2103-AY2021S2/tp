package dog.pawbook.model;

import dog.pawbook.model.managedentity.Entity;
import javafx.collections.ObservableList;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook<T extends Entity> {

    /**
     * Returns an unmodifiable view of the owners list.
     * This list will not contain any duplicate owners.
     */
    ObservableList<T> getEntityList();

}
