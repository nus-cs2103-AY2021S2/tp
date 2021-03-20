package fooddiary.model.entry;

import static java.util.Objects.requireNonNull;

import fooddiary.commons.util.AppUtil;

/**
 * Represents a Entry's rating in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidRating(String)}
 */
public class Rating {


    public static final String MESSAGE_CONSTRAINTS =
            "Ratings should only contain a integer (e.g. 3) between 0-5, and it should be a single digit ";
    public static final String VALIDATION_REGEX = "^[0-5]{1}$";
    public final String value;

    /**
     * Constructs a {@code Rating}.
     *
     * @param rating A valid rating.
     */
    public Rating(String rating) {
        requireNonNull(rating);
        AppUtil.checkArgument(isValidRating(rating), MESSAGE_CONSTRAINTS);
        value = rating;
    }

    /**
     * Returns true if a given string is a valid rating.
     */
    public static boolean isValidRating(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Rating // instanceof handles nulls
                && value.equals(((Rating) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
