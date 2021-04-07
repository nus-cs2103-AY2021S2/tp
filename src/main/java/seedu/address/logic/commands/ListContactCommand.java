package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CONTACTS;

import seedu.address.model.Model;

/**
 * Lists all contacts in Teaching Assistant to the user.
 */
public class ListContactCommand extends Command {

    public static final String COMMAND_WORD = "clist";

    public static final String MESSAGE_SUCCESS = "Listed all contacts";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredContactList(PREDICATE_SHOW_ALL_CONTACTS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
