package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SortCommand;

public class SortCommandParserTest {

    private SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArg_throwsParseException() {
        // user does not input the property to be sorted.
        assertParseFailure(parser, "-a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));

        // user does not input the direction to be sorted.
        assertParseFailure(parser, "-p",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));

        // user does not input the correct property to be sorted.
        assertParseFailure(parser, "-f -a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));

        // user does not input the correct direction to be sorted.
        assertParseFailure(parser, "-p -f",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsSortCommand() {
        // user want to sort name in ascending order
        assertParseSuccess(parser, "-n -a",
                new SortCommand(SortCommand.SORT_BY_NAME, SortCommand.DIRECTION_ASCENDING));

        // user want to sort name in descending order
        assertParseSuccess(parser, "-n -d",
                new SortCommand(SortCommand.SORT_BY_NAME, SortCommand.DIRECTION_DESCENDING));

        // user want to sort policy in ascending order
        assertParseSuccess(parser, "-p -a",
                new SortCommand(SortCommand.SORT_BY_POLICY, SortCommand.DIRECTION_ASCENDING));

        // user want to sort policy in ascending order
        assertParseSuccess(parser, "-p -a",
                new SortCommand(SortCommand.SORT_BY_POLICY, SortCommand.DIRECTION_ASCENDING));
    }
}
