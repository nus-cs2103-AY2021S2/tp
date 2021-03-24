package seedu.iscam.model;

import javafx.collections.ObservableList;
import seedu.iscam.model.meeting.Meeting;
import seedu.iscam.model.meeting.UniqueMeetingList;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class MeetingBook implements ReadOnlyMeetingBook {
    private UniqueMeetingList meetings;

    {
        meetings = new UniqueMeetingList();
    }

    public MeetingBook() {
    }

    public MeetingBook(ReadOnlyMeetingBook toBeCopied) {
        this();

    }

    public void setMeetings(List<Meeting> meetings) {
        this.meetings.setMeetings(meetings);
    }

    public void resetData(ReadOnlyMeetingBook newData) {
        requireNonNull(newData);
        setMeetings(newData.getMeetingList());
    }

    public boolean hasMeeting(Meeting meeting) {
        requireNonNull(meeting);
        return meetings.contains(meeting);
    }

    public void addMeeting(Meeting m) {
        meetings.add(m);
    }

    public void setMeeting(Meeting target, Meeting editedMeeting) {
        requireNonNull(editedMeeting);
        meetings.setMeeting(target, editedMeeting);
    }

    public void removeMeeting(Meeting key) {
        meetings.remove(key);
    }

    @Override
    public ObservableList<Meeting> getMeetingList() {
        return meetings.asUnmodifiableObservableList();
    }

    @Override
    public String toString() {
        return meetings.asUnmodifiableObservableList().size() + " meetings";
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof MeetingBook
                && meetings.equals(((MeetingBook) other).meetings));
    }

    @Override
    public int hashCode() {
        return meetings.hashCode();
    }
}
