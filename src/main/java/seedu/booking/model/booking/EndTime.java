package seedu.booking.model.booking;

import static java.util.Objects.requireNonNull;
import static seedu.booking.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents the end time in the booking system.
 */
public class EndTime {
    public static final String MESSAGE_CONSTRAINTS =
            "End time should follow yyyy-MM-dd HH:mm.";

    public final LocalDateTime value;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    /**
     * Constructs an {@code EndTime}.
     *
     * @param endTime A valid end time.
     */
    public EndTime(LocalDateTime endTime) {
        requireNonNull(endTime);
        checkArgument(isValidTime(endTime), MESSAGE_CONSTRAINTS);
        value = endTime;
    }


    boolean isValidTime(LocalDateTime input) {
        try {
            formatter.parse(input.toString());
            return true;
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("End time should follow yyyy-MM-dd HH:mm.");
        }
    }


    /**
     * Returns endtime
     */
    public LocalDateTime getEndTime() {
        return this.value;
    }


    @Override
    public String toString() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(value);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EndTime // instanceof handles nulls
                && value.equals(((EndTime) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
