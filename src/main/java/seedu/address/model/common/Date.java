package seedu.address.model.common;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * Represents a Category in SOChedule.
 * Guarantees: immutable; date is valid as declared in {@link #isValidDate(String)}.
 */
public class Date implements Comparable<Date> {
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("uuuu-MM-dd");
    public static final String MESSAGE_CONSTRAINTS =
            "Date should be represented in the format of YYYY-MM-DD, and please make sure the date is valid";
    // public static final String VALIDATION_REGEX = "^[0-9]{4}-(1[0-2]|0[1-9])-(3[01]|[12][0-9]|0[1-9])$";

    public final LocalDate date;

    /**
     * Constructs an {@code Date}.
     *
     * @param dateString A valid date.
     */
    public Date(String dateString) {
        requireNonNull(dateString);
        checkArgument(isValidDate(dateString), MESSAGE_CONSTRAINTS);
        date = LocalDate.parse(dateString, DATE_FORMATTER);
    }

    public LocalDate getDate() {
        return date;
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidDate(String test) {
        try {
            LocalDate parsedDate = LocalDate.parse(test,
                    DATE_FORMATTER.withResolverStyle(ResolverStyle.STRICT));
            return true;
        } catch (DateTimeParseException ex) {
            return false;
        }
        // return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Date // instanceof handles nulls
                && date.equals(((Date) other).date)); // state check
    }

    @Override
    public int hashCode() {
        return date.hashCode();
    }

    /**
     * Returns the date in a string.
     */
    public String toString() {
        return this.date.format(DATE_FORMATTER);
    }

    @Override
    public int compareTo(Date other) {
        return this.date.compareTo(other.date);
    }
}
