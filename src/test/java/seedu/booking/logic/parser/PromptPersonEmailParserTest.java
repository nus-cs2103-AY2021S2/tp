package seedu.booking.logic.parser;

import static seedu.booking.commons.core.Messages.MESSAGE_INVALID_EMAIL_FORMAT;
import static seedu.booking.commons.core.Messages.PROMPT_MESSAGE_TRY_AGAIN;
import static seedu.booking.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.booking.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.booking.logic.commands.PromptPersonEmailCommand;
import seedu.booking.logic.parser.promptparsers.PromptPersonEmailParser;
import seedu.booking.model.person.Email;

public class PromptPersonEmailParserTest {

    private final PromptPersonEmailParser parser = new PromptPersonEmailParser();

    @Test
    public void parsePersonEmail_validField_success() {
        // normal email
        assertParseSuccess(parser, "hello@gmail.com",
                new PromptPersonEmailCommand(new Email("hello@gmail.com")));

        // email with special characters
        assertParseSuccess(parser, "hel_0*lo@gmail.com",
                new PromptPersonEmailCommand(new Email("hel_0*lo@gmail.com")));

        // short email
        assertParseSuccess(parser, "a@co",
                new PromptPersonEmailCommand(new Email("a@co")));

        // spaces
        assertParseSuccess(parser, "       hello@gmail.com        ",
                new PromptPersonEmailCommand(new Email("hello@gmail.com")));
    }

    @Test
    public void parsePersonEmail_invalidField_failure() {
        String expectedMessage = MESSAGE_INVALID_EMAIL_FORMAT + PROMPT_MESSAGE_TRY_AGAIN;

        // empty field
        assertParseFailure(parser, "hello@gmail.com   eorij erpf     ", expectedMessage);

        // invalid email syntax, missing domain or head
        assertParseFailure(parser, "allo@", expectedMessage);
        assertParseFailure(parser, "@gmail.com", expectedMessage);

        // too short
        assertParseFailure(parser, "a@m", expectedMessage);

        //assertParseFailure(parser, "+a@com", expectedMessage);
        //assertParseFailure(parser, "a__@com", expectedMessage);
    }
}
