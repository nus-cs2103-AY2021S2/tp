package seedu.address.model.reminder;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.Locale;

/**
 * Represents an Reminder's date in the ReminderList.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}
 */
public class ReminderDate {

    public static final String MESSAGE_CONSTRAINTS = "ReminderDate should be in "
            + "YYYY-MM-DD format";

    /*
     * Date make use of formatter to validate instead of Regex for Date accuracy.
     */
    public static final DateTimeFormatter VALIDATION_PATTERN = new DateTimeFormatterBuilder()
            .appendPattern("[y-M-d]")
            .appendPattern("[d-M-y]")
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
            .toFormatter(Locale.ENGLISH);

    public final LocalDate value;

    /**
     * Constructs an {@code ReminderDate}.
     *
     * @param date A valid date.
     */
    public ReminderDate(String date) {
        requireNonNull(date);
        date = date.toUpperCase();
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        value = LocalDate.parse(date, VALIDATION_PATTERN);
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidDate(String test) {
        try {
            LocalDateTime.parse(test, VALIDATION_PATTERN);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Getter method to retrieve value of ReminderDate.
     *
     * @return LocalDate stored
     */
    public LocalDate getValue() {
        return value;
    }


    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return value.format(formatter);
    }

    /**
     * Returns a date string for storage purpose.
     */
    public String toStorageString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return value.format(formatter);
    }

    /**
     * Returns true if the date is before the current date.
     */
    public boolean isBeforeToday() {
        return value.isBefore(LocalDate.now());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReminderDate // instanceof handles nulls
                && value.isEqual(((ReminderDate) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
