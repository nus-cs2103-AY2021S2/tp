package seedu.smartlib.model.book;

import seedu.smartlib.commons.core.name.Name;

/**
 * Represents an author in SmartLib.
 */
public class Author {

    private final String fullName;

    /**
     * Constructs an {@code Author}.
     *
     * @param fullName A valid full name of the author.
     */
    public Author(String fullName) {
        this.fullName = fullName;
    }

    // todo: REGEX
    public static boolean isValidAuthor(String test) {
        return true;
    }

    @Override
    public String toString() {
        return fullName.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Author // instanceof handles nulls
                && fullName.equals(((Author) other).fullName)); // state check
    }

    @Override
    public int hashCode() {
        return fullName.hashCode();
    }

}
