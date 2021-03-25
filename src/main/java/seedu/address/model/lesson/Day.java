package seedu.address.model.lesson;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Locale;

public class Day implements Comparable<Day> {

    public static final String VALIDATION_REGEX = "^(monday|tuesday|wednesday|thursday|friday|saturday|sunday)";
    public static final String MESSAGE_CONSTRAINTS = "Days should take on one of the following: Monday, "
            + "Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday";
    public final String dayOfTuition;

    /**
     * Constructs a {@code Day}.
     *
     * @param dayOfTuition A valid day.
     */
    public Day(String dayOfTuition) {
        requireNonNull(dayOfTuition);
        checkArgument(isValidDay(dayOfTuition), MESSAGE_CONSTRAINTS);
        this.dayOfTuition = dayOfTuition;
    }

    /**
     * Returns true if a given string is a valid day.
     */
    public static boolean isValidDay(String test) {
        return test.toLowerCase(Locale.ROOT).matches(VALIDATION_REGEX);
    }

    int dayToInt(String day) {
        int value = 0;

        switch (day.toLowerCase()) {

        case "monday":
            value = 1;
            break;

        case "tuesday":
            value = 2;
            break;

        case "wednesday":
            value = 3;
            break;

        case "thursday":
            value = 4;
            break;

        case "friday":
            value = 5;
            break;

        case "saturday":
            value = 6;
            break;

        case "sunday":
            value = 7;
            break;


        default:
            break;
        }

        return value;
    }

    @Override
    public int compareTo(Day other) {
        int thisValue = dayToInt(dayOfTuition);
        int otherValue = dayToInt(other.dayOfTuition);
        if (thisValue < otherValue) {
            return -1;
        } else if (thisValue > otherValue) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return dayOfTuition;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Day // instanceof handles nulls
                && dayOfTuition.equals(((Day) other).dayOfTuition)); // state check
    }

    @Override
    public int hashCode() {
        return dayOfTuition.hashCode();
    }

}
