package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.AliasMapping;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ViewHistoryCommand;
import seedu.address.logic.commands.resident.AddResidentCommand;
import seedu.address.logic.commands.resident.DeleteResidentCommand;
import seedu.address.logic.commands.resident.EditResidentCommand;
import seedu.address.logic.commands.resident.EditResidentCommand.EditResidentDescriptor;
import seedu.address.logic.commands.resident.FindResidentCommand;
import seedu.address.logic.commands.resident.ListResidentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.resident.NameContainsKeywordsPredicate;
import seedu.address.model.resident.Resident;
import seedu.address.testutil.EditResidentDescriptorBuilder;
import seedu.address.testutil.ResidentBuilder;
import seedu.address.testutil.ResidentUtil;

public class AddressBookParserTest {

    private final AddressBookParser parser;

    private final ReadOnlyUserPrefs readOnlyUserPrefs;

    public AddressBookParserTest() {
        parser = new AddressBookParser();

        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setAliasMapping(new AliasMapping());
        readOnlyUserPrefs = userPrefs;
    }

    @Test
    public void parseCommand_add() throws Exception {
        Resident resident = new ResidentBuilder().build();
        AddResidentCommand command = (AddResidentCommand) parser.parseCommand(ResidentUtil.getAddCommand(resident),
                readOnlyUserPrefs);
        assertEquals(new AddResidentCommand(resident), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD, readOnlyUserPrefs)
                instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3", readOnlyUserPrefs)
                instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteResidentCommand command = (DeleteResidentCommand) parser.parseCommand(
                DeleteResidentCommand.COMMAND_WORD + " "
                        + INDEX_FIRST.getOneBased(), readOnlyUserPrefs);
        assertEquals(new DeleteResidentCommand(INDEX_FIRST), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Resident resident = new ResidentBuilder().build();
        EditResidentDescriptor descriptor = new EditResidentDescriptorBuilder(resident).build();
        EditResidentCommand command = (EditResidentCommand) parser.parseCommand(EditResidentCommand.COMMAND_WORD + " "
                + INDEX_FIRST.getOneBased() + " "
                + ResidentUtil.getEditResidentDescriptorDetails(descriptor), readOnlyUserPrefs);
        assertEquals(new EditResidentCommand(INDEX_FIRST, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD, readOnlyUserPrefs)
                instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3", readOnlyUserPrefs)
                instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindResidentCommand command = (FindResidentCommand) parser.parseCommand(
                FindResidentCommand.COMMAND_WORD + " "
                        + keywords.stream().collect(Collectors.joining(" ")), readOnlyUserPrefs);
        assertEquals(new FindResidentCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD, readOnlyUserPrefs)
                instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3", readOnlyUserPrefs)
                instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListResidentCommand.COMMAND_WORD, readOnlyUserPrefs)
                instanceof ListResidentCommand);
        assertTrue(parser.parseCommand(ListResidentCommand.COMMAND_WORD + " 3", readOnlyUserPrefs)
                instanceof ListResidentCommand);
    }

    @Test
    public void parseCommand_history() throws Exception {
        assertTrue(parser.parseCommand(ViewHistoryCommand.COMMAND_WORD, readOnlyUserPrefs)
                instanceof ViewHistoryCommand);
        assertTrue(parser.parseCommand(ViewHistoryCommand.COMMAND_WORD + " 3", readOnlyUserPrefs)
                instanceof ViewHistoryCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand("", readOnlyUserPrefs));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, ()
            -> parser.parseCommand("unknownCommand", readOnlyUserPrefs));
    }
}
