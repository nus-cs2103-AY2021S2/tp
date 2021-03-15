package seedu.address.model.meeting;

import javafx.collections.ObservableList;
import seedu.address.model.meeting.Meeting;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyMeetingBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Meeting> getMeetingList();

}