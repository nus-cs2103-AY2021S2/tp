package seedu.timeforwheels.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.timeforwheels.commons.util.AppUtil.checkArgument;

/**
 * Represents a Tag in the delivery list.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}
 */
public class Tag {

    public static final String MESSAGE_CONSTRAINTS = "Tags names should be \"urgent\", \"fragile\", \"bulky\", "
        + "\"food\", \"liquid\", \"hot\", \"cold\", or \"heavy\".";
    public static final String[] VALID_TAGS = {"urgent", "fragile", "bulky", "food", "liquid", "hot", "cold", "heavy"};
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
    public static boolean isValidTagName(String inputTag) {
        for (String validTag : VALID_TAGS) {
            if (validTag.matches(inputTag)) {
                return true;
            }
        }
        return false;
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

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + tagName + ']';
    }
}
