package seedu.address.logic.commands.order;

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
    private static final String MESSAGE_NOT_ENOUGH_INGREDIENTS_ORDER = "Insufficient inventory to fulfil order";

    /**
     * Checks whether an order is a valid addition.
     * @param toAdd Candidate order to be added.
     * @param model The model object.
     * @return True if the order is a valid addition.
     * @throws CommandException If any parameters are invalid.
     */
    public static boolean isValidOrderAddition(Order toAdd, Model model) throws CommandException {

        if (model.hasOrder(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ORDER);
        }

        if (!model.canFulfilOrder(toAdd)) {
            throw new CommandException(MESSAGE_NOT_ENOUGH_INGREDIENTS_ORDER);
        }

        return true;
    }

    /**
     * Looks up dishes given a list of their IDs.
     * @param dishNumberQuantityList List of IDs to be looked up.
     * @param model The model object.
     * @return List of dishes corresponding to the input IDs.
     * @throws CommandException If any input IDs are invalid.
     */
    public static List<Pair<Dish, Integer>> lookupDishIds(List<Pair<Index, Integer>> dishNumberQuantityList,
                                                          Model model) throws CommandException {
        List<Pair<Dish, Integer>> dishQuantityList = new ArrayList<>();

        for (Pair<Index, Integer> dishIdPair : dishNumberQuantityList) {

            Integer dishId = dishIdPair.getKey().getZeroBased();
            Integer dishQuantity = dishIdPair.getValue();

            if (dishId >= model.getFilteredDishList().size()) {
                throw new CommandException(MESSAGE_DISH_NOT_FOUND);
            }

            Dish dish = model.getDishByIndex(dishId);
            Pair<Dish, Integer> dishQuantityPair = new Pair<>(dish, dishQuantity);

            dishQuantityList.add(dishQuantityPair);
        }

        return dishQuantityList;
    }

    public static Person getValidCustomerByOneIndex(int customerId, Model model) throws CommandException {
        int customerIndex = Index.fromOneBased(customerId).getZeroBased();

        if (customerIndex >= model.getFilteredPersonList().size()) {
            throw new CommandException(MESSAGE_CUSTOMER_INDEX_INVALID);
        }

        Person customer = model.getPersonByIndex(customerIndex);

        return customer;
    }
}
