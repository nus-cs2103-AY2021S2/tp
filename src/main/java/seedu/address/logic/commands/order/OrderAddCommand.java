package seedu.address.logic.commands.order;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DISH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUANTITY;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.Pair;
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
            + PREFIX_NAME + "CUSTOMER_NAME "
            + PREFIX_DATETIME + "DELIVERY_DATETIME (DD-MM-YYYY HH:MM) "
            + "[" + PREFIX_DISH + "DISH "
            + PREFIX_QUANTITY + " QUANTITY]...\n"
            + "Example: " + COMPONENT_WORD + " " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_DATETIME + "14-02-2021 18:30 "
            + PREFIX_DISH + "1 "
            + PREFIX_QUANTITY + "1 "
            + PREFIX_DISH + "3 "
            + PREFIX_QUANTITY + "1";

    public static final String MESSAGE_SUCCESS = "New order added: %1$s";
    public static final String MESSAGE_DUPLICATE_ORDER = "This order already exists in the order list";
    public static final String MESSAGE_CUSTOMER_NOT_FOUND = "This customer doesn't exist in the address book";
    public static final String MESSAGE_DISH_NOT_FOUND = "This dish doesn't exist on the menu";

    private final String datetime;
    private final String customerName;
    private final List<Pair<Integer, Integer>> dishNumberQuantityList;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public OrderAddCommand(String datetime, String customerName,
                           List<Pair<Integer, Integer>> dishNumberQuantityList) {
        this.datetime = datetime;
        this.customerName = customerName;
        this.dishNumberQuantityList = dishNumberQuantityList;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Person customer = null;
        boolean foundCustomer = false;

        for (Person person : model.getFilteredPersonList()) {
            if (person.getName().toString().equals(customerName)) {
                customer = person;
                foundCustomer = true;
            }
        }

        if (!foundCustomer) {
            throw new CommandException(MESSAGE_CUSTOMER_NOT_FOUND);
        }

        assert (customer != null) : " Attempting to construct order with non-existent customer";

        List<Pair<Dish, Integer>> dishQuantityList = new ArrayList<>();

        for (int idx = 0; idx < dishNumberQuantityList.size(); idx++) {
            Integer dishID = dishNumberQuantityList.get(idx).getKey() - 1;
            Integer dishQuant = dishNumberQuantityList.get(idx).getValue();

            if (dishID >= model.getFilteredDishList().size()) {
                throw new CommandException(MESSAGE_DISH_NOT_FOUND);
            }

            Dish dish = model.getFilteredDishList().get(dishID);
            Pair<Dish, Integer> dishQuantPair = new Pair<>(dish, dishQuant);

            dishQuantityList.add(dishQuantPair);
        }

        Order toAdd = new Order(datetime, customer, dishQuantityList);

        if (model.hasOrder(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ORDER);
        }

        model.addOrder(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof OrderAddCommand // instanceof handles nulls
                && datetime.equals(((OrderAddCommand) other).datetime)
                && customerName.equals(((OrderAddCommand) other).customerName)
                && dishNumberQuantityList.equals(((OrderAddCommand) other).dishNumberQuantityList));
    }
}
