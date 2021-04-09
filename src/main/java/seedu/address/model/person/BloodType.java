package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Patient's blood type in DocBob.
 * Guarantees: immutable; is valid as declared in {@link #isValidBloodType(String)}
 */
public class BloodType {

    public static final String MESSAGE_CONSTRAINTS =
            "Blood type must be either A, B, AB or O and include Rh factor (+ or -)";

    public static final String VALIDATION_REGEX = "^(A|B|AB|O)[+-]$";

    public final String value;

    /**
     * Constructs a {@code BloodType}.
     *
     * @param bloodType A valid blood type.
     */
    public BloodType(String bloodType) {
        requireNonNull(bloodType);
        checkArgument(isValidBloodType(bloodType), MESSAGE_CONSTRAINTS);
        value = bloodType;
    }

    /**
     * Returns true if a given string is a valid blood type.
     */
    public static boolean isValidBloodType(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof BloodType // instanceof handles nulls
                && value.equals(((BloodType) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
