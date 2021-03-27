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

    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private static final String MESSAGE_CONSTRAINTS = "DateTimes should not be blank and must be "
            + "of the format dd/MM/yyyy HH:mm";
    private static final String PLACEHOLDER = "NIL";

    private final String dateTime;

    /**
     * Constructs an {@code DateTime}.
     * @param dateTimeStr A valid date and time.
     */
    public DateTime(String dateTimeStr) {
        requireNonNull(dateTimeStr);
        checkArgument(isValidDateTime(dateTimeStr), MESSAGE_CONSTRAINTS);
        dateTime = dateTimeStr;
    }

    /**
     * Constructs a {@code DateTime} with a placeholder as the value.
     */
    public DateTime() {
        dateTime = PLACEHOLDER;
    }

    /**
     * Returns true if a given string is a valid dateTime.
     */
    public static boolean isValidDateTime(String test) {
        try {
            LocalDateTime.parse(test, dateFormat);
            return true;
        } catch (DateTimeParseException e) {
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
