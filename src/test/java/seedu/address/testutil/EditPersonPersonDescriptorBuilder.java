package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditPersonCommand.EditPersonPersonDescriptor;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonId;
import seedu.address.model.person.PersonType;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditPersonPersonDescriptor objects.
 */
public class EditPersonPersonDescriptorBuilder {

    private EditPersonPersonDescriptor descriptor;

    public EditPersonPersonDescriptorBuilder() {
        descriptor = new EditPersonPersonDescriptor();
    }

    public EditPersonPersonDescriptorBuilder(EditPersonPersonDescriptor descriptor) {
        this.descriptor = new EditPersonPersonDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonPersonDescriptor} with fields containing {@code person}'s details
     */
    public EditPersonPersonDescriptorBuilder(Person person) {
        descriptor = new EditPersonPersonDescriptor();
        descriptor.setName(person.getName());
        descriptor.setPhone(person.getPhone());
        descriptor.setEmail(person.getEmail());
        descriptor.setAddress(person.getAddress());
        descriptor.setTags(person.getTags());
        descriptor.setPersonType(person.getPersonType());
        descriptor.setPersonId(person.getPersonId());
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonPersonDescriptor} that we are building.
     */
    public EditPersonPersonDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditPersonPersonDescriptor} that we are building.
     */
    public EditPersonPersonDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditPersonPersonDescriptor} that we are building.
     */
    public EditPersonPersonDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditPersonPersonDescriptor} that we are building.
     */
    public EditPersonPersonDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }
    /**
     * Sets the {@code PersonType} of the {@code EditPersonPersonDescriptor} that we are building.
     */
    public EditPersonPersonDescriptorBuilder withPersonType(String personType) {
        descriptor.setPersonType(new PersonType(personType));
        return this;
    }

    /**
     * Sets the {@code PersonId} of the {@code EditPersonPersonDescriptor} that we are building.
     */
    public EditPersonPersonDescriptorBuilder withPersonId(String personId) {
        descriptor.setPersonId(new PersonId(personId));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditPersonPersonDescriptor}
     * that we are building.
     */
    public EditPersonPersonDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditPersonPersonDescriptor build() {
        return descriptor;
    }
}
