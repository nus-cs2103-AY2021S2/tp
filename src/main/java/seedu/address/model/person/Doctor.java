package seedu.address.model.person;

import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Doctor class in the Doctors Records
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Doctor extends Person {

    /**
     * Every field must be present and not null.
     */
    public Doctor(Name name, Set<Tag> tags) {
        super(name, tags);
    }

    /**
     * Returns true if both doctors have the same identity and data fields.
     * This defines a stronger notion of equality between two patients.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Doctor)) {
            return false;
        }

        Doctor OtherDoctor = (Doctor) other;
        return OtherDoctor.getName().equals(getName())
                && OtherDoctor.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }
}
