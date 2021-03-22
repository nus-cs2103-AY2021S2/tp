package seedu.address.logic.commands.update;

import static seedu.address.logic.parser.CliSyntax.PREFIX_UPDATE;

import java.util.Set;

import seedu.address.logic.commands.Command;
import seedu.address.model.name.Name;
import seedu.address.model.property.Address;
import seedu.address.model.property.Deadline;
import seedu.address.model.property.PostalCode;
import seedu.address.model.property.Property;
import seedu.address.model.property.Type;
import seedu.address.model.property.client.Client;
import seedu.address.model.property.status.Status;
import seedu.address.model.remark.Remark;
import seedu.address.model.tag.Tag;

public abstract class UpdateCommand extends Command {

    public static final String COMMAND_WORD = "update";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Updates the status of a property in the app. \n"
            + "Parameters: INDEX "
            + PREFIX_UPDATE + "new AMOUNT|proceed|cancel\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "u/new 600,000";
    public static final String MESSAGE_SUCCESS = "Updated property: %1$s";

    public static final String MESSAGE_PRE_EXISTING_STATUS = "Property already has a status";

    public static final String MESSAGE_NULL_STATUS = "Property does not have a status";

    public static final String MESSAGE_FINAL_STATUS = "Property sale is already completed";

    /**
     * Creates and returns a {@code Property} with the details of {@code propertyToEdit}
     * edited with {@code editPropertyDescriptor}.
     */
    static Property createEditedProperty(Property propertyToEdit, Status status) {
        assert propertyToEdit != null;

        Name updatedName = propertyToEdit.getName();
        Type updatedType = propertyToEdit.getPropertyType();
        Address updatedAddress = propertyToEdit.getAddress();
        PostalCode updatedPostalCode = propertyToEdit.getPostalCode();
        Deadline updatedDeadline = propertyToEdit.getDeadline();
        Remark updatedRemark = propertyToEdit.getRemarks();

        Client updatedClient = propertyToEdit.getClient();

        Set<Tag> updatedTags = propertyToEdit.getTags();

        return new Property(updatedName, updatedType, updatedAddress, updatedPostalCode, updatedDeadline,
                updatedRemark, updatedClient, updatedTags, status);
    }
}
