package seedu.address.model.human.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTripDay(String)}
 */
public class TripDay {

    public static final String MESSAGE_CONSTRAINTS = "TripDay should only be monday, tuesday, wednesday, thursday, "
            + "friday, saturday or sunday.";

    public static final String[] WEEKDAYS = new String[]{"MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY",
        "SATURDAY", "SUNDAY"};

    public final String value;

    /**
     * Constructs a {@code TripDay}.
     *
     * @param dayOfWeek A valid set of tripDays.
     */
    public TripDay(String dayOfWeek) {
        requireNonNull(dayOfWeek);
        checkArgument(isValidTripDay(dayOfWeek), MESSAGE_CONSTRAINTS);
        value = dayOfWeek.toUpperCase();
    }

    /**
     * Returns true if a given string is a valid set of trip days.
     */
    public static boolean isValidTripDay(String tripDay) {
        String upperCaseTripDay = tripDay.toUpperCase();
        for (String weekday : WEEKDAYS) {
            if (weekday.equals(upperCaseTripDay)) {
                return true;
            }
        }
        return false;
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
