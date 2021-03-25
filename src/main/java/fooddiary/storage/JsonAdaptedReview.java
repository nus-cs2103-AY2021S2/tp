package fooddiary.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import fooddiary.commons.exceptions.IllegalValueException;
import fooddiary.model.entry.Review;

/**
 * Jackson-friendly version of {@link Review}.
 */
class JsonAdaptedReview {

    private final String reviewValue;

    /**
     * Constructs a {@code JsonAdaptedReview} with the given {@code reviewValue}.
     */
    @JsonCreator
    public JsonAdaptedReview(String reviewValue) {
        this.reviewValue = reviewValue;
    }

    /**
     * Converts a given {@code Review} into this class for Jackson use.
     */
    public JsonAdaptedReview(Review source) {
        reviewValue = source.value;
    }

    @JsonValue
    public String getTagName() {
        return reviewValue;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Review} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted review.
     */
    public Review toModelType() throws IllegalValueException {
        if (!Review.isValidReview(reviewValue)) {
            throw new IllegalValueException(Review.MESSAGE_CONSTRAINTS);
        }
        return new Review(reviewValue);
    }

}
