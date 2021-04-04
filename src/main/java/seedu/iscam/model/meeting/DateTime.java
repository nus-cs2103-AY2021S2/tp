package seedu.iscam.model.meeting;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a meeting's date and time in the iscam book.
 * Guarantees: immutable; is valid as declared in {@link #isStringValidDateTime(String)}
 */
public class DateTime {
    public static final String MESSAGE_CONSTRAINTS = "Date & time must be in the form of dd-MM-yyyy HH:mm and cannot "
            + "be in the past.";
    public static final DateTimeFormatter DATETIME_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public final LocalDateTime dateTime;

    /**
     * Construct a {@code DateTime} with a {@code String dateTime}
     */
    public DateTime(String dateTime) {
        requireNonNull(dateTime);
        this.dateTime = LocalDateTime.parse(dateTime, DATETIME_PATTERN);
    }

    public static boolean isStringValidFormat(String str) {
        try {
            LocalDateTime validDateTime = LocalDateTime.parse(str, DATETIME_PATTERN);
            return true;
        } catch (DateTimeParseException exception) {
            return false;
        }
    }

    /**
     * Check if {@code string} can be converted into a valid {@code DateTime}
     */
    public static boolean isStringValidDateTime(String str) {
        try {
            LocalDateTime toVerify = LocalDateTime.parse(str, DATETIME_PATTERN);
            LocalDateTime now = LocalDateTime.now().withSecond(0).withNano(0);
            return toVerify.isEqual(now) || toVerify.isAfter(now);
        } catch (DateTimeParseException exception) {
            return false;
        }
    }

    public LocalDateTime get() {
        return this.dateTime;
    }

    @Override
    public String toString() {
        return dateTime.format(DATETIME_PATTERN);
    }

    @Override
    public boolean equals(Object other) {
        return other == this || (other instanceof DateTime && this.dateTime.isEqual(((DateTime) other).dateTime));
    }

    @Override
    public int hashCode() {
        return dateTime.hashCode();
    }
}
