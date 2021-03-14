package seedu.address.model.event;

import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Time {

    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    public static final String MESSAGE_CONSTRAINTS =
            "Time should be represented in 24-hour notation, in the format of HH:mm";
    public static final String VALIDATION_REGEX = "^(2[0-3]|[01]?[0-9]):([0-5]?[0-9])$";

    private LocalTime time;


    /**
     * Constructs an {@code Time}.
     *
     * @param time A valid time.
     */
    public Time(String time) {
        checkArgument(isValidTime(time), MESSAGE_CONSTRAINTS);
        if (time != null) {
            this.time = LocalTime.parse(time, TIME_FORMATTER);
        }
    }

    public LocalTime getTime() {
        return this.time;
    }

    /**
     * Returns true if a given string is a valid time.
     */
    public static boolean isValidTime(String test) {
        return test == null || test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns the time in a string.
     */
    public String toString() {
        return this.time != null ? this.time.format(TIME_FORMATTER) : "";
    }
}
