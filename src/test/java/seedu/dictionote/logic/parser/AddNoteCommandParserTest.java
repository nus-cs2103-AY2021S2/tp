package seedu.dictionote.logic.parser;

import static seedu.dictionote.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.dictionote.logic.commands.CommandTestUtil.VALID_NOTE_CONTENT;
import static seedu.dictionote.logic.parser.CliSyntax.PREFIX_CONTENT;
import static seedu.dictionote.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.dictionote.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.dictionote.testutil.TypicalNotes.MI_AMOR;

import org.junit.jupiter.api.Test;

import seedu.dictionote.logic.commands.AddNoteCommand;
import seedu.dictionote.model.note.Note;
import seedu.dictionote.testutil.NoteBuilder;

public class AddNoteCommandParserTest {
    private AddNoteCommandParser parser = new AddNoteCommandParser();
    private String emptyString = " ";
    @Test
    public void parse_note_success() {
        Note expectedNote = new NoteBuilder(MI_AMOR).build();
        assertParseSuccess(parser, emptyString
                + PREFIX_CONTENT + VALID_NOTE_CONTENT, new AddNoteCommand(expectedNote));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddNoteCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NOTE_CONTENT, expectedMessage);
    }
}

