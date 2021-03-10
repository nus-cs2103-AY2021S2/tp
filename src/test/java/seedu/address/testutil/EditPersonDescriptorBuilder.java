package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand.EditGarmentDescriptor;
import seedu.address.model.description.Description;
import seedu.address.model.person.Address;
import seedu.address.model.person.Colour;
import seedu.address.model.person.Name;
import seedu.address.model.person.Garment;
import seedu.address.model.person.Size;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditPersonDescriptorBuilder {

    private EditGarmentDescriptor descriptor;

    public EditPersonDescriptorBuilder() {
        descriptor = new EditGarmentDescriptor();
    }

    public EditPersonDescriptorBuilder(EditGarmentDescriptor descriptor) {
        this.descriptor = new EditGarmentDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public EditPersonDescriptorBuilder(Garment garment) {
        descriptor = new EditGarmentDescriptor();
        descriptor.setName(garment.getName());
        descriptor.setSize(garment.getSize());
        descriptor.setColour(garment.getColour());
        descriptor.setAddress(garment.getAddress());
        descriptor.setDescriptions(garment.getDescriptions());
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
    public EditPersonDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
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

    public EditGarmentDescriptor build() {
        return descriptor;
    }
}
