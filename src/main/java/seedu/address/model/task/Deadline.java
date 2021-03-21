package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a Task's deadline number in the planner.
 * Guarantees: immutable; is valid as declared in {@link #isValidDeadline(String)}
 */
public class Deadline {


    public static final String MESSAGE_CONSTRAINTS =
            "Deadline numbers should only contain numbers, and should not be blank";
    public static final String MESSAGE_CONSTRAINTS_INVALID_DATE =
            "Deadline should not be before today";

    public static final String VALIDATION_REGEX = "^(1[0-2]|0[1-9])/(3[01]|[12][0-9]|0[1-9])/[0-9]{4}$";

    public final String value;

    /**
     * Constructs a {@code Deadline}.
     *
     * @param deadline A valid deadline number.
     */
    public Deadline(String deadline) {
        requireNonNull(deadline);
        checkArgument(isValidDeadline(deadline), MESSAGE_CONSTRAINTS);
        value = deadline;
    }

    /**
     * Returns true if a given string is a valid deadline number.
     */
    public static boolean isValidDeadline(String test) {
        Pattern p = Pattern.compile(VALIDATION_REGEX);
        Matcher m = p.matcher(test);
        return m.matches();
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Deadline // instanceof handles nulls
                && value.equals(((Deadline) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
