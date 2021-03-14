package seedu.iScam.model;

import javafx.collections.ObservableList;
import seedu.iScam.model.client.Client;

/**
 * Unmodifiable view of an iScam book
 */
public interface ReadOnlyClientBook {

    /**
     * Returns an unmodifiable view of the clients list.
     * This list will not contain any duplicate clients.
     */
    ObservableList<Client> getClientList();

}
