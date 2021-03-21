package seedu.iscam.model;

import javafx.collections.ObservableList;
import seedu.iscam.model.client.Client;
import seedu.iscam.model.meeting.Meeting;

/**
 * Unmodifiable view of an iscam book
 */
public interface ReadOnlyClientBook {

    /**
     * Returns an unmodifiable view of the clients list.
     * This list will not contain any duplicate clients.
     */
    ObservableList<Client> getClientList();

    ObservableList<Meeting> getMeetingList();
}
