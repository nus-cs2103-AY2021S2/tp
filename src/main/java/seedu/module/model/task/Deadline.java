package seedu.module.model.task;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;
import static java.util.Objects.requireNonNull;
import static seedu.module.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Task's deadline in the module book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDeadline(String)}
 */
public class Deadline implements Comparable<Deadline> {


    public static final String MESSAGE_CONSTRAINTS =
            "Deadline should be formatted as yyyy-MM-dd or yyyy-MM-dd HH:mm";
    public static final String DATE_REGEX = "^((19|2[0-9])[0-9]{2})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])";
    public static final String TIME24HOURS_REGEX = "([01]?[0-9]|2[0-3]):[0-5][0-9]";
    public static final String DATE_TIME_VALIDATION_REGEX = DATE_REGEX + " " + TIME24HOURS_REGEX;
    private static final String yearMonthDayString = "yyyy-MM-dd";
    private static final String timeString = "HH:mm";
    public static final DateTimeFormatter DATE_TIME_FORMATTER_WITH_TIME = DateTimeFormatter.ofPattern(
            yearMonthDayString + " " + timeString);

    public final String value;
    public final LocalDate date;
    public final LocalDateTime time;

    /**
     * Constructs a {@code Deadline}.
     *
     * @param deadline A valid deadline. Can either be in yyyy-MM-dd or yyyy-MM-dd HH:mm format
     */
    public Deadline(String deadline) {
        requireNonNull(deadline);
        checkArgument(isValidDeadline(deadline), MESSAGE_CONSTRAINTS);
        value = deadline;
        String dateValue = value.split(" ")[0];
        if (value.length() == dateValue.length()) {
            date = LocalDate.parse(dateValue, ISO_LOCAL_DATE);
            time = date.atTime(LocalTime.now());
        } else {
            time = LocalDateTime.parse(value, DATE_TIME_FORMATTER_WITH_TIME);
            date = time.toLocalDate();
        }
    }

    public LocalDateTime getTime() {
        return this.time;
    }

    /**
     * Returns true if a given string is a valid deadline.
     */
    public static boolean isValidDeadline(String test) {
        return isValidDate(test) || (isValidDateAndTime(test));
    }

    private static boolean isValidDate(String test) {
        return test.matches(DATE_REGEX);
    }

    private static boolean isValidDateAndTime(String test) {
        return test.matches(DATE_TIME_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Deadline // instanceof handles nulls
                && value.equals(((Deadline) other).value)); // state check
    }

    @Override
    public int compareTo(Deadline other) {
        return time.compareTo(other.getTime());
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
