package seedu.module.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.module.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Task's deadline in the module book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDeadline(String)}
 */
public class Deadline {


    public static final String MESSAGE_CONSTRAINTS =
            "Deadline should be formatted as yyyy-MM-dd HH:mm.";
    public static final String DATE_REGEX = "^((19|2[0-9])[0-9]{2})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])";
    public static final String TIME24HOURS_REGEX = "([01]?[0-9]|2[0-3]):[0-5][0-9]";
    public static final String VALIDATION_REGEX = DATE_REGEX + " " + TIME24HOURS_REGEX;
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public final String value;
    public final LocalDateTime time;

    /**
     * Constructs a {@code Deadline}.
     *
     * @param deadline A valid deadline.
     */
    public Deadline(String deadline) {
        requireNonNull(deadline);
        checkArgument(isValidDeadline(deadline), MESSAGE_CONSTRAINTS);
        value = deadline;
        time = LocalDateTime.parse(value, DATE_TIME_FORMATTER);
    }

    public LocalDateTime getTime() {
        return this.time;
    }

    /**
     * Returns true if a given string is a valid deadline.
     */
    public static boolean isValidDeadline(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Deadline // instanceof handles nulls
                && value.equals(((Deadline) other).value)); // state check
    }

    public int compareTo(Deadline other) {
        return time.compareTo(other.getTime());
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
