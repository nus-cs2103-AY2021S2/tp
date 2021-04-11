package seedu.booking.model;

import static java.util.Objects.requireNonNull;
import static seedu.booking.commons.util.AppUtil.checkArgument;

import seedu.booking.commons.util.StringUtil;

/**
 * Represents a venueTag in the booking system.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}
 */
public class Tag {

    public static final String MESSAGE_CONSTRAINTS =
            "Tag names should be alphanumeric without spaces. \n "
            + "Consecutive commas are not allowed without tags in between. \n";
    public static final String ONE_TAG_CONSTRAINTS = "Only one tag should be provided at a time. \n"
            + "Tag name should be alphanumeric without spaces and should not be empty.";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    public final String tagName;

    /**
     * Constructs a {@code Tag}.
     *
     * @param tagName A valid tag name.
     */
    public Tag(String tagName) {
        requireNonNull(tagName);
        checkArgument(isValidTagName(tagName), MESSAGE_CONSTRAINTS);
        this.tagName = tagName;
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
                || (other instanceof Tag // instanceof handles nulls
                && tagName.equals(((Tag) other).tagName)); // state check
    }

    @Override
    public int hashCode() {
        return tagName.hashCode();
    }

    public String getTagName() {
        return tagName;
    }

    /**
     * Returns true if both tags are the same (case-insensitive).
     * This notion of equality between two tags.
     */
    public boolean isSameTag(Tag tag) {
        return tag != null
                && StringUtil.containsWordIgnoreCase(this.removeSpacesWithinTag(),
                tag.removeSpacesWithinTag());
    }

    public String removeSpacesWithinTag() {
        return this.tagName.replace(" ", "");
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + tagName + ']';
    }

}
