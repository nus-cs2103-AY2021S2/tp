package seedu.iscam.model.meeting;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static java.util.Objects.requireNonNull;
import static seedu.iscam.commons.util.AppUtil.checkArgument;

public class DateTime {
    public static final String MESSAGE_CONSTRAINTS = "Date & time must not be in the past.";
    public static final DateTimeFormatter DATETIME_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public final LocalDateTime dateTime;

    public DateTime(String dateTime) {
        requireNonNull(dateTime);
        checkArgument(isValidDateTimeStr(dateTime), MESSAGE_CONSTRAINTS);
        this.dateTime = LocalDateTime.parse(dateTime, DATETIME_PATTERN);
    }

    public static boolean isValidDateTimeStr(String dateTime) {
        try {
            LocalDateTime.parse(dateTime, DATETIME_PATTERN);
            return true;
        } catch(DateTimeParseException exception) {
            return false;
        }
    }

    public static boolean isValidDateTime(LocalDateTime dateTime) {
        return dateTime.isEqual(LocalDateTime.now()) || dateTime.isAfter(LocalDateTime.now());
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
