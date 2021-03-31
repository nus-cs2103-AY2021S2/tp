package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TASK;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SnoozeCommand;

public class SnoozeCommandParserTest {

    private SnoozeCommandParser parser = new SnoozeCommandParser();

    @Test
    public void parse_missingIndex_failure() {
        assertParseFailure(parser, " ", SnoozeCommandParser.MESSAGE_INVALID_ARGUMENT);
    }

    @Test
    public void parse_tooManyParameters_failure() {
        assertParseFailure(parser, "2 2 2", SnoozeCommandParser.MESSAGE_INVALID_ARGUMENT);
    }

    @Test
    public void parse_indexOnly_success() {
        SnoozeCommand expectedCommand = new SnoozeCommand(INDEX_SECOND_TASK, 1);

        assertParseSuccess(parser, "2", expectedCommand);
    }

    @Test
    public void parse_indexAndDays_success() {
        SnoozeCommand expectedCommand = new SnoozeCommand(INDEX_SECOND_TASK, 3);

        assertParseSuccess(parser, "2 3", expectedCommand);
    }

    @Test
    public void parse_validIndexOnlyWithTrailingWhitespace_success() {
        SnoozeCommand expectedCommand = new SnoozeCommand(INDEX_SECOND_TASK, 1);

        assertParseSuccess(parser, "2   ", expectedCommand);
        assertParseSuccess(parser, "  2", expectedCommand);
        assertParseSuccess(parser, "  2   ", expectedCommand);
    }

    @Test
    public void parse_validIndexAndDaysWithTrailingWhitespace_success() {
        SnoozeCommand expectedCommand = new SnoozeCommand(INDEX_FIRST_TASK, 4);

        assertParseSuccess(parser, "1 4  ", expectedCommand);
        assertParseSuccess(parser, "  1 4", expectedCommand);
        assertParseSuccess(parser, "  1 4   ", expectedCommand);
    }

    @Test
    public void parse_numberOfDaysEqualsZero_failure() {
        assertParseFailure(parser, "1 0", SnoozeCommandParser.MESSAGE_INVALID_ARGUMENT);
    }

    @Test
    public void parse_numberOfDaysNegative_failure() {
        assertParseFailure(parser, "1 -2", SnoozeCommandParser.MESSAGE_INVALID_ARGUMENT);
    }

    @Test
    public void parse_invalidInput_failure() {
        assertParseFailure(parser, "1 0x1", SnoozeCommandParser.MESSAGE_INVALID_ARGUMENT);
        assertParseFailure(parser, "0x1", SnoozeCommandParser.MESSAGE_INVALID_ARGUMENT);
    }
}
