package seedu.address.model.meeting.exceptions;

public class MeetingClashException extends RuntimeException {
    public MeetingClashException() {
        super("There is a meeting");
    }
}