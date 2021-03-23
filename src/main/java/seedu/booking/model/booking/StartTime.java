package seedu.booking.model.booking;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;

/**
 * Represents the start time in the booking system.
 */
public class StartTime {

    public final LocalDateTime value;

    /**
     * Constructs an {@code StartTime}.
     *
     * @param startTime A valid start time.
     */
    public StartTime(LocalDateTime startTime) {
        requireNonNull(startTime);
        value = startTime;
    }

    /**
     * Returns start time
     */
    public LocalDateTime getStartTime() {
        return this.value;
    }


    @Override
    public String toString() {
        return value.toString();
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
