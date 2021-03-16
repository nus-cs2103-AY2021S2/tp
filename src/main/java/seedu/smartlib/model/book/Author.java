package seedu.smartlib.model.book;

import seedu.smartlib.commons.core.name.Name;

/**
 * Represents an author in SmartLib.
 */
public class Author {

    public static final String MESSAGE_CONSTRAINTS =
            "Author's names should only contain alphanumeric characters and spaces, and it should not be blank";

    private final Name fullName;

    /**
     * Constructs an {@code Author}.
     *
     * @param fullName A valid full name of the author.
     */
    public Author(Name fullName) {
        this.fullName = fullName;
    }

    /**
     * Returns true if a given string is a valid author's name.
     */
    public static boolean isValidAuthor(String test) {
        return Name.isValidName(test);
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
