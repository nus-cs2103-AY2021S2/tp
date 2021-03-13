package seedu.address.model.meeting.exceptions;

/**
 * Signals that the operation is unable to find the specified person.
 */
public class MeetingNotFoundException extends RuntimeException {
    public MeetingNotFoundException() {
        super("Can't find the meeting in the UniqueMeetingList.");
    }
}
