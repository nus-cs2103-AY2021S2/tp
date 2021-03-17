package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertValidCommandToAliasFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertValidCommandToAliasSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAliases.getTypicalAlias;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddAliasCommand;
import seedu.address.logic.commands.AliasCommand;
import seedu.address.logic.commands.DeleteAliasCommand;
import seedu.address.logic.commands.ListAliasCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.alias.CommandAlias;
import seedu.address.testutil.CommandAliasBuilder;
import seedu.address.testutil.CommandAliasUtil;

public class AliasCommandParserTest {

    private final AliasCommandParser parser = new AliasCommandParser();

    @Test
    public void parseCommand_aliasAdd() throws Exception {
        CommandAlias commandAlias = new CommandAliasBuilder().build();
        AddAliasCommand command = (AddAliasCommand) parser.parse(AddAliasCommand.ADD_SUB_COMMAND_WORD + " "
                + CommandAliasUtil.getCommandAliasDetails(commandAlias));
        assertEquals(new AddAliasCommand(commandAlias), command);
    }

    @Test
    public void parseCommand_aliasDelete() throws Exception {
        DeleteAliasCommand command = (DeleteAliasCommand) parser.parse(DeleteAliasCommand.DELETE_SUB_COMMAND_WORD
                + " " + getTypicalAlias());
        assertEquals(new DeleteAliasCommand(getTypicalAlias()), command);
    }

    @Test
    public void parseCommand_aliasList() throws Exception {
        assertTrue(parser.parse(ListAliasCommand.LIST_SUB_COMMAND_WORD) instanceof ListAliasCommand);
        assertTrue(parser.parse(ListAliasCommand.LIST_SUB_COMMAND_WORD + " 3") instanceof ListAliasCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, AliasCommand.MESSAGE_USAGE), ()
            -> parser.parse(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_UNKNOWN_COMMAND), ()
            -> parser.parse(AliasCommand.COMMAND_WORD + " unknownSubCommand"));
    }

    @Test
    public void parse_validAddCommandAlias_returnsTrue() {
        // whitespace only
        assertValidCommandToAliasSuccess(parser, PREAMBLE_WHITESPACE);

        // alias add
        assertValidCommandToAliasSuccess(parser, AliasCommand.ADD_SUB_COMMAND_WORD);

        // alias delete
        assertValidCommandToAliasSuccess(parser, AliasCommand.DELETE_SUB_COMMAND_WORD);

        // alias list
        assertValidCommandToAliasSuccess(parser, AliasCommand.LIST_SUB_COMMAND_WORD);
    }

    @Test
    public void parse_invalidAddCommandAlias_returnsFalse() {
        // invalid alias sub command
        assertValidCommandToAliasFailure(parser, INVALID_COMMAND);
    }

}
