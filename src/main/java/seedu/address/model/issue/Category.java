package seedu.address.model.issue;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an issue's category in SunRez. Guarantees: immutable; is valid as
 * declared in {@link #isValidCategory(String)}.
 */
public class Category {

    public static final String MESSAGE_CONSTRAINTS = "Categories should only contain alphanumeric "
            + "characters and spaces, and it should not be blank";
    public static final String NO_CATEGORY_NAME = "No category";
    public static final Category NO_CATEGORY = new Category(NO_CATEGORY_NAME);

    /*
     * The first character of the category must not be a whitespace, otherwise " "
     * (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String value;

    /**
     * Constructs a {@code Category}.
     *
     * @param category A valid category.
     */
    public Category(String category) {
        requireNonNull(category);
        checkArgument(isValidCategory(category), MESSAGE_CONSTRAINTS);
        this.value = category;
    }

    /**
     * Returns true if a given string is a valid category.
     *
     * @param test String to check.
     * @return {@code True} if test is valid.
     */
    public static boolean isValidCategory(String test) {
        return test.matches(VALIDATION_REGEX);
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
