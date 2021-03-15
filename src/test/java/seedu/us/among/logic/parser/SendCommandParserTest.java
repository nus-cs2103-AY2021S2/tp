package seedu.us.among.logic.parser;

import static seedu.us.among.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.us.among.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.us.among.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.us.among.testutil.TypicalIndexes.INDEX_FIRST_ENDPOINT;

import org.junit.jupiter.api.Test;

import seedu.us.among.logic.commands.SendCommand;

public class SendCommandParserTest {

    private SendCommandParser parser = new SendCommandParser();

    @Test
    public void parse_validArgs_returnsSendCommand() {
        assertParseSuccess(parser, "1", new SendCommand(INDEX_FIRST_ENDPOINT));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, SendCommand.MESSAGE_USAGE));
    }

}
