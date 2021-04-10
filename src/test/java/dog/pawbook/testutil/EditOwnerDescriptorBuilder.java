package dog.pawbook.testutil;

import dog.pawbook.logic.commands.EditOwnerCommand.EditOwnerDescriptor;
import dog.pawbook.model.managedentity.owner.Address;
import dog.pawbook.model.managedentity.owner.Email;
import dog.pawbook.model.managedentity.owner.Owner;
import dog.pawbook.model.managedentity.owner.Phone;

/**
 * A utility class to help with building EditOwnerDescriptor objects.
 */
public class EditOwnerDescriptorBuilder
        extends EditEntityDescriptorBuilder<EditOwnerDescriptorBuilder, EditOwnerDescriptor> {

    public EditOwnerDescriptorBuilder() {
        super(new EditOwnerDescriptor());
    }

    public EditOwnerDescriptorBuilder(EditOwnerDescriptor descriptor) {
        super(new EditOwnerDescriptor(descriptor));
    }

    /**
     * Returns an {@code EditOwnerDescriptor} with fields containing {@code owner}'s details
     */
    public EditOwnerDescriptorBuilder(Owner owner) {
        super(new EditOwnerDescriptor(), owner);
        descriptor.setPhone(owner.getPhone());
        descriptor.setEmail(owner.getEmail());
        descriptor.setAddress(owner.getAddress());
    }

    /**
     * Sets the {@code Phone} of the {@code EditOwnerDescriptor} that we are building.
     */
    public final EditOwnerDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return self();
    }

    /**
     * Sets the {@code Email} of the {@code EditOwnerDescriptor} that we are building.
     */
    public final EditOwnerDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return self();
    }

    /**
     * Sets the {@code Address} of the {@code EditOwnerDescriptor} that we are building.
     */
    public final EditOwnerDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return self();
    }

    @Override
    protected EditOwnerDescriptorBuilder self() {
        return this;
    }
}
