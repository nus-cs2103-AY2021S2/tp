package fooddiary.model.entry;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import fooddiary.commons.util.AppUtil;

/**
 * Represents a Restaurant's review in the Food Diary.
 * Guarantees: immutable; is valid as declared in {@link #isValidReview(String)}
 */
public class Review {

    public static final String MESSAGE_CONSTRAINTS = "Reviews can take any values, and it should not be blank";

    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs an {@code Review}.
     *
     * @param review A valid review.
     */
    public Review(String review) {
        requireNonNull(review);
        AppUtil.checkArgument(isValidReview(review), MESSAGE_CONSTRAINTS);
        value = review;
    }

    /**
     * Returns if a given string is a valid review.
     */
    public static boolean isValidReview(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Review // instanceof handles nulls
                && value.equals(((Review) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
