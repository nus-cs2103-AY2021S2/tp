package seedu.address.logic.commands.inventory;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.ingredient.IngredientNameContainsWordsPredicate;

public class InventoryFindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all ingredients whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " tomato fish lemon";


    private final IngredientNameContainsWordsPredicate predicate;

    public InventoryFindCommand(IngredientNameContainsWordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredIngredientList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_ITEMS_LISTED_OVERVIEW,
                        model.getFilteredIngredientList().size(),
                        Messages.ITEM_INGREDIENTS),
                CommandResult.CRtype.INGREDIENT);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof InventoryFindCommand // instanceof handles nulls
                && predicate.equals(((InventoryFindCommand) other).predicate)); // state check
    }
}
