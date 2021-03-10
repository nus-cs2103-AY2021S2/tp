package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MEETING_DESC;
import static seedu.address.logic.commands.CommandTestUtil.MEETING_DESC_PRANK;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalMeetings.MEETING_PRANK;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ScheduleCommand;
import seedu.address.model.meeting.Meeting;

class ScheduleCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, ScheduleCommand.MESSAGE_USAGE);
    private final ScheduleCommandParser parser = new ScheduleCommandParser();

    @Test
    public void parse_validArgs_returnsScheduleCommand() {
        assertParseSuccess(parser, "1 " + MEETING_DESC_PRANK, new ScheduleCommand(INDEX_FIRST_PERSON, MEETING_PRANK));
    }

    @Test
    public void parse_invalidPreamble_failure() {
        assertParseFailure(parser, "-5 " + MEETING_DESC_PRANK, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "0" + MEETING_DESC_PRANK, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "a" + MEETING_DESC_PRANK, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_MEETING_DESC, Meeting.MESSAGE_CONSTRAINTS); // invalid meeting
    }
}
