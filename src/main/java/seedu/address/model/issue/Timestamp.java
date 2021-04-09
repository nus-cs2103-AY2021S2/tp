package seedu.address.model.issue;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Config.DEFAULT_LOCALE;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoField;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;

/**
 * Represents an issue's timestamp in SunRez. Guarantees: immutable; is valid as
 * declared in {@link #isValidTimestamp(String)}.
 */
public class Timestamp implements Comparable<Timestamp> {

    public static final String TIMESTAMP_PATTERN = "yyyy/MM/dd hh:mma";

    public static final DateTimeFormatter FORMATTER = new DateTimeFormatterBuilder().appendPattern(TIMESTAMP_PATTERN)
            .parseDefaulting(ChronoField.ERA, 1)
            .toFormatter(DEFAULT_LOCALE)
            .withResolverStyle(ResolverStyle.STRICT);

    public static final String MESSAGE_CONSTRAINTS = "Timestamps should be of valid date and time in the format "
            + TIMESTAMP_PATTERN + "\n\n"
            + "yyyy - 4 digit year (e.g. 2021)\n"
            + "MM - 2 digit month (e.g. 01, 05, 12)\n"
            + "dd - 2 digit day (e.g. 01, 05, 31)\n"
            + "hh - 2 digit hour (01-12) (midnight is 12:00am)\n"
            + "mm - 2 digit minutes (00-59)\n"
            + "a - case-insensitive AM/PM\n"
            + "Example: 2021/01/01 12:00am";

    public static final String MESSAGE_INVALID_FUTURE = "Timestamps should not be in the future";

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
     *
     * @param test String to check.
     * @return True if test is valid.
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
