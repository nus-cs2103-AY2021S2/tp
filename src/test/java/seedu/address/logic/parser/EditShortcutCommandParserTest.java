package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_SHORTCUT_COMMAND_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_SHORTCUT_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SHORTCUT_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SHORTCUT_COMMAND_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SHORTCUT_NAME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SHORTCUT_NAME_DESC;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditShortcutCommand;
import seedu.address.model.shortcut.Shortcut;

public class EditShortcutCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditShortcutCommand.MESSAGE_USAGE);

    private EditShortcutCommandParser parser = new EditShortcutCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no shortcut name specified
        assertParseFailure(parser, VALID_SHORTCUT_COMMAND_DESC, MESSAGE_INVALID_FORMAT);

        // no shortcut command specified
        assertParseFailure(parser, VALID_SHORTCUT_NAME_DESC, MESSAGE_INVALID_FORMAT);

        // no shortcut name and command specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, INVALID_SHORTCUT_NAME_DESC + VALID_SHORTCUT_COMMAND_DESC,
                Shortcut.MESSAGE_NAME_CONSTRAINTS); // invalid shortcut name
        assertParseFailure(parser, VALID_SHORTCUT_NAME_DESC + INVALID_SHORTCUT_COMMAND_DESC,
                Shortcut.MESSAGE_COMMAND_CONSTRAINTS); // invalid shortcut command

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, INVALID_SHORTCUT_NAME_DESC + INVALID_SHORTCUT_COMMAND_DESC,
                Shortcut.MESSAGE_NAME_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        String userInput = VALID_SHORTCUT_NAME_DESC + VALID_SHORTCUT_COMMAND_DESC;

        EditShortcutCommand expectedCommand = new EditShortcutCommand(VALID_SHORTCUT_NAME, VALID_SHORTCUT_COMMAND);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

}
