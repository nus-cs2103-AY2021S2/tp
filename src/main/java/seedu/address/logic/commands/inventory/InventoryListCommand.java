package seedu.address.logic.commands.inventory;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ITEMS;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Lists all ingredients in the inventory book to the user.
 */
public class InventoryListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all ingredients";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredIngredientList(PREDICATE_SHOW_ALL_ITEMS);
        return new CommandResult(MESSAGE_SUCCESS, CommandResult.CRtype.INGREDIENT);
    }
}
