package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand.EditGarmentDescriptor;
import seedu.address.model.description.Description;
import seedu.address.model.garment.Colour;
import seedu.address.model.garment.DressCode;
import seedu.address.model.garment.Garment;
import seedu.address.model.garment.Name;
import seedu.address.model.garment.Size;
import seedu.address.model.garment.Type;

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
        descriptor.setDressCode(garment.getDressCode());
        descriptor.setType(garment.getType());
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
     * Sets the {@code DressCode} of the {@code EditGarmentDescriptor} that we are building.
     */
    public EditGarmentDescriptorBuilder withDressCode(String dressCode) {
        descriptor.setDressCode(new DressCode(dressCode));
        return this;
    }

    /**
     * Sets the {@code Type} of the {@code EditGarmentDescriptor} that we are building.
     */
    public EditGarmentDescriptorBuilder withType(String type) {
        descriptor.setType(new Type(type));
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
