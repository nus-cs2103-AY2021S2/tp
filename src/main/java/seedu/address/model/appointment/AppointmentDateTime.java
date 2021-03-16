package seedu.address.model.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;

/**
 * Represents an Appointment's datetime in the AppointmentList.
 * Guarantees: immutable; is valid as declared in {@link #isValidDateTime(String)}
 */
public class AppointmentDateTime {

    public static final String MESSAGE_CONSTRAINTS = "AppointmentDateTime can take any values, "
            + "and it should not be blank";

    /*
     * DateTime make use of formatter to validate instead of Regex for DateTime accuracy.
     */
    public static final DateTimeFormatter VALIDATION_PATTERN = new DateTimeFormatterBuilder()
            .appendPattern("[yyyy-MM-dd HH:mm]")
            .appendPattern("[yyyy-MM-dd]")
            .appendPattern("[d-M-yyyy HH:mm]")
            .appendPattern("[d-M-yyyy]")
            .appendPattern("[yyyy/MM/dd HH:mm]")
            .appendPattern("[yyyy/MM/dd]")
            .appendPattern("[d/M/yyyy HH:mm]")
            .appendPattern("[d/M/yyyy]")
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
            .toFormatter();

    public final LocalDateTime value;

    /**
     * Constructs an {@code AppointmentDateTime}.
     *
     * @param dateTime A valid datetime.
     */
    public AppointmentDateTime(String dateTime) {
        requireNonNull(dateTime);
        checkArgument(isValidDateTime(dateTime), MESSAGE_CONSTRAINTS);
        value = LocalDateTime.parse(dateTime, VALIDATION_PATTERN);
    }

    /**
     * Returns true if a given string is a valid DateTime.
     */
    public static boolean isValidDateTime(String test) {
        try {
            LocalDateTime.parse(test, VALIDATION_PATTERN);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Performs a quick {@code LocalDateTime} equality check
     */
    public boolean equalsDateCheck(LocalDateTime other) {
        return other == value // short circuit if same object
                || (other != null // instanceof handles nulls
                && value.toLocalDate().isEqual((other.toLocalDate()))); // state check
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mma");
        return value.format(formatter);
    }

    /**
     * Returns a date only string for display purpose
     */
    public String toDateString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return value.format(formatter);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.appointment.AppointmentDateTime // instanceof handles nulls
                && value.isEqual(((seedu.address.model.appointment.AppointmentDateTime) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
