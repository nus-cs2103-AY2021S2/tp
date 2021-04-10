package seedu.address.model.meeting;

import javafx.collections.ObservableList;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyMeetingBook {

    /**
     * Returns an unmodifiable view of the meeting's list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Meeting> getMeetingList();

    /**
     * Returns a meeting by its name and start time.
     * Returns null if meeting not found.
     */
    public Meeting getMeetingByNameAndStartTime(MeetingName name, DateTime start);

}
