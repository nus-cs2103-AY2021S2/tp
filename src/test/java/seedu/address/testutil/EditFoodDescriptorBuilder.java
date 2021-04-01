package seedu.address.testutil;

import seedu.address.logic.commands.UpdateFoodItemCommand.EditFoodDescriptor;
import seedu.address.model.food.Food;

/**
 * A utility class to help with building EditFoodDescriptor objects.
 */
public class EditFoodDescriptorBuilder {

    private EditFoodDescriptor descriptor;

    public EditFoodDescriptorBuilder() {
        descriptor = new EditFoodDescriptor();
    }

    public EditFoodDescriptorBuilder(EditFoodDescriptor descriptor) {
        this.descriptor = new EditFoodDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditFoodDescriptor} with fields containing {@code food}'s details
     */
    public EditFoodDescriptorBuilder(Food food) {
        descriptor = new EditFoodDescriptor();
        descriptor.setName(food.getName());
        descriptor.setCarbos(food.getCarbos());
        descriptor.setFats(food.getFats());
        descriptor.setProteins(food.getProteins());
    }

    /**
     * Sets the {@code Name} of the {@code EditFoodDescriptor} that we are building.
     */
    public EditFoodDescriptorBuilder withName(String name) {
        descriptor.setName(name);
        return this;
    }

    /**
     * Sets the {@code Carbos} of the {@code EditFoodDescriptor} that we are building.
     */
    public EditFoodDescriptorBuilder withCarbos(Double carbos) {
        descriptor.setCarbos(carbos);
        return this;
    }

    /**
     * Sets the {@code Fats} of the {@code EditFoodDescriptor} that we are building.
     */
    public EditFoodDescriptorBuilder withFats(Double fats) {
        descriptor.setFats(fats);
        return this;
    }

    /**
     * Sets the {@code Proteins} of the {@code EditFoodDescriptor} that we are building.
     */
    public EditFoodDescriptorBuilder withAddress(Double proteins) {
        descriptor.setProteins(proteins);
        return this;
    }

    public EditFoodDescriptor build() {
        return descriptor;
    }
}
