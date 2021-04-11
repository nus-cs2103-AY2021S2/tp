package seedu.flashback.logic.parser;

import static seedu.flashback.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.flashback.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.flashback.logic.parser.CliSyntax.PREFIX_ALIAS_COMMAND;
import static seedu.flashback.logic.parser.CliSyntax.PREFIX_ALIAS_NAME;
import static seedu.flashback.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.flashback.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.flashback.logic.commands.AliasCommand;

public class AliasCommandParserTest {
    private AliasCommandParser parser = new AliasCommandParser();

    @Test
    public void parse_invalidFields_success() {
        assertParseFailure(parser, " ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, AliasCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " " + PREFIX_ALIAS_COMMAND + "add" + " name/a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AliasCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " command/add name/a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AliasCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " command/add" + PREFIX_ALIAS_NAME + "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AliasCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " " + PREFIX_ALIAS_COMMAND + "add",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AliasCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " " + PREFIX_ALIAS_NAME + "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AliasCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validFields_success() {
        AliasCommand expectedAliasCommand = new AliasCommand("add", "a");
        // normal input
        assertParseSuccess(parser, " " + PREFIX_ALIAS_COMMAND + "add"
             + " " + PREFIX_ALIAS_NAME + "a", expectedAliasCommand);
        // trailing white space
        assertParseSuccess(parser, " " + PREFIX_ALIAS_COMMAND + "add"
            + " " + PREFIX_ALIAS_NAME + "a" + PREAMBLE_WHITESPACE, expectedAliasCommand);
        // white space in between
        assertParseSuccess(parser, " " + PREFIX_ALIAS_COMMAND + "add"
            + PREAMBLE_WHITESPACE + " " + PREFIX_ALIAS_NAME + "a", expectedAliasCommand);
    }
}
