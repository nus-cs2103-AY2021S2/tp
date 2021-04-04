package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_SORT_DIRECTION;
import static seedu.address.logic.commands.CommandTestUtil.SORT_ASCENDING;
import static seedu.address.logic.commands.CommandTestUtil.SORT_DESCENDING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SORT_DIRECTION_ASCENDING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SORT_DIRECTION_DESCENDING;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SortCommand;

public class SortCommandParserTest {
    private SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, VALID_SORT_DIRECTION_ASCENDING,
                new SortCommand(SORT_ASCENDING));
        assertParseSuccess(parser, VALID_SORT_DIRECTION_DESCENDING,
                new SortCommand(SORT_DESCENDING));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, INVALID_SORT_DIRECTION,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }
}
