package seedu.dictionote.logic.parser;

import static seedu.dictionote.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.dictionote.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.dictionote.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.dictionote.testutil.TypicalIndexes.INDEX_FIRST_NOTE;

import org.junit.jupiter.api.Test;

import seedu.dictionote.logic.commands.MarkAsUndoneNoteCommand;

public class MarkAsUndoneNoteCommandParserTest {
    private MarkAsUndoneNoteCommandParser parser = new MarkAsUndoneNoteCommandParser();

    @Test
    public void parse_validArgs_returnsMarkAsDoneCommand() {
        assertParseSuccess(parser, "1", new MarkAsUndoneNoteCommand(INDEX_FIRST_NOTE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                MarkAsUndoneNoteCommand.MESSAGE_USAGE));
    }
}
