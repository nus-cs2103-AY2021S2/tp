package seedu.smartlib.model.book;

import static java.util.Objects.requireNonNull;
import static seedu.smartlib.commons.util.AppUtil.checkArgument;

/**
 * Represents an ISBN(International Standard Book Number) of a book in SmartLib.
 * Guarantees: immutable; is valid as declared in {@link #isValidIsbn(String)}
 */
public class Isbn {

    public static final String MESSAGE_CONSTRAINTS =
            "ISBN should only contain numbers, and it should only contain 13 digits.";

    public static final String VALIDATION_REGEX = "\\d{13}";

    //The length of isbn is 13.
    public static final int ISBN_LENGTH = 13;

    private final String value;

    /**
     * Constructs an {@code ISBN}.
     *
     * @param isbn A valid ISBN.
     */
    public Isbn(String isbn) {
        requireNonNull(isbn);
        checkArgument(isValidIsbn(isbn), MESSAGE_CONSTRAINTS);
        value = isbn;
    }

    /**
     * Returns true if a given string is a valid ISBN.
     *
     * @param test string to be tested.
     * @return true if a given string is a valid ISBN.
     */
    public static boolean isValidIsbn(String test) {
        return test.matches(VALIDATION_REGEX) && test.length() == ISBN_LENGTH;
    }

    /**
     * Returns this ISBN in String format.
     *
     * @return this ISBN in String format.
     */
    @Override
    public String toString() {
        return value;
    }

    /**
     * Checks if this ISBN is equal to another ISBN.
     *
     * @param other the other ISBN to be compared.
     * @return true if this ISBN is equal to the other ISBN, and false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Isbn // instanceof handles nulls
                && value.equals(((Isbn) other).value)); // state check
    }

    /**
     * Generates a hashcode for this ISBN.
     *
     * @return the hashcode for this ISBN.
     */
    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
