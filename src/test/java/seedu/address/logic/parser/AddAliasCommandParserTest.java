package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ALIAS_DESC_ADD;
import static seedu.address.logic.commands.CommandTestUtil.COMMAND_DESC_ADD;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ALIAS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_COMMAND_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ALIAS_ADD;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.CommandParserTestUtil.assertValidCommandToAliasFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertValidCommandToAliasSuccess;
import static seedu.address.testutil.TypicalAliases.ADD_COMMAND_ALIAS;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddAliasCommand;
import seedu.address.model.alias.Command;
import seedu.address.model.alias.CommandAlias;
import seedu.address.testutil.CommandAliasBuilder;

public class AddAliasCommandParserTest {
    private AddAliasCommandParser parser = new AddAliasCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        CommandAlias expectedCommandAlias = new CommandAliasBuilder(ADD_COMMAND_ALIAS).build();

        assertParseSuccess(parser, ALIAS_DESC_ADD + COMMAND_DESC_ADD,
                new AddAliasCommand(expectedCommandAlias));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAliasCommand.MESSAGE_USAGE);

        // missing alias, therefore command taken as alias, showing command format error
        assertParseFailure(parser, COMMAND_DESC_ADD, Command.MESSAGE_CONSTRAINTS);

        // missing command
        assertParseFailure(parser, ALIAS_DESC_ADD, Command.MESSAGE_CONSTRAINTS);

        // all fields missing
        assertParseFailure(parser, "", expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // empty alias, therefore command taken as alias, showing command format error
        assertParseFailure(parser, INVALID_ALIAS_DESC + COMMAND_DESC_ADD, Command.MESSAGE_CONSTRAINTS);

        // invalid command
        assertParseFailure(parser, ALIAS_DESC_ADD + INVALID_COMMAND_DESC, Command.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_validAddAliasCommandAlias_returnsTrue() {
        // empty value
        assertValidCommandToAliasSuccess(parser, "");
    }

    @Test
    public void parse_invalidAddAliasCommandAlias_returnsFalse() {
        // non-empty value (does not allow alias add command to be aliased)
        assertValidCommandToAliasFailure(parser, VALID_ALIAS_ADD);
    }

}
