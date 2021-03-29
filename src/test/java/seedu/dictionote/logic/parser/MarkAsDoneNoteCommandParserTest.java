package seedu.dictionote.logic.parser;

import static seedu.dictionote.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.dictionote.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.dictionote.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.dictionote.testutil.TypicalIndexes.INDEX_FIRST_NOTE;

import org.junit.jupiter.api.Test;

import seedu.dictionote.logic.commands.MarkAsDoneNoteCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the MarkAsDoneNote code. For example, inputs "1" and "1 abc" take the
 * same path through the MarkAsDoneNote, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class MarkAsDoneNoteCommandParserTest {
    private MarkAsDoneNoteCommandParser parser = new MarkAsDoneNoteCommandParser();

    @Test
    public void parse_validArgs_returnsMarkAsDoneCommand() {
        assertParseSuccess(parser, "1", new MarkAsDoneNoteCommand(INDEX_FIRST_NOTE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                MarkAsDoneNoteCommand.MESSAGE_USAGE));
    }
}
