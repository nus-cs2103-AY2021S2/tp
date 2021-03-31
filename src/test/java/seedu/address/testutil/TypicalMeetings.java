package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_MEETING_CLASH_PRANK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEETING_PRANK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEETING_STH;

import seedu.address.model.meeting.Meeting;

public class TypicalMeetings {

    public static final Meeting MEETING_STH = new Meeting(VALID_MEETING_STH);
    public static final Meeting MEETING_PRANK = new Meeting(VALID_MEETING_PRANK);
    public static final Meeting MEETING_CLASH_PRANK = new Meeting(VALID_MEETING_CLASH_PRANK);
}
