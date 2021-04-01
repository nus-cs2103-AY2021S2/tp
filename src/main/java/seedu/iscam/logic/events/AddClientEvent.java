package seedu.iscam.logic.events;

import seedu.iscam.logic.commands.AddCommand;
import seedu.iscam.logic.commands.DeleteCommand;
import seedu.iscam.logic.commands.UndoableCommand;
import seedu.iscam.model.client.Client;

/**
 * An event representing an 'add contact' command
 */
public class AddClientEvent implements Event {

    private final Client clientAdded;

    public AddClientEvent(Client clientAdded) {
        this.clientAdded = clientAdded;
    }

    public UndoableCommand undo() {
        return new DeleteCommand(clientAdded);
    }

    public UndoableCommand redo() {
        return new AddCommand(clientAdded);
    }
}
