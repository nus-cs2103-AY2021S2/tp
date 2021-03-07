package seedu.address.model.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Appointment's name.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}.
 */
public class Name {
    public static final String MESSAGE_CONSTRAINTS =
            "Appointment names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the appointment name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    private static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String appointmentName;

    /**
     * Constructs a {@code Name}.
     *
     * @param appointmentName A valid name.
     */
    public Name(String appointmentName) {
        requireNonNull(appointmentName);
        checkArgument(isValidName(appointmentName), MESSAGE_CONSTRAINTS);
        this.appointmentName = appointmentName;
    }

    /**
     * Returns true if a given string is a valid appointment name.
     *
     * @param test The string to test.
     * @return True if the given string is a valid appointment name, otherwise false.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return appointmentName;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Name)) {
            return false;
        }
        Name otherName = (Name) other;
        return appointmentName.equals(otherName.appointmentName);
    }

    @Override
    public int hashCode() {
        return appointmentName.hashCode();
    }
}
