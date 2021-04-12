package seedu.booking.logic.parser;

import static seedu.booking.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.booking.commons.core.Messages.MESSAGE_INVALID_EMAIL_FORMAT;
import static seedu.booking.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.booking.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.booking.logic.commands.PromptAddBookingCommand;
import seedu.booking.logic.parser.promptparsers.PromptBookingEmailParser;
import seedu.booking.model.person.Email;

public class PromptBookingEmailParserTest {
    private final PromptBookingEmailParser parser = new PromptBookingEmailParser();

    @Test
    public void parsePersonEmail_validField_success() {
        // normal email
        assertParseSuccess(parser, " e/hello@gmail.com",
                new PromptAddBookingCommand(new Email("hello@gmail.com")));

        // email with special characters
        assertParseSuccess(parser, " e/hel_0*lo@gmail.com",
                new PromptAddBookingCommand(new Email("hel_0*lo@gmail.com")));

        // short email
        assertParseSuccess(parser, " e/a@co",
                new PromptAddBookingCommand(new Email("a@co")));

        // spaces
        assertParseSuccess(parser, "       e/hello@gmail.com        ",
                new PromptAddBookingCommand(new Email("hello@gmail.com")));
    }

    @Test
    public void parsePersonEmail_invalidField_failure() {
        String expectedMessageInvalidEmail = MESSAGE_INVALID_EMAIL_FORMAT + Email.MESSAGE_CONSTRAINTS;
        String expectedMessageInvalidCommand = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                PromptAddBookingCommand.MESSAGE_USAGE);

        // empty field
        assertParseFailure(parser, " e/hello@gmail.com   eorij erpf     ", expectedMessageInvalidEmail);

        // invalid email syntax, missing domain or head
        assertParseFailure(parser, " e/allo@", expectedMessageInvalidEmail);
        assertParseFailure(parser, " e/@gmail.com", expectedMessageInvalidEmail);

        // too short
        assertParseFailure(parser, " e/a@m", expectedMessageInvalidEmail);

        // no prefix
        assertParseFailure(parser, "hi@gmail.com", expectedMessageInvalidCommand);

    }
}
