package seedu.iScam.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.iScam.model.Model.PREDICATE_SHOW_ALL_CLIENTS;

import seedu.iScam.model.Model;

/**
 * Lists all clients in the iScam book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all clients";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredClientList(PREDICATE_SHOW_ALL_CLIENTS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
