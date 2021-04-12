package seedu.address.model.garment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Garment's size in the nufash.
 * Guarantees: immutable; is valid as declared in {@link #isValidSize(String)}
 */
public class Size {


    public static final String MESSAGE_CONSTRAINTS =
            "Size numbers should only contain numbers that are greater than 0 and less than 200";
    public final String value;

    /**
     * Constructs a {@code Size}.
     *
     * @param size A valid size.
     */
    public Size(String size) {
        requireNonNull(size);
        checkArgument(isValidSize(size), MESSAGE_CONSTRAINTS);
        value = String.valueOf(Integer.parseInt(size));
    }

    /**
     * Returns true if a given string is a valid size.
     */
    public static boolean isValidSize(String test) {
        try {
            int size = Integer.parseInt(test);
            return size > 0 && size <= 200;
        } catch (NumberFormatException numberFormatException) {
            return false;
        }
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Size // instanceof handles nulls
                && value.equals(((Size) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
