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
    public final ArrayList<String> values;

    /**
     * Constructs an {@code Review}.
     *
     * @param review A valid review.
     */
    public Review(String review) {
        requireNonNull(review);
        AppUtil.checkArgument(isValidReview(review), MESSAGE_CONSTRAINTS);
        value = review;
        values = new ArrayList<>();
        values.add(review);
    }

    /**
     * Returns if a given string is a valid review.
     */
    public static boolean isValidReview(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Add a review to a list of reviews.
     * @param review review to add to the list.
     */
    public void addReview(String review) {
        requireNonNull(review);
        AppUtil.checkArgument(isValidReview(review), MESSAGE_CONSTRAINTS);
        values.add(review);
    }

    @Override
    public String toString() {
        assert value.length() > 0 : "Review does not have any values";
        int reviewNumber = 1;
        String reviewPrintResult = "";
        for (String review : values) {
            reviewPrintResult += reviewNumber
                    + ". "
                    + review + "\n";
            reviewNumber++;
        }
        return reviewPrintResult.trim();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Review // instanceof handles nulls
                && values.equals(((Review) other).values)); // state check
    }

    @Override
    public int hashCode() {
        return values.hashCode();
    }

}
