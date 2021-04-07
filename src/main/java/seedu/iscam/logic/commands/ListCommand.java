package seedu.iscam.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.iscam.model.Model.PREDICATE_SHOW_ALL_CLIENTS;

import javafx.collections.ObservableList;
import seedu.iscam.logic.commands.exceptions.CommandException;
import seedu.iscam.model.Model;
import seedu.iscam.model.client.Client;

/**
 * Lists all clients in the iscam book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all clients";
    public static final String MESSAGE_EMPTY_LIST = "There is no client in the iScam book.";


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredClientList(PREDICATE_SHOW_ALL_CLIENTS);
        ObservableList<Client> clients = model.getFilteredClientList();
        if (clients.size() == 0) {
            throw new CommandException(MESSAGE_EMPTY_LIST);
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
