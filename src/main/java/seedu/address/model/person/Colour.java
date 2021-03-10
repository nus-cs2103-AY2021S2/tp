package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's colour in the address book.
 */
public class Colour {

    public static final String MESSAGE_CONSTRAINTS =
            "Person's Colour field is missing!";

    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String colour;

    /**
     * Constructs an {@code Colour}.
     *
     * @param colour A valid colour address.
     */
    public Colour(String colour) {
        if (colour == " " || colour == " ") {
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
        }
        requireNonNull(colour);
        this.colour = colour;
    }

    /**
     * Returns true if a given string is a valid colour.
     */
    public static boolean isValidColour(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return colour;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Colour // instanceof handles nulls
                && colour.equals(((Colour) other).colour)); // state check
    }

    @Override
    public int hashCode() {
        return colour.hashCode();
    }

}
