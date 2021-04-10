package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.MeetCommand.ADD_MEETING;
import static seedu.address.logic.commands.MeetCommand.CLEAR_MEETING;
import static seedu.address.logic.commands.MeetCommand.DELETE_MEETING;
import static seedu.address.logic.commands.MeetCommand.MEETING_EMPTY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INDEX_IS_WORD;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.MeetCommand;

public class MeetCommandParserTest {

    private static final String MEETING_DATE = "15.06.2021";
    private static final String MEETING_START = "15:00";
    private static final String MEETING_END = "18:00";
    private static final String MEETING_PLACE = "KENT RIDGE MRT";

    private MeetCommandParser parser = new MeetCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MeetCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArg_throwsParseException() {
        // missing index
        assertParseFailure(parser, "15.06.2021 15:00 18:00 KENT RIDGE MRT",
                String.format(MESSAGE_INDEX_IS_WORD));

        // missing place
        assertParseFailure(parser, "1 15.06.2021 15:00 18:00",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MeetCommand.MESSAGE_USAGE));

        // invalid index
        assertParseFailure(parser, "0 15.06.2021 15:00 18:00 KENT RIDGE MRT",
                String.format(MESSAGE_INVALID_INDEX));

        // invalid date
        assertParseFailure(parser, "1 15/06/2021 15:00 18:00 KENT RIDGE MRT",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MeetCommand.MESSAGE_INVALID_DATE));

        // invalid time
        assertParseFailure(parser, "1 15.06.2021 1500 1800 KENT RIDGE MRT",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MeetCommand.MESSAGE_INVALID_TIME));
    }

    @Test
    public void parse_validArgs_returnsMeetCommand() {
        // default add meeting
        assertParseSuccess(parser, "1 15.06.2021 15:00 18:00 KENT RIDGE MRT",
                new MeetCommand(INDEX_FIRST_PERSON, ADD_MEETING,
                        MEETING_DATE, MEETING_START, MEETING_END, MEETING_PLACE));

        // add meeting
        assertParseSuccess(parser, "1 -add 15.06.2021 15:00 18:00 KENT RIDGE MRT",
                new MeetCommand(INDEX_FIRST_PERSON, ADD_MEETING,
                        MEETING_DATE, MEETING_START, MEETING_END, MEETING_PLACE));

        // delete meeting
        assertParseSuccess(parser, "1 -delete 15.06.2021 15:00 18:00 KENT RIDGE MRT",
                new MeetCommand(INDEX_FIRST_PERSON, DELETE_MEETING,
                        MEETING_DATE, MEETING_START, MEETING_END, MEETING_PLACE));

        // clear meeting
        assertParseSuccess(parser, "1 -clear",
                new MeetCommand(INDEX_FIRST_PERSON, CLEAR_MEETING,
                        MEETING_EMPTY, MEETING_EMPTY, MEETING_EMPTY, MEETING_EMPTY));
    }
}
