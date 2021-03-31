package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

public class Notes {

    public static final String MESSAGE_CONSTRAINTS = "Remarks can take any values, and it should not be blank";

    public final String value;

    /**
     * Constructs an {@code Notes}.
     *
     * @param notes a notes.
     */
    public Notes(String notes) {
        requireNonNull(notes);
        value = notes;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Notes // instanceof handles nulls
                && value.equals(((Notes) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
