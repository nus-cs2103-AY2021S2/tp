package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.description.Description;
import seedu.address.model.person.DressCode;
import seedu.address.model.person.Colour;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Size;

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
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public EditPersonDescriptorBuilder(Person person) {
        descriptor = new EditPersonDescriptor();
        descriptor.setName(person.getName());
        descriptor.setSize(person.getSize());
        descriptor.setColour(person.getColour());
        descriptor.setDressCode(person.getDressCode());
        descriptor.setDescriptions(person.getDescriptions());
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Size} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withSize(String size) {
        descriptor.setSize(new Size(size));
        return this;
    }

    /**
     * Sets the {@code Colour} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withColour(String colour) {
        descriptor.setColour(new Colour(colour));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withDressCode(String dressCode) {
        descriptor.setDressCode(new DressCode(dressCode));
        return this;
    }

    /**
     * Parses the {@code descriptions} into a {@code Set<Description>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditPersonDescriptorBuilder withDescriptions(String... descriptions) {
        Set<Description> descriptionSet = Stream.of(descriptions).map(Description::new).collect(Collectors.toSet());
        descriptor.setDescriptions(descriptionSet);
        return this;
    }

    public EditPersonDescriptor build() {
        return descriptor;
    }
}
