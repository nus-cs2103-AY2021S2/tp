package seedu.dictionote.logic.parser;

import static seedu.dictionote.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.dictionote.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.dictionote.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.dictionote.testutil.TypicalIndexes.INDEX_FIRST_CONTACT;

import org.junit.jupiter.api.Test;

import seedu.dictionote.logic.commands.EmailContactCommand;

public class EmailContactCommandParserTest {

    private EmailContactCommandParser parser = new EmailContactCommandParser();

    @Test
    public void parse_validArgsExcludingNoteIndex_returnsEmailContactCommand() {
        assertParseSuccess(parser, "1", new EmailContactCommand(INDEX_FIRST_CONTACT));
    }

    @Test
    public void parse_validArgsIncludingNoteIndex_returnsEmailContactCommand() {
        assertParseSuccess(parser, "1 ni/2", new EmailContactCommand(INDEX_FIRST_CONTACT));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EmailContactCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_extraneousArgs_throwsParseException() {
        assertParseFailure(parser, "1 ni/2 t/Tag", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EmailContactCommand.MESSAGE_USAGE));
    }
}
