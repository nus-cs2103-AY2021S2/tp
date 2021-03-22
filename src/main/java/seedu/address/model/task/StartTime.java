package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a Task's StartTime in the List.
 * Guarantees: immutable; is valid as declared in {@link #isValidStartTime(String)}
 */
public class StartTime {
    public static final String FIELD_NAME = "StartTime";

    public static final String MESSAGE_CONSTRAINTS = "Start Time should be numeric and should be in 24 hours format";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "([01]?[0-9]|2[0-3]):[0-5][0-9]";

    public final LocalTime startTime;
    public final LocalTime value;

    /**
     * Constructs an {@code StartTime}.
     *
     * @param startTime A valid start time.
     */
    public StartTime(String startTime) {
        requireNonNull(startTime);
        checkArgument(isValidStartTime(startTime), MESSAGE_CONSTRAINTS);
        this.startTime = parseStartTime(startTime);
        value = parseStartTime(startTime);
    }

    /**
     * Returns true if a given string is a valid start time.
     */
    public static boolean isValidStartTime(String test) {
        Pattern p = Pattern.compile(VALIDATION_REGEX);
        Matcher m = p.matcher(test);
        return m.matches();
    }

    /**
     * Returns a deadline in the form of a LocalDate.
     * @param startTime the specified deadline.
     * @return
     */
    public static LocalTime parseStartTime(String startTime) {
        LocalTime parsedStartTime = LocalTime.parse(startTime,
                DateTimeFormatter.ofPattern("HH:mm"));
        return parsedStartTime;
    }

    @Override
    public String toString() {
        return value.format(
                DateTimeFormatter.ofPattern("HH:mm"));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StartTime // instanceof handles nulls
                && this.startTime.equals(((StartTime) other).startTime)); // state check
    }

    @Override
    public int hashCode() {
        return this.startTime.hashCode();
    }

}
