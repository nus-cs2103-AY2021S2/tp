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
public class TripDays {

    public static final String MESSAGE_CONSTRAINTS = "TripDays should only be monday, tuesday, wednesday, thursday, "
            + "friday, saturday or sunday. Multiple days should be separated by spaces";

    public final List<DayOfWeek> value = new ArrayList<>();

    /**
     * Constructs a {@code TripDay}.
     *
     * @param dayOfWeek A valid set of tripDays.
     */
    public TripDays(String dayOfWeek) {
        requireNonNull(dayOfWeek);
        checkArgument(areValidTripDays(dayOfWeek), MESSAGE_CONSTRAINTS);
        value.add(DayOfWeek.valueOf(dayOfWeek));
    }

    /**
     * Returns true if a given string is a valid set of trip days.
     */
    public static boolean areValidTripDays(String tripDays) {
        String[] tripDaysArray = tripDays.split(" ");
        String upperCaseTripDay;

        for (String inputDay : tripDaysArray) {
            upperCaseTripDay = inputDay.toUpperCase();
            for (DayOfWeek day : DayOfWeek.values()) {
                if (!day.toString().equals(upperCaseTripDay)) {
                    return false;
                }
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
                || (other instanceof TripDays // instanceof handles nulls
                && value.equals(((TripDays) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
