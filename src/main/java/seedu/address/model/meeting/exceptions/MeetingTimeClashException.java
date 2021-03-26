package seedu.address.model.meeting.exceptions;

import seedu.address.model.meeting.Meeting;


public class MeetingTimeClashException extends RuntimeException {
    private static final String MEETING_CLASH_MESSAGE = "Already has a meeting inside the meeting list with clashing"
            + "time.\n\t Meeting that clashes is %s";

    public MeetingTimeClashException(Meeting m) {
        super(String.format(MEETING_CLASH_MESSAGE, m));
    }
}
