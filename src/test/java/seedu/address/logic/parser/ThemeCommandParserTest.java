package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ThemeCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ThemeCommandParserTest {

    private static final String VALID_INPUT_1 = "monokai";
    private static final String VALID_INPUT_2 = "abc";

    private static final String INVALID_INPUT_1 = "\t";
    private static final String INVALID_INPUT_2 = "  ";

    private static final ThemeCommandParser parser = new ThemeCommandParser();

    @Test
    public void parser_parseValidInput_success() {
        assertEquals(new ThemeCommand(VALID_INPUT_1), assertDoesNotThrow(() -> parser.parse(VALID_INPUT_1)));
        assertEquals(new ThemeCommand(VALID_INPUT_2), assertDoesNotThrow(() -> parser.parse(VALID_INPUT_2)));
    }

    @Test
    public void parser_parseInvalidInput_throwsException() {
        assertThrows(ParseException.class, () -> parser.parse(INVALID_INPUT_1));
        assertThrows(ParseException.class, () -> parser.parse(INVALID_INPUT_2));
    }

}
