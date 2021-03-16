package seedu.address.testutil;

import seedu.address.logic.commands.EditMemberCommand;
import seedu.address.logic.commands.EditMemberCommand.EditMemberDescriptor;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditMemberDescriptorBuilder {

    private EditMemberDescriptor descriptor;

    public EditMemberDescriptorBuilder() {
        descriptor = new EditMemberDescriptor();
    }

    public EditMemberDescriptorBuilder(EditMemberDescriptor descriptor) {
        this.descriptor = new EditMemberDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public EditMemberDescriptorBuilder(Person person) {
        descriptor = new EditMemberCommand.EditMemberDescriptor();
        descriptor.setName(person.getName());
        descriptor.setPhone(person.getPhone());
        descriptor.setEmail(person.getEmail());
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditMemberDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditMemberDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditMemberDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }


    public EditMemberCommand.EditMemberDescriptor build() {
        return descriptor;
    }
}
