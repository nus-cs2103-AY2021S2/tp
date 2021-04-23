package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_OPTION;
import static seedu.address.logic.commands.SortCommand.MESSAGE_USAGE;
import static seedu.address.logic.parser.CliSyntax.OPTION_DATE;
import static seedu.address.logic.parser.CliSyntax.OPTION_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SortCommand;

public class SortCommandParserTest {
    private SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
    }

    @Test
    public void parse_validOptionName_returnsSortCommand() {
        assertParseSuccess(parser, " o/name", new SortCommand(OPTION_NAME));
    }

    @Test
    public void parse_validOptionDate_returnsSortCommand() {
        assertParseSuccess(parser, " o/date", new SortCommand(OPTION_DATE));
    }

    @Test
    public void parse_invalidOption_throwsException() {
        String invalidOption = "random";
        assertParseFailure(parser, " o/" + invalidOption,
                String.format(MESSAGE_INVALID_OPTION, invalidOption));
    }

    @Test
    public void parse_invalidArgs_throwsException() {
        assertParseFailure(parser, "aaaaaa",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
    }
}
