package seedu.timeforwheels.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.timeforwheels.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.timeforwheels.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.timeforwheels.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.timeforwheels.testutil.Assert.assertThrows;
import static seedu.timeforwheels.testutil.TypicalIndexes.INDEX_FIRST_CUSTOMER;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.timeforwheels.logic.commands.AddCommand;
import seedu.timeforwheels.logic.commands.ClearCommand;
import seedu.timeforwheels.logic.commands.DeleteCommand;
import seedu.timeforwheels.logic.commands.EditCommand;
import seedu.timeforwheels.logic.commands.EditCommand.EditCustomerDescriptor;
import seedu.timeforwheels.logic.commands.ExitCommand;
import seedu.timeforwheels.logic.commands.FindCommand;
import seedu.timeforwheels.logic.commands.HelpCommand;
import seedu.timeforwheels.logic.commands.ListCommand;
import seedu.timeforwheels.logic.commands.RemarkCommand;
import seedu.timeforwheels.logic.parser.exceptions.ParseException;
import seedu.timeforwheels.model.customer.Customer;
import seedu.timeforwheels.model.customer.NameContainsKeywordsPredicate;
import seedu.timeforwheels.model.customer.Remark;
import seedu.timeforwheels.testutil.CustomerBuilder;
import seedu.timeforwheels.testutil.CustomerUtil;
import seedu.timeforwheels.testutil.EditCustomerDescriptorBuilder;

public class DeliveryListParserTest {

    private final DeliveryListParser parser = new DeliveryListParser();

    @Test
    public void parseCommand_add() throws Exception {
        Customer customer = new CustomerBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(CustomerUtil.getAddCommand(customer));
        assertEquals(new AddCommand(customer), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_CUSTOMER.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_CUSTOMER), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Customer customer = new CustomerBuilder().build();
        EditCustomerDescriptor descriptor = new EditCustomerDescriptorBuilder(customer).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_CUSTOMER.getOneBased() + " " + CustomerUtil.getEditCustomerDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_CUSTOMER, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(FindCommand.COMMAND_WORD + " "
            + keywords.stream().collect(Collectors.joining(" ")));
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
    public void parseCommand_remark() throws Exception {
        final Remark remark = new Remark("Some remark.");
        RemarkCommand command = (RemarkCommand) parser.parseCommand(RemarkCommand.COMMAND_WORD + " "
                + INDEX_FIRST_CUSTOMER.getOneBased() + " " + PREFIX_REMARK + remark.value);
        assertEquals(new RemarkCommand(INDEX_FIRST_CUSTOMER, remark), command);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, ()
            -> parser.parseCommand("unknownCommand"));
    }
}
