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
    public static final String DATE_FORMAT = "MMM dd yyyy";
    public static final String TIME_FORMAT = "hh:mm a";
    public static final String DATE_TIME_FORMAT = DATE_FORMAT + " " + TIME_FORMAT;
    public static final String DATE_TIME_STORAGE_FORMAT = "yyyy-MM-dd hh:mm a";

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
     *
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
     *
     * @return True if time of current {@code AppointmentDateTime} object is smaller
     * than given {@code AppointmentDateTime} in params
     */
    public boolean isTimeFromValid(AppointmentDateTime givenAppointment) {
        return this.getValue().isBefore(givenAppointment.getValue());
    }

    /**
     * Checks whether time from to time to is valid.
     *
     * @return True if time of current {@code AppointmentDateTime} object is smaller
     * than given {@code AppointmentDateTime} in params
     */
    public boolean isInvalidStartTime() {
        return this.getValue().toLocalTime().isBefore(LocalTime.of(6, 0));
    }

    /**
     * Checks whether time from to time to is valid.
     *
     * @return True if time of current {@code AppointmentDateTime} object is smaller
     * than given {@code AppointmentDateTime} in params
     */
    public boolean isInvalidEndTime() {
        return this.getValue().toLocalTime().isAfter(LocalTime.of(23, 1));
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
        return value.format(formatter);
    }

    /**
     * Returns a date only string for display purpose.
     */
    public String toDateString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        return value.format(formatter);
    }

    /**
     * Returns a time only string for display purpose.
     */
    public String toTimeString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIME_FORMAT);
        return value.format(formatter);
    }

    /**
     * Returns a date time string for storage purpose.
     */
    public String toStorageString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_STORAGE_FORMAT);
        return value.format(formatter);
    }

    public boolean isAfter(AppointmentDateTime other) {
        return this.value.isAfter(other.value);
    }

    public boolean isBefore(AppointmentDateTime other) {
        return this.value.isBefore(other.value);
    }

    /**
     * Returns true if the {@code AppointmentDateTime} is before now.
     */
    public boolean isBeforeNow() {
        return this.value.isBefore(LocalDateTime.now());
    }

    /**
     * Returns {@code LocalDate} portion of {@code AppointmentDateTime}.
     */
    public LocalDate toDate() {
        return this.value.toLocalDate();
    }

    /**
     * Returns {@code LocalTime} portion of {@code AppointmentDateTime}.
     */
    public LocalTime toTime() {
        return this.value.toLocalTime();
    }

    /**
     * Returns true if {@code AppointmentDateTime} is in blocks of 30 or 60 minutes.
     */
    public boolean isValidMinutes() {
        int minutes = this.value.getMinute();
        return minutes == 0 || minutes == 30;
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
