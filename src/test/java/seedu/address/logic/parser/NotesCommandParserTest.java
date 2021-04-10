package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTES;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.NotesCommand;
import seedu.address.model.person.Notes;

public class NotesCommandParserTest {
    private NotesCommandParser parser = new NotesCommandParser();
    private final String nonEmptyNotes = "Some notes.";

    @Test
    public void parse_indexSpecified_success() {
        // have notes
        Index targetIndex = INDEX_FIRST_TASK;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_NOTES + nonEmptyNotes;
        NotesCommand expectedCommand = new NotesCommand(INDEX_FIRST_TASK, new Notes(nonEmptyNotes));
        assertParseSuccess(parser, userInput, expectedCommand);

        // no notes
        userInput = targetIndex.getOneBased() + " " + PREFIX_NOTES;
        expectedCommand = new NotesCommand(INDEX_FIRST_TASK, new Notes(""));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, NotesCommand.MESSAGE_USAGE);

        // no parameters
        assertParseFailure(parser, NotesCommand.COMMAND_WORD, expectedMessage);

        // no index
        assertParseFailure(parser, NotesCommand.COMMAND_WORD + " SADASDA" + nonEmptyNotes, expectedMessage);

        // no index and notes
        assertParseFailure(parser, NotesCommand.COMMAND_WORD + " " + nonEmptyNotes, expectedMessage);
    }
}
