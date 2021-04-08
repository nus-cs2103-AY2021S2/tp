package seedu.smartlib.model.record;

import static java.util.Objects.requireNonNull;
import static seedu.smartlib.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * The DateReturned class takes note of the date which a book is returned to SmartLib.
 */
public class DateReturned {

    public static final String MESSAGE_CONSTRAINTS = "Date should be of the format yyyy-mm-dd.";

    private final String value;

    /**
     * Constructs a {@code DateReturned}.
     *
     * @param date A valid date.
     */
    public DateReturned(LocalDateTime date) {
        requireNonNull(date);
        checkArgument(isValidDate(date.toString()), MESSAGE_CONSTRAINTS);
        value = date.toString();
    }

    /**
     * Constructs an {@code DateReturned}.
     *
     * @param date A valid date.
     */
    public DateReturned(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        value = date;
    }

    /**
     * Indicates whether a given string is a valid date.
     *
     * @param test string to be tested.
     * @return true if a given string is a valid date, and false otherwise.
     */
    public static boolean isValidDate(String test) {
        try {
            LocalDateTime.parse(test);
        } catch (DateTimeParseException e) {
            // the given string is not a valid date (cannot be parsed)
            return false;
        }
        return true;
    }

    /**
     * Returns this return date in String format.
     *
     * @return this return date in String format.
     */
    @Override
    public String toString() {
        return value;
    }

    /**
     * Checks if this return date is equal to another return date.
     *
     * @param other the other return date to be compared.
     * @return true if this return date is equal to the other return date, and false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DateReturned // instanceof handles nulls
                && value.equals(((DateReturned) other).value)); // state check
    }

    /**
     * Generates a hashcode for this return date.
     *
     * @return the hashcode for this return date.
     */
    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
