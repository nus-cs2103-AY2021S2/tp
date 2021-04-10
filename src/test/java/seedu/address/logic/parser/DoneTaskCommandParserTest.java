package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INDEXES_LIST_ONE;
import static seedu.address.logic.commands.CommandTestUtil.INDEX_LIST_ONE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DoneTaskCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the UndoneTaskCommandParser code. For example, inputs "1" and "1 abc" take the
 * same path through the UndoneTaskCommandParser, and therefore we test only one of them.
 */
public class DoneTaskCommandParserTest {
    private DoneTaskCommandParser parser = new DoneTaskCommandParser();

    @Test
    public void parse_validArg_returnsDoneTaskCommand() {
        assertParseSuccess(parser, "1", new DoneTaskCommand(INDEX_LIST_ONE));
    }

    @Test
    public void parse_validArgs_returnsDoneTaskCommand() {
        assertParseSuccess(parser, "1 2", new DoneTaskCommand(INDEXES_LIST_ONE));
    }

    @Test
    public void parse_duplicateArgs_throwsParseException() {
        assertParseFailure(parser, "1 1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DoneTaskCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArg_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DoneTaskCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_noArg_throwsParseException() {
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DoneTaskCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgNegativeIndex_throwsParseException() {
        assertParseFailure(parser, "-1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DoneTaskCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgOverflowedIntegerIndex_throwsParseException() {
        assertParseFailure(parser, "2147483648",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DoneTaskCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "1 a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DoneTaskCommand.MESSAGE_USAGE));
    }
}
