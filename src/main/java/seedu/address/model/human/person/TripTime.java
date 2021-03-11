package seedu.address.model.human.person;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTripTime(String)}
 */
public class TripTime {

    public static final String MESSAGE_CONSTRAINTS = "TripTime should only numeric and formatted in 24h time. eg. 1400.";

    public final int value;

    /**
     * Constructs a {@code TripTime}.
     *
     * @param tripTime A valid set of tripDays.
     */
    public TripTime(String tripTime) {
        requireNonNull(tripTime);
        checkArgument(isValidTripTime(tripTime), MESSAGE_CONSTRAINTS);
        this.value = Integer.parseInt(tripTime);
    }

    /**
     * Returns true if a given string is a valid set of trip days.
     */
    public static boolean isValidTripTime(String tripTime) {
        int integerTripTime = Integer.parseInt(tripTime);

        if (integerTripTime < 0 || integerTripTime > 2359) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TripTime // instanceof handles nulls
                && value == ((TripTime) other).value); // state check
    }

    @Override
    public int hashCode() {
        return value;
    }

}
