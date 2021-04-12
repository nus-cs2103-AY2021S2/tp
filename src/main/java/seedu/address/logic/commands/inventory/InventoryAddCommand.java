package seedu.address.logic.commands.inventory;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUANTITY;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ingredient.Ingredient;

/**
 * Adds a person to the address book.
 */
public class InventoryAddCommand extends Command {

    public static final String COMPONENT_WORD = "inventory";
    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMPONENT_WORD + " " + COMMAND_WORD
            + ": Adds or increases quantity of an ingredient to the inventory. \n"
            + "Parameters: "
            + PREFIX_NAME + "[INGREDIENT_NAME] "
            + PREFIX_QUANTITY + "[QUANTITY]\n"
            + "Example: " + COMPONENT_WORD + " " + COMMAND_WORD + " "
            + PREFIX_NAME + "Flour "
            + PREFIX_QUANTITY + "51";

    public static final String ADD_MESSAGE_SUCCESS = "New ingredient added: %1$s";
    public static final String INCREASE_MESSAGE_SUCCESS = "Ingredient increased: %1$s";

    private final Ingredient toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public InventoryAddCommand(Ingredient ingredient) {
        requireNonNull(ingredient);
        toAdd = ingredient;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        InventoryCommandUtil.isValidIngredient(toAdd, model);

        String message = "";

        if (model.hasIngredient(toAdd)) {
            model.increaseIngredientByName(toAdd.getName(), toAdd.getQuantity());
            message = String.format(INCREASE_MESSAGE_SUCCESS, toAdd.getName());
        } else {
            model.addIngredient(toAdd);
            message = String.format(ADD_MESSAGE_SUCCESS, toAdd.getName());
        }

        return new CommandResult(message, CommandResult.CRtype.INGREDIENT);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof InventoryAddCommand // instanceof handles nulls
                && toAdd.equals(((InventoryAddCommand) other).toAdd));
    }
}
