package seedu.iscam.model;

import javafx.collections.ObservableList;
import seedu.iscam.model.client.Client;

/**
 * Unmodifiable view of an iscam book
 */
public interface ReadOnlyClientBook {

    /**
     * Returns an unmodifiable view of the clients list.
     * This list will not contain any duplicate clients.
     */
    ObservableList<Client> getClientList();
}
