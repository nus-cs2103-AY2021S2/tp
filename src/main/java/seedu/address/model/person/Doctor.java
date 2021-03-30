package seedu.address.model.person;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import seedu.address.model.tag.Tag;

/**
 * Represents a Doctor class in the Doctors Records
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Doctor extends Person {

    /**
     * Every field must be present and not null, except for UUID,
     * which can be generated automatically by superclass.
     */
    public Doctor(Name name, Set<Tag> tags) {
        super(name, tags);
    }

    /**
     * Every field must be present and not null.
     */
    public Doctor(UUID uuid, Name name, Set<Tag> tags) {
        super(uuid, name, tags);
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

        Doctor otherDoctor = (Doctor) other;
        return otherDoctor.getName().equals(getName())
                && otherDoctor.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, tags);
    }

    @Override
    public String toString() {
        return super.toString(); // to modify when unique fields arise
    }
}
