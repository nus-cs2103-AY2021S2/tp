package seedu.iscam.logic.events;

import java.util.List;

import seedu.iscam.commons.core.index.Index;
import seedu.iscam.logic.commands.EditCommand;
import seedu.iscam.logic.commands.UndoableCommand;
import seedu.iscam.model.Model;
import seedu.iscam.model.client.Client;

/**
 * An event representing a 'edit client's details' command.
 */
public class EditClientEvent implements Event {
    private final Index index;
    private final EditCommand.EditClientDescriptor editInfo;
    private final EditCommand.EditClientDescriptor reversedEditInfo;

    /**
     * Creates an EditClientEvent wrapper that wraps around an EditCommand editing the client
     * at the specified {@code Index}
     *
     * @param index    index of client to be edited
     * @param editInfo information that client should have after being edited
     * @param model    model of app that client is to be deleted from
     */
    public EditClientEvent(Index index, EditCommand.EditClientDescriptor editInfo, Model model) {
        this.index = index;
        this.editInfo = editInfo;
        this.reversedEditInfo = generateReversedEditInfo(model);
    }

    public UndoableCommand undo() {
        return new EditCommand(index, reversedEditInfo);
    }

    public UndoableCommand redo() {
        return new EditCommand(index, editInfo);
    }

    /**
     * A method to construct an EditClientDescriptor based on the current Client to edit in the model.
     *
     * @param model Current model in the application.
     * @return the EditClientDescriptor containing information of the original Client to be edited.
     */
    private EditCommand.EditClientDescriptor generateReversedEditInfo(Model model) {
        EditCommand.EditClientDescriptor result = new EditCommand.EditClientDescriptor();

        List<Client> lastShownList = model.getFilteredClientList();
        assert (index.getZeroBased() < lastShownList.size());

        Client originalClient = lastShownList.get(index.getZeroBased());

        result.setName(originalClient.getName());
        result.setPhone(originalClient.getPhone());
        result.setEmail(originalClient.getEmail());
        result.setLocation(originalClient.getLocation());

        if (originalClient.getPlan() != null) {
            result.setPlan(originalClient.getPlan());
        } else {
            result.setPlan(null);
        }

        if (originalClient.getImageRes() != null) {
            result.setImageRes(originalClient.getImageRes());
        } else {
            result.setImageRes(null);
        }

        result.setTags(originalClient.getTags());

        return result;
    }
}
