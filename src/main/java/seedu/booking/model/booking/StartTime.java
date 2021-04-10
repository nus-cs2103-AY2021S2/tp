package seedu.booking.model.booking;

import static java.util.Objects.requireNonNull;
import static seedu.booking.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents the start time in the booking system.
 */
public class StartTime {

    public static final String MESSAGE_CONSTRAINTS =
            "Start time should follow yyyy-MM-dd HH:mm.";

    public final LocalDateTime value;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");


    /**
     * Constructs an {@code StartTime}.
     *
     * @param startTime A valid start time.
     */
    public StartTime(LocalDateTime startTime) {
        requireNonNull(startTime);
        checkArgument(isValidTime(startTime), MESSAGE_CONSTRAINTS);
        value = startTime;
    }

    boolean isValidTime(LocalDateTime input) {
        try {
            formatter.parse(input.toString());
            return true;
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Start time should follow yyyy-MM-dd HH:mm.");
        }
    }

    /**
     * Returns start time
     */
    public LocalDateTime getStartTime() {
        return this.value;
    }

    @Override
    public String toString() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(value);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StartTime // instanceof handles nulls
                && value.equals(((StartTime) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
