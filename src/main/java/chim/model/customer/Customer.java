package chim.model.customer;

import static chim.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import chim.model.tag.Tag;

/**
 * Represents a Customer in CHIM.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Customer {
    // Identity fields
    private final CustomerId customerId;
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Customer(Name name, Phone phone, Email email, Address address, Set<Tag> tags, CustomerId customerId) {
        requireAllNonNull(name, phone, email, address, tags, customerId);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.customerId = customerId;
    }
    public Customer(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        this(name, phone, email, address, tags, CustomerId.getNextId());
    }

    public Name getName() {
        return name;
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

    public CustomerId getId() {
        return customerId;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both customers have the same phone.
     * This defines a weaker notion of equality between two customers.
     */
    public boolean isSameCustomer(Customer otherCustomer) {
        if (otherCustomer == this) {
            return true;
        }

        return otherCustomer != null
                && otherCustomer.getPhone().equals(getPhone());
    }

    /**
     * Returns true if both customers have the same identity and data fields.
     * This defines a stronger notion of equality between two customers.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Customer)) {
            return false;
        }

        Customer otherCustomer = (Customer) other;
        return otherCustomer.getId().equals(getId())
                && otherCustomer.getName().equals(getName())
                && otherCustomer.getPhone().equals(getPhone())
                && otherCustomer.getEmail().equals(getEmail())
                && otherCustomer.getAddress().equals(getAddress())
                && otherCustomer.getTags().equals(getTags());
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
