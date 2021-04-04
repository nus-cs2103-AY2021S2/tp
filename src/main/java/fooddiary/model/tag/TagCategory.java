package fooddiary.model.tag;

import static java.util.Objects.requireNonNull;

import fooddiary.commons.util.AppUtil;

public class TagCategory extends Tag {

    public static final String MESSAGE_CONSTRAINTS = "Categories should be of displayed categories.\n"
                                                        + Categories.listAll();

    private String tag;
    private Categories category;

    /**
     * Constructs a {@code Tag}.
     *
     * @param tag A valid tag name.
     */
    public TagCategory(String tag) {
        super(Categories.find(tag).name());
        requireNonNull(tag);
        AppUtil.checkArgument(isValidTagName(tag), MESSAGE_CONSTRAINTS);
        if (isValidTagName(tag)) {
            this.category = Categories.find(tag);
            this.tag = Categories.find(tag).titleCase();
        }
    }

    public String getTag() {
        return this.tag;
    }

    public static boolean isValidTagName(String tagCategory) {
        return Categories.matches(tagCategory) && !(tagCategory.toUpperCase().equals(Categories.INVALID.name()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagCategory // instanceof handles nulls
                && this.tag.equals(((Tag) other).tag)); // state check
    }

    @Override
    public int hashCode() {
        return getTag().hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + this.tag + ']';
    }


}
