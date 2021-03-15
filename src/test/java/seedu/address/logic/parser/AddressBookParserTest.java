package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCustomers.ALICE;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CHEESE;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CUSTOMER;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ORDER;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCheeseCommand;
import seedu.address.logic.commands.AddCustomerCommand;
import seedu.address.logic.commands.AddOrderCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCheeseCommand;
import seedu.address.logic.commands.DeleteCustomerCommand;
import seedu.address.logic.commands.DeleteOrderCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommandStub;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCustomerCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCheesesCommand;
import seedu.address.logic.commands.ListCustomersCommand;
import seedu.address.logic.commands.ListOrdersCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.CheeseIdStub;
import seedu.address.model.CustomerIdStub;
import seedu.address.model.OrderIdStub;
import seedu.address.model.cheese.Cheese;
import seedu.address.model.customer.Customer;
import seedu.address.model.customer.Phone;
import seedu.address.model.customer.predicates.CustomerNamePredicate;
import seedu.address.model.order.Order;
import seedu.address.model.util.ModelCompositePredicate;
import seedu.address.testutil.CheeseBuilder;
import seedu.address.testutil.CheeseUtil;
import seedu.address.testutil.CustomerBuilder;
import seedu.address.testutil.CustomerUtil;
import seedu.address.testutil.EditCustomerDescriptorBuilder;
import seedu.address.testutil.OrderBuilder;
import seedu.address.testutil.OrderUtil;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_addCustomer() throws Exception {
        Customer customer = new CustomerBuilder().withId(CustomerIdStub.getNextId()).build();
        AddCustomerCommand command = (AddCustomerCommand) parser.parseCommand(CustomerUtil.getAddCommand(customer));
        assertEquals(new AddCustomerCommand(customer), command);
    }

    @Test
    public void parseCommand_addOrder() throws Exception {
        Order order = new OrderBuilder().withOrderId(OrderIdStub.getNextId()).build();
        Phone phone = new Phone("65558888");
        AddOrderCommand command = (AddOrderCommand) parser.parseCommand(OrderUtil.getAddCommand(order, phone));
        assertEquals(new AddOrderCommand(order.getCheeseType(), phone,
                order.getQuantity(), order.getOrderDate()), command);
    }

    @Test
    public void parseCommand_addCheese() throws Exception {
        Cheese cheese = new CheeseBuilder().withId(CheeseIdStub.getNextId()).build();
        AddCheeseCommand command = (AddCheeseCommand) parser.parseCommand(CheeseUtil.getAddCommand(cheese, 1));
        assertEquals(new AddCheeseCommand(new Cheese[] { cheese }), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_deleteCustomer() throws Exception {
        DeleteCustomerCommand command = (DeleteCustomerCommand) parser.parseCommand(
                DeleteCustomerCommand.COMMAND_WORD + " " + PREFIX_PHONE + ALICE.getPhone());
        assertEquals(new DeleteCustomerCommand(ALICE.getPhone()), command);
    }

    @Test
    public void parseCommand_deleteCheese() throws Exception {
        DeleteCheeseCommand command = (DeleteCheeseCommand) parser.parseCommand(
                DeleteCheeseCommand.COMMAND_WORD + " " + INDEX_FIRST_CHEESE.getOneBased());
        assertEquals(new DeleteCheeseCommand(INDEX_FIRST_CHEESE), command);
    }

    @Test
    public void parseCommand_deleteOrder() throws Exception {
        DeleteOrderCommand command = (DeleteOrderCommand) parser.parseCommand(
                DeleteOrderCommand.COMMAND_WORD + " " + INDEX_FIRST_ORDER.getOneBased());
        assertEquals(new DeleteOrderCommand(INDEX_FIRST_ORDER), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Customer customer = new CustomerBuilder().withId(CustomerIdStub.getNextId()).build();
        EditCommand.EditCustomerDescriptor descriptor = new EditCustomerDescriptorBuilder(customer).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_CUSTOMER.getOneBased() + " " + CustomerUtil.getEditCustomerDescriptorDetails(descriptor));
        command = new EditCommandStub(command, descriptor);
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
        FindCustomerCommand command = (FindCustomerCommand) parser.parseCommand(
                FindCustomerCommand.COMMAND_WORD + " " + PREFIX_NAME
                    + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCustomerCommand(new ModelCompositePredicate<>(
            new CustomerNamePredicate(keywords))
        ), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_listCustomers() throws Exception {
        assertTrue(parser.parseCommand(ListCustomersCommand.COMMAND_WORD) instanceof ListCustomersCommand);
        assertTrue(parser.parseCommand(ListCustomersCommand.COMMAND_WORD + " 3") instanceof ListCustomersCommand);
    }

    @Test
    public void parseCommand_listCheeses() throws Exception {
        assertTrue(parser.parseCommand(ListCheesesCommand.COMMAND_WORD) instanceof ListCheesesCommand);
        assertTrue(parser.parseCommand(ListCheesesCommand.COMMAND_WORD + " 3") instanceof ListCheesesCommand);
    }

    @Test
    public void parseCommand_listOrders() throws Exception {
        assertTrue(parser.parseCommand(ListOrdersCommand.COMMAND_WORD) instanceof ListOrdersCommand);
        assertTrue(parser.parseCommand(ListOrdersCommand.COMMAND_WORD + " 3") instanceof ListOrdersCommand);
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
