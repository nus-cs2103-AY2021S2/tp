package chim.logic.commands;

import static chim.commons.util.CollectionUtil.requireAllNonNull;
import static chim.logic.parser.CliSyntax.PREFIX_CHEESE_TYPE;
import static chim.logic.parser.CliSyntax.PREFIX_ORDER_DATE;
import static chim.logic.parser.CliSyntax.PREFIX_PHONE;
import static chim.logic.parser.CliSyntax.PREFIX_QUANTITY;
import static java.util.Objects.requireNonNull;

import chim.logic.commands.exceptions.CommandException;
import chim.model.Model;
import chim.model.cheese.CheeseType;
import chim.model.customer.Customer;
import chim.model.customer.Phone;
import chim.model.order.Order;
import chim.model.order.OrderDate;
import chim.model.order.Quantity;

/**
 * Adds an order to CHIM.
 */
public class AddOrderCommand extends AddCommand {

    public static final String COMMAND_WORD = "addorder";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an order to CHIM.\n"
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
    public static final String MESSAGE_NO_CUSTOMERS_FOUND = "No customer in CHIM owns the phone number.";

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
        Order toAdd;

        try {
            toAdd = new Order(toAddCheeseType, toAddQuantity, toAddOrderDate, null, customer.getId());
            model.addOrder(toAdd);
        } catch (IllegalArgumentException e) {
            throw new CommandException(e.getMessage());
        }

        model.setPanelToOrderList();

        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddOrderCommand // instanceof handles nulls
                && toAddQuantity.equals(((AddOrderCommand) other).toAddQuantity)
                && toAddOrderDate.equals(((AddOrderCommand) other).toAddOrderDate));
    }
}
