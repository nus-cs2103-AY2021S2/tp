package seedu.address.logic.parser.favouriteparser;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.favouritecommands.FavouriteCommand;
import seedu.address.logic.commands.tutorcommands.EditCommand;
import seedu.address.logic.parser.exceptions.ParseException;

class FavouriteCommandParserTest {

    private FavouriteCommandParser parser = new FavouriteCommandParser();

    @Test
    public void parse_validArgs() throws ParseException {
        EditCommand.EditTutorDescriptor editTutorDescriptor = new EditCommand.EditTutorDescriptor();
        editTutorDescriptor.setIsFavourite(true);

        String args = "1";

        Command actualCommand = parser.parse(args);
        Command expectedCommand = new FavouriteCommand(INDEX_FIRST_PERSON, editTutorDescriptor);

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
