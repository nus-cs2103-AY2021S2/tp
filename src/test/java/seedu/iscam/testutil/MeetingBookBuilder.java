package seedu.iscam.testutil;

import seedu.iscam.model.MeetingBook;
import seedu.iscam.model.meeting.Meeting;

public class MeetingBookBuilder {

    private MeetingBook meetingBook;

    public MeetingBookBuilder() {
        meetingBook = new MeetingBook();
    }

    public MeetingBookBuilder(MeetingBook meetingBook) {
        this.meetingBook = meetingBook;
    }

    public MeetingBookBuilder withMeeting(Meeting meeting) {
        meetingBook.addMeeting(meeting);
        return this;
    }

    public MeetingBook build() {
        return meetingBook;
    }
}
