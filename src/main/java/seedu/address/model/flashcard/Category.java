package seedu.address.model.flashcard;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Flashcard's category in the Flashback.
 * Guarantees: immutable; is valid as declared in {@link #isValidCategory(String)}
 */
public class Category {

    public static final String MESSAGE_CONSTRAINTS =
            "Categories can take any values, must not be longer than 30 characters and it should not be blank";
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs an {@code Category}.
     *
     * @param category A valid category.
     */
    public Category(String category) {
        requireNonNull(category);
        checkArgument(isValidCategory(category), MESSAGE_CONSTRAINTS);
        value = category;
    }

    /**
     * Returns if a given string is a valid category.
     */
    public static boolean isValidCategory(String test) {
        return test.matches(VALIDATION_REGEX) && test.length() <= 30;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Category // instanceof handles nulls
                && value.equals(((Category) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
