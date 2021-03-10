package seedu.address.testutil;

import seedu.address.model.meeting.Meeting;

import static seedu.address.logic.commands.CommandTestUtil.VALID_MEETING_PRANK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEETING_STH;

public class TypicalMeetings {

    public static Meeting MEETING_STH = new Meeting(VALID_MEETING_STH);
    public static Meeting MEETING_PRANK = new Meeting(VALID_MEETING_PRANK);
}
