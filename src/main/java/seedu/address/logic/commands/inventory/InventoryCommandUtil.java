package seedu.address.logic.commands.inventory;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ingredient.Ingredient;

public class InventoryCommandUtil {
    public static final String MESSAGE_INVALID_QUANTITY = "The quantity entered is invalid, "
            + "please enter a positive number only!";
    /**
     * Checks whether the Ingredient is a valid entry.
     * @param ingredient Candidate Ingredient to be added.
     * @param model The model object.
     * @return true if the Ingredient is a valid entry.
     * @throws CommandException If the Ingredient is an invalid entry.
     */
    public static boolean isValidIngredient(Ingredient ingredient, Model model) throws CommandException {
        if (ingredient.getQuantity() < 0) {
            throw new CommandException(MESSAGE_INVALID_QUANTITY);
        }
        return true;
    }
}
