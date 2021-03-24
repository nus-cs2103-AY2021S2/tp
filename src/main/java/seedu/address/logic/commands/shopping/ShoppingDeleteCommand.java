package seedu.address.logic.commands.shopping;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ingredient.Ingredient;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class ShoppingDeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " [Insert Usage Here]";

    public static final String MESSAGE_DELETE_INGREDIENT_SUCCESS = "Deleted Ingredient: %1$s";

    private final Index targetIndex;

    public ShoppingDeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Ingredient> lastShownList = model.getFilteredIngredientList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INGREDIENT_DISPLAYED_INDEX);
        }

        Ingredient ingredientToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteIngredient(ingredientToDelete);

        return new CommandResult(String.format(MESSAGE_DELETE_INGREDIENT_SUCCESS, ingredientToDelete), CommandResult.CRtype.SHOPPING);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ShoppingDeleteCommand // instanceof handles nulls
                && targetIndex.equals(((ShoppingDeleteCommand) other).targetIndex)); // state check
    }
}
