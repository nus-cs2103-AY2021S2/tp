package seedu.heymatez.model.assignee;

import static java.util.Objects.requireNonNull;
import static seedu.heymatez.commons.util.AppUtil.checkArgument;

/**
 * Represents an Assignee in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidAssigneeName(String)}
 */
public class Assignee {
    public static final String MESSAGE_SEARCH_TASKS_CONSTRAINTS = "Please check that the name you have typed in is a "
            + "proper name";

    public static final String MESSAGE_CONSTRAINTS = "Assignee specified must be a member in the "
            + "displayed member's list, with the exact name (case-sensitive and format-sensitive) and name should not "
            + "have extra spaces or characters between words.";

    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String assigneeName;

    /**
     * Constructs an {@code Assignee}.
     *
     * @param assigneeName A valid assignee name.
     */
    public Assignee(String assigneeName) {
        requireNonNull(assigneeName);
        checkArgument(isValidAssigneeName(assigneeName), MESSAGE_CONSTRAINTS);
        this.assigneeName = assigneeName;
    }

    /**
     * Returns true if a given string is a valid assignee name.
     */
    public static boolean isValidAssigneeName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Assignee // instanceof handles nulls
                && assigneeName.equals(((Assignee) other).assigneeName)); // state check
    }

    @Override
    public int hashCode() {
        return assigneeName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + assigneeName + ']';
    }
}
