package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_STATUS_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_INDEX;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_TASK;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DoneCommand;

public class DoneCommandParserTest {

    private DoneCommandParser parser = new DoneCommandParser();

    @Test
    public void parse_invalidParameter_failure() {
        assertParseFailure(parser, INVALID_STATUS_INDEX,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DoneCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "-1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DoneCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_missingParameter_failure() {
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DoneCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_extraValidParameter_failure() {
        assertParseFailure(parser, VALID_STATUS_INDEX + " " + VALID_STATUS_INDEX,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DoneCommand.MESSAGE_USAGE));
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
