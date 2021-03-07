package seedu.smartlib.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.smartlib.commons.core.name.Name;
import seedu.smartlib.logic.commands.EditCommand;
import seedu.smartlib.logic.commands.EditCommand.EditReaderDescriptor;
import seedu.smartlib.model.reader.Address;
import seedu.smartlib.model.reader.Email;
import seedu.smartlib.model.reader.Phone;
import seedu.smartlib.model.reader.Reader;
import seedu.smartlib.model.tag.Tag;

/**
 * A utility class to help with building EditReaderDescriptor objects.
 */
public class EditReaderDescriptorBuilder {

    private EditCommand.EditReaderDescriptor descriptor;

    public EditReaderDescriptorBuilder() {
        descriptor = new EditCommand.EditReaderDescriptor();
    }

    public EditReaderDescriptorBuilder(EditCommand.EditReaderDescriptor descriptor) {
        this.descriptor = new EditReaderDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditReaderDescriptor} with fields containing {@code reader}'s details
     */
    public EditReaderDescriptorBuilder(Reader reader) {
        descriptor = new EditCommand.EditReaderDescriptor();
        descriptor.setName(reader.getName());
        descriptor.setPhone(reader.getPhone());
        descriptor.setEmail(reader.getEmail());
        descriptor.setAddress(reader.getAddress());
        descriptor.setTags(reader.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditReaderDescriptor} that we are building.
     */
    public EditReaderDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditReaderDescriptor} that we are building.
     */
    public EditReaderDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditReaderDescriptor} that we are building.
     */
    public EditReaderDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditReaderDescriptor} that we are building.
     */
    public EditReaderDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditReaderDescriptor}
     * that we are building.
     */
    public EditReaderDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditCommand.EditReaderDescriptor build() {
        return descriptor;
    }
}
