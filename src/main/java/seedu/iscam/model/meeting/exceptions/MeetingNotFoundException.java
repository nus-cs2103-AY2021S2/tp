package seedu.iscam.model.meeting.exceptions;

/**
 * Signals that the operation is unable to find the specified meeting.
 */
public class MeetingNotFoundException extends RuntimeException {
    public MeetingNotFoundException() {
        super("Desired Meeting cannot be found");
    }
}
