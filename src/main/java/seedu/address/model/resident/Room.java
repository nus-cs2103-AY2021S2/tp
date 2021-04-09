package seedu.address.model.resident;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;


/**
 * Represents a Resident's room assigned.
 * Guarantees: immutable; is valid as declared in {@link #isValidRoom(String)}
 */
public class Room {
    public static final String MESSAGE_CONSTRAINTS =
            "Room should be of the format floor-unit "
            + "and adhere to the following constraints:\n"
            + "1. The floor should contain 2 numeric characters\n"
            + "2. This is followed by a '-' and then a unit. "
            + "The unit must: "
            + "contain 3 numeric characters\n";
    public static final String UNALLOCATED_REGEX = "Room unallocated";
    public static final String FLOOR_REGEX = "\\d{2}";
    public static final String UNIT_REGEX = "\\d{3}";
    public static final String VALIDATION_REGEX = FLOOR_REGEX + "-" + UNIT_REGEX;

    public final String value;

    /**
     * Constructs a {@code Room}.
     *
     * @param room A valid room number.
     */
    public Room(String room) {
        requireNonNull(room);
        checkArgument(isValidRoom(room), MESSAGE_CONSTRAINTS);
        value = room;
    }

    /**
     * Returns true if a given string is a valid room number.
     */
    public static boolean isValidRoom(String test) {
        return test.matches(VALIDATION_REGEX) || test.equals(UNALLOCATED_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.resident.Room // instanceof handles nulls
                && value.equals(((seedu.address.model.resident.Room) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
