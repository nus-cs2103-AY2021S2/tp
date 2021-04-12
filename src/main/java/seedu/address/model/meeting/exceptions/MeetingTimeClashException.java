package seedu.address.model.meeting.exceptions;



public class MeetingTimeClashException extends RuntimeException {
    private static final String MEETING_CLASH_MESSAGE = "Already has a meeting inside the meeting list with clashing "
            + "time.";

    public MeetingTimeClashException() {
        super(String.format(MEETING_CLASH_MESSAGE));
    }
}
