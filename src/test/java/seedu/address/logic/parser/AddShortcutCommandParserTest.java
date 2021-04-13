package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_SHORTCUT_COMMAND_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_SHORTCUT_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SHORTCUT_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SHORTCUT_COMMAND_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SHORTCUT_NAME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SHORTCUT_NAME_DESC;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddShortcutCommand;
import seedu.address.model.shortcut.Shortcut;

public class AddShortcutCommandParserTest {
    private AddShortcutCommandParser parser = new AddShortcutCommandParser();

    @Test
    public void parse_success() {
        Shortcut expectedShortcut = new Shortcut(VALID_SHORTCUT_NAME, VALID_SHORTCUT_COMMAND);

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + VALID_SHORTCUT_NAME_DESC + VALID_SHORTCUT_COMMAND_DESC,
                new AddShortcutCommand(expectedShortcut.getShortcutName(), expectedShortcut.getShortcutCommand()));

    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddShortcutCommand.MESSAGE_USAGE);

        // missing shortcut name prefix
        assertParseFailure(parser, VALID_SHORTCUT_NAME + VALID_SHORTCUT_COMMAND_DESC, expectedMessage);

        // missing shortcut command prefix
        assertParseFailure(parser, VALID_SHORTCUT_NAME_DESC + VALID_SHORTCUT_COMMAND, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_SHORTCUT_NAME + VALID_SHORTCUT_COMMAND, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid shortcut name
        assertParseFailure(parser, INVALID_SHORTCUT_NAME_DESC + VALID_SHORTCUT_COMMAND_DESC,
                Shortcut.MESSAGE_NAME_CONSTRAINTS);

        // invalid shortcut command
        assertParseFailure(parser, VALID_SHORTCUT_NAME_DESC + INVALID_SHORTCUT_COMMAND_DESC,
                Shortcut.MESSAGE_COMMAND_CONSTRAINTS);

        // both invalid values, only invalid shortcut name reported
        assertParseFailure(parser, INVALID_SHORTCUT_NAME_DESC + INVALID_SHORTCUT_COMMAND_DESC,
                Shortcut.MESSAGE_NAME_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + VALID_SHORTCUT_NAME_DESC + VALID_SHORTCUT_COMMAND_DESC,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddShortcutCommand.MESSAGE_USAGE));
    }
}
