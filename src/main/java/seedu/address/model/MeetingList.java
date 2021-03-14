package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.lang.reflect.Method;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.UniqueMeetingList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameClient comparison)
 */
public class MeetingList implements ReadOnlyMeetingList {

    private final UniqueMeetingList meetings;

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

    public MeetingList() {}

    /**
     * Creates a Meeting list using the Meetings in the {@code toBeCopied}
     */
    public MeetingList(ReadOnlyMeetingList toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the meetings list with {@code meetings}.
     * {@code meetings} must not contain duplicate meetings.
     */
    public void setMeetings(List<Meeting> meetings) {
        this.meetings.setMeetings(meetings);
    }

    /**
     * Resets the existing data of this {@code MeetingList} with {@code newData}.
     */
    public void resetData(ReadOnlyMeetingList newData) {
        requireNonNull(newData);

        setMeetings(newData.getMeetingList());
    }

    //// client-level operations

    /**
     * Returns true if a meeting with the same identity as {@code meeting} exists in the meeting list.
     */
    public boolean hasMeeting(Meeting meeting) {
        requireNonNull(meeting);
        return meetings.contains(meeting);
    }

    /**
     * Adds a meeting to the meeting list.
     * The meeting must not already exist in the meeting list.
     */
    public void addMeeting(Meeting p) {
        meetings.add(p);
    }

    /**
     * Replaces the given meeting {@code target} in the list with {@code editedMeeting}.
     * {@code target} must exist in the address book.
     * The meeting identity of {@code editedMeeting} must not be the same as another existing meeting in the meeting list.
     */
    public void setMeetings(Meeting target, Meeting editedMeeting) {
        requireNonNull(editedMeeting);

        meetings.setMeeting(target, editedMeeting);
    }

    /**
     * Removes {@code key} from this {@code Meeting List}.
     * {@code key} must exist in the meeting list.
     */
    public void removeMeeting(Meeting key) {
        meetings.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return meetings.asUnmodifiableObservableList().size() + " clients";
        // TODO: refine later
    }

    @Override
    public ObservableList<Meeting> getMeetingList() {
        return meetings.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MeetingList // instanceof handles nulls
                && meetings.equals(((MeetingList) other).meetings));
    }

    @Override
    public int hashCode() {
        return meetings.hashCode();
    }
}
