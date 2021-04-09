package seedu.address.model.issue;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an issue's room number in SunRez. Guarantees: immutable; is valid
 * as declared in {@link #isValidRoomNumber(String)}.
 */
public class RoomNumber {

    public static final String MESSAGE_CONSTRAINTS = seedu.address.model.room.RoomNumber.MESSAGE_CONSTRAINTS;

    public static final String VALIDATION_REGEX = seedu.address.model.room.RoomNumber.VALIDATION_REGEX;

    public final String value;

    /**
     * Constructs a {@code RoomNumber}.
     *
     * @param roomNumber A valid room number.
     */
    public RoomNumber(String roomNumber) {
        requireNonNull(roomNumber);
        checkArgument(isValidRoomNumber(roomNumber), MESSAGE_CONSTRAINTS);
        this.value = roomNumber;
    }

    /**
     * Returns true if a given string is a valid room number.
     *
     * @param test String to check.
     * @return True if test is valid.
     */
    public static boolean isValidRoomNumber(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RoomNumber // instanceof handles nulls
                        && value.equals(((RoomNumber) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
