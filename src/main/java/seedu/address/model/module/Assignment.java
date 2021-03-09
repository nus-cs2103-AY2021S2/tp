package seedu.address.model.module;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Assignment {
    // todo change message constraints
    public static final String MESSAGE_CONSTRAINTS = "Assignment deadline must be formatted DD/MM/YYYY TIME";

    /**
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    // todo change regex
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final Description description;
    public final LocalDateTime deadline;

    /**
     * Constructs an {@code Assignment}.
     *
     * @param description A valid assignment description.
     * @param deadline A valid date and time.
     */
    public Assignment(Description description, LocalDateTime deadline) {
        requireAllNonNull(description, deadline);
        this.description = description;
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return description + " (due: " + deadline.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm")) + ")";
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Assignment // instanceof handles nulls
                && description.equals(((Assignment) other).description)
                && deadline.equals(((Assignment) other).deadline)); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, deadline);
    }
}
