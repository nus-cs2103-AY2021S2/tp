package seedu.address.model.task;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's status in the address book.
 * Guarantees: immutable; is always valid
 */
public class Status {
    public final String value;

    public Status(String status) {
        requireNonNull(status);
        value = status;
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