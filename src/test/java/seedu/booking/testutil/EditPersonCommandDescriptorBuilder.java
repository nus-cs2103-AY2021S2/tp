package seedu.booking.testutil;

import seedu.booking.logic.commands.EditPersonCommand.EditPersonDescriptor;
import seedu.booking.model.person.Email;
import seedu.booking.model.person.Name;
import seedu.booking.model.person.Person;
import seedu.booking.model.person.Phone;

/**
 * A utility class to help with building EditPersonCommandDescriptorDescriptor objects.
 */
public class EditPersonCommandDescriptorBuilder {

    private EditPersonDescriptor descriptor;

    public EditPersonCommandDescriptorBuilder() {
        descriptor = new EditPersonDescriptor();
    }

    public EditPersonCommandDescriptorBuilder(EditPersonDescriptor descriptor) {
        this.descriptor = new EditPersonDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonCommandDescriptor} with fields containing {@code person}'s details
     */
    public EditPersonCommandDescriptorBuilder(Person person) {
        descriptor = new EditPersonDescriptor();
        descriptor.setName(person.getName());
        descriptor.setPhone(person.getPhone());
        descriptor.setEmail(person.getEmail());
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonCommandDescriptor} that we are building.
     */
    public EditPersonCommandDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditPersonCommandDescriptor} that we are building.
     */
    public EditPersonCommandDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditPersonCommandDescriptor} that we are building.
     */
    public EditPersonCommandDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }


    public EditPersonDescriptor build() {
        return descriptor;
    }
}
