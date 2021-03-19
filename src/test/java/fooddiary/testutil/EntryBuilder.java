package fooddiary.testutil;

import fooddiary.model.entry.*;
import fooddiary.model.tag.Tag;
import fooddiary.model.util.SampleDataUtil;

import java.util.HashSet;
import java.util.Set;

/**
 * A utility class to help with building Person objects.
 */
public class EntryBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_RATING = "3";
    public static final String DEFAULT_REVIEW = "I like this food!";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private Name name;
    private Rating rating;
    private Review review;
    private Address address;
    private Set<Tag> tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public EntryBuilder() {
        name = new Name(DEFAULT_NAME);
        rating = new Rating(DEFAULT_RATING);
        review = new Review(DEFAULT_REVIEW);
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code entryToCopy}.
     */
    public EntryBuilder(Entry entryToCopy) {
        name = entryToCopy.getName();
        rating = entryToCopy.getRating();
        review = entryToCopy.getReview();
        address = entryToCopy.getAddress();
        tags = new HashSet<>(entryToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public EntryBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public EntryBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public EntryBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Rating} of the {@code Person} that we are building.
     */
    public EntryBuilder withRating(String rating) {
        this.rating = new Rating(rating);
        return this;
    }

    /**
     * Sets the {@code Review} of the {@code Person} that we are building.
     */
    public EntryBuilder withReview(String review) {
        this.review = new Review(review);
        return this;
    }

    public Entry build() {
        return new Entry(name, rating, review, address, tags);
    }

}
