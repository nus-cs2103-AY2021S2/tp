package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CHEESE_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXPIRY_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MANUFACTURE_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MATURITY_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUANTITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.cheese.Cheese;
import seedu.address.model.cheese.predicates.CheeseCheeseTypePredicate;
import seedu.address.model.customer.Customer;
import seedu.address.model.customer.predicates.CustomerNamePredicate;
import seedu.address.model.order.Order;
import seedu.address.model.order.predicates.OrderIdPredicate;
import seedu.address.testutil.EditCustomerDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditCustomerDescriptor DESC_AMY;
    public static final EditCommand.EditCustomerDescriptor DESC_BOB;

    public static final int VALID_QUANTITY_1 = 1;
    public static final int VALID_QUANTITY_2 = 2;
    public static final int VALID_QUANTITY_5 = 5;
    public static final String VALID_CHEESE_TYPE_BRIE = "Brie";
    public static final String VALID_CHEESE_TYPE_CAMEMBERT = "Camembert";
    public static final String VALID_CHEESE_TYPE_FETA = "Feta";
    public static final String VALID_CHEESE_TYPE_MOZZARELLA = "Mozzarella";
    public static final String VALID_MANUFACTURE_DATE_1 = "2021-03-04 07:00";
    public static final String VALID_MANUFACTURE_DATE_2 = "2021-03-06 07:00";
    public static final String VALID_MANUFACTURE_DATE_3 = "2021-03-10 07:00";
    public static final String VALID_MANUFACTURE_DATE_4 = "2021-03-12 07:00";
    public static final String VALID_MATURITY_DATE_1 = "2021-04-04 07:00";
    public static final String VALID_MATURITY_DATE_2 = "2021-04-06 07:00";
    public static final String VALID_MATURITY_DATE_3 = "2021-04-10 07:00";
    public static final String VALID_MATURITY_DATE_4 = "2021-04-15 07:00";
    public static final String VALID_EXPIRY_DATE_1 = "2022-03-04 06:00";
    public static final String VALID_EXPIRY_DATE_2 = "2023-04-04 06:00";
    public static final String VALID_EXPIRY_DATE_3 = "2024-04-10 06:00";
    public static final String VALID_EXPIRY_DATE_4 = "2025-04-15 06:00";
    public static final String VALID_CHEESE_ASSIGNED_STATUS = "assigned";
    public static final String VALID_CHEESE_UNASSIGNED_STATUS = "unassigned";
    public static final String VALID_ORDER_DATE_1 = "2021-02-05 07:00";
    public static final String VALID_ORDER_DATE_2 = "2021-03-06 08:00";
    public static final String VALID_ORDER_DATE_3 = "2021-03-07 08:00";
    public static final String VALID_ORDER_DATE_4 = "2021-03-08 08:00";
    public static final String VALID_COMPLETED_DATE_1 = "2022-02-10 13:00";
    public static final String VALID_COMPLETED_DATE_2 = "2022-03-06 10:00";

    public static final String CHEESE_TYPE_DESC_CAMEMBERT = " " + PREFIX_CHEESE_TYPE + VALID_CHEESE_TYPE_CAMEMBERT;
    public static final String CHEESE_TYPE_DESC_FETA = " " + PREFIX_CHEESE_TYPE + VALID_CHEESE_TYPE_FETA;
    public static final String QUANTITY_DESC = " " + PREFIX_QUANTITY + VALID_QUANTITY_1;
    public static final String QUANTITY_5_DESC = " " + PREFIX_QUANTITY + VALID_QUANTITY_5;
    public static final String MANUFACTURE_DATE_DESC = " " + PREFIX_MANUFACTURE_DATE + VALID_MANUFACTURE_DATE_1;
    public static final String MATURITY_DATE_DESC = " " + PREFIX_MATURITY_DATE + VALID_MATURITY_DATE_1;
    public static final String EXPIRY_DATE_DESC = " " + PREFIX_EXPIRY_DATE + VALID_EXPIRY_DATE_1;
    public static final String ORDER_DATE_DESC = " " + PREFIX_ORDER_DATE + VALID_ORDER_DATE_1;
    public static final String ORDER_DATE_DESC_FETA = " " + PREFIX_ORDER_DATE + VALID_ORDER_DATE_2;


    public static final String INVALID_CHEESE_TYPE_DESC = " " + PREFIX_CHEESE_TYPE + ""; // Blanks not allowed
    public static final String INVALID_QUANTITY_DESC = " " + PREFIX_QUANTITY + "911a"; // 'a' not allowed in quantity
    public static final String INVALID_ORDER_DATE_DESC = " " + PREFIX_ORDER_DATE + "20-05$-2020";
    public static final String INVALID_MANUFACTURE_DATE_DESC = " " + PREFIX_MANUFACTURE_DATE + "20-05-2020";
    public static final String INVALID_MATURITY_DATE_DESC = " " + PREFIX_MATURITY_DATE + "2020/12-30";
    public static final String INVALID_EXPIRY_DATE_DESC = " " + PREFIX_EXPIRY_DATE + "12 Feb 2020";


    static {
        DESC_AMY = new EditCustomerDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditCustomerDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered customer list and selected customer in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Customer> expectedFilteredList = new ArrayList<>(actualModel.getFilteredCustomerList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredCustomerList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the customer at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showCustomerAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredCustomerList().size());

        Customer customer = model.getFilteredCustomerList().get(targetIndex.getZeroBased());
        final String[] splitName = customer.getName().fullName.split("\\s+");
        model.updateFilteredCustomerList(new CustomerNamePredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredCustomerList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the cheese at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showCheeseAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredCheeseList().size());

        Cheese cheese = model.getFilteredCheeseList().get(targetIndex.getZeroBased());
        final String[] splitName = cheese.getCheeseType().toString().split("\\s+");
        model.updateFilteredCheeseList(new CheeseCheeseTypePredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredCheeseList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the order at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showOrderAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredOrderList().size());

        Order order = model.getFilteredOrderList().get(targetIndex.getZeroBased());
        final String[] splitName = order.getOrderId().toString().split("\\s+");
        model.updateFilteredOrderList(new OrderIdPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredOrderList().size());
    }
}
