package seedu.address.testutil;

import seedu.address.logic.commands.EditCheeseCommand;
import seedu.address.logic.commands.EditCheeseCommand.EditCheeseDescriptor;
import seedu.address.model.cheese.Cheese;
import seedu.address.model.cheese.CheeseType;
import seedu.address.model.cheese.ExpiryDate;
import seedu.address.model.cheese.ManufactureDate;
import seedu.address.model.cheese.MaturityDate;

/**
 * A utility class to help with building EditCheeseDescriptor objects.
 */
public class EditCheeseDescriptorBuilder {

    private EditCheeseCommand.EditCheeseDescriptor descriptor;

    public EditCheeseDescriptorBuilder() {
        descriptor = new EditCheeseDescriptor();
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
        descriptor.setMaturityDate(cheese.getMaturityDate().get());
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
     * Sets the {@code MaturityDate} of the {@code EditCheeseDescriptor} that we are building.
     */
    public EditCheeseDescriptorBuilder withMaturityDate(String maturityDate) {
        descriptor.setMaturityDate(new MaturityDate(maturityDate));
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
