package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;


/**
 * Represents a Person's room assigned.
 * Guarantees: immutable; is valid as declared in {@link #isValidRoom(String)}
 */
public class Room {
    public static final String MESSAGE_CONSTRAINTS =
            "Room numbers should only contain numbers, and it should be at least 4 digits long";
    public static final String VALIDATION_REGEX = "\\d{4,}";
    public static final String UNALLOCATED_REGEX = "Room unallocated";
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
                || (other instanceof seedu.address.model.person.Room // instanceof handles nulls
                && value.equals(((seedu.address.model.person.Room) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
