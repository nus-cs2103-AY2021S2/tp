package seedu.partyplanet.model;

import javafx.collections.ObservableList;
import seedu.partyplanet.model.event.Event;

/**
 * Unmodifiable view of an event book
 */
public interface ReadOnlyEventBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Event> getEventList();

}
