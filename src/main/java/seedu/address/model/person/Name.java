package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Locale;
/**
 * Represents a Person's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Name implements Comparable<Name> {

    public static final int MAX_CHARACTERS = 70;
    public static final String MESSAGE_CONSTRAINTS =
            "ERROR: Names should only contain alphanumeric characters and spaces, and it should not be blank";

    public static final String INVALID_LENGTH_MESSAGE =
            "ERROR: Input name should only be at most 70 characters long";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String fullName;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public Name(String name) {
        requireNonNull(name);
        checkArgument(isValidLength(name), INVALID_LENGTH_MESSAGE);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        fullName = name.toUpperCase(Locale.ROOT);
    }


    /**
     * Returns true if a given string is within the characters limit
     */
    public static boolean isValidLength(String test) {
        return test.length() <= MAX_CHARACTERS;
    }


    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return fullName;
    }

    @Override
    public int compareTo(Name other) {
        String thisName = this.fullName.toLowerCase(Locale.ROOT);
        String otherName = other.fullName.toLowerCase(Locale.ROOT);
        return thisName.compareTo(otherName);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Name // instanceof handles nulls
                && fullName.equals(((Name) other).fullName)); // state check
    }

    @Override
    public int hashCode() {
        return fullName.hashCode();
    }

}
