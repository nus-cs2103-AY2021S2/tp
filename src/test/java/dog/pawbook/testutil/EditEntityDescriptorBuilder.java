package dog.pawbook.testutil;

import java.util.Set;

import dog.pawbook.logic.commands.EditEntityCommand.EditEntityDescriptor;
import dog.pawbook.model.managedentity.Entity;
import dog.pawbook.model.managedentity.Name;
import dog.pawbook.model.managedentity.tag.Tag;
import dog.pawbook.model.util.SampleDataUtil;

/**
 * A utility class to help with building EditEntityDescriptor objects.
 */
abstract class EditEntityDescriptorBuilder<T extends EditEntityDescriptorBuilder<T, ? extends EditEntityDescriptor>,
        S extends EditEntityDescriptor> {

    protected S descriptor;

    protected EditEntityDescriptorBuilder(S descriptor) {
        this.descriptor = descriptor;
    }

    /**
     * Returns an {@code EditEntityDescriptor} with fields containing {@code entity}'s details
     */
    public EditEntityDescriptorBuilder(S descriptor, Entity entity) {
        this.descriptor = descriptor;
        this.descriptor.setName(entity.getName());
        this.descriptor.setTags(entity.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditEntityDescriptor} that we are building.
     */
    public final T withName(String name) {
        descriptor.setName(new Name(name));
        return self();
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditEntityDescriptor}
     * that we are building.
     */
    public final T withTags(String... tags) {
        Set<Tag> tagSet = SampleDataUtil.getTagSet(tags);
        descriptor.setTags(tagSet);
        return self();
    }

    public final S build() {
        return descriptor;
    }

    protected abstract T self();
}
