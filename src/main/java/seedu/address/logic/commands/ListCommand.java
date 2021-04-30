package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PASSENGERS;

import seedu.address.model.Model;

/**
 * Lists all passengers in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all passengers";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPassengerList(PREDICATE_SHOW_ALL_PASSENGERS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
