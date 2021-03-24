package seedu.taskify.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.taskify.commons.util.AppUtil.checkArgument;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Task's date (and time) in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}
 */
public class Date {
    public static final String MESSAGE_CONSTRAINTS = "Date should be of the format \"yyyy-mm-dd hh:mm\"";
    public final String value;
    private final LocalDateTime localDateTime;

    /**
     * Constructs a {@code Date}
     *
     * @param localDateTime a valid string containing the date and time of the Task
     */
    public Date(String localDateTime) {
        requireNonNull(localDateTime);
        checkArgument(isValidDate(localDateTime), MESSAGE_CONSTRAINTS);
        this.localDateTime = LocalDateTime.parse(localDateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        this.value = localDateTime;
    }

    /**
     * Returns if a given string is a valid date.
     */
    public static boolean isValidDate(String test) {
        if (test == null || !test.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}")) {
            return false;
        }
        SimpleDateFormat df = new SimpleDateFormat("y-M-d H:m");
        df.setLenient(false);
        try {
            df.parse(test);
            return true;
        } catch (ParseException ex) {
            return false;
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("y-M-d H:m");
        return localDateTime.format(formatter);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Date date = (Date) o;
        return localDateTime.equals(date.localDateTime);
    }

    @Override
    public int hashCode() {
        return localDateTime.hashCode();
    }

    public int compareTo(Date date) {
        return this.localDateTime.compareTo(date.localDateTime);
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }
}
