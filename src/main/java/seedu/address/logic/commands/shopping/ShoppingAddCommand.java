package seedu.address.logic.commands.shopping;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ingredient.IngredientStub;

/**
 * Adds a person to the address book.
 */
public class ShoppingAddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " [Insert Usage Here]";

    public static final String MESSAGE_SUCCESS = "New person added: %1$s";
    public static final String MESSAGE_DUPLICATE_INGREDIENT = "This ingredient already exists in the shopping list";

    private final IngredientStub toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public ShoppingAddCommand(IngredientStub ingredient) {
        requireNonNull(ingredient);
        toAdd = ingredient;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // Check model has ingredient here throw MESSAGE_DUPLICATE_DISH

        // Add ingredient to model here
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd), CommandResult.CRtype.SHOPPING);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ShoppingAddCommand // instanceof handles nulls
                && toAdd.equals(((ShoppingAddCommand) other).toAdd));
    }
}
