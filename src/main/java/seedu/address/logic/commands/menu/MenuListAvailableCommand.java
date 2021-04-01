package seedu.address.logic.commands.menu;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

import static java.util.Objects.requireNonNull;

/**
 * Lists all dishes in the menu book that have sufficient ingredients to produce to the user.
 */
public class MenuListAvailableCommand extends MenuListCommand {

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredDishList(model.getAvailableDishPredicate());
        return new CommandResult(MESSAGE_SUCCESS, CommandResult.CRtype.DISH);
    }
}
