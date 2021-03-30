package seedu.address.model.schedule;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Schedule's datetime in Teaching Assistant.
 * Guarantees: immutable; is valid as declared in {@link #isValidDateTime(String)}
 */
public class DateTime {

    public static final String MESSAGE_CONSTRAINTS =
            "Dates should be in the format yyyy-mm-ddThh:mm:ss. Time must be in the 24-hour clock notation.";
    public final LocalDateTime value;

    /**
     * Constructs a {@code Phone}.
     *
     * @param datetime A valid datetime.
     */
    public DateTime(String datetime) {
        requireNonNull(datetime);
        checkArgument(isValidDateTime(datetime), MESSAGE_CONSTRAINTS);
        value = LocalDateTime.parse(datetime);
    }

    /**
     * Returns true if a given string is a valid datetime.
     */
    public static boolean isValidDateTime(String dateStr) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME; //for LocalDate
        try {
            dateTimeFormatter.parse(dateStr);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    public LocalDateTime getDateTime() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof DateTime
                && value.equals(((DateTime) other).value));
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
