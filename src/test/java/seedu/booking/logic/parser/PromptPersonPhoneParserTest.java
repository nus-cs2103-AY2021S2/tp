package seedu.booking.logic.parser;

import static seedu.booking.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.booking.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.booking.logic.commands.PromptPersonPhoneCommand;
import seedu.booking.logic.parser.promptparsers.PromptPersonPhoneParser;
import seedu.booking.model.person.Phone;

public class PromptPersonPhoneParserTest {
    private final PromptPersonPhoneParser parser = new PromptPersonPhoneParser();

    @Test
    public void parsePersonPhone_validField_success() {
        // normal phone
        assertParseSuccess(parser, "93393399",
                new PromptPersonPhoneCommand(new Phone("93393399")));

        // shortest length for phone number
        assertParseSuccess(parser, "1111333",
                new PromptPersonPhoneCommand(new Phone("1111333")));

        // longest length for phone number
        assertParseSuccess(parser, "123456789123455",
                new PromptPersonPhoneCommand(new Phone("123456789123455")));
    }

    @Test
    public void parsePersonPhone_invalidField_failure() {
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;

        // just below minimum phone number length
        assertParseFailure(parser, "324341", expectedMessage);

        // just above maximum phone number length
        assertParseFailure(parser, "1234567891234551", expectedMessage);

        // not a number
        assertParseFailure(parser, "tete", expectedMessage);

        // poor formatting
        assertParseFailure(parser, "1234 5657", expectedMessage);

    }
}
