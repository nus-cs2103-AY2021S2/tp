package seedu.address.model.resident;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;


/**
 * Represents a Resident's seniority in the school.
 * Guarantees: immutable; is valid as declared in {@link #isValidYear(String)}
 */
public class Year {
    public static final String MESSAGE_CONSTRAINTS =
            "Year should be a single digit numeric character from 1 to 5 inclusive\n";
    public static final String VALIDATION_REGEX = "[1-5]{1}";
    public final String value;

    /**
     * Constructs a {@code Year}.
     *
     * @param year A valid year.
     */
    public Year(String year) {
        requireNonNull(year);
        checkArgument(isValidYear(year), MESSAGE_CONSTRAINTS);
        value = year;
    }

    /**
     * Returns true if a given string is a valid year.
     */
    public static boolean isValidYear(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.resident.Year // instanceof handles nulls
                && value.equals(((seedu.address.model.resident.Year) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
