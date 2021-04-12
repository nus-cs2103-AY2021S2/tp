package seedu.partyplanet.model.name;

import static java.util.Objects.requireNonNull;
import static seedu.partyplanet.commons.util.AppUtil.checkArgument;

/**
 * Represents a name in PartyPlanet.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Name {

    public static final String MESSAGE_CONSTRAINTS =
            "Names should only contain alphanumeric characters and spaces. "
                    + "It should not be blank nor longer than 25 characters";

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
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        fullName = name;
    }

    /**
     * Returns true if a given string is a valid name within character limit.
     */
    public static boolean isValidName(String test) {
        return isValidFormat(test) && !isTooLong(test);
    }
    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidFormat(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if a given name is longer than 25 characters.
     */
    public static boolean isTooLong(String test) {
        return test.length() > 25;
    }


    @Override
    public String toString() {
        return fullName;
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
