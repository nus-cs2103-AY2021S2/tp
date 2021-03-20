package fooddiary.model.tag;

import static java.util.Objects.requireNonNull;

import fooddiary.commons.util.AppUtil;

/**
 * Represents a Tag in the food diary.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}
 */
public class Tag {

    public static final String MESSAGE_CONSTRAINTS = "Tags names should be of displayed categories.";
    public final TagCategories tagCategory;

    /**
     * Constructs a {@code Tag}.
     *
     * @param tagCategory A valid tag name.
     */
    public Tag(String tagCategory) {
        requireNonNull(tagCategory);

        AppUtil.checkArgument(isValidTagName(tagCategory), MESSAGE_CONSTRAINTS);
        this.tagCategory = TagCategories.find(tagCategory);
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidTagName(String tagCategory) {
        return TagCategories.matches(tagCategory) && !(tagCategory.toLowerCase().equals("invalid"));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Tag // instanceof handles nulls
                && tagCategory.equals(((Tag) other).tagCategory)); // state check
    }

    @Override
    public int hashCode() {
        return tagCategory.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + tagCategory.name() + ']';
    }

}
