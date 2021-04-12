package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_POOL_DISPLAYED_INDEX;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.UnpoolCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the UnpoolCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the UnpoolCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class UnpoolCommandParserTest {

    private UnpoolCommandParser parser = new UnpoolCommandParser();

    @Test
    public void parse_validArgs_returnsUnpoolCommand() {
        assertParseSuccess(parser, "1", new UnpoolCommand(INDEX_FIRST));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // given index is not a number
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                UnpoolCommand.MESSAGE_USAGE));

        //given index is a negative number
        assertParseFailure(parser, "-1", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                UnpoolCommand.MESSAGE_USAGE));

        //given index is a number greater than Integer.MAX_VALUE
        assertParseFailure(parser, "9999999999999999999", MESSAGE_INVALID_POOL_DISPLAYED_INDEX);
    }
}
