package seedu.address.logic.parser.tutorparser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.tutorcommands.EditCommand;
import seedu.address.logic.commands.tutorcommands.EditNoteCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tutor.Notes;

class EditNoteCommandParserTest {

    private EditNoteCommandParser parser = new EditNoteCommandParser();

    @Test
    public void parse_validArgs() throws ParseException {
        String noteString = "some note here";
        Notes note = new Notes(noteString);
        EditCommand.EditTutorDescriptor editTutorDescriptor = new EditCommand.EditTutorDescriptor();
        editTutorDescriptor.setNotes(note);

        String args = "1" + PREAMBLE_WHITESPACE
                + noteString;

        Command actualCommand = parser.parse(args);
        Command expectedCommand = new EditNoteCommand(INDEX_FIRST_PERSON, editTutorDescriptor);

        assertEquals(actualCommand, expectedCommand);
    }

    @Test
    public void parse_invalidArgs_missingNote() {
        String noteString = "";

        String args = "1" + PREAMBLE_WHITESPACE
                + noteString;

        assertThrows(ParseException.class, () -> parser.parse(args));
    }

    @Test
    public void parse_invalidArgs_missingIndex() {
        String noteString = "some note here";

        String args = "" + PREAMBLE_WHITESPACE
                + noteString;

        assertThrows(ParseException.class, () -> parser.parse(args));
    }

    @Test
    public void parse_invalidArgs_invalidIndexNotNumber() {
        String noteString = "some note here";

        String args = "a" + PREAMBLE_WHITESPACE
                + noteString;

        assertThrows(ParseException.class, () -> parser.parse(args));
    }

    @Test
    public void parse_invalidArgs_invalidIndexNegativeNumber() {
        String noteString = "some note here";

        String args = "-1" + PREAMBLE_WHITESPACE
                + noteString;

        assertThrows(ParseException.class, () -> parser.parse(args));
    }
}
