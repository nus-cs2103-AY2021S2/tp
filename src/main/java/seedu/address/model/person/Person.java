package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public abstract class Person {

    // UUID to determine uniqueness
    protected final UUID uuid;

    // Identity fields
    protected final Name name;

    // Data fields
    protected final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null, except for UUID,
     * which can be generated automatically.
     */
    public Person(Name name, Set<Tag> tags) {
        requireAllNonNull(name, tags);
        this.uuid = UUID.randomUUID();
        this.name = name;
        this.tags.addAll(tags);
    }

    /**
     * Every field must be present and not null.
     */
    public Person(UUID uuid, Name name, Set<Tag> tags) {
        requireAllNonNull(name, tags);
        this.uuid = uuid;
        this.name = name;
        this.tags.addAll(tags);
    }

    public UUID getUuid() {
        return uuid;
    }

    public Name getName() {
        return name;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return otherPerson.getUuid().equals(getUuid())
                && otherPerson.getName().equals(getName())
                && otherPerson.getTags().equals(getTags());
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
