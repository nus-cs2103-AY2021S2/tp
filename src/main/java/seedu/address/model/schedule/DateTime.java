package seedu.address.model.schedule;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTime {
    public static final String MESSAGE_CONSTRAINTS =
            "Dates should be in the format yyyy-mm-ddThh:mm:ss. Time must be in the 24-hour clock notation.";
    public LocalDateTime value;

    public DateTime(LocalDateTime date) {
        requireNonNull(date);
        value = date;
    }

    public static boolean isValidDateTime(String dateStr) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME; //for LocalDate
        try {
            dateTimeFormatter.parse(dateStr);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    public LocalDateTime getDate() {
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
