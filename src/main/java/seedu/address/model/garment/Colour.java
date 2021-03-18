package seedu.address.model.garment;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Represents a Garment's colour in the wardrobe.
 */
public class Colour {
    public static final String MESSAGE_CONSTRAINTS =
            "Garment's Colour field must be non-empty and be either"
            + " red, orange, yellow, green, blue, pink"
            + " purple, brown, or black.";

    public static final String[] COLOURARRAY = {
        "red",
        "orange",
        "yellow",
        "green",
        "blue",
        "pink",
        "purple",
        "brown",
        "black",
        "white"
    };

    public static final HashSet<String> COLOURS = new HashSet<>(Arrays.asList(COLOURARRAY));

    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String colour;

    /**
     * Constructs a {@code Colour}.
     *
     * @param colour A valid colour.
     */
    public Colour(String colour) {
        if (!COLOURS.contains(colour.toLowerCase())) {
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
        }
        requireNonNull(colour);
        this.colour = colour;
    }

    /**
     * Returns true if a given string is a valid colour.
     */
    public static boolean isValidColour(String test) {
        return test.matches(VALIDATION_REGEX) && COLOURS.contains(test.toLowerCase());
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
