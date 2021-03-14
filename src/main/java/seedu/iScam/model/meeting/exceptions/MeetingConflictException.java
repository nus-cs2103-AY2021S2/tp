package seedu.iScam.model.meeting.exceptions;

public class MeetingConflictException extends RuntimeException {
    public MeetingConflictException() {
        super("Meeting's date or time is in conflict with other Meetings");
    }
}
