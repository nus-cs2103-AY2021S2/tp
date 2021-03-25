package fooddiary.model.entry;

import static fooddiary.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import fooddiary.model.tag.Tag;

/**
 * Represents a Entry in the food diary.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Entry {

    // Identity fields
    private final Name name;
    private final List<Review> reviews = new ArrayList<>();
    private final Rating rating;
    private final Price price;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */

    public Entry(Name name, Rating rating, Price price, List<Review> reviews, Address address, Set<Tag> tags) {
        requireAllNonNull(name, rating, price, reviews, address, tags);
        this.name = name;
        this.rating = rating;
        this.price = price;
        this.reviews.addAll(reviews);
        this.address = address;
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public Rating getRating() {
        return rating;
    }

    /**
     * Returns an immutable review list, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public List<Review> getReviews() {
        return Collections.unmodifiableList(reviews);
    }

    public Price getPrice() {
        return price;
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
                && otherEntry.getPrice().equals(getPrice())
                && otherEntry.getReviews().equals(getReviews())
                && otherEntry.getAddress().equals(getAddress())
                && otherEntry.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, rating, reviews, address, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Rating: ")
                .append(getRating())
                .append("; Price: ")
                .append(getPrice());

        List<Review> reviews = getReviews();
        if (!reviews.isEmpty()) {
            builder.append("; Reviews: ");
            reviews.forEach(builder::append);
        }

        builder.append("; Address: ")
                .append(getAddress());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
