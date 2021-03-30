package seedu.address.model.issue;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Config.DEFAULT_LOCALE;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;

/**
 * Represents an issue's timestamp in SunRez. Guarantees: immutable; is valid as
 * declared in {@link #isValidTimestamp(String)}
 */
public class Timestamp implements Comparable<Timestamp> {

    public static final String TIMESTAMP_PATTERN = "yyyy/M/d h:mma";

    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(TIMESTAMP_PATTERN, DEFAULT_LOCALE);

    public static final String MESSAGE_CONSTRAINTS = "Timestamps should be in the format "
            + TIMESTAMP_PATTERN + ", and it should not be blank";

    private static final Logger logger = LogsCenter.getLogger(Timestamp.class);

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
        timestamp = timestamp.toUpperCase();
        checkArgument(isValidTimestamp(timestamp), MESSAGE_CONSTRAINTS);
        this.value = LocalDateTime.parse(timestamp, FORMATTER);
    }

    /**
     * Returns true if a given string is a valid timestamp.
     */
    public static boolean isValidTimestamp(String test) {
        try {
            LocalDateTime.parse(test.toUpperCase(), FORMATTER);
            return true;
        } catch (DateTimeParseException dtpe) {
            logger.warning("Invalid timestamp given: " + dtpe.getMessage());
            return false;
        }
    }

    @Override
    public String toString() {
        return value.format(FORMATTER);
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

    @Override
    public int compareTo(Timestamp o) {
        return value.compareTo(o.value);
    }

}
