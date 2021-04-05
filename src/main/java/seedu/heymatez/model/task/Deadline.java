package seedu.heymatez.model.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents Deadline for Task in HEY MATEz.
 * Guarantees: deadline not empty and in the format YYYY-MM-DD.
 */
public class Deadline {
    public static final String MESSAGE_CONSTRAINTS = "Deadlines should be in the format "
            + "YYYY-MM-DD and should be a valid date";

    public static final String VALIDATION_REGEX = "\\d{4}-\\d{2}-\\d{2}";
    public final LocalDate date;
    public final String dateString;
    public final String unformattedDate;

    /**
     * Constructs a {@code Deadline}.
     *
     * @param date A valid date.
     */
    public Deadline(String date) {
        this.unformattedDate = date;
        this.date = LocalDate.parse(date);
        this.dateString = this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Returns a String value of unformatted deadline.
     *
     * @return a String value of unformatted deadline
     */
    public String getUnformattedDate() {
        return unformattedDate;
    }

    /**
     * Returns a parsed deadline.
     *
     * @return a LocalDate object of the parsed deadline
     */
    public LocalDate getDeadline() {
        return this.date;
    }

    /**
     * Returns true if a given string is a valid deadline.
     *
     * @return a boolean value
     */
    public static boolean isValidDeadline(String test) {
        try {
            LocalDate.parse(test);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Returns true if a given string has a valid deadline format.
     *
     * @return a boolean value
     */
    public static boolean isValidFormat(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Deadline // instanceof handles nulls
                && dateString.equals(((Deadline) other).dateString)); // state check
    }
}
