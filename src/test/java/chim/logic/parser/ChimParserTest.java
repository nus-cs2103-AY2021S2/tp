package chim.logic.parser;

import static chim.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static chim.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static chim.logic.parser.CliSyntax.PREFIX_NAME;
import static chim.logic.parser.CliSyntax.PREFIX_PHONE;
import static chim.testutil.Assert.assertThrows;
import static chim.testutil.TypicalCustomers.ALICE;
import static chim.testutil.TypicalIndexes.INDEX_FIRST_CHEESE;
import static chim.testutil.TypicalIndexes.INDEX_FIRST_CUSTOMER;
import static chim.testutil.TypicalIndexes.INDEX_FIRST_ORDER;
import static chim.testutil.TypicalModels.getTypicalChim;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import chim.logic.commands.AddCheeseCommand;
import chim.logic.commands.AddCustomerCommand;
import chim.logic.commands.AddOrderCommand;
import chim.logic.commands.ClearCommand;
import chim.logic.commands.DeleteCheeseCommand;
import chim.logic.commands.DeleteCustomerCommand;
import chim.logic.commands.DeleteOrderCommand;
import chim.logic.commands.EditCommandStub;
import chim.logic.commands.EditCustomerCommand;
import chim.logic.commands.ExitCommand;
import chim.logic.commands.FindCustomerCommand;
import chim.logic.commands.HelpCommand;
import chim.logic.commands.ListCheesesCommand;
import chim.logic.commands.ListCustomersCommand;
import chim.logic.commands.ListOrdersCommand;
import chim.logic.parser.exceptions.ParseException;
import chim.model.CheeseIdStub;
import chim.model.CustomerIdStub;
import chim.model.Model;
import chim.model.ModelManager;
import chim.model.OrderIdStub;
import chim.model.UserPrefs;
import chim.model.cheese.Cheese;
import chim.model.customer.Customer;
import chim.model.customer.Phone;
import chim.model.customer.predicates.CustomerNamePredicate;
import chim.model.order.Order;
import chim.model.util.predicate.CompositeFieldPredicate;
import chim.testutil.CheeseBuilder;
import chim.testutil.CheeseUtil;
import chim.testutil.CustomerBuilder;
import chim.testutil.CustomerUtil;
import chim.testutil.EditCustomerDescriptorBuilder;
import chim.testutil.OrderBuilder;
import chim.testutil.OrderUtil;

public class ChimParserTest {

    private final Model model = new ModelManager(getTypicalChim(), new UserPrefs());
    private final ChimParser parser = new ChimParser(model);

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
        EditCustomerCommand.EditCustomerDescriptor descriptor = new EditCustomerDescriptorBuilder(customer).build();
        EditCustomerCommand command = (EditCustomerCommand) parser.parseCommand(EditCustomerCommand.COMMAND_WORD + " "
                + INDEX_FIRST_CUSTOMER.getOneBased() + " " + CustomerUtil.getEditCustomerDescriptorDetails(descriptor));
        command = new EditCommandStub(command, descriptor);
        assertEquals(new EditCustomerCommand(INDEX_FIRST_CUSTOMER, descriptor), command);
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
        assertEquals(new FindCustomerCommand(new CompositeFieldPredicate<>(
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
