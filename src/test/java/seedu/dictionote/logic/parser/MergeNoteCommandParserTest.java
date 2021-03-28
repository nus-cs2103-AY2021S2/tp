package seedu.dictionote.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.dictionote.logic.commands.MergeNoteCommand;

import static seedu.dictionote.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.dictionote.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.dictionote.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.dictionote.testutil.TypicalIndexes.INDEX_FIRST_NOTE;
import static seedu.dictionote.testutil.TypicalIndexes.INDEX_SECOND_NOTE;

public class MergeNoteCommandParserTest {
    private MergeNoteCommandParser parser = new MergeNoteCommandParser();

    @Test
    public void parse_validArgs_returnsMergeCommand() {
        assertParseSuccess(parser, " 1 2", new MergeNoteCommand(INDEX_FIRST_NOTE, INDEX_SECOND_NOTE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, " a b", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                MergeNoteCommand.MESSAGE_USAGE));
    }
}
