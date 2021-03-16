package seedu.us.among.logic.parser;

import static seedu.us.among.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.us.among.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.us.among.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.us.among.logic.parser.ToggleCommandParser.MESSAGE_INVALID_THEME;

import org.junit.jupiter.api.Test;

import seedu.us.among.logic.commands.ToggleCommand;
import seedu.us.among.ui.ThemeType;

public class ToggleCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            ToggleCommand.MESSAGE_USAGE);
    private static final String MESSAGE_INVALID_THEME_FORMAT = String.format(MESSAGE_INVALID_THEME,
            ToggleCommand.MESSAGE_USAGE);

    private ToggleCommandParser parser = new ToggleCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no theme specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "sf24", MESSAGE_INVALID_THEME_FORMAT);
        assertParseFailure(parser, "*45775", MESSAGE_INVALID_THEME_FORMAT);
    }

    @Test
    public void parse_themeSpecified_success() {
        String userInput = ThemeType.getTheme("light").toString();
        ToggleCommand expectedCommand = new ToggleCommand(ThemeType.getTheme("light").toString());

        assertParseSuccess(parser, userInput, expectedCommand);
    }


}
