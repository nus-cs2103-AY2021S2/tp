package seedu.address.model.garment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Garment's DressCode in the wardrobe.
 * Guarantees: immutable; is valid as declared in {@link #isValidDressCode(String)}
 */
public class DressCode {

    public static final String MESSAGE_CONSTRAINTS = "DressCode can take 3 values: formal, active, casual";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "FORMAL|ACTIVE|CASUAL|formal|active|casual";

    public final String value;

    /**
     * Constructs an {@code dresscode}.
     *
     * @param dresscode A valid DressCode.
     */
    public DressCode(String dresscode) {
        requireNonNull(dresscode);
        checkArgument(isValidDressCode(dresscode), MESSAGE_CONSTRAINTS);
        value = dresscode;
    }

    /**
     * Returns true if a given string is a valid DressCode.
     */
    public static boolean isValidDressCode(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DressCode // instanceof handles nulls
                && value.equals(((DressCode) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
