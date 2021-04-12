package seedu.address.logic.commands.inventory;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUANTITY;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ingredient.Ingredient;

/**
 * Decreases the quantity of an existing ingredient.
 */
public class InventoryDecreaseCommand extends Command {

    public static final String COMPONENT_WORD = "inventory";
    public static final String COMMAND_WORD = "decrease";

    public static final String MESSAGE_USAGE = COMPONENT_WORD + " " + COMMAND_WORD
            + ": Decrease the quantity of an existing ingredient in the inventory.\n"
            + "Parameters: [INDEX] "
            + PREFIX_QUANTITY + "[QUANTITY]\n"
            + "Example: " + COMPONENT_WORD + " " + COMMAND_WORD + " "
            + "1 "
            + PREFIX_QUANTITY + "2";

    public static final String MESSAGE_SUCCESS = "Ingredient quantity has been decreased: %1$s";
    public static final String MESSAGE_INVALID_INGREDIENT = "Ingredient was not found in the system, "
            + "have you added the ingredient to the inventory?";

    private final Index targetIndex;
    private final int quantity;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public InventoryDecreaseCommand(Index targetIndex, int quantity) {
        this.targetIndex = targetIndex;
        this.quantity = quantity;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Ingredient toDecrease = model.getIngredientByIndex(this.targetIndex.getZeroBased());

        InventoryCommandUtil.isValidIngredient(toDecrease, model);

        String message = "";

        if (model.hasIngredient(toDecrease)) {
            model.decreaseIngredient(toDecrease, quantity);
        } else {
            throw new CommandException(MESSAGE_INVALID_INGREDIENT);
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, toDecrease.getName()), CommandResult.CRtype.INGREDIENT);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof InventoryDecreaseCommand // instanceof handles nulls
                && targetIndex.equals(((InventoryDecreaseCommand) other).targetIndex));
    }
}
