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
     *
     * @param test the string to be tested.
     * @return true if a given string is a valid author's name, and false otherwise.
     */
    public static boolean isValidAuthor(String test) {
        return Name.isValidName(test);
    }

    /**
     * Returns this Author in String format.
     *
     * @return this Author in String format.
     */
    @Override
    public String toString() {
        return fullName.toString();
    }

    /**
     * Checks if this Author is equal to another Author.
     *
     * @param other the other Author to be compared.
     * @return true if this Author is equal to the other Author, and false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Author // instanceof handles nulls
                && fullName.equals(((Author) other).fullName)); // state check
    }

    /**
     * Generates a hashCode for this Author object.
     *
     * @return hashCode of Author.
     */
    @Override
    public int hashCode() {
        return fullName.hashCode();
    }

}
