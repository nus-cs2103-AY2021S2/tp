package seedu.budgetbaby.model.record;

import static java.util.Objects.requireNonNull;
import static seedu.budgetbaby.commons.util.AppUtil.checkArgument;

/**
 * Represents a Category in the budget tracker.
 */
public class Category {

    public static final String MESSAGE_CONSTRAINTS = "Categories should be alphanumeric";

    public final String category;

    /**
     * Constructs a {@code Tag}.
     *
     * @param tagName A valid tag name.
     */
    public Category(String tagName) {
        requireNonNull(tagName);
        this.category = tagName;
    }

    /**
     * Returns the tag name.
     */
    public String getCategory() {
        return this.category;
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
