package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Patient's height in DocBob.
 * Guarantees: immutable; is valid as declared in {@link #isValidHeight(String)}
 */
public class Height {
    public static final String MESSAGE_CONSTRAINTS =
            "Height must be a number and be in cm";
    public static final String UNIT = "cm";
    public static final String VALIDATION_REGEX = "^[1-9][0-9]*" + UNIT + "$";

    public final String value;

    /**
     * Constructs a {@code Height}.
     *
     * @param height A valid height.
     */
    public Height(String height) {
        requireNonNull(height);
        checkArgument(isValidHeight(height), MESSAGE_CONSTRAINTS);
        value = height;
    }

    /**
     * Returns true if a given string is a valid height.
     */
    public static boolean isValidHeight(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Height // instanceof handles nulls
                && value.equals(((Height) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
