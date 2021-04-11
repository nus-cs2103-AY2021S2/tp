package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Represents a Passenger's address in the address book.
 * Guarantees: immutable;
 */
public class TripTime {

    public static final String MESSAGE_CONSTRAINTS = "TripTime should only be numeric and formatted in 24h time. "
            + "eg. 1400.";

    public final LocalTime value;

    /**
     * Constructs a {@code TripTime}.
     *
     * @param tripTime A valid trip time.
     */
    public TripTime(LocalTime tripTime) {
        requireNonNull(tripTime);
        this.value = tripTime;
    }

    /**
     * Compares the time difference between 2 {@code TripTime} objects
     * @param otherTripTime the other {@code TripTime} to compare to.
     * @return long of 2 {@code TripTime} objects difference.
     */
    public long compareMinutes(TripTime otherTripTime) {
        requireNonNull(otherTripTime);
        long timeDifference = ChronoUnit.MINUTES.between(this.value, otherTripTime.value);

        return Math.abs(timeDifference);
    }

    @Override
    public String toString() {
        return value.format(DateTimeFormatter.ofPattern("HHmm"));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TripTime // instanceof handles nulls
                && value.equals(((TripTime) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
