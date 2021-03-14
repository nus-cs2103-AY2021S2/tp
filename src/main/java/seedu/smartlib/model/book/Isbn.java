package seedu.smartlib.model.book;

import static java.util.Objects.requireNonNull;
import static seedu.smartlib.commons.util.AppUtil.checkArgument;

/**
 * Represents an ISBN(International Standard Book Number) of a book in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidIsbn(String)}
 */
public class Isbn {


    public static final String MESSAGE_CONSTRAINTS =
            "ISBN should only contain numbers, and it should be 13 digits long with 4 hyphens.";
    public static final String PREFIX_REGEX = "\\d{3}";
    public static final String REGISTRATION_GROUP_REGEX = "\\d{1,5}";
    public static final String REGISTRANT_REGEX = "\\d{1,7}";
    public static final String PUBLICATION_REGEX = "\\d{1,6}";
    public static final String CHECK_DIGIT_REGEX = "\\d{1}";
    public static final String VALIDATION_REGEX = PREFIX_REGEX + "-" + REGISTRATION_GROUP_REGEX
            + "-" + REGISTRANT_REGEX + "-" + PUBLICATION_REGEX + "-" + CHECK_DIGIT_REGEX;

    //The length of isbn is 13 and includes 4 hyphens.
    public static final int ISBN_LENGTH = 17;

    public final String value;

    /**
     * Constructs an {@code ISBN}.
     *
     * @param Isbn A valid ISBN number.
     */
    public Isbn(String Isbn) {
        requireNonNull(Isbn);
        checkArgument(isValidIsbn(Isbn), MESSAGE_CONSTRAINTS);
        value = Isbn;
    }

    /**
     *
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidIsbn(String test) {
        return test.matches(VALIDATION_REGEX) && test.length() == ISBN_LENGTH;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Isbn // instanceof handles nulls
                && value.equals(((Isbn) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
