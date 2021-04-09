package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.HelpCommand;

class HelpCommandParserTest {
    private HelpCommandParser parser = new HelpCommandParser();

    @Test
    void parse_commandNotSpecified_success() {
        assertParseSuccess(parser, " ", new HelpCommand());
    }

    @Test
    void parse_commandSpecified_success() {
        assertParseSuccess(parser, "find", new HelpCommand("find"));
    }

    @Test
    void parse_multipleCommandsSpecified_success() {
        assertParseSuccess(parser, "find exit", new HelpCommand("exit"));
        assertParseSuccess(parser, "find find", new HelpCommand("find"));
        assertParseSuccess(parser, "find exit find", new HelpCommand("find"));
        assertParseSuccess(parser, "find exit find help", new HelpCommand("help"));
    }
}
