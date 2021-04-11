package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.ListCommand;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_OPTION;
import static seedu.address.logic.commands.ListCommand.MESSAGE_USAGE;
import static seedu.address.logic.parser.CliSyntax.OPTION_FAVOURITE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

public class ListCommandParserTest {

    ListCommandParser parser = new ListCommandParser();

    @Test void parse_emptyArgs_returnListCommand() {
        assertParseSuccess(parser, "   ", new ListCommand());
    }

    @Test
    public void parse_validOption_returnsListCommand() {
        assertParseSuccess(parser, " o/fav", new ListCommand(OPTION_FAVOURITE));
    }

    @Test
    public void parse_invalidOption_throwsException() {
        String invalidOption = "random";
        String expectedMessage = String.format(MESSAGE_INVALID_OPTION, invalidOption);
        assertParseFailure(parser, " o/" + invalidOption, expectedMessage);
    }

    @Test
    public void parse_invalidArgs_throwsException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE);
        assertParseFailure(parser, "aaaaaa", expectedMessage);
    }
}
