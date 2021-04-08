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

import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.commandhistory.ViewHistoryCommand;
import seedu.address.logic.commands.resident.AddResidentCommand;
import seedu.address.logic.commands.resident.DeleteResidentCommand;
import seedu.address.logic.commands.resident.EditResidentCommand;
import seedu.address.logic.commands.resident.EditResidentCommand.EditResidentDescriptor;
import seedu.address.logic.commands.resident.FindResidentCommand;
import seedu.address.logic.commands.resident.ListResidentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.AddressBook;
import seedu.address.model.resident.NameContainsKeywordsPredicate;
import seedu.address.model.resident.Resident;
import seedu.address.testutil.resident.EditResidentDescriptorBuilder;
import seedu.address.testutil.resident.ResidentBuilder;
import seedu.address.testutil.resident.ResidentUtil;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Resident resident = new ResidentBuilder().build();
        AddResidentCommand command = (AddResidentCommand) parser.parseCommand(ResidentUtil.getAddCommand(resident),
                new AddressBook());
        assertEquals(new AddResidentCommand(resident), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD, new AddressBook())
                instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3", new AddressBook())
                instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteResidentCommand command = (DeleteResidentCommand) parser.parseCommand(
                DeleteResidentCommand.COMMAND_WORD + " "
                        + INDEX_FIRST.getOneBased(), new AddressBook());
        assertEquals(new DeleteResidentCommand(INDEX_FIRST), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Resident resident = new ResidentBuilder().build();
        EditResidentDescriptor descriptor = new EditResidentDescriptorBuilder(resident).build();
        EditResidentCommand command = (EditResidentCommand) parser.parseCommand(EditResidentCommand.COMMAND_WORD + " "
                + INDEX_FIRST.getOneBased() + " "
                + ResidentUtil.getEditResidentDescriptorDetails(descriptor), new AddressBook());
        assertEquals(new EditResidentCommand(INDEX_FIRST, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD, new AddressBook())
                instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3", new AddressBook())
                instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindResidentCommand command = (FindResidentCommand) parser.parseCommand(
                FindResidentCommand.COMMAND_WORD + " "
                        + keywords.stream().collect(Collectors.joining(" ")), new AddressBook());
        assertEquals(new FindResidentCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD, new AddressBook())
                instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3", new AddressBook())
                instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListResidentCommand.COMMAND_WORD, new AddressBook())
                instanceof ListResidentCommand);
        assertTrue(parser.parseCommand(ListResidentCommand.COMMAND_WORD + " 3", new AddressBook())
                instanceof ListResidentCommand);
    }

    @Test
    public void parseCommand_history() throws Exception {
        assertTrue(parser.parseCommand(ViewHistoryCommand.COMMAND_WORD, new AddressBook())
                instanceof ViewHistoryCommand);
        assertTrue(parser.parseCommand(ViewHistoryCommand.COMMAND_WORD + " 3", new AddressBook())
                instanceof ViewHistoryCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand("", new AddressBook()));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, ()
            -> parser.parseCommand("unknownCommand", new AddressBook()));
    }
}
