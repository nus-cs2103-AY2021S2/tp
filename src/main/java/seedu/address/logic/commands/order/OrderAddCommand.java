package seedu.address.logic.commands.order;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DISH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUANTITY;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

import seedu.address.commons.core.Pair;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.dish.Dish;
import seedu.address.model.order.Order;
import seedu.address.model.person.Person;

/**
 * Adds a person to the address book.
 */
public class OrderAddCommand extends Command {

    public static final String COMPONENT_WORD = "order";
    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMPONENT_WORD + " " + COMMAND_WORD
        + " Adds an order to the order list. "
        + "Parameters: "
        + PREFIX_NAME + "CUSTOMER_ID "
        + PREFIX_DATETIME + "DELIVERY_DATETIME (DD-MM-YYYY HH:MM) "
        + "[" + PREFIX_DISH + "DISH "
        + PREFIX_QUANTITY + " QUANTITY]...\n"
        + "Example: " + COMPONENT_WORD + " " + COMMAND_WORD + " "
        + PREFIX_NAME + "2 "
        + PREFIX_DATETIME + "14-02-2021 18:30 "
        + PREFIX_DISH + "1 "
        + PREFIX_QUANTITY + "1 "
        + PREFIX_DISH + "3 "
        + PREFIX_QUANTITY + "1";

    public static final String MESSAGE_SUCCESS = "New order added: %1$s";

    private final LocalDateTime dateTime;
    private final Integer customerId;
    private final List<Pair<Index, Integer>> dishNumberQuantityList;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public OrderAddCommand(LocalDateTime dateTime, Integer customerId,
                           List<Pair<Index, Integer>> dishNumberQuantityList) {
        this.dateTime = dateTime;
        this.customerId = customerId;
        this.dishNumberQuantityList = dishNumberQuantityList;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Pair<Dish, Integer>> dishQuantityList =
                OrderCommandUtil.lookupDishIds(dishNumberQuantityList, model);

        Person customer = OrderCommandUtil.getValidCustomerByOneIndex(customerId, model);

        Order toAdd = new Order(dateTime, customer, dishQuantityList, Order.State.UNCOMPLETED);

        // isValidOrderAddition throws a CommandException and hence acts as a guard clause
        OrderCommandUtil.isValidOrderAddition(toAdd, model);
        model.addOrder(toAdd);
        model.decreaseIngredientByOrder(toAdd);

        model.updateFilteredOrderList(order -> order.getState() == Order.State.UNCOMPLETED);
        Comparator<Order> comparator = new OrderChronologicalComparator();
        model.updateFilteredOrderList(comparator);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd), CommandResult.CRtype.ORDER);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof OrderAddCommand // instanceof handles nulls
                && dateTime.equals(((OrderAddCommand) other).dateTime)
                && customerId.equals(((OrderAddCommand) other).customerId)
                && dishNumberQuantityList.equals(((OrderAddCommand) other).dishNumberQuantityList));
    }
}
