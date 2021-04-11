package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.OPTION_DARK;
import static seedu.address.logic.parser.CliSyntax.OPTION_LIGHT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ThemeCommand;

class ThemeCommandParserTest {

    private ThemeCommandParser parser = new ThemeCommandParser();

    @Test
    public void parse_validOption_success() {
        //dark
        assertParseSuccess(parser, " o/dark", new ThemeCommand(OPTION_DARK));

        //light
        assertParseSuccess(parser, " o/light", new ThemeCommand(OPTION_LIGHT));
    }

    @Test
    public void parse_duplicateOption_takesLastOptionSuccess() {
        assertParseSuccess(parser, " o/light o/light", new ThemeCommand(OPTION_LIGHT));
        assertParseSuccess(parser, " o/dark o/dark", new ThemeCommand(OPTION_DARK));
        assertParseSuccess(parser, " o/light o/dark", new ThemeCommand(OPTION_DARK));
        assertParseSuccess(parser, " o/dark o/light", new ThemeCommand(OPTION_LIGHT));
    }

    @Test
    public void parse_notValidOption_failure() {
        //full string
        assertParseFailure(parser, " o/lightest", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ThemeCommand.MESSAGE_USAGE));

        //empty string
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT, ThemeCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "   ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, ThemeCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_nullOption_throwsNullException() {
        assertThrows(NullPointerException.class, () -> parser.parse(null));
    }

}
