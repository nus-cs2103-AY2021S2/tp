package seedu.dictionote.logic.parser;

import static seedu.dictionote.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.dictionote.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.dictionote.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.dictionote.logic.commands.FindNoteCommand;
import seedu.dictionote.model.note.NoteContainsKeywordsPredicate;
import seedu.dictionote.model.note.TagNoteContainKeywordsPredicate;

public class FindNoteCommandParserTest {

    private FindNoteCommandParser parser = new FindNoteCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindNoteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindContactCommand() {
        // no leading and trailing whitespaces
        FindNoteCommand expectedFindNoteCommand =
                new FindNoteCommand(
                        new NoteContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")),
                        new TagNoteContainKeywordsPredicate(Arrays.asList("A", "B")));
        assertParseSuccess(parser, " c/Alice c/Bob t/A t/B", expectedFindNoteCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n c/Alice \n \t c/Bob t/A  \t t/B", expectedFindNoteCommand);
    }

}
