package seedu.iscam.logic.parser.meetingcommands;

import static seedu.iscam.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.iscam.logic.commands.CommandTestUtil.DATETIME_DESC_CLEO;
import static seedu.iscam.logic.commands.CommandTestUtil.VALID_DATETIME_CLEO;
import static seedu.iscam.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.iscam.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.iscam.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.iscam.testutil.TypicalIndexes.INDEX_FIRST_ITEM;

import org.junit.jupiter.api.Test;

import seedu.iscam.logic.commands.RescheduleMeetingCommand;
import seedu.iscam.model.meeting.DateTime;

class RescheduleMeetingCommandParserTest {
    private RescheduleMeetingCommandParser parser = new RescheduleMeetingCommandParser();

    @Test
    public void parse_validArgs_returnsRescheduleMeetingCommand() {
        assertParseSuccess(parser, "1" + DATETIME_DESC_CLEO,
                new RescheduleMeetingCommand(INDEX_FIRST_ITEM, new DateTime(VALID_DATETIME_CLEO)));
    }

    @Test
    public void parse_missingArg_failure() {
        assertParseFailure(parser, "1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, RescheduleMeetingCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_indexNotANumber_throwsParseException() {
        assertParseFailure(parser, "a" + DATETIME_DESC_CLEO,
                String.format(MESSAGE_INVALID_INDEX, RescheduleMeetingCommand.MESSAGE_USAGE));
    }
}
