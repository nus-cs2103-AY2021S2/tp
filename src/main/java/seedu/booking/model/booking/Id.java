package seedu.booking.model.booking;

import static java.util.Objects.requireNonNull;

/**
 * Represents the id in the booking system.
 */
public class Id {

    public static final String MESSAGE_CONSTRAINTS =
            "Id should only contain numbers, and it should be 10 digits long";
    public final Integer value;

    /**
     * Constructs a {@code Id}.
     *
     * @param id A valid id.
     */
    public Id(Integer id) {
        requireNonNull(id);
        value = id;
    }


    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Id // instanceof handles nulls
                && value.equals(((Id) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
