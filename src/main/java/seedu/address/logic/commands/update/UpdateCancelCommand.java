package seedu.address.logic.commands.update;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PROPERTIES;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.property.Property;
import seedu.address.model.property.status.Status;

public class UpdateCancelCommand extends UpdateCommand {

    private final Index index;

    public UpdateCancelCommand(Index index) {
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (index.getZeroBased() >= model.getPropertyListSize()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PROPERTY_DISPLAYED_INDEX);
        }

        Property propertyToEdit = model.getProperty(index.getZeroBased());
        Status status = propertyToEdit.getStatus();

        if (status == null) {
            throw new CommandException(MESSAGE_NULL_STATUS);
        }

        Property editedProperty = createEditedProperty(propertyToEdit, null);

        model.setProperty(index.getZeroBased(), editedProperty);
        model.updateFilteredPropertyList(PREDICATE_SHOW_ALL_PROPERTIES);
        return new CommandResult(String.format(MESSAGE_SUCCESS, editedProperty));
    }
}
