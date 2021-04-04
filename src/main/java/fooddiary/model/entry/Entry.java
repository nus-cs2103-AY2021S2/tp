package fooddiary.model.entry;

import static fooddiary.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import fooddiary.model.tag.TagCategory;
import fooddiary.model.tag.TagSchool;

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
    private final Set<TagCategory> tagCategories = new HashSet<>();
    private final Set<TagSchool> tagSchools = new HashSet<>();

    /**
     * Every field must be present and not null.
     */

    public Entry(Name name, Rating rating, Price price, List<Review> reviews,
                 Address address, Set<TagCategory> tagCategories, Set<TagSchool> tagSchools) {
        requireAllNonNull(name, rating, price, reviews, address, tagCategories, tagSchools);
        this.name = name;
        this.rating = rating;
        this.price = price;
        this.reviews.addAll(reviews);
        this.address = address;
        this.tagCategories.addAll(tagCategories);
        this.tagSchools.addAll(tagSchools);
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
    public Set<TagSchool> getTagSchools() {
        return Collections.unmodifiableSet(tagSchools);
    }

    public Set<TagCategory> getTagCategories() {
        return Collections.unmodifiableSet(tagCategories);
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
                && otherEntry.getName().equals(getName()) && otherEntry.getAddress().equals(getAddress());
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
                && otherEntry.getTagCategories().equals(getTagCategories())
                && otherEntry.getTagSchools().equals(getTagSchools());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, rating, reviews, address, tagCategories, tagSchools);
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

        Set<TagCategory> tagCategories = getTagCategories();
        if (!tagCategories.isEmpty()) {
            builder.append("; Categories: ");
            tagCategories.forEach(builder::append);
        }

        Set<TagSchool> tagSchools = getTagSchools();
        if (!tagSchools.isEmpty()) {
            builder.append("; Schools: ");
            tagSchools.forEach(builder::append);
        }
        return builder.toString();
    }

}
