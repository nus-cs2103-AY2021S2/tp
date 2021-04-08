package dog.pawbook.testutil;

import dog.pawbook.logic.commands.EditDogCommand.EditDogDescriptor;
import dog.pawbook.model.managedentity.dog.Breed;
import dog.pawbook.model.managedentity.dog.DateOfBirth;
import dog.pawbook.model.managedentity.dog.Dog;
import dog.pawbook.model.managedentity.dog.Sex;

/**
 * A utility class to help with building EditDogDescriptor objects.
 */
public class EditDogDescriptorBuilder extends EditEntityDescriptorBuilder<EditDogDescriptorBuilder, EditDogDescriptor> {

    public EditDogDescriptorBuilder() {
        super(new EditDogDescriptor());
    }

    public EditDogDescriptorBuilder(EditDogDescriptor descriptor) {
        super(new EditDogDescriptor(descriptor));
    }

    /**
     * Returns an {@code EditDogDescriptor} with fields containing {@code dog}'s details
     */
    public EditDogDescriptorBuilder(Dog dog) {
        super(new EditDogDescriptor(), dog);
        descriptor.setBreed(dog.getBreed());
        descriptor.setDob(dog.getDob());
        descriptor.setSex(dog.getSex());
        descriptor.setOwnerId(dog.getOwnerId());
    }

    /**
     * Sets the {@code Breed} of the {@code EditDogDescriptor} that we are building.
     */
    public final EditDogDescriptorBuilder withBreed(String breed) {
        descriptor.setBreed(new Breed(breed));
        return self();
    }

    /**
     * Sets the {@code DateOfBirth} of the {@code EditDogDescriptor} that we are building.
     */
    public final EditDogDescriptorBuilder withDob(String dob) {
        descriptor.setDob(new DateOfBirth(dob));
        return self();
    }

    /**
     * Sets the {@code Sex} of the {@code EditDogDescriptor} that we are building.
     */
    public final EditDogDescriptorBuilder withSex(String sex) {
        descriptor.setSex(new Sex(sex));
        return self();
    }

    /**
     * Sets the {@code OwnerId} of the {@code EditDogDescriptor} that we are building.
     */
    public final EditDogDescriptorBuilder withOwnerId(int ownerId) {
        descriptor.setOwnerId(ownerId);
        return self();
    }

    @Override
    protected EditDogDescriptorBuilder self() {
        return this;
    }
}
