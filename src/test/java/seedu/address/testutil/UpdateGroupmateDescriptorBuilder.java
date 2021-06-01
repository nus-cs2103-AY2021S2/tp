package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.UpdateGroupmateCommand;
import seedu.address.model.groupmate.Groupmate;
import seedu.address.model.groupmate.Name;
import seedu.address.model.groupmate.Role;

/**
 * A utility class to help with building UpdateGroupmateDescriptor objects.
 */
public class UpdateGroupmateDescriptorBuilder {

    private UpdateGroupmateCommand.UpdateGroupmateDescriptor descriptor;

    public UpdateGroupmateDescriptorBuilder() {
        descriptor = new UpdateGroupmateCommand.UpdateGroupmateDescriptor();
    }

    public UpdateGroupmateDescriptorBuilder(UpdateGroupmateCommand.UpdateGroupmateDescriptor descriptor) {
        this.descriptor = new UpdateGroupmateCommand.UpdateGroupmateDescriptor(descriptor);
    }

    /**
     * Returns an {@code UpdateGroupmateDescriptorBuilder} with fields containing {@code Groupmate}'s details
     */
    public UpdateGroupmateDescriptorBuilder(Groupmate groupmate) {
        descriptor = new UpdateGroupmateCommand.UpdateGroupmateDescriptor();
        descriptor.setName(groupmate.getName());
        descriptor.setRoles(groupmate.getRoles());
    }

    /**
     * Sets the {@code Name} of the {@code UpdateGroupmateDescriptorBuilder} that we are building.
     */
    public UpdateGroupmateDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Parses the {@code roles} into a {@code Set<Role>} and set it to the {@code UpdateGroupmateDescriptorBuilder}
     * that we are building.
     */
    public UpdateGroupmateDescriptorBuilder withRoles(String... roles) {
        Set<Role> roleSet = Stream.of(roles).map(Role::new).collect(Collectors.toSet());
        descriptor.setRoles(roleSet);
        return this;
    }

    public UpdateGroupmateCommand.UpdateGroupmateDescriptor build() {
        return descriptor;
    }
}
