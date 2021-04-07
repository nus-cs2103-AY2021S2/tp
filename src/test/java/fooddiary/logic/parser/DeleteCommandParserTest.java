package fooddiary.logic.parser;

import static fooddiary.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static fooddiary.commons.core.Messages.MESSAGE_INVALID_ENTRY_INDEX_OUT_OF_BOUNDS;
import static fooddiary.logic.parser.CommandParserTestUtil.assertParseFailure;
import static fooddiary.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static fooddiary.testutil.TypicalIndexes.INDEX_FIRST_ENTRY;
import static fooddiary.testutil.TypicalIndexes.INDEX_MILLIONTH_ENTRY;

import org.junit.jupiter.api.Test;

import fooddiary.logic.commands.DeleteCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteCommandParserTest {

    private DeleteCommandParser parser = new DeleteCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "1", new DeleteCommand(INDEX_FIRST_ENTRY));
        assertParseSuccess(parser, "1000000", new DeleteCommand(INDEX_MILLIONTH_ENTRY));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "-1", MESSAGE_INVALID_ENTRY_INDEX_OUT_OF_BOUNDS);
        assertParseFailure(parser, "0", MESSAGE_INVALID_ENTRY_INDEX_OUT_OF_BOUNDS);
        assertParseFailure(parser, "1000001", MESSAGE_INVALID_ENTRY_INDEX_OUT_OF_BOUNDS);
    }
}
