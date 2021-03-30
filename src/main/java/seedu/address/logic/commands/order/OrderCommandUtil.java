package seedu.address.logic.commands.order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.Pair;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.dish.Dish;
import seedu.address.model.order.Order;
import seedu.address.model.person.Person;

public class OrderCommandUtil {
    public static final String MESSAGE_DUPLICATE_ORDER = "This order already exists in the order list";
    public static final String MESSAGE_CUSTOMER_INDEX_INVALID = "Invalid customer index!";
    public static final String MESSAGE_DISH_NOT_FOUND = "Dish in order not found on the menu";

    /**
     * Constructs a valid Order from the given parameters.
     * @param dateTime Date and time the order is to be delivered at.
     * @param customerOneIndex Customer's ID number
     * @param dishNumberQuantityList List of pairs of the form (dish index, dish quantity).
     * @param model The model object.
     * @return Constructed Order object.
     * @throws CommandException If any parameters are invalid.
     */
    public static Order constructValidOrder(LocalDateTime dateTime, Integer customerOneIndex,
                                            List<Pair<Integer, Integer>> dishNumberQuantityList,
                                            Model model) throws CommandException {

        int customerZeroIndex = Index.fromOneBased(customerOneIndex).getZeroBased();

        if (customerZeroIndex >= model.getFilteredPersonList().size()) {
            throw new CommandException(MESSAGE_CUSTOMER_INDEX_INVALID);
        }

        assert (customerZeroIndex >= 0 && customerZeroIndex < model.getFilteredPersonList().size())
                : "Invalid customer index called";

        Person customer = model.getPersonByIndex(customerZeroIndex);

        assert (customer != null) : "Attempting to construct order with non-existent customer";

        List<Pair<Dish, Integer>> dishQuantityList = new ArrayList<>();

        for (Pair<Integer, Integer> dishIdPair : dishNumberQuantityList) {

            Integer dishId = Index.fromOneBased(dishIdPair.getKey()).getZeroBased();
            Integer dishQuant = dishIdPair.getValue();

            if (dishId >= model.getFilteredDishList().size()) {
                throw new CommandException(MESSAGE_DISH_NOT_FOUND);
            }

            Dish dish = model.getDishByIndex(dishId);
            Pair<Dish, Integer> dishQuantPair = new Pair<>(dish, dishQuant);

            dishQuantityList.add(dishQuantPair);
        }

        Order toAdd = new Order(dateTime, customer, dishQuantityList);

        if (model.hasOrder(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ORDER);
        }

        return toAdd;
    }
}
