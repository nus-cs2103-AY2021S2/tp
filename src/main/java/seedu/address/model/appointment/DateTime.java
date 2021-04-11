package seedu.address.model.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an Appointment's dateTime in the appointment book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDateTime(String)}
 */
public class DateTime {

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    public static final String MESSAGE_INVALID_DATETIME = "Date or time is not valid.\n"
            + "Day of date should be between 1 - 28/29/30/31, depending on the month.\n"
            + "Month of date should be between 1 - 12.\n"
            + "Time should be between 00:00 - 23:59.";
    public static final String MESSAGE_INVALID_FORMAT = "DateTime should be of format dd/MM/yyyy HH:mm";
    public static final String MESSAGE_BLANK = "Date should not be blank";
    public static final String MESSAGE_PAST_CURRENT = "Date should not be a date that has already past";
    public static final String PLACEHOLDER = "NIL";

    public final String dateTime;

    /**
     * Constructs an {@code DateTime}.
     * @param dateTimeStr A valid date and time.
     */
    public DateTime(String dateTimeStr) {
        requireNonNull(dateTimeStr);
        checkArgument(isNotBlank(dateTimeStr), MESSAGE_BLANK);
        checkArgument(isValidFormat(dateTimeStr), MESSAGE_INVALID_FORMAT);
        checkArgument(isValidDateTime(dateTimeStr), MESSAGE_INVALID_DATETIME);
        checkArgument(isFutureDateTime(dateTimeStr), MESSAGE_PAST_CURRENT);
        dateTime = dateTimeStr;
    }

    /**
     * Constructs a {@code DateTime} with a placeholder as the value.
     */
    public DateTime() {
        dateTime = PLACEHOLDER;
    }

    /**
     * Returns true if a given string is not blank.
     */
    public static boolean isNotBlank(String test) {
        if (!test.isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * Returns true if a given string is of the correct DateTime format.
     */
    public static boolean isValidFormat(String test) {
        try {
            LocalDateTime.parse(test, DATE_TIME_FORMATTER);
            return true;
        } catch (DateTimeParseException e) {
            if (e.getMessage().contains("Invalid value")) {
                //DateTimeParseException already handles certain cases where values of day/month/time are invalid
                //and it returns a message with the words "Invalid value" in it
                //to let this error be caught by isValidDateTime instead
                //return true
                return true;
            }
            return false;
        }
    }

    /**
     * Returns true if a given string is a valid dateTime. Returns false if the string is invalid, like 29/02/2022.
     */
    public static boolean isValidDateTime(String test) {
        try {
            LocalDateTime d = LocalDateTime.parse(test, DATE_TIME_FORMATTER);
            String revertBackStr = d.format(DATE_TIME_FORMATTER);
            if (revertBackStr.equals(test)) {
                return true;
            }
            return false;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Returns true if a given String is of a future date and time.
     */
    public static boolean isFutureDateTime(String test) {
        LocalDateTime d = LocalDateTime.parse(test, DATE_TIME_FORMATTER);
        if (d.isAfter(LocalDateTime.now())) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return dateTime;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DateTime // instanceof handles nulls
                && dateTime.equals(((DateTime) other).dateTime)); // state check
    }

    @Override
    public int hashCode() {
        return dateTime.hashCode();
    }

}
