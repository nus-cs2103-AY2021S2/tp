package fooddiary.logic.parser;

import static fooddiary.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static fooddiary.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import fooddiary.logic.commands.AddOnCommand;
import fooddiary.logic.commands.CommandTestUtil;
import fooddiary.model.entry.Review;


public class AddOnCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddOnCommand.MESSAGE_USAGE);

    private final AddOnCommandParser parser = new AddOnCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, CommandTestUtil.VALID_NAME_A, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", AddOnCommand.MESSAGE_NOT_ADDED_ON);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + CommandTestUtil.REVIEW_DESC_A, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + CommandTestUtil.REVIEW_DESC_A, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed before prefix
        assertParseFailure(parser, "1 some random string"
                + CommandTestUtil.REVIEW_DESC_A, MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);

    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1"
                + CommandTestUtil.INVALID_REVIEW_DESC, Review.MESSAGE_CONSTRAINTS); // invalid review

        // multiple invalid values, but only the first invalid value is captured
        //TODO
    }

}
