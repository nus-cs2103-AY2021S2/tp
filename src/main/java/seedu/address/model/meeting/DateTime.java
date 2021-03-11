package seedu.address.model.meeting;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Meeting's DateTime in a meeting.
 * Guarantees: immutable; is valid as declared in {@link #isValidDateTime(String)}
 */
public class DateTime implements Comparable<DateTime>{


    public static final String MESSAGE_CONSTRAINTS = "DateTime should use YYYY-MM-DD HH:MM format";
    public static final DateTimeFormatter VALIDATION_DATETIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public final LocalDateTime value;

    /**
     * Constructs a {@code DateTime}.
     *
     * @param dateTime A valid DateTime number.
     */
    public DateTime(String dateTime) {
        requireNonNull(dateTime);
        checkArgument(isValidDateTime(dateTime), MESSAGE_CONSTRAINTS);
        value = LocalDateTime.parse(dateTime, VALIDATION_DATETIME_FORMAT);
    }

    /**
     * Returns true if a given string is a valid DateTime number.
     */
    public static boolean isValidDateTime(String test) {
        try {
            LocalDateTime.parse(test, VALIDATION_DATETIME_FORMAT);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(DateTime other) {
        if (value.isBefore(other.value)) {
            return -1;
        }
        return 1;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.meeting.DateTime // instanceof handles nulls
                && value.equals(((seedu.address.model.meeting.DateTime) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
