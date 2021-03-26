package fooddiary.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import fooddiary.commons.exceptions.IllegalValueException;
import fooddiary.model.entry.Address;
import fooddiary.model.entry.Entry;
import fooddiary.model.entry.Name;
import fooddiary.model.entry.Price;
import fooddiary.model.entry.Rating;
import fooddiary.model.entry.Review;
import fooddiary.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Entry}.
 */
class JsonAdaptedEntry {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Entry's %s field is missing!";

    private final String name;
    private final List<JsonAdaptedReview> reviews = new ArrayList<>();
    private final String rating;
    private final String price;
    private final String address;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedEntry} with the given entry details.
     */
    @JsonCreator
    public JsonAdaptedEntry(@JsonProperty("name") String name, @JsonProperty("rating") String rating,
                            @JsonProperty("price") String price,
                            @JsonProperty("review") List<JsonAdaptedReview> reviews,
                            @JsonProperty("address") String address,
                            @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.name = name;
        this.rating = rating;
        this.price = price;
        if (reviews != null) {
            this.reviews.addAll(reviews);
        }
        this.address = address;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Entry} into this class for Jackson use.
     */
    public JsonAdaptedEntry(Entry source) {
        name = source.getName().fullName;
        rating = source.getRating().value;
        price = source.getPrice().value;
        reviews.addAll(source.getReviews().stream()
                .map(JsonAdaptedReview::new)
                .collect(Collectors.toList()));
        address = source.getAddress().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted entry object into the model's {@code Entry} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted entry.
     */
    public Entry toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        final List<Review> reviewList = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            personTags.add(tag.toModelType());
        }
        for (JsonAdaptedReview review : reviews) {
            reviewList.add(review.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (rating == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Rating.class.getSimpleName()));
        }
        if (!Rating.isValidRating(rating)) {
            throw new IllegalValueException(Rating.MESSAGE_CONSTRAINTS);
        }
        final Rating modelRating = new Rating(rating);

        if (price == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Price.class.getSimpleName()));
        }
        if (!Price.isValidPrice(price)) {
            throw new IllegalValueException(Price.MESSAGE_CONSTRAINTS);
        }
        final Price modelPrice = new Price(price);

        final List<Review> modelReviews = new ArrayList<>(reviewList);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        final Set<Tag> modelTags = new HashSet<>(personTags);

        return new Entry(modelName, modelRating, modelPrice, modelReviews, modelAddress, modelTags);
    }

}
