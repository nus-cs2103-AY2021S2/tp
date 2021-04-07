package seedu.address.logic.commands.menu;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Lists all dishes in the menu book that have sufficient ingredients to produce to the user.
 */
public class MenuListAvailableCommand extends MenuListCommand {

    public static final String MESSAGE_SUCCESS = "Listed all available dishes";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredDishList(model.getAvailableDishPredicate());
        return new CommandResult(MESSAGE_SUCCESS, CommandResult.CRtype.DISH);
    }
}
