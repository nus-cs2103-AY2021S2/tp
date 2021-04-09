package seedu.us.among.logic.parser;

import static seedu.us.among.commons.core.Messages.MESSAGE_INVALID_COMMAND_ERROR;
import static seedu.us.among.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.us.among.commons.core.Messages.MESSAGE_INVALID_INDEX;
import static seedu.us.among.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.us.among.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.us.among.testutil.TypicalIndexes.INDEX_FIRST_ENDPOINT;

import org.junit.jupiter.api.Test;

import seedu.us.among.logic.commands.SendCommand;

public class SendCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT_NAN = String.format(MESSAGE_INVALID_COMMAND_ERROR,
            MESSAGE_INVALID_INDEX, SendCommand.MESSAGE_USAGE);

    private final SendCommandParser parser = new SendCommandParser();

    @Test
    public void parse_validArgs_returnsSendCommand() {
        assertParseSuccess(parser, "1", new SendCommand(INDEX_FIRST_ENDPOINT));
    }

    @Test
    public void parse_emptyArgs_returnsSendCommand() {
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SendCommand.MESSAGE_USAGE + "\n"));
        assertParseFailure(parser, " ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SendCommand.MESSAGE_USAGE + "\n"));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "1000000000000000000", MESSAGE_INVALID_FORMAT_NAN); // overflow
        assertParseFailure(parser, "-99999999999999999", MESSAGE_INVALID_FORMAT_NAN); // underflow
        assertParseFailure(parser, "a", MESSAGE_INVALID_FORMAT_NAN);
        assertParseFailure(parser, ".", MESSAGE_INVALID_FORMAT_NAN);
        assertParseFailure(parser, "1a.", MESSAGE_INVALID_FORMAT_NAN);
        assertParseFailure(parser, "a1", MESSAGE_INVALID_FORMAT_NAN);
        assertParseFailure(parser, ".1", MESSAGE_INVALID_FORMAT_NAN);
        assertParseFailure(parser, "1.", MESSAGE_INVALID_FORMAT_NAN);
    }

}
