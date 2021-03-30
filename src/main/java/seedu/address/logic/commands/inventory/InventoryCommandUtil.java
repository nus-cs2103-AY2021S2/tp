package seedu.address.logic.commands.inventory;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ingredient.Ingredient;

public class InventoryCommandUtil {
    public static final String MESSAGE_DUPLICATE_INGREDIENT = "This ingredient already exists in the inventory";

    public static boolean isValidIngredient(Ingredient ingredient, Model model) throws CommandException {
        if (model.hasIngredient(ingredient)) {
            throw new CommandException(MESSAGE_DUPLICATE_INGREDIENT);
        }
        return true;
    }
}
