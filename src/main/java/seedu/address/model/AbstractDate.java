package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.Locale;

public abstract class AbstractDate {
    public static final String MESSAGE_CONSTRAINTS =
        "Dates should be given in the following formats (dd/MM/yyyy or yyyy-MM-dd or MMM d yyyy)";

    public static final DateTimeFormatter INPUT_DATE_TIME_FORMATTER = new DateTimeFormatterBuilder()
        .appendPattern("[d/M/yyyy HH:mm]")
        .appendPattern("[d/M/yyyy]")
        .appendPattern("[yyyy-M-d HH:mm]")
        .appendPattern("[yyyy-M-d]")
        .appendPattern("[MMM d yyyy HH:mm]")
        .appendPattern("[MMM d yyyy]")
        .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
        .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
        .toFormatter(Locale.ENGLISH);

    public static final DateTimeFormatter TO_STRING_FORMATTER = DateTimeFormatter.ofPattern("d MMM yy");
    public static final DateTimeFormatter TO_JSON_STRING_FORMATTER = DateTimeFormatter.ofPattern("yyyy-M-d HH:mm");

    public final LocalDateTime value;

    /**
     * Constructs a {@code Date} with current date time.
     */
    public AbstractDate() {
        value = LocalDateTime.now();
    }

    /**
     * Constructs a {@code Date}.
     *
     * @param date A valid String date.
     */
    public AbstractDate(String date) {
        requireNonNull(date);
        LocalDateTime dateTime = parseDate(date);
        checkArgument(dateTime != null, MESSAGE_CONSTRAINTS);
        value = dateTime;
    }

    /**
     * @param dateText A String containing date
     * @return null if date is invalid otherwise a LocalDateTime object of String date
     */
    public static LocalDateTime parseDate(String dateText) {
        try {
            return LocalDateTime.parse(dateText, INPUT_DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    /**
     * @param dateText A String containing date
     * @return false if date is invalid otherwise true
     */
    public static boolean isValidDate(String dateText) {
        return parseDate(dateText) != null;
    }

    public String toJsonString() {
        return value.format(TO_JSON_STRING_FORMATTER);
    }

    public boolean isAfter(AbstractDate otherDate) {
        return this.value.isAfter(otherDate.value);
    }

    @Override
    public String toString() {
        return value.format(TO_STRING_FORMATTER);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof AbstractDate // instanceof handles nulls
            && value.equals(((AbstractDate) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
