package seedu.address.model.task.attributes;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a Task's Duration in the List.
 * Guarantees: immutable; is valid as declared in {@link #isValidDuration(String)}
 */
public class Duration implements Attribute {
    public static final String FIELD_NAME = "Duration";

    public static final String MESSAGE_CONSTRAINTS = "Duration should be numeric, consisting of start time and end time"
            + " and should be in 24 hours format in the following example format 10:00-14:00."
            + "Start time should also be before end time.";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "([01]?[0-9]|2[0-3]):[0-5][0-9]-([01]?[0-9]|2[0-3]):[0-5][0-9]";

    public final String duration;
    public final String value;

    private String startTime;
    private String endTime;

    /**
     * Constructs an {@code Duration}.
     *
     * @param duration A valid duration.
     */
    public Duration(String duration) {
        requireNonNull(duration);
        checkArgument(isValidDuration(duration), MESSAGE_CONSTRAINTS);
        this.duration = duration;
        value = duration;
    }

    /**
     * Returns true if a given string is a valid duration.
     */
    public static boolean isValidDuration(String test) {
        Pattern p = Pattern.compile(VALIDATION_REGEX);
        Matcher m = p.matcher(test);
        boolean valid = true;
        if (m.matches()) {
            String[] timings = test.split("-");
            try {
                Date getFirstTime = new SimpleDateFormat("HH:mm").parse(timings[0]);
                Date getSecondTime = new SimpleDateFormat("HH:mm").parse(timings[1]);
                if (getFirstTime.after(getSecondTime)) {
                    valid = false;
                }
            } catch (ParseException e) {
                throw new IllegalArgumentException(e);
            }

        }

        return m.matches() && valid || test.matches("");
    }

    /**
     * Parses the given duration into its start and end time.
     *
     * @param duration The specified duration.
     */
    private void parseDuration(String duration) {
        assert isValidDuration(duration) : "Cannot parse duration that is in an invalid format.";
        if (duration.isEmpty()) {
            return;
        }

        String[] times = duration.split("-");
        this.startTime = times[0];
        this.endTime = times[1];
    }

    /**
     * Checks if the value of Duration is Empty.
     *
     * @return true if value.isEmpty(), false otherwise.
     */
    public boolean isEmptyValue() {
        return value.isEmpty();
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
