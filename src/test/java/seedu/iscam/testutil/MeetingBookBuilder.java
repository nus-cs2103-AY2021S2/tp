package seedu.iscam.testutil;

import seedu.iscam.model.meeting.Meeting;
import seedu.iscam.model.util.meetingbook.MeetingBook;

/**
 * A utility class to help with building MeetingBook objects.
 * Example usage: <br>
 *     {@code MeetingBook mb = new MeetingBookBuilder().withMeeting(new Meeting(...)).build();}
 */
public class MeetingBookBuilder {

    private MeetingBook meetingBook;

    public MeetingBookBuilder() {
        meetingBook = new MeetingBook();
    }

    public MeetingBookBuilder(MeetingBook meetingBook) {
        this.meetingBook = meetingBook;
    }

    /**
     * Adds a new {@code Meeting} to the {@code MeetingBook} that we are building.
     */
    public MeetingBookBuilder withMeeting(Meeting meeting) {
        meetingBook.addMeeting(meeting);
        return this;
    }

    public MeetingBook build() {
        return meetingBook;
    }
}
