package seedu.iscam.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.iscam.model.Model.PREDICATE_SHOW_ALL_CLIENTS;

import seedu.iscam.model.Model;

/**
 * Lists all clients in the iscam book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all clients";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredClientList(PREDICATE_SHOW_ALL_CLIENTS);
        model.setClientMode();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
