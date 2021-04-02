package seedu.address.model.contact;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.Address;
import seedu.address.model.Name;
import seedu.address.model.tag.Tag;

/**
 * Represents a Contact in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Contact implements Comparable<Contact> {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();

    private final TimeAdded timeAdded;
    private final Favourite favourite;

    /**
     * Every field must be present and not null.
     */
    public Contact(Name name, Phone phone, Email email, Address address,
                   Set<Tag> tags, TimeAdded timeAdded, Favourite favourite) {
        requireAllNonNull(name, phone, email, address, tags, timeAdded, favourite);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.timeAdded = timeAdded;
        this.favourite = favourite;
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

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public TimeAdded getTimeAdded() {
        return timeAdded;
    }

    public Favourite getFavourite() {
        return favourite;
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */

    public boolean isSameContact(Contact otherContact) {
        if (otherContact == this) {
            return true;
        }

        return otherContact != null
                && otherContact.getName().equals(getName())
                && otherContact.getTags().equals(getTags());
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

        if (!(other instanceof Contact)) {
            return false;
        }

        Contact otherContact = (Contact) other;
        return otherContact.getName().equals(getName())
                && otherContact.getPhone().equals(getPhone())
                && otherContact.getEmail().equals(getEmail())
                && otherContact.getAddress().equals(getAddress())
                && otherContact.getTags().equals(getTags())
                && otherContact.getFavourite().equals(getFavourite());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags, favourite);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("\nPhone: ")
                .append(getPhone())
                .append("\nEmail: ")
                .append(getEmail())
                .append("\nAddress: ")
                .append(getAddress());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("\nTags: ");
            tags.forEach(builder::append);
        }

        return builder.toString();
    }

    @Override
    public int compareTo(Contact p) {
        return this.getName().fullName.compareToIgnoreCase(p.getName().fullName);
    }
}
