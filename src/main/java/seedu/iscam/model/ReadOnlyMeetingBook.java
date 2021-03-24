package seedu.iscam.model;

import javafx.collections.ObservableList;
import seedu.iscam.model.meeting.Meeting;

public interface ReadOnlyMeetingBook {
    ObservableList<Meeting> getMeetingList();
}
