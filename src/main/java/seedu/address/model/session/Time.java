package seedu.address.model.session;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Time {
    public static final String TIME_FORMAT = "HH:MM";
    public static final String MESSAGE_CONSTRAINTS = "Time must be in the format: " + TIME_FORMAT;

    private LocalTime time;

    public Time(String time) {
        requireNonNull(day);
        checkArgument(isValidTime(time), MESSAGE_CONSTRAINTS);
        this.time = LocalTime.parse(time, getFormatter());
    }

    public static boolean isValidTime(String test) {
        requireAllNonNull(s);

        try {
            LocalTime.parse(test, getFormatter());
            return true;
        } catch (DateTimeParseException dtpe) {
            return false;
        }
    }

    public static DateTimeFormatter getFormatter() {
        return DateTimeFormatter.ofPattern(TIME_FORMAT);
    }

    public boolean isSame(LocalTime other) {
        return this.time.equals(other);
    }

    public boolean isBefore(LocalTime other) {
        return this.time.isBefore(other);
    }

    public boolean isAfter(LocalTime other) {
        return this.time.isAfter(other);
    }

    public String toString() {
        return this.time.toString();
    }
}
