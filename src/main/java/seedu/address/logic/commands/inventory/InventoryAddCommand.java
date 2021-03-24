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
            + ": Adds an ingredient to the inventory. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_QUANTITY + "QUANTITY\n"
            + "Example: " + COMPONENT_WORD + " " + COMMAND_WORD + " "
            + PREFIX_NAME + "potato "
            + PREFIX_QUANTITY + "51";

    public static final String MESSAGE_SUCCESS = "New ingredient added: %1$s";
    public static final String MESSAGE_DUPLICATE_INGREDIENT = "This ingredient already exists in the inventory";

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

        // Check model has ingredient here throw MESSAGE_DUPLICATE_INGREDIENT
        if (model.hasIngredient(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_INGREDIENT);
        }

        model.addIngredient(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd), CommandResult.CRtype.INGREDIENT);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof InventoryAddCommand // instanceof handles nulls
                && toAdd.equals(((InventoryAddCommand) other).toAdd));
    }
}
