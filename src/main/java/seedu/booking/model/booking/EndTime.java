package seedu.booking.model.booking;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;

/**
 * Represents the end time in the booking system.
 */
public class EndTime {


    public final LocalDateTime value;

    /**
     * Constructs an {@code EndTime}.
     *
     * @param endTime A valid end time.
     */
    public EndTime(LocalDateTime endTime) {
        requireNonNull(endTime);
        value = endTime;
    }


    /**
     * Returns endtime
     */
    public LocalDateTime getEndTime() {
        return this.value;
    }


    @Override
    public String toString() {
        return value.toString();
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
