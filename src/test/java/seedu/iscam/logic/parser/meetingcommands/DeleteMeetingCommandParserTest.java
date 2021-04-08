package seedu.iscam.logic.parser.meetingcommands;

import static seedu.iscam.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.iscam.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.iscam.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.iscam.testutil.TypicalIndexes.INDEX_FIRST_ITEM;

import org.junit.jupiter.api.Test;

import seedu.iscam.logic.commands.DeleteMeetingCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteMeetingCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteMeetingCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
class DeleteMeetingCommandParserTest {
    private DeleteMeetingCommandParser parser = new DeleteMeetingCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteMeetingCommand() {
        assertParseSuccess(parser, "1", new DeleteMeetingCommand(INDEX_FIRST_ITEM));
    }

    @Test
    public void parse_indexNotANumber_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_INDEX, DeleteMeetingCommand.MESSAGE_USAGE));
    }
}