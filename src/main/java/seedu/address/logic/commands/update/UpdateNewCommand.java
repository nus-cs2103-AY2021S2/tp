package seedu.address.logic.commands.update;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.EditPropertyCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.name.Name;
import seedu.address.model.property.Address;
import seedu.address.model.property.Deadline;
import seedu.address.model.property.PostalCode;
import seedu.address.model.property.Property;
import seedu.address.model.property.Type;
import seedu.address.model.property.client.Client;
import seedu.address.model.property.status.Offer;
import seedu.address.model.property.status.Option;
import seedu.address.model.property.status.Status;
import seedu.address.model.remark.Remark;
import seedu.address.model.tag.Tag;

import java.util.Set;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PROPERTIES;

public class UpdateNewCommand extends UpdateCommand {

    public static final String MESSAGE_PRE_EXISTING_STATUS = "Property already has a status";

    private final Offer amount;
    private final Index index;

    public UpdateNewCommand(Index index, Offer amount) {
        this.amount = amount;
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (index.getZeroBased() >= model.getPropertySize()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PROPERTY_DISPLAYED_INDEX);
        }

        Property propertyToEdit = model.getProperty(index.getZeroBased());
        Status status = propertyToEdit.getStatus();

        if (status != null) {
            throw new CommandException(MESSAGE_PRE_EXISTING_STATUS);
        }

        Property editedProperty = createEditedProperty(propertyToEdit, new Option(amount));

        model.setProperty(index.getZeroBased(), editedProperty);
        model.updateFilteredPropertyList(PREDICATE_SHOW_ALL_PROPERTIES);
        return new CommandResult(String.format(MESSAGE_SUCCESS, editedProperty));
    }
}
