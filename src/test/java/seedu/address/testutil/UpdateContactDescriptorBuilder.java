package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.UpdateContactCommand;
import seedu.address.model.contact.Address;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.Email;
import seedu.address.model.contact.Name;
import seedu.address.model.contact.Phone;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building UpdateContactDescriptor objects.
 */
public class UpdateContactDescriptorBuilder {

    private UpdateContactCommand.UpdateContactDescriptor descriptor;

    public UpdateContactDescriptorBuilder() {
        descriptor = new UpdateContactCommand.UpdateContactDescriptor();
    }

    public UpdateContactDescriptorBuilder(UpdateContactCommand.UpdateContactDescriptor descriptor) {
        this.descriptor = new UpdateContactCommand.UpdateContactDescriptor(descriptor);
    }

    /**
     * Returns an {@code UpdateContactDescriptorBuilder} with fields containing {@code Contact}'s details
     */
    public UpdateContactDescriptorBuilder(Contact contact) {
        descriptor = new UpdateContactCommand.UpdateContactDescriptor();
        descriptor.setName(contact.getName());
        descriptor.setPhone(contact.getPhone());
        descriptor.setEmail(contact.getEmail());
        descriptor.setAddress(contact.getAddress());
        descriptor.setTags(contact.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code UpdateContactDescriptorBuilder} that we are building.
     */
    public UpdateContactDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code UpdateContactDescriptorBuilder} that we are building.
     */
    public UpdateContactDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code UpdateContactDescriptorBuilder} that we are building.
     */
    public UpdateContactDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code UpdateContactDescriptorBuilder} that we are building.
     */
    public UpdateContactDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code UpdateContactDescriptorBuilder}
     * that we are building.
     */
    public UpdateContactDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public UpdateContactCommand.UpdateContactDescriptor build() {
        return descriptor;
    }
}
