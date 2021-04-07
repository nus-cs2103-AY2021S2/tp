package seedu.booking.logic.parser;

import static seedu.booking.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.booking.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.booking.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.booking.logic.commands.PromptAddPersonCommand;
import seedu.booking.logic.parser.promptparsers.PromptPersonNameParser;
import seedu.booking.model.person.Name;

public class PromptPersonNameParserTest {
    private final PromptPersonNameParser parser = new PromptPersonNameParser();

    @Test
    public void parsePersonName_validField_success() {
        assertParseSuccess(parser, " n/Johnathon",
                new PromptAddPersonCommand(new Name("Johnathon")));

        // names with spacings
        assertParseSuccess(parser, " n/Britney Spears",
                new PromptAddPersonCommand(new Name("Britney Spears")));
    }

    @Test
    public void parsePersonName_invalidField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                PromptAddPersonCommand.MESSAGE_USAGE);
        // empty input
        assertParseFailure(parser, "", expectedMessage);

        // no space between command and prefix
        assertParseFailure(parser, "n/Boba", expectedMessage);

        // no prefix
        assertParseFailure(parser, " Boba", expectedMessage);

        // wrong prefix
        assertParseFailure(parser, " v/Boba", expectedMessage);
    }
}
