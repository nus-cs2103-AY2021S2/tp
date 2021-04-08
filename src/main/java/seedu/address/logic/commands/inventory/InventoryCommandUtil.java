package seedu.address.logic.commands.inventory;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ingredient.Ingredient;

public class InventoryCommandUtil {
    public static final String MESSAGE_DUPLICATE_INGREDIENT = "This ingredient already exists in the inventory";

    /**
     * Checks whether the Ingredient is a valid entry.
     * @param ingredient Candidate Ingredient to be added.
     * @param model The model object.
     * @return true if the Ingredient is a valid entry.
     * @throws CommandException If the Ingredient is an invalid entry.
     */
    public static boolean isValidIngredient(Ingredient ingredient, Model model) throws CommandException {
        if (model.hasIngredient(ingredient)) {
//            throw new CommandException(MESSAGE_DUPLICATE_INGREDIENT);
            return false;
        }
        return true;
    }
}
