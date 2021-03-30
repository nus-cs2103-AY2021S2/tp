package seedu.storemando.model.item;

import static java.util.Objects.requireNonNull;
import static seedu.storemando.commons.util.AppUtil.checkArgument;

/**
 * Represents an Item's Location in StoreMando.
 * Guarantees: immutable; is valid as declared in {@link #isValidLocation(String)}
 */
public class Location {

    public static final String MESSAGE_CONSTRAINTS = "Locations can take any values, and it should not be blank";

    /*
     * The first character of the location must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs an {@code Location}.
     *
     * @param location A valid location.
     */
    public Location(String location) {
        requireNonNull(location);
        checkArgument(isValidLocation(location), MESSAGE_CONSTRAINTS);
        value = location;
    }

    /**
     * Returns true if a given string is a valid location.
     */
    public static boolean isValidLocation(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    /**
     * Defines a weaker notion of equals. Returns true if 2 locations have the same spelling.
     *
     */
    public boolean isSimilar(Object other) {
        return other == this // short circuit if same object
            || (other instanceof Location // instanceof handles nulls
            && value.equalsIgnoreCase(((Location) other).value)); // state check
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof Location // instanceof handles nulls
            && value.equals(((Location) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    public int compare(Location anotherLocation) {
        return this.value.compareTo(anotherLocation.value);
    }

}
