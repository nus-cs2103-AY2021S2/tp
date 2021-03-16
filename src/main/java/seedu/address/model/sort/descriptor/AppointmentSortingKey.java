package seedu.address.model.sort.descriptor;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a value.
 * Guarantees: immutable; is valid as declared in {@link #isValidAppointmentSortingKey(String)}.
 */
public class AppointmentSortingKey {
    public static final String MESSAGE_CONSTRAINTS = "AppointmentSortingKey can take value of either datetime or name"
            + ", and it should not be any other values";

    private static final String VALIDATION_REGEX = "datetime|name";

    private static final String DATETIME_STRING = "datetime";

    private static final String NAME_STRING = "name";

    public final String value;

    /**
     * Constructs a {@code AppointmentSortingKey}.
     *
     * @param value A valid value representing an AppointmentSortingKey.
     */
    public AppointmentSortingKey(String value) {
        requireNonNull(value);
        checkArgument(isValidAppointmentSortingKey(value), MESSAGE_CONSTRAINTS);
        this.value = value;
    }

    /**
     * Returns true if a given string is a valid value.
     *
     * @param test The string to test.
     * @return True if the given string is a valid value, otherwise false.
     */
    public static boolean isValidAppointmentSortingKey(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Tests whether the sorting key is datetime.
     *
     * @return True if the sorting key is datetime, otherwise false.
     */
    public boolean isDatetime() {
        return this.value.equals(DATETIME_STRING);
    }

    /**
     * Tests whether the sorting key is name.
     *
     * @return True if the sorting key is name, otherwise false.
     */
    public boolean isName() {
        return this.value.equals(NAME_STRING);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AppointmentSortingKey)) {
            return false;
        }
        AppointmentSortingKey otherAppointmentSortingKey = (AppointmentSortingKey) other;
        return value.equals(otherAppointmentSortingKey.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
