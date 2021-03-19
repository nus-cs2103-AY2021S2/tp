package fooddiary.model.entry;

import fooddiary.model.tag.Tag;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static fooddiary.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a Entry in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Entry {

    // Identity fields
    private final Name name;
    private final Review review;
    private final Rating rating;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */

    public Entry(Name name, Rating rating, Review review, Address address, Set<Tag> tags) {
        requireAllNonNull(name, rating, review, address, tags);
        this.name = name;
        this.rating = rating;
        this.review = review;
        this.address = address;
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public Rating getRating() {
        return rating;
    }

    public Review getReview() {
        return review;
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
     * Returns true if both entries have the same name.
     * This defines a weaker notion of equality between two entries.
     */
    public boolean isSameEntry(Entry otherEntry) {
        if (otherEntry == this) {
            return true;
        }

        return otherEntry != null
                && otherEntry.getName().equals(getName());
    }

    /**
     * Returns true if both entries have the same identity and data fields.
     * This defines a stronger notion of equality between two entries.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Entry)) {
            return false;
        }

        Entry otherEntry = (Entry) other;
        return otherEntry.getName().equals(getName())
                && otherEntry.getRating().equals(getRating())
                && otherEntry.getReview().equals(getReview())
                && otherEntry.getAddress().equals(getAddress())
                && otherEntry.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, rating, review, address, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Rating: ")
                .append(getRating())
                .append("; Review: ")
                .append(getReview())
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
