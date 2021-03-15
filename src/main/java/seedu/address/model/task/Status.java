package seedu.address.model.task;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's status in the address book.
 * Guarantees: immutable; is always valid
 */
public class Status {

    public static final String MESSAGE_CONSTRAINTS = "Status should be either 'done' or 'not done'.";

    public final String value;

    public Status(String status) {
        requireNonNull(status);
        value = status;
    }

    public static boolean isValidStatus(String value) {
        return value.equals("done") || value.equals("not done");
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Status // instanceof handles nulls
                && value.equals(((Status) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}