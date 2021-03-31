package seedu.address.logic.parser.tutorparser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.tutorcommands.DeleteNoteCommand;
import seedu.address.logic.commands.tutorcommands.EditCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tutor.Notes;

class DeleteNoteCommandParserTest {

    private DeleteNoteCommandParser parser = new DeleteNoteCommandParser();

    @Test
    public void parse_validArgs() throws ParseException {
        String noteString = "";
        Notes note = new Notes(noteString);
        EditCommand.EditTutorDescriptor editTutorDescriptor = new EditCommand.EditTutorDescriptor();
        editTutorDescriptor.setNotes(note);

        String args = "1";

        Command actualCommand = parser.parse(args);
        Command expectedCommand = new DeleteNoteCommand(INDEX_FIRST_PERSON, editTutorDescriptor);

        assertEquals(actualCommand, expectedCommand);
    }

    @Test
    public void parse_invalidArgs_missingIndex() {
        String noteString = "";

        String args = "" + PREAMBLE_WHITESPACE
                + noteString;

        assertThrows(ParseException.class, () -> parser.parse(args));
    }

    @Test
    public void parse_invalidArgs_invalidIndexNotNumber() {
        String noteString = "";

        String args = "a" + PREAMBLE_WHITESPACE
                + noteString;

        assertThrows(ParseException.class, () -> parser.parse(args));
    }

    @Test
    public void parse_invalidArgs_invalidIndexNegativeNumber() {
        String noteString = "";

        String args = "-1" + PREAMBLE_WHITESPACE
                + noteString;

        assertThrows(ParseException.class, () -> parser.parse(args));
    }

}
