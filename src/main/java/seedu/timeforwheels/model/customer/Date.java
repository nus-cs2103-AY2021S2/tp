package seedu.timeforwheels.model.customer;

import static java.util.Objects.requireNonNull;
import static seedu.timeforwheels.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Date {
    public static final String MESSAGE_CONSTRAINTS = "Dates should be of the format YYYY-MM-DD, and must not be blank.";

    /*
     * The first character of the date must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^\\d{4}-\\d{2}-\\d{2}$";

    public final String value;
    public final LocalDate date;

    /**
     * Constructs an {@code Date}.
     *
     * @param date A valid address.
     */
    public Date(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        this.date = LocalDate.parse(date);
        value = date;
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidDate(String test) {
        try {
            LocalDate.parse(test);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public LocalDate getLocalDate() {
        return this.date;
    }

    @Override
    public String toString() {
        assert isValidDate(value);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        return date.format(formatter);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Date // instanceof handles nulls
                && value.equals(((Date) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
