package seedu.address.logic.commands.inventory;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.dish.Dish;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.order.Order;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class InventoryDeleteCommand extends Command {

    public static final String COMPONENT_WORD = "inventory";
    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMPONENT_WORD + " " + COMMAND_WORD
            + ": Deletes the ingredient identified by the index number used in the displayed inventory.\n"
            + "Parameters: [INDEX] (-f)\n"
            + "Example: " + COMPONENT_WORD + " " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_INGREDIENT_SUCCESS = "Deleted ingredient: %1$s";
    public static final String MESSAGE_DELETE_INGREDIENT_FAILURE =
            "Failed to delete ingredient: %1$s due to associated dishes, "
                    + "add -f flag to force delete the ingredient\n"
                    + "Warning: This will delete any dishes that contains %1$s\n"
                    + "\t\t   This will also mark associated orders as 'Cancelled'";

    private final Index targetIndex;
    private final boolean isForce;

    /**
     * Forces delete dish with given index along with associated ingredients
     * @param targetIndex index in ingredient list
     * @param isForce forces delete a dish
     */
    public InventoryDeleteCommand(Index targetIndex, boolean isForce) {
        this.targetIndex = targetIndex;
        this.isForce = isForce;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Ingredient> lastShownList = model.getFilteredIngredientList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(
                    String.format(Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX, Messages.ITEM_INGREDIENT));
        }

        Ingredient ingredientToDelete = lastShownList.get(targetIndex.getZeroBased());

        List<Dish> dishesToCascade = model.getDishesByIngredients(ingredientToDelete);
        boolean dishesContainsIngredient = !dishesToCascade.isEmpty();

        if (dishesContainsIngredient && !isForce) {
            throw new CommandException(String.format(MESSAGE_DELETE_INGREDIENT_FAILURE, ingredientToDelete.getName()));
        }

        for (Dish dishToDelete: dishesToCascade) {
            List<Order> outstandingOrders = model.getIncompleteOrdersContainingDish(dishToDelete);
            model.cancelOrders(outstandingOrders);
            model.deleteDish(dishToDelete);
        }
        model.deleteIngredient(ingredientToDelete);

        // Delete dish here from model
        return new CommandResult(String.format(MESSAGE_DELETE_INGREDIENT_SUCCESS, ingredientToDelete.getName()),
                CommandResult.CRtype.INGREDIENT);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof InventoryDeleteCommand // instanceof handles nulls
                && targetIndex.equals(((InventoryDeleteCommand) other).targetIndex)); // state check
    }
}
