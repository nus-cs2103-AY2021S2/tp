package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Task's deadlineTime in the task list.
 * Guarantees: is valid as declared in {@link #isValidDeadlineTime(String)}
 */
public class DeadlineTime implements Comparable<DeadlineTime> {

    public static final String MESSAGE_CONSTRAINTS =
            "Deadline time should: \n 1.HH:mm (24h format), and\n 2.it should be a logical time\n";

    public static final String VALIDATION_REGEX = "^([01][0-9]|2[0-3]):([0-5][0-9])$";
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    private LocalTime deadlineTime;

    /**
     * Constructs a {@code DeadlineTime}.
     *
     * @param deadlineTime A valid deadline date.
     */
    public DeadlineTime(String deadlineTime) {
        requireNonNull(deadlineTime);
        checkArgument(isValidDeadlineTime(deadlineTime), MESSAGE_CONSTRAINTS);
        this.deadlineTime = LocalTime.parse(deadlineTime, dateTimeFormatter);
    }

    /**
     * Returns true if a given String is a valid deadline date.
     */
    public static boolean isValidDeadlineTime(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return deadlineTime.format(dateTimeFormatter);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeadlineTime // instanceof handles nulls
                && deadlineTime.equals(((DeadlineTime) other).deadlineTime)); // state check
    }

    @Override
    public int hashCode() {
        return deadlineTime.hashCode();
    }

    @Override
    public int compareTo(DeadlineTime otherDeadline) {
        return this.deadlineTime.compareTo(otherDeadline.deadlineTime);
    }
}
