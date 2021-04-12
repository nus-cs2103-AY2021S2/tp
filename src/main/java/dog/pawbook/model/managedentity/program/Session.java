//@@author branzuelajohn
package dog.pawbook.model.managedentity.program;

import static dog.pawbook.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Program's date and time in the database.
 * Guarantees: immutable; is valid as declared in {@link #isValidDateTime(String)}
 */
public class Session {
    public static final String DATETIME_FORMAT = "dd-MM-yyyy HH:mm";
    public static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern(DATETIME_FORMAT);
    public static final String MESSAGE_CONSTRAINTS = "The date and time of a Session should be in the "
            + DATETIME_FORMAT + " format.";

    public final LocalDateTime dateTime;
    public final String value;

    /**
     * Construct a {@code Session}.
     *
     * @param value A valid date and time.
     */
    public Session(String value) {
        requireNonNull(value);
        checkArgument(isValidDateTime(value), MESSAGE_CONSTRAINTS);
        this.value = value;
        this.dateTime = LocalDateTime.parse(value, DATETIME_FORMATTER);
    }

    /**
     * Construct a {@code Session} from a given {@code LocalDateTime}.
     */
    public Session(LocalDateTime localDateTime) {
        requireNonNull(localDateTime);
        this.dateTime = localDateTime;
        this.value = localDateTime.format(DATETIME_FORMATTER);
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidDateTime(String value) {
        try {
            LocalDate.parse(value, DATETIME_FORMATTER);
        } catch (DateTimeParseException e) {
            return false;
        }

        return true;
    }

    public LocalDateTime getDate() {
        return dateTime;
    }


    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof Session // instanceof handles nulls
            && value.equals(((Session) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
