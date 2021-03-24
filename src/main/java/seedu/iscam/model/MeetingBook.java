package seedu.iscam.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.iscam.model.meeting.Meeting;
import seedu.iscam.model.meeting.UniqueMeetingList;

/**
 * Wraps all meeting data at the iscam-book level
 * Duplicates are not allowed (by .isInConflict comparison)
 */
public class MeetingBook implements ReadOnlyMeetingBook {
    private UniqueMeetingList meetings;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        meetings = new UniqueMeetingList();
    }

    public MeetingBook() {
    }

    /**
     * Creates an MeetingBook using the meetings in the {@code toBeCopied}
     */
    public MeetingBook(ReadOnlyMeetingBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Replaces the contents of the meeting list with {@code meetings}.
     * {@code meetings} must not contain duplicate meetings.
     */
    public void setMeetings(List<Meeting> meetings) {
        this.meetings.setMeetings(meetings);
    }

    /**
     * Resets the existing data of this {@code MeetingBook} with {@code newData}.
     */
    public void resetData(ReadOnlyMeetingBook newData) {
        requireNonNull(newData);
        setMeetings(newData.getMeetingList());
    }

    /**
     * Returns true if a meeting with the same identity as {@code meeting} exists in the iscam book.
     */
    public boolean hasMeeting(Meeting meeting) {
        requireNonNull(meeting);
        return meetings.contains(meeting);
    }

    /**
     * Adds a meeting to the iscam book.
     * The meeting must not already exist in the iscam book.
     */
    public void addMeeting(Meeting m) {
        meetings.add(m);
    }

    /**
     * Replaces the given meeting {@code target} in the list with {@code editedMeeting}.
     * {@code target} must exist in the iscam book.
     * The identity of {@code editedMeeting} must not be the same as another existing meeting in the iscam book.
     */
    public void setMeeting(Meeting target, Meeting editedMeeting) {
        requireNonNull(editedMeeting);
        meetings.setMeeting(target, editedMeeting);
    }

    /**
     * Removes {@code key} from this {@code MeetingBook}.
     * {@code key} must exist in the iscam book.
     */
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
