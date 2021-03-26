package seedu.address.model.pool;

import static java.util.Objects.requireNonNull;

import java.time.DayOfWeek;

/**
 * Represents a Passenger's address in the address book.
 * Guarantees: immutable;
 */
public class TripDay {

    public static final String MESSAGE_CONSTRAINTS = "TripDay should only be monday, tuesday, wednesday, thursday, "
            + "friday, saturday or sunday.";


    public final DayOfWeek value;

    /**
     * Constructs a {@code TripDay}.
     *
     * @param dayOfWeek A valid set of tripDays.
     */
    public TripDay(DayOfWeek dayOfWeek) {
        requireNonNull(dayOfWeek);
        value = dayOfWeek;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TripDay // instanceof handles nulls
                && value.equals(((TripDay) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
