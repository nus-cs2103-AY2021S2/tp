package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a Task's Duration in the List.
 * Guarantees: immutable; is valid as declared in {@link #isValidDuration(String)}
 */
public class Duration {
    public static final String FIELD_NAME = "Duration";

    public static final String MESSAGE_CONSTRAINTS = "Duration should be numeric and should be in 24 hours format";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "([01]?[0-9]|2[0-3]):[0-5][0-9]-([01]?[0-9]|2[0-3]):[0-5][0-9]";

    public final String duration;
    public final String value;

    /**
     * Constructs an {@code Duration}.
     *
     * @param duration A valid duration.
     */
    public Duration(String duration) {
        requireNonNull(duration);
        checkArgument(isValidDuration(duration), MESSAGE_CONSTRAINTS);
        this.duration = parseDuration(duration);
        value = parseDuration(duration);
    }

    /**
     * Returns true if a given string is a valid duration.
     */
    public static boolean isValidDuration(String test) {
        Pattern p = Pattern.compile(VALIDATION_REGEX);
        Matcher m = p.matcher(test);
        return m.matches() || test.matches("");
    }

    /**
     * Returns a deadline in the form of a LocalDate.
     * @param duration the specified deadline.
     * @return
     */
    public static String parseDuration(String duration) {
        return duration;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Duration // instanceof handles nulls
                && this.duration.equals(((Duration) other).duration)); // state check
    }

    @Override
    public int hashCode() {
        return this.duration.hashCode();
    }

}
