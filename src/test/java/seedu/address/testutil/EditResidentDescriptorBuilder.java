package seedu.address.testutil;

import seedu.address.logic.commands.EditResidentCommand.EditPersonDescriptor;
import seedu.address.model.resident.Email;
import seedu.address.model.resident.Name;
import seedu.address.model.resident.Resident;
import seedu.address.model.resident.Phone;
import seedu.address.model.resident.Room;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditPersonDescriptorBuilder {

    private EditPersonDescriptor descriptor;

    public EditPersonDescriptorBuilder() {
        descriptor = new EditPersonDescriptor();
    }

    public EditPersonDescriptorBuilder(EditPersonDescriptor descriptor) {
        this.descriptor = new EditPersonDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code resident}'s details
     */
    public EditPersonDescriptorBuilder(Resident resident) {
        descriptor = new EditPersonDescriptor();
        descriptor.setName(resident.getName());
        descriptor.setPhone(resident.getPhone());
        descriptor.setEmail(resident.getEmail());
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Room} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withRoom(String room) {
        descriptor.setRoom(new Room(room));
        return this;
    }


    public EditPersonDescriptor build() {
        return descriptor;
    }
}
