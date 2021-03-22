package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PASSENGER;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.PoolCommand;
import seedu.address.logic.commands.UnpoolCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.driver.Driver;
import seedu.address.model.person.passenger.NameContainsKeywordsPredicate;
import seedu.address.model.person.passenger.Passenger;
import seedu.address.testutil.CommuterBuilder;
import seedu.address.testutil.DriverBuilder;
import seedu.address.testutil.DriverUtil;
import seedu.address.testutil.EditPassengerDescriptorBuilder;
import seedu.address.testutil.PassengerBuilder;
import seedu.address.testutil.PassengerUtil;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Passenger passenger = new PassengerBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(PassengerUtil.getAddCommand(passenger));
        assertEquals(new AddCommand(passenger), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PASSENGER.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_PASSENGER), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Passenger passenger = new PassengerBuilder().build();
        EditCommand.EditPassengerDescriptor descriptor = new EditPassengerDescriptorBuilder(passenger).build();
        EditCommand command = (EditCommand) parser.parseCommand(
                EditCommand.COMMAND_WORD + " " + INDEX_FIRST_PASSENGER.getOneBased() + " "
                        + PassengerUtil.getEditPassengerDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_PASSENGER, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo");
        String userInput = FindCommand.COMMAND_WORD + " " + "n/foo";
        FindCommand command = (FindCommand) parser.parseCommand(userInput);
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_pool() throws Exception {
        Driver driver = new DriverBuilder().build();
        Set<Index> commuters = new CommuterBuilder().build();
        PoolCommand command = (PoolCommand) parser.parseCommand(DriverUtil.getPoolCommand(driver, commuters));

        assertEquals(new PoolCommand(driver, commuters), command);
    }

    @Test
    public void parseCommand_unpool() throws Exception {
        Driver driver = new DriverBuilder().build();
        UnpoolCommand command = (UnpoolCommand) parser.parseCommand(DriverUtil.getUnpoolCommand(driver));

        assertEquals(new UnpoolCommand(driver), command);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
