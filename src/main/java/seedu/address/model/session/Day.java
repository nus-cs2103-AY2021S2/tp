package seedu.address.model.session;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

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

    public Day(String day) {
        requireNonNull(day);
        checkArgument(isValidDay(day), MESSAGE_CONSTRAINTS);
        this.day = DayValue.valueOf(day.toUpperCase());
    }

    public static boolean isValidDay(String day) {
        String upperCaseDay = day.toUpperCase();

        for (DayValue d : DayValue.values()) {
            if (d.name().equals(upperCaseDay)) {
                return true;
            }
        }
    
        return false;
    }

    @Override
    public String toString() {
        return this.day.name();
    }
}
