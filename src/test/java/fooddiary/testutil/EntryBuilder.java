package fooddiary.testutil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import fooddiary.model.entry.Address;
import fooddiary.model.entry.Entry;
import fooddiary.model.entry.Name;
import fooddiary.model.entry.Price;
import fooddiary.model.entry.Rating;
import fooddiary.model.entry.Review;
import fooddiary.model.tag.Tag;
import fooddiary.model.util.SampleDataUtil;


/**
 * A utility class to help with building Entry objects.
 */
public class EntryBuilder {

    public static final String DEFAULT_NAME = "Restaurant Z";
    public static final String DEFAULT_RATING = "3";
    public static final String DEFAULT_PRICE = "6";
    public static final String DEFAULT_REVIEW = "I like this food!";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private Name name;
    private Rating rating;
    private Price price;
    private List<Review> reviews;
    private Address address;
    private Set<Tag> tags;

    /**
     * Creates a {@code EntryBuilder} with the default details.
     */
    public EntryBuilder() {
        name = new Name(DEFAULT_NAME);
        rating = new Rating(DEFAULT_RATING);
        price = new Price(DEFAULT_PRICE);
        reviews = new ArrayList<>();
        reviews.add(new Review(DEFAULT_REVIEW));
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
    }

    /**
     * Initializes the EntryBuilder with the data of {@code entryToCopy}.
     */
    public EntryBuilder(Entry entryToCopy) {
        name = entryToCopy.getName();
        rating = entryToCopy.getRating();
        price = entryToCopy.getPrice();
        reviews = entryToCopy.getReviews();
        address = entryToCopy.getAddress();
        tags = new HashSet<>(entryToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Entry} that we are building.
     */
    public EntryBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Entry} that we are building.
     */
    public EntryBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Entry} that we are building.
     */
    public EntryBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Rating} of the {@code Entry} that we are building.
     */
    public EntryBuilder withRating(String rating) {
        this.rating = new Rating(rating);
        return this;
    }

    /**
     * Sets the {@code Price} of the {@code price} that we are building.
     */
    public EntryBuilder withPrice(String price) {
        this.price = new Price(price);
        return this;
    }

    /**
     * Parses the {@code reviews} into a {@code List<Review>} and set it to the {@code Entry} that we are building.
     */
    public EntryBuilder withReviews(String ... reviews) {
        this.reviews = SampleDataUtil.getReviewList(reviews);
        return this;
    }

    public Entry build() {
        return new Entry(name, rating, price, reviews, address, tags);
    }

}
