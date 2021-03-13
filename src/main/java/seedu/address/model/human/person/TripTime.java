package seedu.address.model.human.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTripTime(String)}
 */
public class TripTime {

    public static final String MESSAGE_CONSTRAINTS = "TripTime should only numeric and formatted in 24h time. "
            + "eg. 1400.";

    public static final String VALIDATION_REGEX = "\\d{4}";

    public final String value;

    /**
     * Constructs a {@code TripTime}.
     *
     * @param tripTime A valid trip time.
     */
    public TripTime(String tripTime) {
        requireNonNull(tripTime);
        checkArgument(isValidTripTime(tripTime), MESSAGE_CONSTRAINTS);
        this.value = tripTime;
    }

    /**
     * Returns true if a given string is a valid trip time.
     */
    public static boolean isValidTripTime(String tripTime) {

        if (!tripTime.matches(VALIDATION_REGEX)) {
            return false;
        }

        int integerTripTime = Integer.parseInt(tripTime);

        if (integerTripTime < 0 || integerTripTime > 2359) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TripTime // instanceof handles nulls
                && value == ((TripTime) other).value); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
