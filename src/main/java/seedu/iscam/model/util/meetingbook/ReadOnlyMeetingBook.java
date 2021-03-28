package seedu.iscam.model.util.meetingbook;

import javafx.collections.ObservableList;
import seedu.iscam.model.meeting.Meeting;

/**
 * Unmodifiable view of an iscam book
 */
public interface ReadOnlyMeetingBook {

    /**
     * Returns an unmodifiable view of the meetings list.
     * This list will not contain any duplicate meetings.
     */
    ObservableList<Meeting> getMeetingList();
}
