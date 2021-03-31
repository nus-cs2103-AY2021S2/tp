package dog.pawbook.model.managedentity.owner;

import static dog.pawbook.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import dog.pawbook.model.managedentity.Entity;
import dog.pawbook.model.managedentity.Name;
import dog.pawbook.model.managedentity.tag.Tag;

/**
 * Represents a Owner in the database.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Owner extends Entity {
    public static final String ENTITY_WORD = "owner";

    // Identity fields
    private final Phone phone;
    private final Email email;
    // Data fields
    private final Address address;
    private final Set<Integer> dogidSet = new HashSet<>();
    /**
     * Every field must be present and not null.
     */
    public Owner(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        super(name, tags);
        requireAllNonNull(phone, email, address);
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    /**
     * Every field must be present and not null.
     */
    public Owner(Name name, Phone phone, Email email, Address address, Set<Tag> tags, Set<Integer> dogIDs) {
        super(name, tags);
        requireAllNonNull(phone, email, address, dogIDs);
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.dogidSet.addAll(dogIDs);
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public Set<Integer> getDogIdSet() {
        return Collections.unmodifiableSet(dogidSet);
    }

    /**
     * Returns true if both owners have the same identity and data fields.
     * This defines a stronger notion of equality between two owners.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Owner)) {
            return false;
        }

        Owner otherOwner = (Owner) other;
        return super.equals(other)
                && otherOwner.getPhone().equals(getPhone())
                && otherOwner.getEmail().equals(getEmail())
                && otherOwner.getAddress().equals(getAddress())
                && otherOwner.getDogIdSet().equals(getDogIdSet());
    }

    @Override
    public String[] getOtherPropertiesAsString() {
        return new String[] {phone.value, address.value, email.value};
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Address: ")
                .append(getAddress());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

    /**
     * Returns an array of IDs that are closely related to the entity.
     */
    @Override
    public Collection<Integer> getRelatedEntityIds() {
        return new ArrayList<>(dogidSet);
    }
}
