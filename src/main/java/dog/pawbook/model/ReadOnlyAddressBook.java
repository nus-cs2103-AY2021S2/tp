package dog.pawbook.model;

import dog.pawbook.model.owner.Owner;
import javafx.collections.ObservableList;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the owners list.
     * This list will not contain any duplicate owners.
     */
    ObservableList<Owner> getOwnerList();

}
