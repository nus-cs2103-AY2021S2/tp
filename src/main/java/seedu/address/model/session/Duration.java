package seedu.address.model.session;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a the duration of a session in minutes
 * Guarantees: immutable;
 */
public class Duration {

    public static final String MESSAGE_CONSTRAINTS =
            "Format of duration input is incorrect, or duration is less than 1";
    private static final String VALIDATION_REGEX = "([1-9]\\d*)";

    private int value;

    /**
     * Constructs a {@code Duration}.
     *
     * @param value The duration for that session in minutes
     */
    public Duration(String value) {
        requireNonNull(value);
        checkArgument(isValidDuration(value), MESSAGE_CONSTRAINTS);
        this.value = Integer.parseInt(value);
    }

    public int getValue() {
        return this.value;
    }

    /**
     * Returns true if duration is valid.
     */
    public static boolean isValidDuration(String value) {
        return value.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Duration)) {
            return false;
        }

        Duration otherDuration = (Duration) other;
        Integer durationValue = getValue();
        Integer otherDurationValue = otherDuration.getValue();
        return durationValue.equals(otherDurationValue);
    }
}
