package chim.testutil;

import chim.logic.commands.EditCheeseCommand;
import chim.model.cheese.Cheese;
import chim.model.cheese.CheeseType;
import chim.model.cheese.ExpiryDate;
import chim.model.cheese.ManufactureDate;

/**
 * A utility class to help with building EditCheeseDescriptor objects.
 */
public class EditCheeseDescriptorBuilder {

    private EditCheeseCommand.EditCheeseDescriptor descriptor;

    public EditCheeseDescriptorBuilder() {
        descriptor = new EditCheeseCommand.EditCheeseDescriptor();
    }

    public EditCheeseDescriptorBuilder(EditCheeseCommand.EditCheeseDescriptor descriptor) {
        this.descriptor = new EditCheeseCommand.EditCheeseDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditCheeseDescriptor} with fields containing {@code cheese}'s details
     */
    public EditCheeseDescriptorBuilder(Cheese cheese) {
        descriptor = new EditCheeseCommand.EditCheeseDescriptor();
        descriptor.setCheeseType(cheese.getCheeseType());
        descriptor.setManufactureDate(cheese.getManufactureDate());
        descriptor.setExpiryDate(cheese.getExpiryDate().get());
    }

    /**
     * Sets the {@code CheeseType} of the {@code EditCheeseDescriptor} that we are building.
     */
    public EditCheeseDescriptorBuilder withCheeseType(CheeseType cheeseType) {
        descriptor.setCheeseType(CheeseType.getCheeseType(cheeseType.toString()));
        return this;
    }

    /**
     * Sets the {@code ManufactureDate} of the {@code EditCheeseDescriptor} that we are building.
     */
    public EditCheeseDescriptorBuilder withManufactureDate(String manufactureDate) {
        descriptor.setManufactureDate(new ManufactureDate(manufactureDate));
        return this;
    }

    /**
     * Sets the {@code ExpiryDate} of the {@code EditCheeseDescriptor} that we are building.
     */
    public EditCheeseDescriptorBuilder withExpiryDate(String expiryDate) {
        descriptor.setExpiryDate(new ExpiryDate(expiryDate));
        return this;
    }

    public EditCheeseCommand.EditCheeseDescriptor build() {
        return descriptor;
    }
}
