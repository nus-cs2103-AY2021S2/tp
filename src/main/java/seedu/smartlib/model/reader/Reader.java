package seedu.smartlib.model.reader;

import static seedu.smartlib.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import seedu.smartlib.commons.core.name.Name;
import seedu.smartlib.model.record.DateBorrowed;
import seedu.smartlib.model.tag.Tag;

/**
 * Represents a reader in SmartLib.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Reader {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();

    private final Map<Name, DateBorrowed> borrows = new HashMap<>();

    /**
     * Every field must be present and not null.
     */
    public Reader(Name name, Phone phone, Email email, Address address, Set<Tag> tags, Map<Name, DateBorrowed> borrows) {
        requireAllNonNull(name, phone, email, address, tags, borrows);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.borrows.putAll(borrows);
    }


    public Map<Name, DateBorrowed> getBorrows() {
        return borrows;
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

    /**
     * Checks if a reader has overdue books
     * @return true if the reader has overdue books
     */
    public boolean hasOverdueBooks() {
        for (DateBorrowed dateBorrowed : this.borrows.values()) {
            if (dateBorrowed.isOverdue()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if both readers have the same name.
     * This defines a weaker notion of equality between two readers.
     */
    public boolean isSameReader(Reader otherReader) {
        if (otherReader == this) {
            return true;
        }

        return otherReader != null
                && otherReader.getName().equals(getName());
    }

    /**
     * Returns true if both readers have the same identity and data fields.
     * This defines a stronger notion of equality between two readers.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Reader)) {
            return false;
        }

        Reader otherReader = (Reader) other;
        return otherReader.getName().equals(getName())
                && otherReader.getPhone().equals(getPhone())
                && otherReader.getEmail().equals(getEmail())
                && otherReader.getAddress().equals(getAddress())
                && otherReader.getTags().equals(getTags());
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
