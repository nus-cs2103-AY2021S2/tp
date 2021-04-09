package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditContactCommand.EditContactDescriptor;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.ContactEmail;
import seedu.address.model.contact.ContactName;
import seedu.address.model.contact.ContactPhone;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditContactDescriptor objects.
 */
public class EditContactDescriptorBuilder {

    private EditContactDescriptor descriptor;

    public EditContactDescriptorBuilder() {
        descriptor = new EditContactDescriptor();
    }

    public EditContactDescriptorBuilder(EditContactDescriptor descriptor) {
        this.descriptor = new EditContactDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditContactDescriptor} with fields containing {@code contact}'s details
     */
    public EditContactDescriptorBuilder(Contact contact) {
        descriptor = new EditContactDescriptor();
        descriptor.setName(contact.getName());
        descriptor.setPhone(contact.getPhone());
        descriptor.setEmail(contact.getEmail());
        descriptor.setTags(contact.getTags());
    }

    /**
     * Sets the {@code ContactName} of the {@code EditContactDescriptor} that we are building.
     */
    public EditContactDescriptorBuilder withContactName(String name) {
        descriptor.setName(new ContactName(name));
        return this;
    }

    /**
     * Sets the {@code ContactPhone} of the {@code EditContactDescriptor} that we are building.
     */
    public EditContactDescriptorBuilder withContactPhone(String phone) {
        descriptor.setPhone(new ContactPhone(phone));
        return this;
    }

    /**
     * Sets the {@code ContactEmail} of the {@code EditContactDescriptor} that we are building.
     */
    public EditContactDescriptorBuilder withContactEmail(String email) {
        descriptor.setEmail(new ContactEmail(email));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditContactDescriptor}
     * that we are building.
     */
    public EditContactDescriptorBuilder withContactTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditContactDescriptor build() {
        return descriptor;
    }
}
