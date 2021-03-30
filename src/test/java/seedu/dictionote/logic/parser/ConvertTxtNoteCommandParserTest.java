package seedu.dictionote.logic.parser;

import static seedu.dictionote.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.dictionote.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.dictionote.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.dictionote.testutil.TypicalIndexes.INDEX_FIRST_NOTE;

import org.junit.jupiter.api.Test;

import seedu.dictionote.logic.commands.ConvertTxtNoteCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the ConvertTxtNote code. For example, inputs "1" and "1 abc" take the
 * same path through the ConvertTxtNote, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class ConvertTxtNoteCommandParserTest {
    private ConvertTxtNoteCommandParser parser = new ConvertTxtNoteCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "1", new ConvertTxtNoteCommand(INDEX_FIRST_NOTE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ConvertTxtNoteCommand.MESSAGE_USAGE));
    }
}
