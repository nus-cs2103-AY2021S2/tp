package seedu.address.model.meeting;


import seedu.address.logic.parser.DateTimeUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;


/**
 * Represents a Meeting's DateTime in a meeting.
 * Guarantees: immutable; is valid as declared in {@link #isValidDateTime(String)}
 */
public class DateTime implements Comparable<DateTime> {


    public static final String MESSAGE_CONSTRAINTS = "DateTime should use YYYY-MM-DD HH:MM format";
    public static final DateTimeFormatter VALIDATION_DATETIME_FORMAT = DateTimeUtil.ISO_DATE_FORMATTER_NO_SECONDS;
    public final LocalDateTime value;

    /**
     * Constructs a {@code DateTime}.{}
     *
     * @param dateTime A valid DateTime number.
     */
    public DateTime(String dateTime) {
        requireNonNull(dateTime);
        checkArgument(isValidDateTime(dateTime), MESSAGE_CONSTRAINTS);
        value = LocalDateTime.parse(dateTime, VALIDATION_DATETIME_FORMAT);
    }

    /**
     * Constructs a {@code DateTime}
     * @param value a LocalDateTime object
     */

    public DateTime(LocalDateTime value) {
        requireNonNull(value);
        this.value = value;
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

    /**
     * Formats the LocalDateTime attribute,
     * @return the ISO formatted string of LocalDateTime
     */

    public String toIsoFormatString() {
        return DateTimeUtil.formatDateTime(value);
    }

    /**
     * Converts the dateTime object to localDate.
     *
     * @return localDate
     */
    public LocalDate toLocalDate() {
        return value.toLocalDate();
    }

    /**
     * Converts DateTime to LocalDateTime.
     * @return
     */

    public LocalDateTime toLocalDateTime() {
        return value;
    }


    @Override
    public int compareTo(DateTime other) {
        if (value.isBefore(other.value)) {
            return -1;
        } else if (value.isEqual(other.value)){
            return 0;
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
