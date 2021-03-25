package dog.pawbook.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import dog.pawbook.logic.commands.EditCommand.EditOwnerDescriptor;
import dog.pawbook.model.managedentity.Name;
import dog.pawbook.model.managedentity.owner.Address;
import dog.pawbook.model.managedentity.owner.Email;
import dog.pawbook.model.managedentity.owner.Owner;
import dog.pawbook.model.managedentity.owner.Phone;
import dog.pawbook.model.managedentity.tag.Tag;

/**
 * A utility class to help with building EditOwnerDescriptor objects.
 */
public class EditOwnerDescriptorBuilder {

    private EditOwnerDescriptor descriptor;

    public EditOwnerDescriptorBuilder() {
        descriptor = new EditOwnerDescriptor();
    }

    public EditOwnerDescriptorBuilder(EditOwnerDescriptor descriptor) {
        this.descriptor = new EditOwnerDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditOwnerDescriptor} with fields containing {@code owner}'s details
     */
    public EditOwnerDescriptorBuilder(Owner owner) {
        descriptor = new EditOwnerDescriptor();
        descriptor.setName(owner.getName());
        descriptor.setPhone(owner.getPhone());
        descriptor.setEmail(owner.getEmail());
        descriptor.setAddress(owner.getAddress());
        descriptor.setTags(owner.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditOwnerDescriptor} that we are building.
     */
    public EditOwnerDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditOwnerDescriptor} that we are building.
     */
    public EditOwnerDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditOwnerDescriptor} that we are building.
     */
    public EditOwnerDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditOwnerDescriptor} that we are building.
     */
    public EditOwnerDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditOwnerDescriptor}
     * that we are building.
     */
    public EditOwnerDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditOwnerDescriptor build() {
        return descriptor;
    }
}
