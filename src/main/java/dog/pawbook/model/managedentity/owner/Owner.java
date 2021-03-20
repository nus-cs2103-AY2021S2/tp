package dog.pawbook.model.managedentity.owner;

import static dog.pawbook.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;
import java.util.Set;

import dog.pawbook.model.managedentity.Entity;
import dog.pawbook.model.managedentity.Name;
import dog.pawbook.model.managedentity.tag.Tag;

/**
 * Represents a Owner in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Owner extends Entity {
    public static final String ENTITY_WORD = "owner";

    // Identity fields
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;

    /**
     * Every field must be present and not null.
     */
    public Owner(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        super(name, tags);
        requireAllNonNull(phone, email, address, tags);
        this.phone = phone;
        this.email = email;
        this.address = address;
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
                && otherOwner.getAddress().equals(getAddress());
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
}
