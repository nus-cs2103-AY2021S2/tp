package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.ThemeCommand;


import static seedu.address.logic.parser.CliSyntax.OPTION_DARK;
import static seedu.address.logic.parser.CliSyntax.OPTION_LIGHT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OPTION;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

class ThemeCommandParserTest {

    private ThemeCommandParser parser = new ThemeCommandParser();

    @Test
    public void parse_validOption_success() {
        assertParseSuccess(parser, OPTION_DARK, new ThemeCommand(OPTION_DARK));

        assertParseSuccess(parser, OPTION_DARK, new ThemeCommand(OPTION_DARK));
    }

}