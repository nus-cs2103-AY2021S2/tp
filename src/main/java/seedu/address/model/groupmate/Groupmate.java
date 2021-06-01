package seedu.address.model.groupmate;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Represents a groupmate in the groupmate list.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Groupmate {

    private final Name name;
    private final Set<Role> roles = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Groupmate(Name name, Set<Role> roles) {
        requireAllNonNull(name, roles);
        this.name = name;
        this.roles.addAll(roles);
    }

    public Name getName() {
        return name;
    }

    /**
     * Returns an immutable role set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Role> getRoles() {
        return Collections.unmodifiableSet(roles);
    }

    /**
     * Returns true if both Groupmates have the same name.
     * This defines a weaker notion of equality between two Groupmates.
     */
    public boolean isSameGroupmate(Groupmate other) {
        if (other == this) {
            return true;
        }

        return other != null
                && other.getName().equals(getName());
    }

    /**
     * Returns true if both Groupmates have the same identity and data fields.
     * This defines a stronger notion of equality between two Groupmates.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Groupmate)) {
            return false;
        }

        Groupmate otherGroupmate = (Groupmate) other;
        return otherGroupmate.getName().equals(getName())
                && otherGroupmate.getRoles().equals(getRoles());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, roles);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName());

        Set<Role> roles = getRoles();
        if (!roles.isEmpty()) {
            builder.append("; Roles: ");
            roles.forEach(builder::append);
        }
        return builder.toString();
    }

}
