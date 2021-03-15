package seedu.address.model.issue;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an issue's timestamp in SunRez. Guarantees: immutable; is valid as
 * declared in {@link #isValidTimestamp(String)}
 */
public class Timestamp {

    public static final String MESSAGE_CONSTRAINTS = "Timestamps should be in the format "
            + "YYYY-MM-DD HH:mm:ss, and it should not be blank";

    /*
     * The first character of the timestamp must not be a whitespace, otherwise " "
     * (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}";
    public static final String TIMESTAMP_PATTERN = "yyyy-MM-dd HH:mm";

    public final LocalDateTime value;

    /**
     * Constructs a {@code Timestamp}.
     */
    public Timestamp() {
        value = LocalDateTime.now();
    }

    /**
     * Constructs a {@code Timestamp}.
     *
     * @param timestamp A valid timestamp.
     */
    public Timestamp(String timestamp) {
        requireNonNull(timestamp);
        checkArgument(isValidTimestamp(timestamp), MESSAGE_CONSTRAINTS);
        this.value = LocalDateTime.parse(timestamp, DateTimeFormatter.ofPattern(TIMESTAMP_PATTERN));
    }

    /**
     * Returns true if a given string is a valid timestamp.
     */
    public static boolean isValidTimestamp(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value.format(DateTimeFormatter.ofPattern(TIMESTAMP_PATTERN));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Timestamp // instanceof handles nulls
                        && value.equals(((Timestamp) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
