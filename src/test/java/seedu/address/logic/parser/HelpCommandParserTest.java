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

    //    @Test
    //    void parse_invalidCommandSpecified_failure() {
    //        String expectedMessage = MESSAGE_UNKNOWN_COMMAND;
    //
    //        assertParseFailure(parser, "hello", expectedMessage);
    //    }
    //
    //    @Test
    //    void parse_multipleCommandsSpecified_failure() {
    //        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE);
    //
    //        assertParseFailure(parser, "help find", expectedMessage);
    //    }
}
