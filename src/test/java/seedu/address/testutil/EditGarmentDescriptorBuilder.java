package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand.EditGarmentDescriptor;
import seedu.address.model.description.Description;
import seedu.address.model.garment.Address;
import seedu.address.model.garment.Colour;
import seedu.address.model.garment.Garment;
import seedu.address.model.garment.Name;
import seedu.address.model.garment.Size;

/**
 * A utility class to help with building EditGarmentDescriptor objects.
 */
public class EditGarmentDescriptorBuilder {

    private EditGarmentDescriptor descriptor;

    public EditGarmentDescriptorBuilder() {
        descriptor = new EditGarmentDescriptor();
    }

    public EditGarmentDescriptorBuilder(EditGarmentDescriptor descriptor) {
        this.descriptor = new EditGarmentDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditGarmentDescriptor} with fields containing {@code garment}'s details
     */
    public EditGarmentDescriptorBuilder(Garment garment) {
        descriptor = new EditGarmentDescriptor();
        descriptor.setName(garment.getName());
        descriptor.setSize(garment.getSize());
        descriptor.setColour(garment.getColour());
        descriptor.setAddress(garment.getAddress());
        descriptor.setDescriptions(garment.getDescriptions());
    }

    /**
     * Sets the {@code Name} of the {@code EditGarmentDescriptor} that we are building.
     */
    public EditGarmentDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Size} of the {@code EditGarmentDescriptor} that we are building.
     */
    public EditGarmentDescriptorBuilder withSize(String size) {
        descriptor.setSize(new Size(size));
        return this;
    }

    /**
     * Sets the {@code Colour} of the {@code EditGarmentDescriptor} that we are building.
     */
    public EditGarmentDescriptorBuilder withColour(String colour) {
        descriptor.setColour(new Colour(colour));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditGarmentDescriptor} that we are building.
     */
    public EditGarmentDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Parses the {@code descriptions} into a {@code Set<Description>} and set it to the {@code EditGarmentDescriptor}
     * that we are building.
     */
    public EditGarmentDescriptorBuilder withDescriptions(String... descriptions) {
        Set<Description> descriptionSet = Stream.of(descriptions).map(Description::new).collect(Collectors.toSet());
        descriptor.setDescriptions(descriptionSet);
        return this;
    }

    public EditGarmentDescriptor build() {
        return descriptor;
    }
}
