package seedu.address.model.entry;

import static java.time.format.ResolverStyle.STRICT;
import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Entry's date and time.
 */
public class EntryDate {

    public static final String DATE_CONSTRAINTS =
            "Dates should be valid and in the format yyyy-mm-dd HH:MM. Time must be in the 24-hour clock notation.";

    public static final DateTimeFormatter DEFAULT_FORMATTER =
            DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm").withResolverStyle(STRICT);

    public final LocalDateTime value;

    /**
     * Creates an EntryDate with valid date times.
     */
    public EntryDate(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), DATE_CONSTRAINTS);
        value = LocalDateTime.parse(date, DEFAULT_FORMATTER);
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidDate(String dateStr) {
        try {
            DEFAULT_FORMATTER.parse(dateStr);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Returns true if this entryDate is after the specified entryDate.
     */
    public boolean isAfter(EntryDate other) {
        return value.isAfter(other.getDate());
    }

    /**
     * Returns true if this entryDate is before the specified entryDate.
     */
    public boolean isBefore(EntryDate other) {
        return value.isBefore(other.getDate());
    }

    public LocalDateTime getDate() {
        return value;
    }

    @Override
    public String toString() {
        return getDate().format(DEFAULT_FORMATTER);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof EntryDate
                && value.equals(((EntryDate) other).value));
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
