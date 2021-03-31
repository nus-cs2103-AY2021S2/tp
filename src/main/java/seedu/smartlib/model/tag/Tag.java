package seedu.smartlib.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.smartlib.commons.util.AppUtil.checkArgument;

/**
 * Represents a Tag in SmartLib.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}
 */
public class Tag {

    public static final String MESSAGE_CONSTRAINTS = "Tags names should be alphanumeric";

    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    private final String tagName;

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
     * Returns the name associated with this tag.
     *
     * @return The name associated with this tag
     */
    public String getTagName() {
        return tagName;
    }

    /**
     * Returns true if a given string is a valid tag name.
     *
     * @param test string to be tested.
     * @return true if a given string is a valid tag name, and false otherwise.
     */
    public static boolean isValidTagName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Checks if this Tag is equal to another Tag.
     *
     * @param other the other Tag to be compared.
     * @return true if this Tag is equal to the other Tag, and false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Tag // instanceof handles nulls
                && tagName.equals(((Tag) other).tagName)); // state check
    }

    /**
     * Generates a hashcode for this Tag.
     *
     * @return the hashcode for this Tag.
     */
    @Override
    public int hashCode() {
        return tagName.hashCode();
    }

    /**
     * Formats the tag as text for viewing.
     *
     * @return the tag as text for viewing.
     */
    public String toString() {
        return '[' + tagName + ']';
    }

}
