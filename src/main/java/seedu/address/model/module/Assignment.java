package seedu.address.model.module;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.Objects;

public class Assignment {
    // todo change message constraints
    public static final String MESSAGE_CONSTRAINTS = "Addresses can take any values, and it should not be blank";

    /**
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    // todo change regex
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String assignment;
    public final LocalDateTime deadline;

    /**
     * Constructs an {@code Assignment}.
     *
     * @param assignment A valid assignment description.
     * @param deadline A valid date and time.
     */
    public Assignment(String assignment, LocalDateTime deadline) {
        requireAllNonNull(assignment, deadline);
        checkArgument(isValidAssignment(assignment), MESSAGE_CONSTRAINTS);
        this.assignment = assignment;
        this.deadline = deadline;
    }

    /**
     * Returns true if a given string is a valid assignment.
     */
    public static boolean isValidAssignment(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return assignment + deadline.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Assignment // instanceof handles nulls
                && assignment.equals(((Assignment) other).assignment)
                && deadline.equals(((Assignment) other).deadline)); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(assignment, deadline);
    }
}
