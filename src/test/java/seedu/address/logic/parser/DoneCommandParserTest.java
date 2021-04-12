package seedu.address.logic.parser;

import static seedu.address.logic.commands.CommandTestUtil.INVALID_STATUS_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_INDEX;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_TASK;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.DoneCommand;

public class DoneCommandParserTest {
    /*
    This comment is to describe the EPs for grading purposes. Parameters: INDEX
    For INDEX: [-1][0][1][Integer.MAX_VALUE + 1][non integer][null]
     */

    private DoneCommandParser parser = new DoneCommandParser();

    @Test
    public void parse_invalidIndex_failure() {
        assertParseFailure(parser, INVALID_STATUS_INDEX, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);

        assertParseFailure(parser, "-1", Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);

        assertParseFailure(parser, "0", Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);

        assertParseFailure(parser, "j", Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void parse_missingIndex_failure() {
        assertParseFailure(parser, "",
                Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void parse_extraValidParameter_failure() {
        assertParseFailure(parser, VALID_STATUS_INDEX + " " + VALID_STATUS_INDEX,
                Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
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
