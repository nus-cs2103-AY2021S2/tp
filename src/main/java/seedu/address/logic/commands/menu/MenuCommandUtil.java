package seedu.address.logic.commands.menu;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.Pair;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.dish.Dish;
import seedu.address.model.ingredient.Ingredient;

public class MenuCommandUtil {
    public static final String MESSAGE_DUPLICATE_DISH = "This dish already exists in the menu";
    public static final String MESSAGE_INGREDIENT_NOT_FOUND = "Ingredient in dish not found in the inventory";

    /**
     * Checks whether a dish is a valid addition.
     * @param toAdd Candidate dish to be added.
     * @param model The model object.
     * @return True if the dish is a valid addition.
     * @throws CommandException If the dish is an invalid addition.
     */
    public static boolean isValidDishAddition(Dish toAdd, Model model) throws CommandException {

        if (model.hasDish(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_DISH);
        }

        return true;
    }

    /**
     * Looks up ingredients given a list of their IDs.
     * @param ingredientIdsList List of IDs to be looked up.
     * @param model The model object.
     * @return List of ingredients corresponding to the input IDs.
     * @throws CommandException If any input IDs are invalid.
     */
    public static List<Pair<Ingredient, Integer>> lookupIngredientIds(
            List<Pair<Index, Integer>> ingredientIdsList,
            Model model) throws CommandException {

        List<Pair<Ingredient, Integer>> ingredientQuantityList = new ArrayList<>();

        for (Pair<Index, Integer> ingredientPair : ingredientIdsList) {
            int ingredientId = ingredientPair.getKey().getZeroBased();
            Integer ingredientQuantity = ingredientPair.getValue();

            if (ingredientId >= model.getFilteredIngredientList().size()) {
                throw new CommandException(MESSAGE_INGREDIENT_NOT_FOUND);
            }

            Ingredient ingredient = model.getIngredientByIndex(ingredientId);
            Pair<Ingredient, Integer> ingredientQuantityPair = new Pair<>(ingredient, ingredientQuantity);

            ingredientQuantityList.add(ingredientQuantityPair);
        }

        return ingredientQuantityList;
    }
}
