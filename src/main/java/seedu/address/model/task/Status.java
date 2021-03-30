package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's status in the address book.
 * Guarantees: immutable; is always valid
 */
public class Status {
    public static final String FIELD_NAME = "Status";

    public static final String MESSAGE_CONSTRAINTS = "Status should be either 'done' or 'not done'.";

    public final String value;

    /**
     * Constructs a Status object.
     *
     * @param status A valid status.
     */
    public Status(String status) {
        requireNonNull(status);
        checkArgument(isValidStatus(status), MESSAGE_CONSTRAINTS);

        value = status;
    }

    /**
     * Checks if a status value is valid.
     * Only 'done' and 'not done' are valid.
     *
     * @param value valid String value for status
     * @return boolean value indicating if value is a valid status value.
     */
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

    public boolean isDone() {
        return value.equals("done");
    }
}
