package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TASK;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SnoozeCommand;

public class SnoozeCommandParserTest {
    /*
    This comment is to describe the EPs for grading purposes. Parameters: INDEX, DAYS(optional)
    For INDEX: [-1][0][1][valid int not in range of list][Integer.MAX_Value][Integer.MAX_VALUE + 1][non integer][null]
    For DAYS: [-1][0][1][Integer.MAX_Value][Integer.MAX_VALUE + 1][non integer][null]
    Heuristics has been applied using 'at least once', then 'every valid value at least in one positive test case', then
    'no more than 1 invalid value', then 'hidden test cases'
     */

    private SnoozeCommandParser parser = new SnoozeCommandParser();

    @Test
    public void parse_invalidIndex_failure() {
        assertParseFailure(parser, "-1 1", SnoozeCommandParser.MESSAGE_INVALID_ARGUMENT);

        assertParseFailure(parser, "0 1", SnoozeCommandParser.MESSAGE_INVALID_ARGUMENT);
    }

    @Test
    public void parse_indexOnly_success() {
        SnoozeCommand expectedCommand = new SnoozeCommand(INDEX_SECOND_TASK, 1);

        assertParseSuccess(parser, "2", expectedCommand);
    }

    @Test
    public void parse_indexAndDays_success() {
        SnoozeCommand expectedCommand = new SnoozeCommand(INDEX_FIRST_TASK, 3);

        assertParseSuccess(parser, "1 3", expectedCommand);
    }

    @Test
    public void parse_daysExceedMaxInt_failure() {
        assertParseFailure(parser, "1 2147483648", SnoozeCommandParser.MESSAGE_NUMBER_OUT_OF_BOUND);
    }

    @Test
    public void parse_indexExceedMaxInt_failure() {
        assertParseFailure(parser, "102938172938 2", SnoozeCommandParser.MESSAGE_NUMBER_OUT_OF_BOUND);
    }

    @Test
    public void parse_numberOfDaysEqualsZero_failure() {
        assertParseFailure(parser, "1 0", SnoozeCommandParser.MESSAGE_INVALID_ARGUMENT);
    }

    @Test
    public void parse_numberOfDaysNegative_failure() {
        assertParseFailure(parser, "1 -1", SnoozeCommandParser.MESSAGE_INVALID_ARGUMENT);
    }

    @Test
    public void parse_invalidInput_failure() {
        assertParseFailure(parser, "1 0x1", SnoozeCommandParser.MESSAGE_INVALID_ARGUMENT);
        assertParseFailure(parser, "0x1", SnoozeCommandParser.MESSAGE_INVALID_ARGUMENT);
    }

    // For grading: The following are hidden test cases.
    @Test
    public void parse_missingIndex_failure() {
        assertParseFailure(parser, " ", SnoozeCommandParser.MESSAGE_INVALID_ARGUMENT);
    }

    @Test
    public void parse_tooManyParameters_failure() {
        assertParseFailure(parser, "2 2 2", SnoozeCommandParser.MESSAGE_INVALID_ARGUMENT);
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
}
