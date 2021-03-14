package seedu.iScam.model;

import javafx.collections.ObservableList;
import seedu.iScam.model.meeting.Meeting;

/**
 * Unmodifiable view of a meeting list
 */
public interface ReadOnlyMeetingList {

    /**
     * Returns an unmodifiable view of the meetings list.
     * This list will not contain any duplicate meetings.
     */
    ObservableList<Meeting> getMeetingList();
}
