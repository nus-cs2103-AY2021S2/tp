package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.UndoneTaskCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the UndoneTaskCommandParser code. For example, inputs "1" and "1 abc" take the
 * same path through the UndoneTaskCommandParser, and therefore we test only one of them.
 */
public class UndoneTaskCommandParserTest {
    private UndoneTaskCommandParser parser = new UndoneTaskCommandParser();

    @Test
    public void parse_validArg_returnsUndoneTaskCommand() {
        assertParseSuccess(parser, "1", new UndoneTaskCommand(INDEX_FIRST_TASK));
    }

    @Test
    @Disabled
    public void parse_invalidArg_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, UndoneTaskCommand.MESSAGE_USAGE));
    }

    @Test
    @Disabled
    public void parse_invalidArgNegativeIndex_throwsParseException() {
        assertParseFailure(parser, "-1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, UndoneTaskCommand.MESSAGE_USAGE));
    }
}
