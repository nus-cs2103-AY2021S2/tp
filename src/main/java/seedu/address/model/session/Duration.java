package seedu.address.model.session;

import static java.util.Objects.requireNonNull;

/**
 * Represents a the duration of a session in minutes
 * Guarantees: immutable;
 */
public class Duration {

    private String value;

    /**
     * Constructs a {@code Duration}.
     *
     * @param value The duration for that session in minutes
     */
    public Duration(String value) {
        requireNonNull(value);
        this.value = value;
    }
}
