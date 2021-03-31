package seedu.booking.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.booking.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.booking.model.Model;

/**
 * Lists all persons in the booking system to the user.
 */
public class ListPersonCommand extends Command {

    public static final String COMMAND_WORD = "list_persons";

    public static final String MESSAGE_SUCCESS = "Listed all persons";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
