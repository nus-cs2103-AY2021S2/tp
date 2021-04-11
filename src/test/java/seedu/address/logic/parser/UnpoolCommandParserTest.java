package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PASSENGER_DISPLAYED_INDEX;
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
        assertParseFailure(parser, "a", MESSAGE_INVALID_PASSENGER_DISPLAYED_INDEX);
    }
}
