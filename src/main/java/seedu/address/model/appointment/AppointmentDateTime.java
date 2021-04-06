package seedu.address.model.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.Locale;

/**
 * Represents an Appointment's date and time in the AppointmentList.
 * Guarantees: immutable; is valid as declared in {@link #isValidDateTime(String)}
 */
public class AppointmentDateTime {

    public static final String MESSAGE_CONSTRAINTS = "AppointmentDateTime should be in "
            + "YYYY-MM-DD HH:MM AM/PM format";

    /*
     * DateTime make use of formatter to validate instead of Regex for DateTime accuracy.
     */
    public static final DateTimeFormatter VALIDATION_PATTERN = new DateTimeFormatterBuilder()
            .appendPattern("[y-M-d [h:m[ ]a]]")
            .appendPattern("[d-M-y [h:m[ ]a]]")
            .appendPattern("[y/M/d [h:m[ ]a]]")
            .appendPattern("[d/M/y [h:m[ ]a]]")
            .parseDefaulting(ChronoField.CLOCK_HOUR_OF_AMPM, 12)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
            .toFormatter(Locale.ENGLISH);

    public final LocalDateTime value;

    /**
     * Constructs an {@code AppointmentDateTime}.
     *
     * @param dateTime A valid datetime.
     */
    public AppointmentDateTime(String dateTime) {
        requireNonNull(dateTime);
        dateTime = dateTime.toUpperCase();
        checkArgument(isValidDateTime(dateTime), MESSAGE_CONSTRAINTS);
        value = LocalDateTime.parse(dateTime, VALIDATION_PATTERN);
    }

    /**
     * Constructs an {@code AppointmentDateTime}.
     *
     * @param date A valid date.
     * @param time A valid time.
     */
    public AppointmentDateTime(LocalDate date, LocalTime time) {
        requireAllNonNull(date, time);
        value = LocalDateTime.of(date, time);
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

    /**
     * Getter method to retrieve value of AppointmentDateTime.
     * @return LocalDateTime stored
     */
    public LocalDateTime getValue() {
        return value;
    }


    /**
     * Method to retrieve time in chronunits minutes of the appointment.
     */
    public long getDifferenceInMinutes(LocalDateTime timeTo) {
        Duration durationDifference = Duration.between(this.value, timeTo);
        return durationDifference.toMinutes();
    }

    /**
     * Checks whether time from to time to is valid.
     * @return True if time of current {@code AppointmentDateTime} object is smaller
     * than given {@code AppointmentDateTime} in params
     */
    public boolean isTimeFromValid(AppointmentDateTime givenAppointment) {
        return this.getValue().isBefore(givenAppointment.getValue());
    }

    /**
     * @param otherAppointmentTime Other appointment date time to check with.
     * @return True if current appointment date time is before or equals
     */
    public boolean isTimeBefore(AppointmentDateTime otherAppointmentTime) {
        return this.getValue().isBefore(otherAppointmentTime.getValue())
                || this.getValue().isEqual(otherAppointmentTime.getValue());
    }

    /**
     * @param otherAppointmentTime Other appointment date time to check with.
     * @return True if current appointment date time is after or equals
     */
    public boolean isTimeAfter(AppointmentDateTime otherAppointmentTime) {
        return this.getValue().isAfter(otherAppointmentTime.getValue())
                || this.getValue().isEqual(otherAppointmentTime.getValue());
    }


    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mma");
        return value.format(formatter);
    }

    /**
     * Returns a date only string for display purpose.
     */
    public String toDateString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return value.format(formatter);
    }

    /**
     * Returns a time only string for display purpose.
     */
    public String toTimeString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        return value.format(formatter);
    }

    /**
     * Returns a date time string for storage purpose.
     */
    public String toStorageString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");
        return value.format(formatter);
    }

    public boolean isAfter(AppointmentDateTime other) {
        return this.value.isAfter(other.value);
    }

    public boolean isBefore(AppointmentDateTime other) {
        return this.value.isBefore(other.value);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AppointmentDateTime // instanceof handles nulls
                && value.isEqual(((AppointmentDateTime) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
