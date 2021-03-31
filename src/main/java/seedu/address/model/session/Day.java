package seedu.address.model.session;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Session's day in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDay(String)}
 */
public class Day {
    public static final String MESSAGE_CONSTRAINTS = "Days can only be of one of the following values:\n"
            + "Monday, Tuesday, Wednesday, Thursday, Friday, Saturday and Sunday\n"
            + "(not case sensitive)";

    private enum DayValue {
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY,
        SUNDAY
    }

    private DayValue day;

    /**
     * Constructs an {@code Day}.
     *
     * @param day A valid day.
     */
    public Day(String day) {
        requireNonNull(day);
        checkArgument(isValidDay(day), MESSAGE_CONSTRAINTS);
        this.day = DayValue.valueOf(day.toUpperCase());
    }

    /**
     * Returns if a given string is a valid day.
     */
    public static boolean isValidDay(String test) {
        String upperCaseTest = test.toUpperCase();

        for (DayValue d : DayValue.values()) {
            if (d.name().equals(upperCaseTest)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String toString() {
        return this.day.name();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Day // instanceof handles nulls
                && day.equals(((Day) other).day)); // state check
    }
}
