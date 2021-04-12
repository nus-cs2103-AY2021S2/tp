package seedu.address.logic.parser;

import static seedu.address.logic.commands.CommandTestUtil.INVALID_STATUS_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_INDEX;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_TASK;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DoneCommand;

public class DoneCommandParserTest {
    /*
    This comment is to describe the EPs for grading purposes. Parameters: INDEX
    For INDEX: [-1][0][1][Integer.MAX_VALUE + 1][non integer][null]
     */

    private DoneCommandParser parser = new DoneCommandParser();

    @Test
    public void parse_invalidIndex_failure() {
        assertParseFailure(parser, INVALID_STATUS_INDEX, DoneCommandParser.MESSAGE_DONE_FORMAT);

        assertParseFailure(parser, "-1", DoneCommandParser.MESSAGE_DONE_FORMAT);

        assertParseFailure(parser, "0", DoneCommandParser.MESSAGE_DONE_FORMAT);

        assertParseFailure(parser, "j", DoneCommandParser.MESSAGE_DONE_FORMAT);
    }

    @Test
    public void parse_missingIndex_failure() {
        assertParseFailure(parser, "",
                DoneCommandParser.MESSAGE_DONE_FORMAT);
    }

    @Test
    public void parse_extraValidParameter_failure() {
        assertParseFailure(parser, VALID_STATUS_INDEX + " " + VALID_STATUS_INDEX,
                DoneCommandParser.MESSAGE_DONE_FORMAT);
    }

    @Test
    public void parse_normalParameter_success() {
        DoneCommand expectedDoneCommand = new DoneCommand(INDEX_THIRD_TASK);

        assertParseSuccess(parser, "3", expectedDoneCommand);
    }

    @Test
    public void parse_extraWhitespaceWithValidParameter_success() {
        DoneCommand expectedDoneCommand = new DoneCommand(INDEX_THIRD_TASK);

        assertParseSuccess(parser, " 3", expectedDoneCommand);

        assertParseSuccess(parser, "3  ", expectedDoneCommand);

        assertParseSuccess(parser, "  3   ", expectedDoneCommand);
    }
}
