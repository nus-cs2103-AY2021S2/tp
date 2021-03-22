package seedu.address.model.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline {
    public static final String MESSAGE_CONSTRAINTS =
            "Deadlines should be in the format YYYY-MM-DD";

    public final LocalDate date;

    public final String dateString;

    /**
     * Constructs a {@code Deadline}.
     *
     * @param date A valid date.
     */
    public Deadline(String date) {
        this.date = LocalDate.parse(date);
        this.dateString = this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public LocalDate getDeadline() {
        return this.date;
    }

    /**
     * Returns true if a given string is a valid deadline.
     */
    public static boolean isValidDeadline(String test) {
        try {
            LocalDate.parse(test);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Deadline // instanceof handles nulls
                && date.equals(((Deadline) other).date)); // state check
    }

}
