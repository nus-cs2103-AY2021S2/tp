package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CHEESES;

import seedu.address.model.Model;

/**
 * Lists all cheeses in the CHIM to the user.
 */
public class ListCheesesCommand extends Command {

    public static final String COMMAND_WORD = "listcheeses";

    public static final String MESSAGE_SUCCESS = "Listed all cheeses";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredCheeseList(PREDICATE_SHOW_ALL_CHEESES);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
