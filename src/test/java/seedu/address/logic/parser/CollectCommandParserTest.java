package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SEPARATOR;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CollectCommand;

public class CollectCommandParserTest {
    private CollectCommandParser parser = new CollectCommandParser();
    private final String emptySeparator = "";
    private final String nonEmptySeparator = ",";

    @Test
    public void parse_success() {
        // have non empty separator
        String userInput = CollectCommand.COMMAND_WORD + " " + PREFIX_EMAIL
                + " " + PREFIX_SEPARATOR + nonEmptySeparator;
        CollectCommand expectedCommand = new CollectCommand(PREFIX_EMAIL, nonEmptySeparator);
        assertParseSuccess(parser, userInput, expectedCommand);

        // have empty separator
        userInput = CollectCommand.COMMAND_WORD + " " + PREFIX_EMAIL + " " + PREFIX_SEPARATOR;
        expectedCommand = new CollectCommand(PREFIX_EMAIL, emptySeparator);
        assertParseSuccess(parser, userInput, expectedCommand);

        // no separator
        userInput = CollectCommand.COMMAND_WORD + " " + PREFIX_EMAIL;
        expectedCommand = new CollectCommand(PREFIX_EMAIL, CollectCommand.DEFAULT_SEPARATOR);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                CollectCommand.MESSAGE_USAGE);

        // no parameters
        assertParseFailure(parser, CollectCommand.COMMAND_WORD, expectedMessage);

        // no type of detail
        assertParseFailure(parser, CollectCommand.COMMAND_WORD + " "
                + PREFIX_SEPARATOR + nonEmptySeparator, expectedMessage);
    }

    @Test
    public void parse_tooManyFields_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                CollectCommand.MESSAGE_USAGE);

        // 2 types of detail
        assertParseFailure(parser, CollectCommand.COMMAND_WORD + PREFIX_ADDRESS
                + PREFIX_EMAIL, expectedMessage);
    }
}
