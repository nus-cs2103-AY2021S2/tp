package dog.pawbook.model.managedentity.owner;

import static dog.pawbook.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import dog.pawbook.model.managedentity.Entity;
import dog.pawbook.model.managedentity.Name;
import dog.pawbook.model.managedentity.tag.Tag;

/**
 * Represents a Owner in the database.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Owner extends Entity {
    public static final String ENTITY_WORD = "owner";

    private static final String PHONE_PREFIX = "Phone: ";
    private static final String EMAIL_PREFIX = "Email: ";
    private static final String ADDRESS_PREFIX = "Address: ";
    private static final String DOG_ID_PREFIX = "ID of Dog(s) owned: ";
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

    @Override
    public boolean isSameAs(Entity otherEntity) {
        if (otherEntity == this) {
            return true;
        }

        if (!(otherEntity instanceof Owner)) {
            return false;
        }

        Owner otherOwner = (Owner) otherEntity;

        return super.isSameAs(otherEntity)
                && (otherOwner.getPhone().equals(getPhone())
                    || otherOwner.getEmail().equals(getEmail()));
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
    public List<String> getOtherPropertiesAsString() {
        List<String> properties = new ArrayList<>();
        properties.add(PHONE_PREFIX + phone.value);
        properties.add(ADDRESS_PREFIX + address.value);
        properties.add(EMAIL_PREFIX + email.value);

        if (!dogidSet.isEmpty()) {
            properties.add(dogidSet.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(", ", DOG_ID_PREFIX, "")));
        }
        return properties;
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

    @Override
    public List<Integer> getRelatedEntityIds() {
        return dogidSet.stream().sorted().collect(Collectors.toList());
    }
}
