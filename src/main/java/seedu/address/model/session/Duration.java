package seedu.address.model.session;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a the duration of a session in minutes
 * Guarantees: immutable;
 */
public class Duration {

    public static final String MESSAGE_CONSTRAINTS = "Format of duration input is incorrect.";
    private static final String VALIDATION_REGEX = "(0|[1-9]\\d+)";

    private String value;

    /**
     * Constructs a {@code Duration}.
     *
     * @param value The duration for that session in minutes
     */
    public Duration(String value) {
        requireNonNull(value);
        checkArgument(isValidDuration(value), MESSAGE_CONSTRAINTS);
        this.value = value;
    }

    public String getValue() {
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
        return value;
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
        return otherDuration.getValue().equals(getValue());
    }
}
