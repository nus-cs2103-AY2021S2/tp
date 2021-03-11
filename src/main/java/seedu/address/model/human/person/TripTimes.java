package seedu.address.model.human.person;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #areValidTripDays(String)}
 */
public class TripTimes {

    public static final String MESSAGE_CONSTRAINTS = "TripTimes should only numeric and formatted in 24h time. eg. 1400."
            + " Multiple days should be separated by spaces";

    public final List<Integer> value = new ArrayList<>();

    /**
     * Constructs a {@code TripTimes}.
     *
     * @param tripTime A valid set of tripDays.
     */
    public TripTimes(String tripTime) {
        requireNonNull(tripTime);
        checkArgument(areValidTripDays(tripTime), MESSAGE_CONSTRAINTS);
        value.add(Integer.valueOf(tripTime));
    }

    /**
     * Returns true if a given string is a valid set of trip days.
     */
    public static boolean areValidTripDays(String tripTimes) {
        String[] tripDaysArray = tripTimes.split(" ");
        int integerTripTime;

        for (String inputTime : tripDaysArray) {
            integerTripTime = Integer.parseInt(inputTime);
            if (integerTripTime < 0 || integerTripTime > 2359) {
                return false;
            }
        }

        return true;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TripTimes // instanceof handles nulls
                && value.equals(((TripTimes) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
