package seedu.iscam.logic.events;

import java.util.List;

import seedu.iscam.commons.core.Messages;
import seedu.iscam.commons.core.index.Index;
import seedu.iscam.logic.commands.AddCommand;
import seedu.iscam.logic.commands.Command;
import seedu.iscam.logic.commands.DeleteCommand;
import seedu.iscam.logic.commands.UndoableCommand;
import seedu.iscam.logic.commands.exceptions.CommandException;
import seedu.iscam.logic.events.exceptions.EventException;
import seedu.iscam.model.Model;
import seedu.iscam.model.client.Client;

/**
 * An event representing a 'delete client' command.
 */
public class DeleteClientEvent implements Event {
    private final Index index;
    private final Client deletedClient;

    public DeleteClientEvent(Index index, Model model) {
        this.index = index;
        this.deletedClient = generateDeletedClient(model);
    }

    public UndoableCommand undo() {
        return new AddCommand(index, deletedClient);
    }

    public UndoableCommand redo() {
        return new DeleteCommand(index);
    }

    /**
     * Retrieves the Client object that is going to be deleted.
     * @param model Current model in the application.
     * @return Client about to be deleted.
     */
    private Client generateDeletedClient(Model model) {
        List<Client> lastShownList = model.getFilteredClientList();
        Client clientToDelete;
        assert (index.getZeroBased() < lastShownList.size());
        clientToDelete = lastShownList.get(index.getZeroBased());
        return clientToDelete;
    }
}
