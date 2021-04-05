package seedu.module.model.task;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;
import static java.util.Objects.requireNonNull;
import static seedu.module.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Task's deadline in the module book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTime(String)}
 */
public class Time implements Comparable<Time> {

    public static final String MESSAGE_CONSTRAINTS = "Time should be formatted as yyyy-MM-dd or yyyy-MM-dd HH:mm";
    private static final String yearMonthDayString = "yyyy-MM-dd";
    private static final String timeString = "HH:mm";
    public static final DateTimeFormatter DATE_TIME_FORMATTER_WITHOUT_TIME = DateTimeFormatter.ofPattern(
            yearMonthDayString);

    public static final DateTimeFormatter DATE_TIME_FORMATTER_WITH_TIME = DateTimeFormatter.ofPattern(
            yearMonthDayString + " " + timeString);

    public final String value;
    public final LocalDate date;
    public final LocalDateTime time;

    /**
     * Constructs a {@code Time}.
     *
     * @param timeString A valid timeString. Can either be in yyyy-MM-dd or yyyy-MM-dd HH:mm format
     */
    public Time(String timeString) {
        requireNonNull(timeString);
        checkArgument(isValidTime(timeString), MESSAGE_CONSTRAINTS);
        value = timeString;
        String dateString = value.split(" ")[0];
        if (value.length() == dateString.length()) {
            date = parseDate(dateString, ISO_LOCAL_DATE);
            time = date.atTime(0, 0);
        } else {
            time = parseDateAndTime(value, DATE_TIME_FORMATTER_WITH_TIME);
            date = time.toLocalDate();
        }
    }

    /**
     * Method to make new Deadline using LocalDateTime object instead
     *
     * @param deadlineTime a valid LocalDateTime that is used to construct Deadline object.
     */
    public static Time makeDeadlineWithTime(LocalDateTime deadlineTime) {
        requireNonNull(deadlineTime);
        String deadlineString = deadlineTime.format(DATE_TIME_FORMATTER_WITH_TIME);

        return new Time(deadlineString);
    }

    public LocalDate getDate() {
        return this.date;
    }

    public LocalDateTime getTime() {
        return this.time;
    }

    /**
     * Returns true if a given string is a valid time.
     * A valid time can either be in yyyy-MM-dd or yyyy-MM-dd HH:mm format.
     */
    public static boolean isValidTime(String test) {
        return isValidDate(test) || isValidDateAndTime(test);
    }

    private static LocalDate parseDate(String dateString, DateTimeFormatter formatter) throws DateTimeParseException {
        return LocalDate.parse(dateString, formatter);
    }

    private static boolean isValidDate(String dateString) {
        try {
            parseDate(dateString, DATE_TIME_FORMATTER_WITHOUT_TIME);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    private static LocalDateTime parseDateAndTime(String dateTimeString, DateTimeFormatter formatter)
            throws DateTimeParseException {

        return LocalDateTime.parse(dateTimeString, formatter);
    }

    private static boolean isValidDateAndTime(String dateTimeString) {
        try {
            parseDateAndTime(dateTimeString, DATE_TIME_FORMATTER_WITH_TIME);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Time // instanceof handles nulls
                && value.equals(((Time) other).value)); // state check
    }

    @Override
    public int compareTo(Time other) {
        return time.compareTo(other.getTime());
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
