package seedu.address.testutil.resident;

import seedu.address.logic.commands.resident.EditResidentCommand;
import seedu.address.logic.commands.resident.EditResidentCommand.EditResidentDescriptor;
import seedu.address.model.resident.Email;
import seedu.address.model.resident.Name;
import seedu.address.model.resident.Phone;
import seedu.address.model.resident.Resident;
import seedu.address.model.resident.Room;
import seedu.address.model.resident.Year;

/**
 * A utility class to help with building EditResidentDescriptor objects.
 */
public class EditResidentDescriptorBuilder {

    private EditResidentDescriptor descriptor;

    public EditResidentDescriptorBuilder() {
        descriptor = new EditResidentCommand.EditResidentDescriptor();
    }

    public EditResidentDescriptorBuilder(EditResidentCommand.EditResidentDescriptor descriptor) {
        this.descriptor = new EditResidentCommand.EditResidentDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditResidentDescriptor} with fields containing {@code resident}'s details
     */
    public EditResidentDescriptorBuilder(Resident resident) {
        descriptor = new EditResidentCommand.EditResidentDescriptor();
        descriptor.setName(resident.getName());
        descriptor.setPhone(resident.getPhone());
        descriptor.setEmail(resident.getEmail());
        descriptor.setYear(resident.getYear());
    }

    /**
     * Sets the {@code Name} of the {@code EditResidentDescriptor} that we are building.
     */
    public EditResidentDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditResidentDescriptor} that we are building.
     */
    public EditResidentDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditResidentDescriptor} that we are building.
     */
    public EditResidentDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Year} of the {@code EditResidentDescriptor} that we are building.
     */
    public EditResidentDescriptorBuilder withYear(String year) {
        descriptor.setYear(new Year(year));
        return this;
    }

    /**
     * Sets the {@code Room} of the {@code EditResidentDescriptor} that we are building.
     */
    public EditResidentDescriptorBuilder withRoom(String room) {
        descriptor.setRoom(new Room(room));
        return this;
    }

    public EditResidentCommand.EditResidentDescriptor build() {
        return descriptor;
    }
}
