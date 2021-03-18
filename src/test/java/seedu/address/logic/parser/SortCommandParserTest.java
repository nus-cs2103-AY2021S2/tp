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
        // missing attribute
        assertParseFailure(parser, "-a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));

        // missing direction
        assertParseFailure(parser, "-p",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));

        // invalid attribute
        assertParseFailure(parser, "-f -a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));

        // invalid direction
        assertParseFailure(parser, "-p -f",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsSortCommand() {
        // sort by name in ascending order
        assertParseSuccess(parser, "-n -a",
                new SortCommand(SortCommand.SORT_BY_NAME, SortCommand.DIRECTION_ASCENDING));

        // sort by name in descending order
        assertParseSuccess(parser, "-n -d",
                new SortCommand(SortCommand.SORT_BY_NAME, SortCommand.DIRECTION_DESCENDING));

        // sort by policy in ascending order
        assertParseSuccess(parser, "-p -a",
                new SortCommand(SortCommand.SORT_BY_POLICY, SortCommand.DIRECTION_ASCENDING));

        // sort by policy in descending order
        assertParseSuccess(parser, "-p -a",
                new SortCommand(SortCommand.SORT_BY_POLICY, SortCommand.DIRECTION_ASCENDING));
    }
}
