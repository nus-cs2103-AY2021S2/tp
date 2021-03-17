package seedu.address.logic.commands.menu;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.dish.Dish;
import seedu.address.model.dish.DishStub;
import seedu.address.model.ingredient.Ingredient;

import java.util.List;

/**
 * Deletes a dish identified using it's displayed index from the address book.
 */
public class MenuDeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " [Insert Usage Here]";

    public static final String MESSAGE_DELETE_DISH_SUCCESS = "Deleted dish: %1$s";

    private final Index targetIndex;

    public MenuDeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        System.out.println("HERE");

        List<Dish> lastShownList = model.getFilteredDishList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_DISH_DISPLAYED_INDEX);
        }

        Dish dishToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteDish(dishToDelete);

        return new CommandResult(String.format(MESSAGE_DELETE_DISH_SUCCESS, dishToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MenuDeleteCommand // instanceof handles nulls
                && targetIndex.equals(((MenuDeleteCommand) other).targetIndex)); // state check
    }
}
