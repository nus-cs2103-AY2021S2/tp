package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CHEESE_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUANTITY;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.cheese.CheeseType;
import seedu.address.model.customer.Customer;
import seedu.address.model.customer.Phone;
import seedu.address.model.order.Order;
import seedu.address.model.order.OrderDate;
import seedu.address.model.order.Quantity;

/**
 * Adds an order to the address book.
 */
public class AddOrderCommand extends AddCommand {

    public static final String COMMAND_WORD = "addorder";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an order to the address book.\n"
            + "Parameters: "
            + PREFIX_CHEESE_TYPE + "CHEESE TYPE "
            + PREFIX_QUANTITY + "QUANTITY "
            + PREFIX_PHONE + "PHONE "
            + "[" + PREFIX_ORDER_DATE + "ORDER DATE]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_CHEESE_TYPE + "Parmesan "
            + PREFIX_QUANTITY + "2 "
            + PREFIX_PHONE + "65555555 "
            + PREFIX_ORDER_DATE + "2020-12-30";

    public static final String MESSAGE_SUCCESS = "New order added: %1$s";
    public static final String MESSAGE_NO_CUSTOMERS_FOUND = "No customer in the address book owns the phone number";

    private final CheeseType toAddCheeseType;
    private final Phone customerPhone;
    private final Quantity toAddQuantity;
    private final OrderDate toAddOrderDate;

    /**
     * Creates an AddOrderCommand to add the specified {@code Order}
     */
    public AddOrderCommand(CheeseType cheeseType, Phone phone, Quantity quantity, OrderDate orderDate) {
        requireAllNonNull(cheeseType, phone, quantity, orderDate);
        toAddCheeseType = cheeseType;
        customerPhone = phone;
        toAddQuantity = quantity;
        toAddOrderDate = orderDate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasCustomerWithPhone(customerPhone)) {
            throw new CommandException(MESSAGE_NO_CUSTOMERS_FOUND);
        }

        Customer customer = model.getCustomerWithPhone(customerPhone);
        Order toAdd = new Order(toAddCheeseType, toAddQuantity, toAddOrderDate, null, customer.getId());

        model.addOrder(toAdd);
        model.setPanelToOrderList();

        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddOrderCommand // instanceof handles nulls
                && toAddCheeseType.equals(((AddOrderCommand) other).toAddCheeseType)
                && toAddQuantity.equals(((AddOrderCommand) other).toAddQuantity)
                && toAddOrderDate.equals(((AddOrderCommand) other).toAddOrderDate)
                && toAddCheeseType.equals(((AddOrderCommand) other).toAddCheeseType));
    }
}
