package seedu.address.logic.commands.menu;

import seedu.address.commons.core.Pair;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.dish.Dish;
import seedu.address.model.ingredient.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class MenuCommandUtil {
    public static final String MESSAGE_DUPLICATE_DISH = "This dish already exists in the menu";
    public static final String MESSAGE_INGREDIENT_NOT_FOUND = "Ingredient in dish not found in the inventory";

    public static Dish constructValidDish(String name, double price,
                                          List<Pair<Integer, Integer>> ingredientIdsList,
                                          Model model) throws CommandException {

        List<Pair<Ingredient, Integer>> ingredientQuantityList = new ArrayList<>();

        for (Pair<Integer, Integer> ingredientPair : ingredientIdsList) {
            int ingredientId = Index.fromOneBased(ingredientPair.getKey()).getOneBased();
            Integer ingredientQuant = ingredientPair.getValue();

            if (ingredientId >= model.getFilteredDishList().size()) {
                throw new CommandException(MESSAGE_INGREDIENT_NOT_FOUND);
            }

            Ingredient ingredient = model.getIngredientByIndex(ingredientId);
            Pair<Ingredient, Integer> ingredientQuantPair = new Pair<>(ingredient, ingredientQuant);

            ingredientQuantityList.add(ingredientQuantPair);
        }

        Dish toAdd = new Dish(name, price, ingredientQuantityList);

        if (model.hasDish(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_DISH);
        }

        return toAdd;
    }


}
