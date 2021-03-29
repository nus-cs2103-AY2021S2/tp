package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.groupmate.Groupmate;
import seedu.address.model.groupmate.Name;
import seedu.address.model.groupmate.Role;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Contact objects.
 */
public class GroupmateBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";

    private seedu.address.model.groupmate.Name name;
    private Set<Role> roles;

    /**
     * Creates a {@code ContactBuilder} with the default details.
     */
    public GroupmateBuilder() {
        name = new Name(DEFAULT_NAME);
        roles = new HashSet<>();
    }

    /**
     * Initializes the ContactBuilder with the data of {@code groupmateToCopy}.
     */
    public GroupmateBuilder(Groupmate groupmateToCopy) {
        name = groupmateToCopy.getName();
        roles = new HashSet<>(groupmateToCopy.getRoles());
    }

    /**
     * Sets the {@code Name} of the {@code Contact} that we are building.
     */
    public GroupmateBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code roles} into a {@code Set<Role>} and set it to the {@code Groupmate} that we are building.
     */
    public GroupmateBuilder withRoles(String ... roles) {
        this.roles = SampleDataUtil.getRoleSet(roles);
        return this;
    }

    /**
     * Builds the {@code Contact} object.
     *
     * @return {@code Contact}.
     */
    public Groupmate build() {
        return new Groupmate(name, roles);
    }

}
