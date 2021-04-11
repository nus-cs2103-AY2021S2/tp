package seedu.budgetbaby.model.record;

import static java.util.Objects.requireNonNull;
import static seedu.budgetbaby.commons.util.AppUtil.checkArgument;

/**
 * Represents a Category in the budget tracker.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}
 */
public class Category {

    public static final String MESSAGE_CONSTRAINTS = "Category labels should "
            + "contain at least 1 non-whitespace character but no longer than 20 characters";
    public static final String VALIDATION_REGEX = "^.{1,20}$";

    public final String category;

    /**
     * Constructs a {@code Tag}.
     *
     * @param tagName A valid tag name.
     */
    public Category(String tagName) {
        requireNonNull(tagName);
        checkArgument(isValidTagName(tagName), MESSAGE_CONSTRAINTS);
        this.category = tagName;
    }

    /**
     * Returns the tag name.
     */
    public String getCategory() {
        return this.category;
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidTagName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Category // instanceof handles nulls
                && category.equals(((Category) other).category)); // state check
    }

    @Override
    public int hashCode() {
        return category.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + category + ']';
    }

}
