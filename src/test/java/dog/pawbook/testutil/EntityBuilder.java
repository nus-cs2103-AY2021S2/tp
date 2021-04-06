package dog.pawbook.testutil;

import java.util.HashSet;
import java.util.Set;

import dog.pawbook.model.managedentity.Entity;
import dog.pawbook.model.managedentity.Name;
import dog.pawbook.model.managedentity.tag.Tag;
import dog.pawbook.model.util.SampleDataUtil;

abstract class EntityBuilder<S extends EntityBuilder<S, ? extends Entity>, T extends Entity> {
    protected Name name;
    protected Set<Tag> tags;

    protected EntityBuilder(String name) {
        this.name = new Name(name);
        tags = new HashSet<>();
    }

    protected EntityBuilder(Entity entityToCopy) {
        name = entityToCopy.getName();
        tags = new HashSet<>(entityToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Entity} that we are building.
     */
    public final S withName(String name) {
        this.name = new Name(name);
        return self();
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Entity} that we are building.
     */
    public final S withTags(String... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return self();
    }

    public abstract T build();

    protected abstract S self();
}
