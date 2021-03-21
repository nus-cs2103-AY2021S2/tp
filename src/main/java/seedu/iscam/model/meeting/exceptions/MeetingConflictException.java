package seedu.iscam.model.meeting.exceptions;

/**
 * Signals that the operation will result in meetings with same date and time.
 */
public class MeetingConflictException extends RuntimeException {
    public MeetingConflictException() {
        super("Meeting's date or time is in conflict with other Meetings");
    }
}
