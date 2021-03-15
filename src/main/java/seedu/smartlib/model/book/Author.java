package seedu.smartlib.model.book;

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

    /**
     * Calculates hashCode of this Author object
     * @return hashCode of Author
     */
    @Override
    public int hashCode() {
        return fullName.hashCode();
    }

}
