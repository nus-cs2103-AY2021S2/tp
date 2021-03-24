package fooddiary.model.tag;

import static java.util.Objects.requireNonNull;

import fooddiary.commons.util.AppUtil;

/**
 * Represents a Tag in the food diary.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}
 */
public class Tag {

    public static final String MESSAGE_CONSTRAINTS = "Tags names should be of displayed categories.";
    public final String tag;

    /**
     * Constructs a {@code Tag}.
     *
     * @param tag A valid tag name.
     */
    public Tag(String tag) {
        requireNonNull(tag);

        AppUtil.checkArgument(isValidTagName(tag), MESSAGE_CONSTRAINTS);
        if (isValidSchoolName(tag)) {
            this.tag = School.find(tag).name();
        } else {
            this.tag = Categories.find(tag).titleCase();

        }

    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidTagName(String tagCategory) {
        return isValidCategoryName(tagCategory) || isValidSchoolName(tagCategory);
    }

    private static boolean isValidCategoryName(String tagCategory) {
        return Categories.matches(tagCategory) && !(tagCategory.toLowerCase().equals("invalid"));
    }

    private static boolean isValidSchoolName(String tagCategory) {
        return School.matches(tagCategory) && !(tagCategory.toUpperCase().equals("INVALID"));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Tag // instanceof handles nulls
                && this.tag.equals(((Tag) other).tag)); // state check
    }

    @Override
    public int hashCode() {
        return tag.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + this.tag + ']';
    }

}
