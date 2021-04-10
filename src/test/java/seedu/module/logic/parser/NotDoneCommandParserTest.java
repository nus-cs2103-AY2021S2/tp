package seedu.module.logic.parser;

import static seedu.module.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.module.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.module.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.module.testutil.TypicalIndexes.INDEX_FIRST_TASK;

import org.junit.jupiter.api.Test;

import seedu.module.logic.commands.NotDoneCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the NotDoneCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the NotDoneCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class NotDoneCommandParserTest {
    private NotDoneCommandParser parser = new NotDoneCommandParser();

    @Test
    public void parse_validArgs_returnsNotDoneCommand() {
        assertParseSuccess(parser, "1", new NotDoneCommand(INDEX_FIRST_TASK));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "-1", String.format(ParserUtil.MESSAGE_INVALID_INDEX));
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, NotDoneCommand.MESSAGE_USAGE));
    }
}
