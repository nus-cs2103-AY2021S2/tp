package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.MeetCommand.CHECK_CLASHES;
import static seedu.address.logic.commands.MeetCommand.DELETE_MEETING;
import static seedu.address.logic.commands.MeetCommand.IGNORE_CLASHES;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.MeetCommand;

public class MeetCommandParserTest {

    private static final String MEETING_PLACE = "KENT RIDGE MRT";
    private static final String MEETING_DATE = "15/06/2021";
    private static final String MEETING_TIME = "15:00";

    private MeetCommandParser parser = new MeetCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MeetCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArg_throwsParseException() {
        // missing index
        assertParseFailure(parser, "-ignore KENT RIDGE MRT;15/06/2021;15:00",
                String.format(MESSAGE_INVALID_INDEX));

        // missing action
        assertParseFailure(parser, "1 KENT RIDGE MRT;15/06/2021;15:00",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MeetCommand.MESSAGE_USAGE));

        // missing place
        assertParseFailure(parser, "1 -ignore",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MeetCommand.MESSAGE_USAGE));

        // invalid date
        assertParseFailure(parser, "1 -ignore KENT RIDGE MRT;15-06-2021;15:00",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MeetCommand.MESSAGE_USAGE));

        // invalid time
        assertParseFailure(parser, "1 -ignore KENT RIDGE MRT;15/06/2021;1500",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MeetCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsMeetCommand() {
        // check for clashes and add meeting
        assertParseSuccess(parser, "1 -ignore KENT RIDGE MRT;15/06/2021;15:00",
                new MeetCommand(INDEX_FIRST_PERSON, IGNORE_CLASHES,
                        MEETING_PLACE, MEETING_DATE, MEETING_TIME));

        // ignore clashes and add meeting
        assertParseSuccess(parser, "1 -check KENT RIDGE MRT;15/06/2021;15:00",
                new MeetCommand(INDEX_FIRST_PERSON, CHECK_CLASHES,
                        MEETING_PLACE, MEETING_DATE, MEETING_TIME));

        // delete meeting
        assertParseSuccess(parser, "1 -delete",
                new MeetCommand(INDEX_FIRST_PERSON, DELETE_MEETING, "", "", ""));

    }
}
