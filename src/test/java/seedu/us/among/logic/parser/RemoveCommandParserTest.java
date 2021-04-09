package seedu.us.among.logic.parser;

import static seedu.us.among.commons.core.Messages.MESSAGE_INVALID_COMMAND_ERROR;
import static seedu.us.among.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.us.among.commons.core.Messages.MESSAGE_INVALID_INDEX;
import static seedu.us.among.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.us.among.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.us.among.testutil.TypicalIndexes.INDEX_FIRST_ENDPOINT;

import org.junit.jupiter.api.Test;

import seedu.us.among.logic.commands.RemoveCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the RemoveCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the RemoveCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class RemoveCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT_NAN = String.format(MESSAGE_INVALID_COMMAND_ERROR,
            MESSAGE_INVALID_INDEX, RemoveCommand.MESSAGE_USAGE);

    private RemoveCommandParser parser = new RemoveCommandParser();

    @Test
    public void parse_validArgs_returnsRemoveCommand() {
        assertParseSuccess(parser, "1", new RemoveCommand(INDEX_FIRST_ENDPOINT));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                RemoveCommand.MESSAGE_USAGE + "\n"));
        assertParseFailure(parser, "a", MESSAGE_INVALID_FORMAT_NAN);
        assertParseFailure(parser, "1000000000000000000", MESSAGE_INVALID_FORMAT_NAN); // overflow
        assertParseFailure(parser, "-99999999999999999", MESSAGE_INVALID_FORMAT_NAN); // underflow
        assertParseFailure(parser, "one", MESSAGE_INVALID_FORMAT_NAN); // invalid number (should be numeric)
    }
}
