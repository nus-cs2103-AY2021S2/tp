package seedu.address.model.meeting;


import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javafx.collections.ObservableList;

public class MeetingBook implements ReadOnlyMeetingBook {
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

    public MeetingBook() {}

    /**
     * Creates an MeetingBook using the meetings in the {@code toBeCopied}
     */
    public MeetingBook(ReadOnlyMeetingBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

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

    //// meeting-level operations

    /**
     * Returns true if a meeting with the same identity as {@code meeting} exists in the meeting book.
     */
    public boolean hasMeeting(Meeting meeting) {
        requireNonNull(meeting);
        return meetings.contains(meeting);
    }

    /**
     * Adds a meeting to the meeting book.
     * The meeting must not already exist in the meeting book.
     * The meeting must not clash with any meeting in the meetingBook.
     */
    public void addMeeting(Meeting m) {
        meetings.add(m);
    }

    /**
     * Replaces the given meeting {@code target} in the list with {@code editedMeeting}.
     * {@code target} must exist in the meeting book.
     * The meeting identity of {@code editedMeeting} must not be the same as another
     * existing meeting in the meeting book.
     */
    public void setMeeting(Meeting target, Meeting editedMeeting) {
        requireNonNull(editedMeeting);
        meetings.setMeeting(target, editedMeeting);
    }

    /**
     * Removes {@code key} from this {@code MeetingBook}.
     * {@code key} must exist in the meeting book.
     */
    public void removeMeeting(Meeting key) {
        meetings.remove(key);
    }

    // ===================== Clashing meetings checks =================================================

    /**
     * Checks if there is a clash in Meeting Times within the meeting book.
     */
    public boolean clashes(Meeting toCheck) {
        return meetings.clashes(toCheck);
    }

    /**
     * Gets a list of meetings that overlap with this meeting.
     */
    public List<Meeting> getClashes(Meeting toCheck) {
        return meetings.getClashes(toCheck);
    }

    /**
     * Gets the meeting ( if any ) happening at this point in time.
     */

    public Optional<Meeting> getMeetingAtInstant(LocalDateTime localDateTime) {
        return meetings.getMeetingAtInstant(localDateTime);
    }


    //// ================= Util methods ==============================================
    @Override
    public String toString() {
        return meetings.asUnmodifiableObservableList().size() + " meetings";
        // TODO: refine later
    }

    @Override
    public ObservableList<Meeting> getMeetingList() {
        return meetings.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MeetingBook // instanceof handles nulls
                && meetings.equals(((MeetingBook) other).meetings));
    }

    @Override
    public int hashCode() {
        return meetings.hashCode();
    }
}

