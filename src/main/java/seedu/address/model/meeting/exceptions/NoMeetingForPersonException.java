package seedu.address.model.meeting.exceptions;

public class NoMeetingForPersonException extends RuntimeException {
    public NoMeetingForPersonException() {
        super("No meeting is scheduled for this person.");
    }
}
