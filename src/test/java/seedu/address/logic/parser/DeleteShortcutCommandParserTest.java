package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SHORTCUT_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteShortcutCommand;

public class DeleteShortcutCommandParserTest {

    private DeleteShortcutCommandParser parser = new DeleteShortcutCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "ls", new DeleteShortcutCommand(VALID_SHORTCUT_NAME));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "*", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteShortcutCommand.MESSAGE_USAGE));
    }
}
