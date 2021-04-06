package seedu.smartlib.model.book;

import seedu.smartlib.commons.core.name.Name;

/**
 * Represents a genre of books in SmartLib.
 */
public class Genre {

    public static final String MESSAGE_CONSTRAINTS =
            "Genre should only contain alphanumeric characters (it may contain spaces between characters), "
            + "and it should not be blank.";

    private final Name genreName;

    /**
     * Constructs an {@code Genre}.
     *
     * @param genreName A valid name of a genre.
     */
    public Genre(Name genreName) {
        this.genreName = genreName;
    }

    /**
     * Returns true if a given string is a valid genre name.
     *
     * @param test string to be tested.
     * @return true if the given string is a valid genre name.
     */
    public static boolean isValidGenre(String test) {
        return Name.isValidName(test);
    }

    /**
     * Returns this Genre in String format.
     *
     * @return this Genre in String format.
     */
    @Override
    public String toString() {
        return genreName.toString();
    }

    /**
     * Checks if this Genre is equal to another Genre.
     *
     * @param other the other Genre to be compared.
     * @return true if this Genre is equal to the other Genre, and false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Genre // instanceof handles nulls
                && genreName.equals(((Genre) other).genreName)); // state check
    }

    /**
     * Calculates the hashCode of this Genre object.
     *
     * @return hashCode of Genre.
     */
    @Override
    public int hashCode() {
        return genreName.hashCode();
    }

}
